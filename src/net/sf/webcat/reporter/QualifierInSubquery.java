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

import com.webobjects.eocontrol.EOQualifier;
import er.extensions.eof.qualifiers.ERXQualifierInSubquery;

//-------------------------------------------------------------------------
/**
 * A subclass of ERXQualifierInSubquery that exposes its properties through
 * read-only getters, so the qualifier can be inspected and turned back into
 * an AdvancedQueryCriterion.
 * 
 * @author Tony Allevato
 * @version $Id$
 */
public class QualifierInSubquery extends ERXQualifierInSubquery
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    public QualifierInSubquery(EOQualifier qualifier)
    {
        super(qualifier);
    }

    
    // ----------------------------------------------------------
    public QualifierInSubquery(EOQualifier qualifier, String entityName,
            String relationshipName)
    {
        super(qualifier, entityName, relationshipName);
    }
    
    
    // ----------------------------------------------------------
    public QualifierInSubquery(EOQualifier qualifier, String entityName,
            String attributeName, String destinationAttName)
    {
        super(qualifier, entityName, attributeName, destinationAttName);
    }
    

    //~ Methods ...............................................................

    // ----------------------------------------------------------
    public EOQualifier qualifier()
    {
        return qualifier;
    }


    // ----------------------------------------------------------
    public String entityName()
    {
        return entityName;
    }


    // ----------------------------------------------------------
    public String relationshipName()
    {
        return relationshipName;
    }


    // ----------------------------------------------------------
    public String attributeName()
    {
        return attributeName;
    }


    // ----------------------------------------------------------
    public String destinationAttributeName()
    {
        return destinationAttName;
    }
}
