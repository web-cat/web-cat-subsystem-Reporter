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

package net.sf.webcat.reporter.queryassistants;

import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSBundle;
import com.webobjects.foundation.NSData;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSMutableDictionary;
import com.webobjects.foundation.NSPropertyListSerialization;
import net.sf.webcat.core.Application;
import org.apache.log4j.Logger;

//-------------------------------------------------------------------------
/**
 * A singleton that manages the set of query assistants that are available,
 * and looks up those that are applicable based on the kind of entity a query
 * is for.  The available query assistants are read from a plist file
 * stored in the subsystem's resources.
 *
 * @author aallowat
 * @version $Id$
 */
public class QueryAssistantManager
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Get the singleton instance of this class.
     * @return The single shared instance of this class
     */
    public static QueryAssistantManager getInstance()
    {
        if (instance == null)
        {
            instance = new QueryAssistantManager();
        }
        return instance;
    }


    // ----------------------------------------------------------
    /**
     * The constructor is private, since this is a singleton class.
     */
    private QueryAssistantManager()
    {
        NSBundle myBundle = NSBundle.bundleForClass(getClass());

        NSData data = new NSData(myBundle.bytesForResourcePath(
            "QueryAssistants.plist"));
        NSDictionary<String, Object> plist = (NSDictionary<String, Object>)
            NSPropertyListSerialization.propertyListFromData(data, "UTF-8");

        assistants = new
            NSMutableDictionary<String, QueryAssistantDescriptor>();
        entitiesToIds = new
            NSMutableDictionary<String, NSMutableArray<String>>();

        for (String id : plist.allKeys())
        {
            NSDictionary<String, Object> asstInPlist =
                (NSDictionary<String, Object>)plist.objectForKey(id);

            NSArray<String> entities =
                (NSArray<String>)asstInPlist.objectForKey("entities");
            String modelName =
                (String)asstInPlist.objectForKey("modelName");
            String editorComponentName =
                (String)asstInPlist.objectForKey("editorComponentName");
            String previewComponentName =
                (String)asstInPlist.objectForKey("previewComponentName");
            String description =
                (String)asstInPlist.objectForKey("description");

            boolean success = verifyClassInheritance(modelName,
                    AbstractQueryAssistantModel.class);

            if (success)
            {
                QueryAssistantDescriptor qad =
                    new QueryAssistantDescriptor(
                        id, entities, modelName, editorComponentName,
                        previewComponentName, description);

                assistants.setObjectForKey(qad, id);

                if (entities != null)
                {
                    // Add the query assistant to each entity in the map.

                    for (String entityName : entities)
                    {
                        NSMutableArray<String> ids =
                            entitiesToIds.objectForKey(entityName);

                        if (ids == null)
                        {
                            ids = new NSMutableArray<String>();
                            entitiesToIds.setObjectForKey(ids, entityName);
                        }

                        ids.addObject(id);
                    }
                }
                else
                {
                    // Add the query assistant to the "blank" array in the map.
                    // We use a blank entry because we don't know which
                    // entities will be in the map until we've processed them
                    // all, so we just merge this array with the one for a
                    // specific entity in the assistantsForEntity method.

                    NSMutableArray<String> ids =
                        entitiesToIds.objectForKey("");

                    if (ids == null)
                    {
                        ids = new NSMutableArray<String>();
                        entitiesToIds.setObjectForKey(ids, "");
                    }

                    ids.addObject(id);
                }
            }
        }
    }


    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    public NSArray<QueryAssistantDescriptor> assistantsForEntity(String entity)
    {
        NSMutableArray<QueryAssistantDescriptor> array =
            new NSMutableArray<QueryAssistantDescriptor>();

        NSArray<String> ids;

        // Add the query assistants for this specific entity type.

        ids = entitiesToIds.objectForKey(entity);
        if (ids != null)
        {
            for (String id : ids)
            {
                array.addObject(assistantWithId(id));
            }
        }

        // Add the query assistants that apply to all entity types.

        ids = entitiesToIds.objectForKey("");
        if (ids != null)
        {
            for (String id : ids)
            {
                array.addObject(assistantWithId(id));
            }
        }

        return array;
    }


    public QueryAssistantDescriptor assistantWithId(String id)
    {
        return assistants.objectForKey(id);
    }


    //~ Private Methods .......................................................

    // ----------------------------------------------------------
    private boolean verifyClassInheritance(String className,
            Class<?> superclass)
    {
        Class<?> klass = null;

        try
        {
            klass = Class.forName(className);
        }
        catch(Exception e)
        {
            log.error("Could not find a class named " + className +
                    ". This query assistant will not be available.");
            return false;
        }

        if(klass != null)
        {
            if(!superclass.isAssignableFrom(klass))
            {
                log.error("The class " + className + " does not" +
                        "inherit from " + superclass.getCanonicalName() + ". " +
                        "This query assistant will not be available.");
            }
        }

        return true;
    }


    //~ Instance/static variables .............................................

    private NSMutableDictionary<String, QueryAssistantDescriptor> assistants;
    private NSMutableDictionary<String, NSMutableArray<String>> entitiesToIds;
    private static QueryAssistantManager instance;
    private static Logger log = Logger.getLogger(QueryAssistantManager.class);
}
