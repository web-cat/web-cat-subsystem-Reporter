/*==========================================================================*\
 |  Copyright (C) 2009-2021 Virginia Tech
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

package org.webcat.reporter.messaging;

import org.webcat.core.User;
import org.webcat.core.messaging.Message;
import org.webcat.reporter.GeneratedReport;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOGlobalID;
import com.webobjects.foundation.NSArray;

//-------------------------------------------------------------------------
/**
 * A message that is sent to the user who requested that a report be
 * generated once the report is complete.
 *
 * @author Tony Allevato
 */
public class ReportCompleteMessage extends Message
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new instance of the ReportCompleteMessage class.
     *
     * @param report the report that was completed
     */
    public ReportCompleteMessage(EOEditingContext ec, GeneratedReport report)
    {
        description = report.description();
        setUserEmails(new NSArray<String>(report.user().email()));
        setUserIds(new NSArray<EOGlobalID>(report.user().globalId()));
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Called by the subsystem init() to register the message.
     */
    public static void register()
    {
        Message.registerMessage(
                ReportCompleteMessage.class,
                "Reports",
                "Report Completed",
                false,
                User.GRADER_PRIVILEGES);
    }


    // ----------------------------------------------------------
    @Override
    public String fullBody()
    {
        // TODO make this better
        return "The report named \"" + description +
            "\" was generated.";
    }


    // ----------------------------------------------------------
    @Override
    public String shortBody()
    {
        return "The report named \"" + description +
            "\" was generated.";
    }


    // ----------------------------------------------------------
    @Override
    public String title()
    {
        return "Report completed: " + description;
    }


    //~ Static/instance variables .............................................

    private String description;
}
