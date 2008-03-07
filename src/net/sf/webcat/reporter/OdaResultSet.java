package net.sf.webcat.reporter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.Map;

import ognl.Node;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import ognl.enhance.ExpressionAccessor;
import ognl.webobjects.WOOgnl;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOFetchSpecification;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSKeyValueCoding;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.ERXFetchSpecificationBatchIterator;

import net.sf.webcat.core.Application;
import net.sf.webcat.oda.IWebCATResultSet;
import net.sf.webcat.oda.WebCATDataException;

public class OdaResultSet implements IWebCATResultSet
{
	private String jobUuid;

	private ReportQuery query;
	
	private EOEditingContext editingContext;

	private EOQualifier fetchQualifier;
	
	private EOQualifier inMemoryQualifier;

	private ERXFetchSpecificationBatchIterator iterator;
	
	private int currentRow;
	
	private int rawCurrentRow;

	private String[] expressions;

	private ExpressionAccessor[] accessors;
	
	private OgnlContext defaultContext;
	
	private NSArray<Object> currentBatch;
	
	private Enumeration<Object> currentBatchEnum;

	private Object currentObject;
	
	private boolean wasNull;
	
	private long lastThrottleCheck;

	private static final long MILLIS_BETWEEN_THROTTLE_CHECK = 3000;
	
	private static final long MILLIS_TO_THROTTLE = 5000;

	private static final int PROGRESS_STEP_SIZE = 10;

	public OdaResultSet(String jobUuid, ReportQuery query)
	{
		this.jobUuid = jobUuid;
		this.query = query;
		currentRow = 0;
		rawCurrentRow = 0;
		
		lastThrottleCheck = 0;
	}


	private void recycleEditingContext()
	{
		if(editingContext != null)
			Application.releasePeerEditingContext(editingContext);
		
		editingContext = Application.newPeerEditingContext();

		if(iterator != null)
			iterator.setEditingContext(editingContext);
	}


	public void close() throws WebCATDataException
	{
		Application.releasePeerEditingContext(editingContext);
		
		ProgressManager.getInstance().completeCurrentTaskForJob(jobUuid);
	}

	public int currentRow() throws WebCATDataException
	{
		return currentRow;
	}

	public void execute() throws WebCATDataException
	{
		recycleEditingContext();

		EOQualifier qualifier = QualifierUtils.qualifierWithEOsForGIDs(
				query.qualifier(), editingContext);

		EOQualifier[] quals = QualifierUtils.partitionQualifier(qualifier,
				query.wcEntityName());
		fetchQualifier = quals[0];
		inMemoryQualifier = quals[1];

		EOFetchSpecification fetch = new EOFetchSpecification(
				query.wcEntityName(), fetchQualifier, null);
		iterator = new ERXFetchSpecificationBatchIterator(fetch, editingContext);

		ProgressManager.getInstance().beginTaskForJob(
 				jobUuid, iterator.count());
	}

	public void prepare(String entityType, String[] expressions)
	throws WebCATDataException
	{
		this.expressions = expressions;

		defaultContext = new OgnlContext();

		accessors = new ExpressionAccessor[expressions.length];

		int i = 0;
		for(String expression : expressions)
		{
			Node node;

			try
			{
				node = Ognl.compileExpression(defaultContext, null, expression);
				accessors[i] = node.getAccessor();
			}
			catch(Exception e)
			{
				throw new WebCATDataException(e);
			}
			
			i++;
		}
	}

	private boolean getNextBatch()
	{
		if(iterator.hasNextBatch())
		{
			boolean getBatch = true;
			
			while(getBatch)
			{
				recycleEditingContext();
				currentBatch = iterator.nextBatch();
				
				if(inMemoryQualifier != null)
					currentBatch = EOQualifier.filteredArrayWithQualifier(
							currentBatch, inMemoryQualifier);

				if(currentBatch.isEmpty())
				{
					getBatch = iterator.hasNextBatch();
				}
				else
				{
					currentBatchEnum = currentBatch.objectEnumerator();
					return true;
				}
			}
		}

		return false;
	}

	public boolean moveToNextRow() throws WebCATDataException
	{
		throttleIfNecessary();

		boolean hasNext = true;

		rawCurrentRow++;
		if(rawCurrentRow % PROGRESS_STEP_SIZE == 0)
			ProgressManager.getInstance().stepJob(jobUuid, PROGRESS_STEP_SIZE);

		if(currentBatchEnum == null || !currentBatchEnum.hasMoreElements())
		{
			hasNext = getNextBatch();
		}

		if(hasNext)
		{
			currentObject = currentBatchEnum.nextElement();
			currentRow++;
		}

		return hasNext;
	}


	private void throttleIfNecessary()
	{
		long currentTime = System.currentTimeMillis();
//		System.out.println("currentTime = " + currentTime);
//		System.out.println("lastThrottleCheck = " + lastThrottleCheck);
		if(currentTime - lastThrottleCheck > MILLIS_BETWEEN_THROTTLE_CHECK)
		{
			while(Reporter.getInstance().refreshThrottleStatus())
			{
				try
				{
					Thread.sleep(MILLIS_TO_THROTTLE);
				}
				catch(InterruptedException e) { }
			}

			lastThrottleCheck = System.currentTimeMillis();
		}
	}


	public int rowCount() throws WebCATDataException
	{
		// This should be deprecated. It's not always possible to determine
		// the number of rows before the result set is completely traversed,
		// and the ODA classes don't even call this.
		return 0;
	}

	private <T> T evaluate(int column, Class<T> destType)
	throws WebCATDataException
	{
		ExpressionAccessor accessor = accessors[column];
		Object result = null;
		
		try
		{
			result = accessor.get(defaultContext, currentObject);
		}
		catch(NSKeyValueCoding.UnknownKeyException e)
		{
			// Translate the expression into something a little easier for the
			// user to read.
			String msg = String.format(
					"In the expression (%s), the key \"%s\" is not recognized " +
					"by the source object (which is of type \"%s\")",
					expressions[column], e.key(),
					e.object().getClass().getName());
			
			throw new WebCATDataException(new IllegalArgumentException(msg));
		}
		catch(Exception e)
		{
			if(e instanceof OgnlException)
			{
				wasNull = true;
				return null;
			}

			throw new WebCATDataException(e);
		}
		
		if(result == null)
		{
			wasNull = true;
			return null;
		}
		else
		{
			wasNull = false;

			if(destType.isInstance(result))
				return (T)result;
			else
				return (T)defaultContext.getTypeConverter().convertValue(
						null, null, null, null, result, destType);
		}
	}

	public boolean booleanValueAtIndex(int column) throws WebCATDataException
	{
		Boolean value = evaluate(column, Boolean.class);
		return (value == null)? false : value;
	}

	public BigDecimal decimalValueAtIndex(int column) throws WebCATDataException
	{
		return evaluate(column, BigDecimal.class);
	}

	public double doubleValueAtIndex(int column) throws WebCATDataException
	{
		Double value = evaluate(column, Double.class);
		if(value == null)
			return 0.0;
		else
			return value;
	}

	public int intValueAtIndex(int column) throws WebCATDataException
	{
		Integer value = evaluate(column, Integer.class);
		return (value == null)? 0 : value;
	}

	public String stringValueAtIndex(int column) throws WebCATDataException
	{
		return evaluate(column, String.class);
	}

	public Timestamp timestampValueAtIndex(int column)
	throws WebCATDataException
	{
		ExpressionAccessor accessor = accessors[column];
		Object result = accessor.get(defaultContext, currentObject);
		
		if(result instanceof NSTimestamp)
		{
			return (NSTimestamp)result;
		}
		else
		{
			// TODO Probably do some more conversions here.
			return null;
		}
	}

	public boolean wasValueNull() throws WebCATDataException
	{
		return wasNull;
	}

}