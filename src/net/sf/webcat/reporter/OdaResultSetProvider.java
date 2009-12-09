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

import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableDictionary;
import net.sf.webcat.oda.commons.IWebCATResultSet;
import net.sf.webcat.oda.commons.IWebCATResultSetProvider;

//-------------------------------------------------------------------------
/**
 * Generates result sets for a report generation job based on the data set
 * queries that it has specified.
 *
 * @author Tony Allevato
 * @version $Id$
 */
public class OdaResultSetProvider implements IWebCATResultSetProvider
{
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Create a new result set provider for a report job.
     *
     * @param job
     *            the report job
     * @param report
     *            the generated report that this data will go into
     */
    public OdaResultSetProvider(ManagedReportGenerationJob job)
    {
        this.job = job;

        int dataSetRefs = countDataSetReferences(
                job.generatedReport().reportTemplate());
        job.beginTask("Generating report", dataSetRefs);

        dataSetQueries = job.generatedReport().dataSetQueries();
        queryMap = new NSMutableDictionary<Integer, ReportQuery>();

        // Construct the mapping from data set IDs to queries that define the
        // data to be retrieved.

        for (ReportDataSetQuery dataSetQuery : dataSetQueries)
        {
            Number dataSetId = dataSetQuery.dataSet().id();
            ReportQuery query = dataSetQuery.reportQuery();

            queryMap.setObjectForKey(query,
                    Integer.valueOf(dataSetId.intValue()));
        }
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    public IWebCATResultSet resultSetWithId(String id)
    {
        Integer dataSetId = Integer.parseInt(id);
        ReportQuery query = queryMap.objectForKey(dataSetId);

        return new OdaResultSet(dataSetId, job, query);
    }


    // ----------------------------------------------------------
    /**
     * Counts the total number of data set references contained inside the
     * specified report template. This is used to compute progress information
     * about the report generation task.
     *
     * @param template the ReportTemplate being executed
     * @return the total number of data set references in the template
     */
    private int countDataSetReferences(ReportTemplate template)
    {
        NSArray<ReportDataSet> dataSets = template.dataSets();
        int dataSetRefs = 0;

        for (ReportDataSet dataSet : dataSets)
        {
            dataSetRefs += dataSet.referenceCount();
        }

        return dataSetRefs;
    }


    //~ Instance/static variables .............................................

    private ManagedReportGenerationJob job;
    private NSArray<ReportDataSetQuery> dataSetQueries;
    private NSMutableDictionary<Integer, ReportQuery> queryMap;
}
