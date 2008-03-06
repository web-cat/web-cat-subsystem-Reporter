package net.sf.webcat.reporter.actions;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.util.Enumeration;

import ognl.Node;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import ognl.enhance.ExpressionAccessor;

import net.sf.webcat.core.Application;
import net.sf.webcat.oda.WebCATDataException;
import net.sf.webcat.reporter.QualifierUtils;
import net.sf.webcat.reporter.queryassistants.AdvancedQueryComparison;
import net.sf.webcat.reporter.queryassistants.AdvancedQueryCriterion;
import net.sf.webcat.reporter.queryassistants.AdvancedQueryModel;
import net.sf.webcat.reporter.queryassistants.AdvancedQueryUtils;
import net.sf.webcat.reporter.queryassistants.KeyPathParser;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.webobjects.appserver.WOResponse;
import com.webobjects.appserver.WOSession;
import com.webobjects.eoaccess.EOEntity;
import com.webobjects.eoaccess.EORelationship;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOFetchSpecification;
import com.webobjects.eocontrol.EOGenericRecord;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSData;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSKeyValueCoding;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.ERXDirectAction;
import er.extensions.ERXFetchSpecificationBatchIterator;

public class designerPreview extends ERXDirectAction
{
	private static final int BATCH_SIZE = 50;

	private static final String PARAM_ENTITY_TYPE = "entityType";
	
	private static final String PARAM_EXPRESSIONS = "expressions";
	
	private static final String PARAM_QUERY = "query";
	
	private static final String PARAM_TIMEOUT = "timeout";

	private static final String SESSION_ITERATOR =
		"net.sf.webcat.reporter.actions.designerPreview.iterator";

	private static final String SESSION_EXPRESSIONS =
		"net.sf.webcat.reporter.actions.designerPreview.expressions";
	
	private static final String SESSION_EXPRESSION_STRINGS =
		"net.sf.webcat.reporter.actions.designerPreview.expressionStrings";

	private static final String SESSION_OGNLCONTEXT =
		"net.sf.webcat.reporter.actions.designerPreview.ognlContext";
	
	private static final String SESSION_SLOW_QUALIFIER =
		"net.sf.webcat.reporter.actions.designerPreview.slowQualifier";
	
	private static final String SESSION_TIMEOUT =
		"net.sf.webcat.reporter.actions.designerPreview.timeout";

	private static final String SESSION_START_TIME =
		"net.sf.webcat.reporter.actions.designerPreview.startTime";

	private static final String SESSION_CANCELED =
		"net.sf.webcat.reporter.actions.designerPreview.canceled";

	public designerPreview(WORequest request)
	{
		super(request);
	}

	public WOActionResults startRetrievalAction()
	{
		WOResponse response = new WOResponse();
		WOSession session = session();
		
		String entityType =
			request().formValueForKey(PARAM_ENTITY_TYPE).toString();
		String expressionString =
			request().formValueForKey(PARAM_EXPRESSIONS).toString();
		String[] expressions = expressionString.split("%===%");

		int timeout = Integer.parseInt(
				request().formValueForKey(PARAM_TIMEOUT).toString());

		EOEditingContext context = Application.newPeerEditingContext();

		EOQualifier fastQualifier = null;
		EOQualifier slowQualifier = null;

		if(request().formValueForKey(PARAM_QUERY) != null)
		{
			String query = request().formValueForKey(PARAM_QUERY).toString();
			
			if(query.length() > 0)
			{
				EOQualifier fullQualifier = translateQueryToQualifier(
						entityType, query, context);
				
				EOQualifier[] quals = QualifierUtils.partitionQualifier(
						fullQualifier, entityType);
				fastQualifier = quals[0];
				slowQualifier = quals[1];
			}
		}

		try
		{
			OgnlContext ognlContext = new OgnlContext();
	
			EOEntity rootEntity = EOUtilities.entityNamed(context, entityType);
	
			EOFetchSpecification spec = new EOFetchSpecification(
					entityType, fastQualifier, null);
	
			ExpressionAccessor[] compiled = compileAndPrefetchExpressions(
						ognlContext, expressions, rootEntity, spec);
	
			ERXFetchSpecificationBatchIterator iterator =
				new ERXFetchSpecificationBatchIterator(spec, context);
			iterator.setBatchSize(50);
	
			session.setObjectForKey(ognlContext, SESSION_OGNLCONTEXT);
			session.setObjectForKey(iterator, SESSION_ITERATOR);
			session.setObjectForKey(compiled, SESSION_EXPRESSIONS);
			session.setObjectForKey(expressions, SESSION_EXPRESSION_STRINGS);
			session.setObjectForKey(timeout, SESSION_TIMEOUT);
			session.setObjectForKey(Boolean.FALSE, SESSION_CANCELED);
			
			if(slowQualifier == null)
				session.removeObjectForKey(SESSION_SLOW_QUALIFIER);
			else
				session.setObjectForKey(slowQualifier, SESSION_SLOW_QUALIFIER);

			session.setObjectForKey(System.currentTimeMillis(),
					SESSION_START_TIME);

			// A successful response to this action contains the session ID to
			// use to continue batching from this request.

			response.appendContentString(session.sessionID());
			response.appendContentCharacter('\n');
		}
		catch(Exception e)
		{
			// Send any exception back as the response so that the report
			// designer can report the error to the user.
			response = errorResponse(e);
		}

		return response;
	}


	public WOActionResults retrieveNextBatchAction()
	{
		WOResponse response = new WOResponse();

		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);

			ERXFetchSpecificationBatchIterator iterator =
				(ERXFetchSpecificationBatchIterator)session().objectForKey(
						SESSION_ITERATOR);

			long startTime = (Long)session().objectForKey(SESSION_START_TIME);
			int timeout =
				(Integer)session().objectForKey(SESSION_TIMEOUT);
			long endTime = startTime + (timeout * 1000);

			int count = 0;
			boolean isTimedOut = (System.currentTimeMillis() > endTime);

			while(count < BATCH_SIZE && iterator.hasNextBatch() &&
					!isCanceledInSession() && !isTimedOut)
			{
				int recordsRetrieved = serializeNextBatch(iterator, oos,
						endTime);
				count += recordsRetrieved;
				
				isTimedOut = (System.currentTimeMillis() > endTime);
			}
			
			// Write the end of data marker.
			oos.writeBoolean(false);

			oos.close();
			baos.close();
			
			NSData data = new NSData(baos.toByteArray());
			response.appendContentData(data);
		}
		catch(Exception e)
		{
			response = errorResponse(e);
		}

		return response;
	}

	public WOActionResults cancelRetrievalAction()
	{
		setCanceledInSession();
		return new WOResponse();
	}

	private static WOResponse errorResponse(Exception e)
	{
		WOResponse response = new WOResponse();

		response.appendContentString("!!! ERROR\n");
		response.appendContentString(e.toString());
		
		return response;
	}

	private boolean isCanceledInSession()
	{
		synchronized(designerPreview.class)
		{
			return (Boolean)session().objectForKey(SESSION_CANCELED);
		}
	}

	private void setCanceledInSession()
	{
		System.out.println("setting canceled");
		synchronized(designerPreview.class)
		{
			System.out.println("inside sync block");
			session().setObjectForKey(Boolean.TRUE, SESSION_CANCELED);
		}
	}

	private int serializeNextBatch(
			ERXFetchSpecificationBatchIterator iterator, ObjectOutputStream oos,
			long endTime)
	throws IOException
	{
		WOSession session = session();

		int recordsRetrieved = 0;

		if(iterator.hasNextBatch())
		{
			OgnlContext ognlContext =
				(OgnlContext)session.objectForKey(SESSION_OGNLCONTEXT);
			
			ExpressionAccessor[] expressions =
				(ExpressionAccessor[])session.objectForKey(SESSION_EXPRESSIONS);

			String[] expressionStrings =
				(String[])session.objectForKey(SESSION_EXPRESSION_STRINGS);

			EOQualifier slowQualifier =
				(EOQualifier)session.objectForKey(SESSION_SLOW_QUALIFIER);

			NSArray batch = EOQualifier.filteredArrayWithQualifier(
					iterator.nextBatch(), slowQualifier);

			boolean isTimedOut = (System.currentTimeMillis() > endTime);

			Enumeration<?> e = batch.objectEnumerator();
			while(e.hasMoreElements() && !isTimedOut)
			{
				// Write the next-object-exists marker.
				oos.writeBoolean(true);

				EOGenericRecord eo = (EOGenericRecord)e.nextElement();

				for(int i = 0; i < expressions.length; i++)
				{
					Object value = getValueOfExpression(expressions[i],
							ognlContext, eo, expressionStrings[i]);

					if(value instanceof NSTimestamp)
					{
						// We don't want to send an NSTimestamp back to the
						// report designer because the report designer
						// doesn't have access to WebObjects classes. So,
						// we create a java.sql.Timestamp from its value
						// instead.

						NSTimestamp timestamp = (NSTimestamp)value;
						long time = timestamp.getTime();
						java.sql.Timestamp sqlTime =
							new java.sql.Timestamp(time);
						value = sqlTime;
					}

					oos.writeObject(value);
				}
				
				recordsRetrieved++;

				isTimedOut = (System.currentTimeMillis() > endTime);				
			}
		}

		// Recycle the editing context after we've processed this batch to
		// flush out all of the current objects.
		if(iterator != null)
		{
			Application.releasePeerEditingContext(iterator.editingContext());
			iterator.setEditingContext(Application.newPeerEditingContext());
		}
		
		return recordsRetrieved;
	}

	private ExpressionAccessor[] compileAndPrefetchExpressions(
			OgnlContext ognlContext, String[] expressions,
			EOEntity rootEntity, EOFetchSpecification spec)
	{
		ExpressionAccessor[] compiled =
			new ExpressionAccessor[expressions.length];
		NSMutableArray prefetchedRelationships = new NSMutableArray();

		ognlContext = new OgnlContext();

		int i = 0;
		for(String expression : expressions)
		{
			// We only bother prefetching relationships out of the expression
			// if some arbitrary prefix of the expression is a keypath and not
			// a richer OGNL expression. This allows us to still prefetch
			// relationships that are used in certain types of OGNL expressions
			// like selection or projection (for example,
			// "object.relationship.{? #this instanceof SomeClass }", but
			// without the challenge of trying to parse keypaths out of the
			// entire expression.

			EOEntity entity = rootEntity;

			String[] parts = expression.split("\\.");
			String partsSoFar = "";
			for(String part : parts)
			{
				partsSoFar += part;

				EORelationship relationship = entity.relationshipNamed(part);
				if(relationship != null)
				{
					entity = relationship.destinationEntity();
					prefetchedRelationships.addObject(partsSoFar);
				}
				else
				{
					break;
				}
				
				partsSoFar += ".";
			}

			// Compile the OGNL expressions for more efficient accesses during
			// the preview batch operations. If an error occurs, pass it back
			// up to the direct action so that a proper response can be sent
			// back to the report designer.

			Node node;

			try
			{
				node = Ognl.compileExpression(ognlContext, null, expression);
				compiled[i] = node.getAccessor();
			}
			catch(Exception e)
			{
				throw new IllegalArgumentException(e);
			}
			
			i++;
		}

		spec.setPrefetchingRelationshipKeyPaths(prefetchedRelationships);

		return compiled;
	}

	private Object getValueOfExpression(ExpressionAccessor accessor,
			OgnlContext ognlContext, EOGenericRecord object,
			String expressionString)
	{
		Object result = null;

		try
		{
			result = accessor.get(ognlContext, object);
		}
		catch(NSKeyValueCoding.UnknownKeyException e)
		{
			// Translate the expression into something a little easier for the
			// user to read.
			String msg = String.format(
					"In the expression (%s), the key \"%s\" is not recognized " +
					"by the source object (which is of type \"%s\")",
					expressionString, e.key(), e.object().getClass().getName());
			
			throw new IllegalArgumentException(msg);
		}
		catch(Exception e)
		{
			if(e instanceof OgnlException)
			{
				return null;
			}
			else
			{
				throw new IllegalArgumentException(e);
			}
		}
		
		return result;
	}

	private EOQualifier translateQueryToQualifier(String entityType,
			String query, EOEditingContext ec)
	{
		AdvancedQueryModel model = new AdvancedQueryModel();

		try
		{
			BufferedReader reader = new BufferedReader(new StringReader(query));
			String line;
			
			NSMutableArray<AdvancedQueryCriterion> criteria =
				new NSMutableArray<AdvancedQueryCriterion>();

			while((line = reader.readLine()) != null)
			{
				String keypath = line;
				String comparisonString = reader.readLine();
				String comparandTypeString = reader.readLine();
				String valueRepresentation = reader.readLine();
				reader.readLine();
				
				AdvancedQueryCriterion criterion = new AdvancedQueryCriterion();
				
				AdvancedQueryComparison comparison =
					AdvancedQueryComparison.comparisonWithName(
							comparisonString);
 
				criterion.setKeyPath(keypath);
				criterion.setComparison(comparison);
				criterion.setComparandType(
						Integer.parseInt(comparandTypeString));

				int type = AdvancedQueryUtils.typeOfKeyPath(
						entityType, keypath);
				Object value = null;
				
				if(comparison == AdvancedQueryComparison.IS_BETWEEN ||
						comparison == AdvancedQueryComparison.IS_NOT_BETWEEN)
				{
					value = AdvancedQueryUtils.
						valueRangeForPreviewRepresentation(
							type, valueRepresentation, ec);
				}
				else if(comparison.doesSupportMultipleValues())
				{
					value = AdvancedQueryUtils.
						multipleValuesForPreviewRepresentation(
							type, valueRepresentation, ec);
				}
				else
				{
					value = AdvancedQueryUtils.
						singleValueForPreviewRepresentation(
							type, valueRepresentation, ec);
				}

				criterion.setValue(value);
				
				criteria.addObject(criterion);
			}

			model.setCriteria(criteria);
			return model.qualifierFromValues();
		}
		catch(IOException e)
		{
			return null;
		}
	}
}
