/*==========================================================================*\
 |  _EnqueuedReportJob.java
 |*-------------------------------------------------------------------------*|
 |  Created by eogenerator
 |  DO NOT EDIT.  Make changes to EnqueuedReportJob.java instead.
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

import com.webobjects.foundation.*;
import com.webobjects.eocontrol.*;
import com.webobjects.eoaccess.*;
import java.util.Enumeration;
import org.apache.log4j.Logger;

// -------------------------------------------------------------------------
/**
 * An automatically generated EOGenericRecord subclass.  DO NOT EDIT.
 * To change, use EOModeler, or make additions in
 * EnqueuedReportJob.java.
 *
 * @author Generated by eogenerator
 * @version version suppressed to control auto-generation
 */
public abstract class _EnqueuedReportJob
    extends er.extensions.ERXGenericRecord
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new _EnqueuedReportJob object.
     */
    public _EnqueuedReportJob()
    {
        super();
    }


    // ----------------------------------------------------------
    /**
     * A static factory method for creating a new
     * _EnqueuedReportJob object given required
     * attributes and relationships.
     * @param editingContext The context in which the new object will be
     * inserted
     * @param discarded
     * @param paused
     * @return The newly created object
     */
    public static EnqueuedReportJob create(
        EOEditingContext editingContext,
        boolean discarded,
        boolean paused
        )
    {
        EnqueuedReportJob eoObject = (EnqueuedReportJob)
            EOUtilities.createAndInsertInstance(
                editingContext,
                _EnqueuedReportJob.ENTITY_NAME);
        eoObject.setDiscarded(discarded);
        eoObject.setPaused(paused);
        return eoObject;
    }


    // ----------------------------------------------------------
    /**
     * Get a local instance of the given object in another editing context.
     * @param editingContext The target editing context
     * @param eo The object to import
     * @return An instance of the given object in the target editing context
     */
    public static EnqueuedReportJob localInstance(
        EOEditingContext editingContext, EnqueuedReportJob eo)
    {
        return (eo == null)
            ? null
            : (EnqueuedReportJob)EOUtilities.localInstanceOfObject(
                editingContext, eo);
    }


    // ----------------------------------------------------------
    /**
     * Look up an object by id number.  Assumes the editing
     * context is appropriately locked.
     * @param ec The editing context to use
     * @param id The id to look up
     * @return The object, or null if no such id exists
     */
    public static EnqueuedReportJob forId(
        EOEditingContext ec, int id )
    {
        EnqueuedReportJob obj = null;
        if (id > 0)
        {
            NSArray results = EOUtilities.objectsMatchingKeyAndValue( ec,
                ENTITY_NAME, "id", new Integer( id ) );
            if ( results != null && results.count() > 0 )
            {
                obj = (EnqueuedReportJob)results.objectAtIndex( 0 );
            }
        }
        return obj;
    }


    // ----------------------------------------------------------
    /**
     * Look up an object by id number.  Assumes the editing
     * context is appropriately locked.
     * @param ec The editing context to use
     * @param id The id to look up
     * @return The object, or null if no such id exists
     */
    public static EnqueuedReportJob forId(
        EOEditingContext ec, String id )
    {
        return forId( ec, er.extensions.ERXValueUtilities.intValue( id ) );
    }


    //~ Constants (for key names) .............................................

    // Attributes ---
    public static final String DESCRIPTION_KEY = "description";
    public static final String DISCARDED_KEY = "discarded";
    public static final String PAUSED_KEY = "paused";
    public static final String QUEUE_TIME_KEY = "queueTime";
    public static final String RENDERED_RESOURCE_ACTION_URL_KEY = "renderedResourceActionUrl";
    public static final String RENDERING_METHOD_KEY = "renderingMethod";
    public static final String UUID_KEY = "uuid";
    // To-one relationships ---
    public static final String REPORT_TEMPLATE_KEY = "reportTemplate";
    public static final String USER_KEY = "user";
    // To-many relationships ---
    public static final String DATA_SET_QUERIES_KEY = "dataSetQueries";
    // Fetch specifications ---
    public static final String USER_FSPEC = "user";
    public static final String UUID_FSPEC = "uuid";
    public static final String ENTITY_NAME = "EnqueuedReportJob";


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Get a local instance of this object in another editing context.
     * @param editingContext The target editing context
     * @return An instance of this object in the target editing context
     */
    public EnqueuedReportJob localInstance(EOEditingContext editingContext)
    {
        return (EnqueuedReportJob)EOUtilities.localInstanceOfObject(
            editingContext, this);
    }


    // ----------------------------------------------------------
    /**
     * Get a list of changes between this object's current state and the
     * last committed version.
     * @return a dictionary of the changes that have not yet been committed
     */
    public NSDictionary changedProperties()
    {
        return changesFromSnapshot(
            editingContext().committedSnapshotForObject(this) );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>id</code> value.
     * @return the value of the attribute
     */
    public Number id()
    {
        try
        {
            return (Number)EOUtilities.primaryKeyForObject(
                editingContext() , this ).objectForKey( "id" );
        }
        catch (Exception e)
        {
            return er.extensions.ERXConstant.ZeroInteger;
        }
    }

    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>description</code> value.
     * @return the value of the attribute
     */
    public String description()
    {
        return (String)storedValueForKey( "description" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>description</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setDescription( String value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setDescription("
                + value + "): was " + description() );
        }
        takeStoredValueForKey( value, "description" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>discarded</code> value.
     * @return the value of the attribute
     */
    public boolean discarded()
    {
        Number result =
            (Number)storedValueForKey( "discarded" );
        return ( result == null )
            ? false
            : ( result.intValue() > 0 );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>discarded</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setDiscarded( boolean value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setDiscarded("
                + value + "): was " + discarded() );
        }
        Number actual =
            er.extensions.ERXConstant.integerForInt( value ? 1 : 0 );
        setDiscardedRaw( actual );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>discarded</code> value.
     * @return the value of the attribute
     */
    public Number discardedRaw()
    {
        return (Number)storedValueForKey( "discarded" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>discarded</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setDiscardedRaw( Number value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setDiscardedRaw("
                + value + "): was " + discardedRaw() );
        }
        takeStoredValueForKey( value, "discarded" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>paused</code> value.
     * @return the value of the attribute
     */
    public boolean paused()
    {
        Number result =
            (Number)storedValueForKey( "paused" );
        return ( result == null )
            ? false
            : ( result.intValue() > 0 );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>paused</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setPaused( boolean value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setPaused("
                + value + "): was " + paused() );
        }
        Number actual =
            er.extensions.ERXConstant.integerForInt( value ? 1 : 0 );
        setPausedRaw( actual );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>paused</code> value.
     * @return the value of the attribute
     */
    public Number pausedRaw()
    {
        return (Number)storedValueForKey( "paused" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>paused</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setPausedRaw( Number value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setPausedRaw("
                + value + "): was " + pausedRaw() );
        }
        takeStoredValueForKey( value, "paused" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>queueTime</code> value.
     * @return the value of the attribute
     */
    public NSTimestamp queueTime()
    {
        return (NSTimestamp)storedValueForKey( "queueTime" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>queueTime</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setQueueTime( NSTimestamp value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setQueueTime("
                + value + "): was " + queueTime() );
        }
        takeStoredValueForKey( value, "queueTime" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>renderedResourceActionUrl</code> value.
     * @return the value of the attribute
     */
    public String renderedResourceActionUrl()
    {
        return (String)storedValueForKey( "renderedResourceActionUrl" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>renderedResourceActionUrl</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setRenderedResourceActionUrl( String value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setRenderedResourceActionUrl("
                + value + "): was " + renderedResourceActionUrl() );
        }
        takeStoredValueForKey( value, "renderedResourceActionUrl" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>renderingMethod</code> value.
     * @return the value of the attribute
     */
    public String renderingMethod()
    {
        return (String)storedValueForKey( "renderingMethod" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>renderingMethod</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setRenderingMethod( String value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setRenderingMethod("
                + value + "): was " + renderingMethod() );
        }
        takeStoredValueForKey( value, "renderingMethod" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>uuid</code> value.
     * @return the value of the attribute
     */
    public String uuid()
    {
        return (String)storedValueForKey( "uuid" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>uuid</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setUuid( String value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setUuid("
                + value + "): was " + uuid() );
        }
        takeStoredValueForKey( value, "uuid" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the entity pointed to by the <code>reportTemplate</code>
     * relationship.
     * @return the entity in the relationship
     */
    public net.sf.webcat.reporter.ReportTemplate reportTemplate()
    {
        return (net.sf.webcat.reporter.ReportTemplate)storedValueForKey( "reportTemplate" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>reportTemplate</code>
     * relationship (DO NOT USE--instead, use
     * <code>setReportTemplateRelationship()</code>.
     * This method is provided for WebObjects use.
     *
     * @param value The new entity to relate to
     */
    public void setReportTemplate( net.sf.webcat.reporter.ReportTemplate value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setReportTemplate("
                + value + "): was " + reportTemplate() );
        }
        takeStoredValueForKey( value, "reportTemplate" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>reportTemplate</code>
     * relationship.  This method is a type-safe version of
     * <code>addObjectToBothSidesOfRelationshipWithKey()</code>.
     *
     * @param value The new entity to relate to
     */
    public void setReportTemplateRelationship(
        net.sf.webcat.reporter.ReportTemplate value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setReportTemplateRelationship("
                + value + "): was " + reportTemplate() );
        }
        if ( value == null )
        {
            net.sf.webcat.reporter.ReportTemplate object = reportTemplate();
            if ( object != null )
                removeObjectFromBothSidesOfRelationshipWithKey( object, "reportTemplate" );
        }
        else
        {
            addObjectToBothSidesOfRelationshipWithKey( value, "reportTemplate" );
        }
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the entity pointed to by the <code>user</code>
     * relationship.
     * @return the entity in the relationship
     */
    public net.sf.webcat.core.User user()
    {
        return (net.sf.webcat.core.User)storedValueForKey( "user" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>user</code>
     * relationship (DO NOT USE--instead, use
     * <code>setUserRelationship()</code>.
     * This method is provided for WebObjects use.
     *
     * @param value The new entity to relate to
     */
    public void setUser( net.sf.webcat.core.User value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setUser("
                + value + "): was " + user() );
        }
        takeStoredValueForKey( value, "user" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>user</code>
     * relationship.  This method is a type-safe version of
     * <code>addObjectToBothSidesOfRelationshipWithKey()</code>.
     *
     * @param value The new entity to relate to
     */
    public void setUserRelationship(
        net.sf.webcat.core.User value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setUserRelationship("
                + value + "): was " + user() );
        }
        if ( value == null )
        {
            net.sf.webcat.core.User object = user();
            if ( object != null )
                removeObjectFromBothSidesOfRelationshipWithKey( object, "user" );
        }
        else
        {
            addObjectToBothSidesOfRelationshipWithKey( value, "user" );
        }
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the entities pointed to by the <code>dataSetQueries</code>
     * relationship.
     * @return an NSArray of the entities in the relationship
     */
    public NSArray dataSetQueries()
    {
        return (NSArray)storedValueForKey( "dataSetQueries" );
    }


    // ----------------------------------------------------------
    /**
     * Replace the list of entities pointed to by the
     * <code>dataSetQueries</code> relationship.
     *
     * @param value The new set of entities to relate to
     */
    public void setDataSetQueries( NSMutableArray value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setDataSetQueries("
                + value + "): was " + dataSetQueries() );
        }
        takeStoredValueForKey( value, "dataSetQueries" );
    }


    // ----------------------------------------------------------
    /**
     * Add a new entity to the <code>dataSetQueries</code>
     * relationship (DO NOT USE--instead, use
     * <code>addToDataSetQueriesRelationship()</code>.
     * This method is provided for WebObjects use.
     *
     * @param value The new entity to relate to
     */
    public void addToDataSetQueries( net.sf.webcat.reporter.ReportDataSetQuery value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "addToDataSetQueries("
                + value + "): was " + dataSetQueries() );
        }
        NSMutableArray array = (NSMutableArray)dataSetQueries();
        willChange();
        array.addObject( value );
    }


    // ----------------------------------------------------------
    /**
     * Remove a specific entity from the <code>dataSetQueries</code>
     * relationship (DO NOT USE--instead, use
     * <code>removeFromDataSetQueriesRelationship()</code>.
     * This method is provided for WebObjects use.
     *
     * @param value The entity to remove from the relationship
     */
    public void removeFromDataSetQueries( net.sf.webcat.reporter.ReportDataSetQuery value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "RemoveFromDataSetQueries("
                + value + "): was " + dataSetQueries() );
        }
        NSMutableArray array = (NSMutableArray)dataSetQueries();
        willChange();
        array.removeObject( value );
    }


    // ----------------------------------------------------------
    /**
     * Add a new entity to the <code>dataSetQueries</code>
     * relationship.
     *
     * @param value The new entity to relate to
     */
    public void addToDataSetQueriesRelationship( net.sf.webcat.reporter.ReportDataSetQuery value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "addToDataSetQueriesRelationship("
                + value + "): was " + dataSetQueries() );
        }
        addObjectToBothSidesOfRelationshipWithKey(
            value, "dataSetQueries" );
    }


    // ----------------------------------------------------------
    /**
     * Remove a specific entity from the <code>dataSetQueries</code>
     * relationship.
     *
     * @param value The entity to remove from the relationship
     */
    public void removeFromDataSetQueriesRelationship( net.sf.webcat.reporter.ReportDataSetQuery value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "removeFromDataSetQueriesRelationship("
                + value + "): was " + dataSetQueries() );
        }
        removeObjectFromBothSidesOfRelationshipWithKey(
            value, "dataSetQueries" );
    }


    // ----------------------------------------------------------
    /**
     * Create a brand new object that is a member of the
     * <code>dataSetQueries</code> relationship.
     *
     * @return The new entity
     */
    public net.sf.webcat.reporter.ReportDataSetQuery createDataSetQueriesRelationship()
    {
        if (log.isDebugEnabled())
        {
            log.debug( "createDataSetQueriesRelationship()" );
        }
        EOClassDescription eoClassDesc = EOClassDescription
            .classDescriptionForEntityName( "ReportDataSetQuery" );
        EOEnterpriseObject eoObject = eoClassDesc
            .createInstanceWithEditingContext( editingContext(), null );
        editingContext().insertObject( eoObject );
        addObjectToBothSidesOfRelationshipWithKey(
            eoObject, "dataSetQueries" );
        return (net.sf.webcat.reporter.ReportDataSetQuery)eoObject;
    }


    // ----------------------------------------------------------
    /**
     * Remove and then delete a specific entity that is a member of the
     * <code>dataSetQueries</code> relationship.
     *
     * @param value The entity to remove from the relationship and then delete
     */
    public void deleteDataSetQueriesRelationship( net.sf.webcat.reporter.ReportDataSetQuery value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "deleteDataSetQueriesRelationship("
                + value + "): was " + dataSetQueries() );
        }
        removeObjectFromBothSidesOfRelationshipWithKey(
            value, "dataSetQueries" );
        editingContext().deleteObject( value );
    }


    // ----------------------------------------------------------
    /**
     * Remove (and then delete, if owned) all entities that are members of the
     * <code>dataSetQueries</code> relationship.
     */
    public void deleteAllDataSetQueriesRelationships()
    {
        if (log.isDebugEnabled())
        {
            log.debug( "deleteAllDataSetQueriesRelationships(): was "
                + dataSetQueries() );
        }
        Enumeration objects = dataSetQueries().objectEnumerator();
        while ( objects.hasMoreElements() )
            deleteDataSetQueriesRelationship(
                (net.sf.webcat.reporter.ReportDataSetQuery)objects.nextElement() );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve object according to the <code>User</code>
     * fetch specification.
     *
     * @param context The editing context to use
     * @param userBinding fetch spec parameter
     * @return an NSArray of the entities retrieved
     */
    public static NSArray objectsForUser(
            EOEditingContext context,
            net.sf.webcat.core.User userBinding
        )
    {
        EOFetchSpecification spec = EOFetchSpecification
            .fetchSpecificationNamed( "user", "EnqueuedReportJob" );

        NSMutableDictionary bindings = new NSMutableDictionary();

        if ( userBinding != null )
            bindings.setObjectForKey( userBinding,
                                      "user" );
        spec = spec.fetchSpecificationWithQualifierBindings( bindings );

        NSArray result = context.objectsWithFetchSpecification( spec );
        if (log.isDebugEnabled())
        {
            log.debug( "objectsForUser(ec"
            
                + ", " + userBinding
                + "): " + result );
        }
        return result;
    }


    // ----------------------------------------------------------
    /**
     * Retrieve object according to the <code>Uuid</code>
     * fetch specification.
     *
     * @param context The editing context to use
     * @param uuidBinding fetch spec parameter
     * @return an NSArray of the entities retrieved
     */
    public static NSArray objectsForUuid(
            EOEditingContext context,
            String uuidBinding
        )
    {
        EOFetchSpecification spec = EOFetchSpecification
            .fetchSpecificationNamed( "uuid", "EnqueuedReportJob" );

        NSMutableDictionary bindings = new NSMutableDictionary();

        if ( uuidBinding != null )
            bindings.setObjectForKey( uuidBinding,
                                      "uuid" );
        spec = spec.fetchSpecificationWithQualifierBindings( bindings );

        NSArray result = context.objectsWithFetchSpecification( spec );
        if (log.isDebugEnabled())
        {
            log.debug( "objectsForUuid(ec"
            
                + ", " + uuidBinding
                + "): " + result );
        }
        return result;
    }


    //~ Instance/static variables .............................................

    static Logger log = Logger.getLogger( EnqueuedReportJob.class );
}
