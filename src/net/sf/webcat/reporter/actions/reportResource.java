package net.sf.webcat.reporter.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSData;

import net.sf.webcat.core.DirectAction;
import net.sf.webcat.reporter.GeneratedReport;

public class reportResource extends DirectAction
{
	public reportResource(WORequest request)
	{
		super(request);
	}

	public WOActionResults imageAction()
	{
		WOResponse response = new WOResponse();
		
		String uuid = request().stringFormValueForKey("uuid");
		String image = request().stringFormValueForKey("image");
		
		File file = new File(GeneratedReport.renderedResourcePath(uuid, image));
		
		try
		{
			NSData data = new NSData(new FileInputStream(file),
					(int)file.length());
			
			response.appendContentData(data);
		}
		catch (IOException e)
		{
			log.error(e);
		}

		return response;
	}
	
	private static Logger log = Logger.getLogger(reportResource.class);
}
