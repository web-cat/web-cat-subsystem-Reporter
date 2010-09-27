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

package org.webcat.reporter;

import org.webcat.core.Course;
import org.webcat.core.CourseOffering;
import org.webcat.core.Department;
import org.webcat.core.Semester;
import org.webcat.ui.AbstractTreeModel;
import org.webcat.ui.util.WCTableLayoutBuilder;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WODisplayGroup;
import com.webobjects.eocontrol.EOFetchSpecification;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import er.extensions.eof.ERXS;

//-------------------------------------------------------------------------
/**
 * This page allows the user to select the template to use for a new report.
 *
 * @author Tony Allevato
 * @version $Id$
 */
public class PickTemplateToGeneratePage
    extends ReporterComponent
{
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Create a new page.
     * @param context The page's context
     */
    public PickTemplateToGeneratePage(WOContext context)
    {
        super(context);
    }


    //~ KVC Attributes (must be public) .......................................

    public WODisplayGroup reportTemplatesDisplayGroup;


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    public WODisplayGroup reportTemplatesDisplayGroup()
    {
        if (reportTemplatesDisplayGroup == null)
        {
            NSArray<ReportTemplate> templates =
                ReportTemplate.templatesAccessibleByUser(
                        localContext(), user());

            reportTemplatesDisplayGroup = new WODisplayGroup();
            reportTemplatesDisplayGroup.setObjectArray(templates);
        }

        return reportTemplatesDisplayGroup;
    }


    // ----------------------------------------------------------
    public WOComponent templateChosen()
    {
        clearLocalReportState();

        ReportTemplate template =
            (ReportTemplate) reportTemplatesDisplayGroup.selectedObject();

        setLocalReportTemplate(template);

        return pageWithName(DescribeReportInputsPage.class);
    }
}