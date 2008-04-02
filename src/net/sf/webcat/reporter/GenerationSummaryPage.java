/*==========================================================================*\
 |  $Id$
 |*-------------------------------------------------------------------------*|
 |  Copyright (C) 2006-2008 Virginia Tech
 |
 |  This file is part of Web-CAT.
 |
 |  Web-CAT is free software; you can redistribute it and/or modify
 |  it under the terms of the GNU Affero General Public License as published
 |  by the Free Software Foundation; either version 3 of the License, or
 |  (at your option) any later version.
 |
 |  Web-CAT is distributed in the hope that it will be useful,
 |  but WITHOUT ANY WARRANTY; without even the implied warranty of
 |  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 |  GNU General Public License for more details.
 |
 |  You should have received a copy of the GNU Affero General Public License
 |  along with Web-CAT; if not, see <http://www.gnu.org/licenses/>.
\*==========================================================================*/

package net.sf.webcat.reporter;

import com.webobjects.appserver.*;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSTimestampFormatter;

//-------------------------------------------------------------------------
/**
 * This page summarizes the report that will be generated.
 *
 * @author  Anthony Allevato
 * @version $Id$
 */
public class GenerationSummaryPage
    extends ReporterComponent
{
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Create a new page.
     * @param context The page's context
     */
    public GenerationSummaryPage(WOContext context)
    {
        super(context);
    }


    //~ KVC Attributes (must be public) .......................................

	public ReportTemplate         reportTemplate;
	public NSArray<ReportDataSet> dataSets;
	public ReportDataSet          dataSet;
	public int                    index;
	public String                 reportDescription;
	public IRenderingMethod       renderingMethod;
	public IRenderingMethod       selectedRenderingMethod;


    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    public void appendToResponse(WOResponse response, WOContext context)
    {
    	reportTemplate = localReportTemplate();
    	dataSets = reportTemplate.dataSets();

    	super.appendToResponse(response, context);
    }


    // ----------------------------------------------------------
    public String defaultDescription()
    {
    	return reportTemplate.name();
    }


    // ----------------------------------------------------------
    public String querySummaryForDataSet()
    {
    	ReportQuery query = queryForLocalDataSetUuid(dataSet.uuid());
    	return query.qualifier().toString();
    }


    // ----------------------------------------------------------
    public NSArray<IRenderingMethod> renderingMethods()
    {
    	return Reporter.getInstance().allRenderingMethods();
    }


    // ----------------------------------------------------------
    public WOComponent goNext()
    {
    	String desc = reportDescription;
    	if (desc == null)
    	{
    		desc = defaultDescription();
    	}

    	setLocalReportDescription(desc);
    	setLocalRenderingMethod(selectedRenderingMethod.methodName());

    	commitReportGeneration();
    	return pageWithName(GeneratedReportPage.class.getName());
    }
}