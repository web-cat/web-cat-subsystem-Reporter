/*==========================================================================*\
 |  $Id$
 |*-------------------------------------------------------------------------*|
 |  Copyright (C) 2006 Virginia Tech
 |
 |  This file is part of Web-CAT.
 |
 |  Web-CAT is free software; you can redistribute it and/or modify
 |  it under the terms of the GNU General Public License as published by
 |  the Free Software Foundation; either version 2 of the License, or
 |  (at your option) any later version.
 |
 |  Web-CAT is distributed in the hope that it will be useful,
 |  but WITHOUT ANY WARRANTY; without even the implied warranty of
 |  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 |  GNU General Public License for more details.
 |
 |  You should have received a copy of the GNU General Public License
 |  along with Web-CAT; if not, write to the Free Software
 |  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA
 |
 |  Project manager: Stephen Edwards <edwards@cs.vt.edu>
 |  Virginia Tech CS Dept, 660 McBryde Hall (0106), Blacksburg, VA 24061 USA
\*==========================================================================*/

package net.sf.webcat.reporter;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.eocontrol.EOGlobalID;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSMutableDictionary;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//-------------------------------------------------------------------------
/**
 * Provides static utility methods for converting arrays or dictionaries
 * of EO IDs to the corresponding EOs and vice versa.
 *
 * @author aallowat
 * @version $Id$
 */
public class EOGlobalIDUtils
{
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * This class provides only static utility methods, so no instances
     * should ever be created.
     */
    private EOGlobalIDUtils()
    {
        // Nothing to do
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
	public static NSArray enterpriseObjectsForIdArray(List array,
			EOEditingContext ec)
	{
		NSMutableArray newArray = new NSMutableArray();

		if (array instanceof NSArray)
		{
			Enumeration e = ((NSArray)array).objectEnumerator();
			while (e.hasMoreElements())
			{
				Object value = e.nextElement();

				Object newValue = tryEnterpriseObjectForId(value, ec);
				newArray.addObject(newValue);
			}
		}
		else
		{
			Iterator it = array.iterator();
			while (it.hasNext())
			{
				Object value = it.next();

				Object newValue = tryEnterpriseObjectForId(value, ec);
				newArray.addObject(newValue);
			}
		}

		return newArray;
	}


    // ----------------------------------------------------------
	public static NSDictionary enterpriseObjectsForIdDictionary(
	    Map dictionary, EOEditingContext ec)
	{
		NSMutableDictionary newDictionary = new NSMutableDictionary();

		if (dictionary instanceof NSDictionary)
		{
			Enumeration e = ((NSDictionary)dictionary).keyEnumerator();
			while (e.hasMoreElements())
			{
				Object key = e.nextElement();
				Object value = ((NSDictionary)dictionary).objectForKey(key);

				Object newValue = tryEnterpriseObjectForId(value, ec);
				newDictionary.setObjectForKey(newValue, key);
			}
		}
		else
		{
			Iterator it = dictionary.keySet().iterator();
			while (it.hasNext())
			{
				Object key = it.next();
				Object value = dictionary.get(key);

				Object newValue = tryEnterpriseObjectForId(value, ec);
				newDictionary.setObjectForKey(newValue, key);
			}
		}

		return newDictionary;
	}


    // ----------------------------------------------------------
	public static Object tryEnterpriseObjectForId(
        Object value, EOEditingContext ec)
	{
		if (value instanceof List)
		{
			return enterpriseObjectsForIdArray((List)value, ec);
		}
		else if (value instanceof Map)
		{
			return enterpriseObjectsForIdDictionary((Map)value, ec);
		}
		else if (value instanceof EOGlobalID)
		{
			return ec.faultForGlobalID((EOGlobalID)value, ec);
		}
		else
		{
			return value;
		}
	}


    // ----------------------------------------------------------
	public static NSArray idsForEnterpriseObjectArray(
	    List array, EOEditingContext ec)
	{
		NSMutableArray newArray = new NSMutableArray();

		if (array instanceof NSArray)
		{
			Enumeration e = ((NSArray)array).objectEnumerator();
			while (e.hasMoreElements())
			{
				Object value = e.nextElement();

				Object newValue = tryIdForEnterpriseObject(value, ec);
				newArray.addObject(newValue);
			}
		}
		else
		{
			Iterator it = array.iterator();
			while (it.hasNext())
			{
				Object value = it.next();

				Object newValue = tryIdForEnterpriseObject(value, ec);
				newArray.addObject(newValue);
			}
		}

		return newArray;
	}


    // ----------------------------------------------------------
	public static NSDictionary idsForEnterpriseObjectDictionary(
			Map dictionary, EOEditingContext ec)
	{
		NSMutableDictionary newDictionary = new NSMutableDictionary();

		if (dictionary instanceof NSDictionary)
		{
			Enumeration e = ((NSDictionary)dictionary).keyEnumerator();
			while (e.hasMoreElements())
			{
				Object key = e.nextElement();
				Object value = ((NSDictionary)dictionary).objectForKey(key);

				Object newValue = tryIdForEnterpriseObject(value, ec);
				newDictionary.setObjectForKey(newValue, key);
			}
		}
		else
		{
			Iterator it = dictionary.keySet().iterator();
			while (it.hasNext())
			{
				Object key = it.next();
				Object value = dictionary.get(key);

				Object newValue = tryIdForEnterpriseObject(value, ec);
				newDictionary.setObjectForKey(newValue, key);
			}
		}

		return newDictionary;
	}


    // ----------------------------------------------------------
	public static Object tryIdForEnterpriseObject(
        Object value, EOEditingContext ec)
	{
		if (value instanceof List)
		{
			return idsForEnterpriseObjectArray((List)value, ec);
		}
		else if (value instanceof Map)
		{
			return idsForEnterpriseObjectDictionary((Map)value, ec);
		}
		else if (value instanceof EOEnterpriseObject)
		{
			return ec.globalIDForObject((EOEnterpriseObject)value);
		}
		else
		{
			return value;
		}
	}
}
