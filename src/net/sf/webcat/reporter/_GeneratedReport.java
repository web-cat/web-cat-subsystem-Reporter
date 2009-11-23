/*==========================================================================*\
 |  _GeneratedReport.java
 |*-------------------------------------------------------------------------*|
 |  Created by eogenerator
 |  DO NOT EDIT.  Make changes to GeneratedReport.java instead.
 |*-------------------------------------------------------------------------*|
 |  Copyright (C) 2006-2009 Virginia Tech
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

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.util.Enumeration;
import org.apache.log4j.Logger;

// -------------------------------------------------------------------------
/**
 * An automatically generated EOGenericRecord subclass.  DO NOT EDIT.
 * To change, use EOModeler, or make additions in
 * GeneratedReport.java.
 *
 * @author Generated by eogenerator
 * @version version suppressed to control auto-generation
 */
public abstract class _GeneratedReport
    extends er.extensions.eof.ERXGenericRecord
    implements net.sf.webcat.core.MutableContainer.MutableContainerOwner
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new _GeneratedReport object.
     */
    public _GeneratedReport()
    {
        super();
    }


    // ----------------------------------------------------------
    /**
     * A static factory method for creating a new
     * _GeneratedReport object given required
     * attributes and relationships.
     * @param editingContext The context in which the new object will be
     * inserted
     * @param isComplete
     * @param updateMutableFields
     * @return The newly created object
     */
    public static GeneratedReport create(
        EOEditingContext editingContext,
        boolean isComplete,
        boolean updateMutableFields
        )
    {
        GeneratedReport eoObject = (GeneratedReport)
            EOUtilities.createAndInsertInstance(
                editingContext,
                _GeneratedReport.ENTITY_NAME);
        eoObject.setIsComplete(isComplete);
        eoObject.setUpdateMutableFields(updateMutableFields);
        return eoObject;
    }


    // ----------------------------------------------------------
    /**
     * Get a local instance of the given object in another editing context.
     * @param editingContext The target editing context
     * @param eo The object to import
     * @return An instance of the given object in the target editing context
     */
    public static GeneratedReport localInstance(
        EOEditingContext editingContext, GeneratedReport eo)
    {
        return (eo == null)
            ? null
            : (GeneratedReport)EOUtilities.localInstanceOfObject(
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
    public static GeneratedReport forId(
        EOEditingContext ec, int id )
    {
        GeneratedReport obj = null;
        if (id > 0)
        {
            NSArray results = EOUtilities.objectsMatchingKeyAndValue( ec,
                ENTITY_NAME, "id", new Integer( id ) );
            if ( results != null && results.count() > 0 )
            {
                obj = (GeneratedReport)results.objectAtIndex( 0 );
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
    public static GeneratedReport forId(
        EOEditingContext ec, String id )
    {
        return forId( ec, er.extensions.foundation.ERXValueUtilities.intValue( id ) );
    }


    //~ Constants (for key names) .............................................

    // Attributes ---
    public static final String DESCRIPTION_KEY = "description";
    public static final String ERRORS_KEY = "errors";
    public static final String GENERATED_TIME_KEY = "generatedTime";
    public static final String IS_COMPLETE_KEY = "isComplete";
    public static final String UPDATE_MUTABLE_FIELDS_KEY = "updateMutableFields";
    // To-one relationships ---
    public static final String REPORT_TEMPLATE_KEY = "reportTemplate";
    public static final String USER_KEY = "user";
    // To-many relationships ---
    public static final String DATA_SET_QUERIES_KEY = "dataSetQueries";
    // Fetch specifications ---
    public static final String USER_FSPEC = "user";
    public static final String USER_COMPLETE_REPORTS_FSPEC = "userCompleteReports";
    public static final String USER_INCOMPLETE_REPORTS_FSPEC = "userIncompleteReports";
    public static final String ENTITY_NAME = "GeneratedReport";


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Get a local instance of this object in another editing context.
     * @param editingContext The target editing context
     * @return An instance of this object in the target editing context
     */
    public GeneratedReport localInstance(EOEditingContext editingContext)
    {
        return (GeneratedReport)EOUtilities.localInstanceOfObject(
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
            return er.extensions.eof.ERXConstant.ZeroInteger;
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


    //-- Local mutable cache --
    private net.sf.webcat.core.MutableArray errorsCache;
    private NSData errorsRawCache;

    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>errors</code> value.
     * @return the value of the attribute
     */
    public net.sf.webcat.core.MutableArray errors()
    {
        NSData dbValue =
            (NSData)storedValueForKey( "errors" );
        if ( errorsRawCache != dbValue )
        {
            if ( dbValue != null && dbValue.equals( errorsRawCache ) )
            {
                // They are still equal, so just update the raw cache
                errorsRawCache = dbValue;
            }
            else
            {
                // Underlying attribute may have changed because
                // of a concurrent update through another editing
                // context, so throw away current values.
                errorsRawCache = dbValue;
                net.sf.webcat.core.MutableArray newValue =
                    net.sf.webcat.core.MutableArray
                    .objectWithArchiveData( dbValue );
                if ( errorsCache != null )
                {
                    errorsCache.copyFrom( newValue );
                }
                else
                {
                    errorsCache = newValue;
                }
                errorsCache.setOwner( this );
                setUpdateMutableFields( true );
            }
        }
        else if ( dbValue == null && errorsCache == null )
        {
            errorsCache =
                net.sf.webcat.core.MutableArray
                .objectWithArchiveData( dbValue );
             errorsCache.setOwner( this );
             setUpdateMutableFields( true );
        }
        return errorsCache;
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>errors</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setErrors( net.sf.webcat.core.MutableArray value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setErrors("
                + value + ")" );
        }
        if ( errorsCache == null )
        {
            errorsCache = value;
            value.setHasChanged( false );
            errorsRawCache = value.archiveData();
            takeStoredValueForKey( errorsRawCache, "errors" );
        }
        else if ( errorsCache != value )  // ( errorsCache != null )
        {
            errorsCache.copyFrom( value );
            setUpdateMutableFields( true );
        }
        else  // ( errorsCache == non-null value )
        {
            // no nothing
        }
    }


    // ----------------------------------------------------------
    /**
     * Clear the value of this object's <code>errors</code>
     * property.
     */
    public void clearErrors()
    {
        if (log.isDebugEnabled())
        {
            log.debug( "clearErrors()" );
        }
        takeStoredValueForKey( null, "errors" );
        errorsRawCache = null;
        errorsCache = null;
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>generatedTime</code> value.
     * @return the value of the attribute
     */
    public NSTimestamp generatedTime()
    {
        return (NSTimestamp)storedValueForKey( "generatedTime" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>generatedTime</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setGeneratedTime( NSTimestamp value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setGeneratedTime("
                + value + "): was " + generatedTime() );
        }
        takeStoredValueForKey( value, "generatedTime" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>isComplete</code> value.
     * @return the value of the attribute
     */
    public boolean isComplete()
    {
        Integer result =
            (Integer)storedValueForKey( "isComplete" );
        return ( result == null )
            ? false
            : ( result.intValue() > 0 );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>isComplete</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setIsComplete( boolean value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setIsComplete("
                + value + "): was " + isComplete() );
        }
        Integer actual =
            er.extensions.eof.ERXConstant.integerForInt( value ? 1 : 0 );
            setIsCompleteRaw( actual );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>isComplete</code> value.
     * @return the value of the attribute
     */
    public Integer isCompleteRaw()
    {
        return (Integer)storedValueForKey( "isComplete" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>isComplete</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setIsCompleteRaw( Integer value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setIsCompleteRaw("
                + value + "): was " + isCompleteRaw() );
        }
        takeStoredValueForKey( value, "isComplete" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>updateMutableFields</code> value.
     * @return the value of the attribute
     */
    public boolean updateMutableFields()
    {
        Integer result =
            (Integer)storedValueForKey( "updateMutableFields" );
        return ( result == null )
            ? false
            : ( result.intValue() > 0 );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>updateMutableFields</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setUpdateMutableFields( boolean value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setUpdateMutableFields("
                + value + "): was " + updateMutableFields() );
        }
        Integer actual =
            er.extensions.eof.ERXConstant.integerForInt( value ? 1 : 0 );
            setUpdateMutableFieldsRaw( actual );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>updateMutableFields</code> value.
     * @return the value of the attribute
     */
    public Integer updateMutableFieldsRaw()
    {
        return (Integer)storedValueForKey( "updateMutableFields" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>updateMutableFields</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setUpdateMutableFieldsRaw( Integer value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setUpdateMutableFieldsRaw("
                + value + "): was " + updateMutableFieldsRaw() );
        }
        takeStoredValueForKey( value, "updateMutableFields" );
    }


    // ----------------------------------------------------------
    /**
     * Called just before this object is saved to the database.
     */
    public void saveMutables()
    {
        log.debug("saveMutables()");
        if ( errorsCache != null
            && errorsCache.hasChanged() )
        {
            errorsRawCache = errorsCache.archiveData();
            takeStoredValueForKey( errorsRawCache, "errors" );
            errorsCache.setHasChanged( false );
        }

        setUpdateMutableFields( false );
    }


    // ----------------------------------------------------------
    /**
     * Called just before this object is saved to the database.
     */
    public void willUpdate()
    {
        log.debug("willUpdate()");
        saveMutables();
        super.willUpdate();
    }


    // ----------------------------------------------------------
    /**
     * Called just before this object is inserted into the database.
     */
    public void willInsert()
    {
        log.debug("willInsert()");
        saveMutables();
        super.willInsert();
    }


    // ----------------------------------------------------------
    /**
     * Called when the object is invalidated.
     */
    public void flushCaches()
    {
        log.debug("flushCaches()");
        errorsCache = null;
        errorsRawCache  = null;
        super.flushCaches();
    }


    // ----------------------------------------------------------
    /**
     * Called when an owned mutable container object is changed.
     */
    public void mutableContainerHasChanged()
    {
        setUpdateMutableFields( true );
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
    @SuppressWarnings("unchecked")
    public NSArray<net.sf.webcat.reporter.ReportDataSetQuery> dataSetQueries()
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
    public void setDataSetQueries( NSMutableArray<net.sf.webcat.reporter.ReportDataSetQuery>  value )
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
        NSMutableArray<net.sf.webcat.reporter.ReportDataSetQuery> array =
            (NSMutableArray<net.sf.webcat.reporter.ReportDataSetQuery>)dataSetQueries();
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
        NSMutableArray<net.sf.webcat.reporter.ReportDataSetQuery> array =
            (NSMutableArray<net.sf.webcat.reporter.ReportDataSetQuery>)dataSetQueries();
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
     * Retrieve objects using a fetch specification.
     *
     * @param context The editing context to use
     * @param fspec The fetch specification to use
     *
     * @return an NSArray of the entities retrieved
     */
    @SuppressWarnings("unchecked")
    public static NSArray<GeneratedReport> objectsWithFetchSpecification(
        EOEditingContext context,
        EOFetchSpecification fspec)
    {
        return context.objectsWithFetchSpecification(fspec);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve all objects of this type.
     *
     * @param context The editing context to use
     *
     * @return an NSArray of the entities retrieved
     */
    @SuppressWarnings("unchecked")
    public static NSArray<GeneratedReport> allObjects(
        EOEditingContext context)
    {
        return objectsMatchingQualifier(context, null, null);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve objects using a qualifier.
     *
     * @param context The editing context to use
     * @param qualifier The qualifier to use
     *
     * @return an NSArray of the entities retrieved
     */
    @SuppressWarnings("unchecked")
    public static NSArray<GeneratedReport> objectsMatchingQualifier(
        EOEditingContext context,
        EOQualifier qualifier)
    {
        return objectsMatchingQualifier(context, qualifier, null);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve objects using a qualifier and sort orderings.
     *
     * @param context The editing context to use
     * @param qualifier The qualifier to use
     * @param sortOrderings The sort orderings to use
     *
     * @return an NSArray of the entities retrieved
     */
    @SuppressWarnings("unchecked")
    public static NSArray<GeneratedReport> objectsMatchingQualifier(
        EOEditingContext context,
        EOQualifier qualifier,
        NSArray<EOSortOrdering> sortOrderings)
    {
        EOFetchSpecification fspec = new EOFetchSpecification(
            ENTITY_NAME, qualifier, sortOrderings);

        return objectsWithFetchSpecification(context, fspec);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve objects using a list of keys and values to match.
     *
     * @param context The editing context to use
     * @param keysAndValues a list of keys and values to match, alternating
     *     "key", "value", "key", "value"...
     *
     * @return an NSArray of the entities retrieved
     */
    @SuppressWarnings("unchecked")
    public static NSArray<GeneratedReport> objectsMatchingValues(
        EOEditingContext context,
        Object... keysAndValues)
    {
        if (keysAndValues.length % 2 != 0)
        {
            throw new IllegalArgumentException("There should a value " +
                "corresponding to every key that was passed.");
        }

        NSMutableDictionary<String, Object> valueDictionary =
            new NSMutableDictionary<String, Object>();

        for (int i = 0; i < keysAndValues.length; i += 2)
        {
            Object key = keysAndValues[i];
            Object value = keysAndValues[i + 1];

            if (!(key instanceof String))
            {
                throw new IllegalArgumentException("Keys should be strings.");
            }

            valueDictionary.setObjectForKey(value, key);
        }

        return objectsMatchingValues(context, valueDictionary);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve objects using a dictionary of keys and values to match.
     *
     * @param context The editing context to use
     * @param keysAndValues a dictionary of keys and values to match
     *
     * @return an NSArray of the entities retrieved
     */
    @SuppressWarnings("unchecked")
    public static NSArray<GeneratedReport> objectsMatchingValues(
        EOEditingContext context,
        NSDictionary<String, Object> keysAndValues)
    {
        return EOUtilities.objectsMatchingValues(context, ENTITY_NAME,
            keysAndValues);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve a single object using a list of keys and values to match.
     *
     * @param context The editing context to use
     * @param keysAndValues a list of keys and values to match, alternating
     *     "key", "value", "key", "value"...
     *
     * @return the single entity that was retrieved
     *
     * @throws EOObjectNotAvailableException
     *     if there is no matching object
     * @throws EOUtilities.MoreThanOneException
     *     if there is more than one matching object
     */
    @SuppressWarnings("unchecked")
    public static GeneratedReport objectMatchingValues(
        EOEditingContext context,
        Object... keysAndValues) throws EOObjectNotAvailableException,
                                        EOUtilities.MoreThanOneException
    {
        if (keysAndValues.length % 2 != 0)
        {
            throw new IllegalArgumentException("There should a value " +
                "corresponding to every key that was passed.");
        }

        NSMutableDictionary<String, Object> valueDictionary =
            new NSMutableDictionary<String, Object>();

        for (int i = 0; i < keysAndValues.length; i += 2)
        {
            Object key = keysAndValues[i];
            Object value = keysAndValues[i + 1];

            if (!(key instanceof String))
            {
                throw new IllegalArgumentException("Keys should be strings.");
            }

            valueDictionary.setObjectForKey(value, key);
        }

        return objectMatchingValues(context, valueDictionary);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve an object using a dictionary of keys and values to match.
     *
     * @param context The editing context to use
     * @param keysAndValues a dictionary of keys and values to match
     *
     * @return the single entity that was retrieved
     *
     * @throws EOObjectNotAvailableException
     *     if there is no matching object
     * @throws EOUtilities.MoreThanOneException
     *     if there is more than one matching object
     */
    @SuppressWarnings("unchecked")
    public static GeneratedReport objectMatchingValues(
        EOEditingContext context,
        NSDictionary<String, Object> keysAndValues)
        throws EOObjectNotAvailableException,
               EOUtilities.MoreThanOneException
    {
        return (GeneratedReport) EOUtilities.objectMatchingValues(
            context, ENTITY_NAME, keysAndValues);
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
    public static NSArray<GeneratedReport> objectsForUser(
            EOEditingContext context,
            net.sf.webcat.core.User userBinding
        )
    {
        EOFetchSpecification spec = EOFetchSpecification
            .fetchSpecificationNamed( "user", "GeneratedReport" );

        NSMutableDictionary<String, Object> bindings =
            new NSMutableDictionary<String, Object>();

        if ( userBinding != null )
        {
            bindings.setObjectForKey( userBinding,
                                      "user" );
        }
        spec = spec.fetchSpecificationWithQualifierBindings( bindings );

        NSArray<GeneratedReport> result = objectsWithFetchSpecification( context, spec );
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
     * Retrieve object according to the <code>UserCompleteReports</code>
     * fetch specification.
     *
     * @param context The editing context to use
     * @param userBinding fetch spec parameter
     * @return an NSArray of the entities retrieved
     */
    public static NSArray<GeneratedReport> objectsForUserCompleteReports(
            EOEditingContext context,
            net.sf.webcat.core.User userBinding
        )
    {
        EOFetchSpecification spec = EOFetchSpecification
            .fetchSpecificationNamed( "userCompleteReports", "GeneratedReport" );

        NSMutableDictionary<String, Object> bindings =
            new NSMutableDictionary<String, Object>();

        if ( userBinding != null )
        {
            bindings.setObjectForKey( userBinding,
                                      "user" );
        }
        spec = spec.fetchSpecificationWithQualifierBindings( bindings );

        NSArray<GeneratedReport> result = objectsWithFetchSpecification( context, spec );
        if (log.isDebugEnabled())
        {
            log.debug( "objectsForUserCompleteReports(ec"
                + ", " + userBinding
                + "): " + result );
        }
        return result;
    }


    // ----------------------------------------------------------
    /**
     * Retrieve object according to the <code>UserIncompleteReports</code>
     * fetch specification.
     *
     * @param context The editing context to use
     * @param userBinding fetch spec parameter
     * @return an NSArray of the entities retrieved
     */
    public static NSArray<GeneratedReport> objectsForUserIncompleteReports(
            EOEditingContext context,
            net.sf.webcat.core.User userBinding
        )
    {
        EOFetchSpecification spec = EOFetchSpecification
            .fetchSpecificationNamed( "userIncompleteReports", "GeneratedReport" );

        NSMutableDictionary<String, Object> bindings =
            new NSMutableDictionary<String, Object>();

        if ( userBinding != null )
        {
            bindings.setObjectForKey( userBinding,
                                      "user" );
        }
        spec = spec.fetchSpecificationWithQualifierBindings( bindings );

        NSArray<GeneratedReport> result = objectsWithFetchSpecification( context, spec );
        if (log.isDebugEnabled())
        {
            log.debug( "objectsForUserIncompleteReports(ec"
                + ", " + userBinding
                + "): " + result );
        }
        return result;
    }


    // ----------------------------------------------------------
    /**
     * Produce a string representation of this object.  This implementation
     * calls UserPresentableDescription(), which uses WebObjects' internal
     * mechanism to print out the visible fields of this object.  Normally,
     * subclasses would override userPresentableDescription() to change
     * the way the object is printed.
     *
     * @return A string representation of the object's value
     */
    public String toString()
    {
        return userPresentableDescription();
    }


    //~ Instance/static variables .............................................

    static Logger log = Logger.getLogger( GeneratedReport.class );
}
