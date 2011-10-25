/*==========================================================================*\
 |  _ReportDataSet.java
 |*-------------------------------------------------------------------------*|
 |  Created by eogenerator
 |  DO NOT EDIT.  Make changes to ReportDataSet.java instead.
 |*-------------------------------------------------------------------------*|
 |  Copyright (C) 2006-2010 Virginia Tech
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

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import er.extensions.eof.ERXEOControlUtilities;
import er.extensions.eof.ERXKey;
import org.apache.log4j.Logger;
import org.webcat.core.EOBasedKeyGenerator;
import org.webcat.woextensions.WCFetchSpecification;

// -------------------------------------------------------------------------
/**
 * An automatically generated EOGenericRecord subclass.  DO NOT EDIT.
 * To change, use EOModeler, or make additions in
 * ReportDataSet.java.
 *
 * @author Generated by eogenerator
 * @version version suppressed to control auto-generation
 */
public abstract class _ReportDataSet
    extends er.extensions.eof.ERXGenericRecord
    implements org.webcat.core.MutableContainer.MutableContainerOwner
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new _ReportDataSet object.
     */
    public _ReportDataSet()
    {
        super();
    }


    // ----------------------------------------------------------
    /**
     * A static factory method for creating a new
     * ReportDataSet object given required
     * attributes and relationships.
     * @param editingContext The context in which the new object will be
     * inserted
     * @param updateMutableFieldsValue
     * @return The newly created object
     */
    public static ReportDataSet create(
        EOEditingContext editingContext,
        boolean updateMutableFieldsValue
        )
    {
        ReportDataSet eoObject = (ReportDataSet)
            EOUtilities.createAndInsertInstance(
                editingContext,
                _ReportDataSet.ENTITY_NAME);
        eoObject.setUpdateMutableFields(updateMutableFieldsValue);
        return eoObject;
    }


    // ----------------------------------------------------------
    /**
     * Get a local instance of the given object in another editing context.
     * @param editingContext The target editing context
     * @param eo The object to import
     * @return An instance of the given object in the target editing context
     */
    public static ReportDataSet localInstance(
        EOEditingContext editingContext, ReportDataSet eo)
    {
        return (eo == null)
            ? null
            : (ReportDataSet)EOUtilities.localInstanceOfObject(
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
    public static ReportDataSet forId(
        EOEditingContext ec, int id)
    {
        ReportDataSet obj = null;
        if (id > 0)
        {
            NSArray<ReportDataSet> objects =
                objectsMatchingValues(ec, "id", new Integer(id));
            if (objects != null && objects.count() > 0)
            {
                obj = objects.objectAtIndex(0);
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
    public static ReportDataSet forId(
        EOEditingContext ec, String id)
    {
        return forId(ec, er.extensions.foundation.ERXValueUtilities.intValue(id));
    }


    //~ Constants (for key names) .............................................

    // Attributes ---
    public static final String CONSTRAINTS_KEY = "constraints";
    public static final ERXKey<NSData> constraints =
        new ERXKey<NSData>(CONSTRAINTS_KEY);
    public static final String DESCRIPTION_KEY = "description";
    public static final ERXKey<String> description =
        new ERXKey<String>(DESCRIPTION_KEY);
    public static final String NAME_KEY = "name";
    public static final ERXKey<String> name =
        new ERXKey<String>(NAME_KEY);
    public static final String REFERENCE_COUNT_KEY = "referenceCount";
    public static final ERXKey<Integer> referenceCount =
        new ERXKey<Integer>(REFERENCE_COUNT_KEY);
    public static final String UPDATE_MUTABLE_FIELDS_KEY = "updateMutableFields";
    public static final ERXKey<Integer> updateMutableFields =
        new ERXKey<Integer>(UPDATE_MUTABLE_FIELDS_KEY);
    public static final String WC_ENTITY_NAME_KEY = "wcEntityName";
    public static final ERXKey<String> wcEntityName =
        new ERXKey<String>(WC_ENTITY_NAME_KEY);
    // To-one relationships ---
    public static final String REPORT_TEMPLATE_KEY = "reportTemplate";
    public static final ERXKey<org.webcat.reporter.ReportTemplate> reportTemplate =
        new ERXKey<org.webcat.reporter.ReportTemplate>(REPORT_TEMPLATE_KEY);
    // To-many relationships ---
    // Fetch specifications ---
    public static final String ENTITY_NAME = "ReportDataSet";

    public transient final EOBasedKeyGenerator generateKey =
        new EOBasedKeyGenerator(this);


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Get a local instance of this object in another editing context.
     * @param editingContext The target editing context
     * @return An instance of this object in the target editing context
     */
    public ReportDataSet localInstance(EOEditingContext editingContext)
    {
        return (ReportDataSet)EOUtilities.localInstanceOfObject(
            editingContext, this);
    }


    // ----------------------------------------------------------
    /**
     * Get a list of changes between this object's current state and the
     * last committed version.
     * @return a dictionary of the changes that have not yet been committed
     */
    @SuppressWarnings("unchecked")
    public NSDictionary<String, Object> changedProperties()
    {
        return changesFromSnapshot(
            editingContext().committedSnapshotForObject(this));
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
                editingContext() , this).objectForKey("id");
        }
        catch (Exception e)
        {
            return er.extensions.eof.ERXConstant.ZeroInteger;
        }
    }

    //-- Local mutable cache --
    private org.webcat.core.MutableArray constraintsCache;
    private NSData constraintsRawCache;

    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>constraints</code> value.
     * @return the value of the attribute
     */
    public org.webcat.core.MutableArray constraints()
    {
        NSData dbValue =
            (NSData)storedValueForKey("constraints");
        if (constraintsRawCache != dbValue)
        {
            if (dbValue != null && dbValue.equals( constraintsRawCache))
            {
                // They are still equal, so just update the raw cache
                constraintsRawCache = dbValue;
            }
            else
            {
                // Underlying attribute may have changed because
                // of a concurrent update through another editing
                // context, so throw away current values.
                constraintsRawCache = dbValue;
                org.webcat.core.MutableArray newValue =
                    org.webcat.core.MutableArray
                    .objectWithArchiveData( dbValue );
                if ( constraintsCache != null )
                {
                    constraintsCache.copyFrom( newValue );
                }
                else
                {
                    constraintsCache = newValue;
                }
                constraintsCache.setOwner( this );
                setUpdateMutableFields( true );
            }
        }
        else if ( dbValue == null && constraintsCache == null )
        {
            constraintsCache =
                org.webcat.core.MutableArray
                .objectWithArchiveData( dbValue );
             constraintsCache.setOwner( this );
             setUpdateMutableFields( true );
        }
        return constraintsCache;
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>constraints</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setConstraints( org.webcat.core.MutableArray value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setConstraints("
                + value + ")" );
        }
        if ( constraintsCache == null )
        {
            constraintsCache = value;
            value.setHasChanged( false );
            constraintsRawCache = value.archiveData();
            takeStoredValueForKey( constraintsRawCache, "constraints" );
        }
        else if ( constraintsCache != value )  // ( constraintsCache != null )
        {
            constraintsCache.copyFrom( value );
            setUpdateMutableFields( true );
        }
        else  // ( constraintsCache == non-null value )
        {
            // no nothing
        }
    }


    // ----------------------------------------------------------
    /**
     * Clear the value of this object's <code>constraints</code>
     * property.
     */
    public void clearConstraints()
    {
        if (log.isDebugEnabled())
        {
            log.debug( "clearConstraints()" );
        }
        takeStoredValueForKey( null, "constraints" );
        constraintsRawCache = null;
        constraintsCache = null;
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
     * Retrieve this object's <code>name</code> value.
     * @return the value of the attribute
     */
    public String name()
    {
        return (String)storedValueForKey( "name" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>name</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setName( String value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setName("
                + value + "): was " + name() );
        }
        takeStoredValueForKey( value, "name" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>referenceCount</code> value.
     * @return the value of the attribute
     */
    public int referenceCount()
    {
        Integer returnValue =
            (Integer)storedValueForKey( "referenceCount" );
        return ( returnValue == null )
            ? 0
            : returnValue.intValue();
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>referenceCount</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setReferenceCount( int value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setReferenceCount("
                + value + "): was " + referenceCount() );
        }
        Integer actual =
            er.extensions.eof.ERXConstant.integerForInt( value );
            setReferenceCountRaw( actual );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>referenceCount</code> value.
     * @return the value of the attribute
     */
    public Integer referenceCountRaw()
    {
        return (Integer)storedValueForKey( "referenceCount" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>referenceCount</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setReferenceCountRaw( Integer value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setReferenceCountRaw("
                + value + "): was " + referenceCountRaw() );
        }
        takeStoredValueForKey( value, "referenceCount" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>updateMutableFields</code> value.
     * @return the value of the attribute
     */
    public boolean updateMutableFields()
    {
        Integer returnValue =
            (Integer)storedValueForKey( "updateMutableFields" );
        return ( returnValue == null )
            ? false
            : ( returnValue.intValue() > 0 );
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
     * Retrieve this object's <code>wcEntityName</code> value.
     * @return the value of the attribute
     */
    public String wcEntityName()
    {
        return (String)storedValueForKey( "wcEntityName" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>wcEntityName</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setWcEntityName( String value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setWcEntityName("
                + value + "): was " + wcEntityName() );
        }
        takeStoredValueForKey( value, "wcEntityName" );
    }


    // ----------------------------------------------------------
    /**
     * Called just before this object is saved to the database.
     */
    public void saveMutables()
    {
        log.debug("saveMutables()");
        if ( constraintsCache != null
            && constraintsCache.hasChanged() )
        {
            constraintsRawCache = constraintsCache.archiveData();
            takeStoredValueForKey( constraintsRawCache, "constraints" );
            constraintsCache.setHasChanged( false );
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
        constraintsCache = null;
        constraintsRawCache  = null;
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
    public org.webcat.reporter.ReportTemplate reportTemplate()
    {
        return (org.webcat.reporter.ReportTemplate)storedValueForKey( "reportTemplate" );
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
    public void setReportTemplate( org.webcat.reporter.ReportTemplate value )
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
        org.webcat.reporter.ReportTemplate value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setReportTemplateRelationship("
                + value + "): was " + reportTemplate() );
        }
        if ( value == null )
        {
            org.webcat.reporter.ReportTemplate object = reportTemplate();
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
     * Retrieve objects using a fetch specification.
     *
     * @param context The editing context to use
     * @param fspec The fetch specification to use
     *
     * @return an NSArray of the entities retrieved
     */
    @SuppressWarnings("unchecked")
    public static NSArray<ReportDataSet> objectsWithFetchSpecification(
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
    public static NSArray<ReportDataSet> allObjects(
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
    public static NSArray<ReportDataSet> objectsMatchingQualifier(
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
    public static NSArray<ReportDataSet> objectsMatchingQualifier(
        EOEditingContext context,
        EOQualifier qualifier,
        NSArray<EOSortOrdering> sortOrderings)
    {
        @SuppressWarnings("unchecked")
        EOFetchSpecification fspec = new WCFetchSpecification(
                ENTITY_NAME, qualifier, sortOrderings);
        fspec.setUsesDistinct(true);
        return objectsWithFetchSpecification(context, fspec);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the first object that matches a qualifier, when
     * sorted with the specified sort orderings.
     *
     * @param context The editing context to use
     * @param qualifier The qualifier to use
     * @param sortOrderings the sort orderings
     *
     * @return the first entity that was retrieved, or null if there was none
     */
    public static ReportDataSet firstObjectMatchingQualifier(
        EOEditingContext context,
        EOQualifier qualifier,
        NSArray<EOSortOrdering> sortOrderings)
    {
        NSArray<ReportDataSet> objects =
            objectsMatchingQualifier(context, qualifier, sortOrderings);
        return (objects.size() > 0)
            ? objects.get(0)
            : null;
    }


    // ----------------------------------------------------------
    /**
     * Retrieve a single object using a list of keys and values to match.
     *
     * @param context The editing context to use
     * @param qualifier The qualifier to use
     *
     * @return the single entity that was retrieved
     *
     * @throws EOUtilities.MoreThanOneException
     *     if there is more than one matching object
     */
    public static ReportDataSet uniqueObjectMatchingQualifier(
        EOEditingContext context,
        EOQualifier qualifier) throws EOUtilities.MoreThanOneException
    {
        NSArray<ReportDataSet> objects =
            objectsMatchingQualifier(context, qualifier);
        if (objects.size() > 1)
        {
            throw new EOUtilities.MoreThanOneException(null);
        }
        return (objects.size() > 0)
            ? objects.get(0)
            : null;
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
    public static NSArray<ReportDataSet> objectsMatchingValues(
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

            valueDictionary.setObjectForKey(value, (String)key);
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
    public static NSArray<ReportDataSet> objectsMatchingValues(
        EOEditingContext context,
        NSDictionary<String, Object> keysAndValues)
    {
        return EOUtilities.objectsMatchingValues(context, ENTITY_NAME,
            keysAndValues);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the first object that matches a set of keys and values, when
     * sorted with the specified sort orderings.
     *
     * @param context The editing context to use
     * @param sortOrderings the sort orderings
     * @param keysAndValues a list of keys and values to match, alternating
     *     "key", "value", "key", "value"...
     *
     * @return the first entity that was retrieved, or null if there was none
     */
    public static ReportDataSet firstObjectMatchingValues(
        EOEditingContext context,
        NSArray<EOSortOrdering> sortOrderings,
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

            valueDictionary.setObjectForKey(value, (String)key);
        }

        return firstObjectMatchingValues(
            context, sortOrderings, valueDictionary);
    }


    // ----------------------------------------------------------
    /**
     * Retrieves the first object that matches a set of keys and values, when
     * sorted with the specified sort orderings.
     *
     * @param context The editing context to use
     * @param sortOrderings the sort orderings
     * @param keysAndValues a dictionary of keys and values to match
     *
     * @return the first entity that was retrieved, or null if there was none
     */
    public static ReportDataSet firstObjectMatchingValues(
        EOEditingContext context,
        NSArray<EOSortOrdering> sortOrderings,
        NSDictionary<String, Object> keysAndValues)
    {
        @SuppressWarnings("unchecked")
        EOFetchSpecification fspec = new WCFetchSpecification(
                ENTITY_NAME,
                EOQualifier.qualifierToMatchAllValues(keysAndValues),
                sortOrderings);
        fspec.setFetchLimit(1);

        NSArray<ReportDataSet> objects =
            objectsWithFetchSpecification( context, fspec );

        if ( objects.count() == 0 )
        {
            return null;
        }
        else
        {
            return objects.objectAtIndex(0);
        }
    }


    // ----------------------------------------------------------
    /**
     * Retrieve a single object using a list of keys and values to match.
     *
     * @param context The editing context to use
     * @param keysAndValues a list of keys and values to match, alternating
     *     "key", "value", "key", "value"...
     *
     * @return the single entity that was retrieved, or null if there was none
     *
     * @throws EOUtilities.MoreThanOneException
     *     if there is more than one matching object
     */
    public static ReportDataSet uniqueObjectMatchingValues(
        EOEditingContext context,
        Object... keysAndValues) throws EOUtilities.MoreThanOneException
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

            valueDictionary.setObjectForKey(value, (String)key);
        }

        return uniqueObjectMatchingValues(context, valueDictionary);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve an object using a dictionary of keys and values to match.
     *
     * @param context The editing context to use
     * @param keysAndValues a dictionary of keys and values to match
     *
     * @return the single entity that was retrieved, or null if there was none
     *
     * @throws EOUtilities.MoreThanOneException
     *     if there is more than one matching object
     */
    public static ReportDataSet uniqueObjectMatchingValues(
        EOEditingContext context,
        NSDictionary<String, Object> keysAndValues)
        throws EOUtilities.MoreThanOneException
    {
        try
        {
            return (ReportDataSet)EOUtilities.objectMatchingValues(
                context, ENTITY_NAME, keysAndValues);
        }
        catch (EOObjectNotAvailableException e)
        {
            return null;
        }
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the count of all objects of this type.
     *
     * @param context The editing context to use
     *
     * @return the count of all objects
     */
    public static int countOfAllObjects(EOEditingContext context)
    {
        return countOfObjectsMatchingQualifier(context, null);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the count of objects that match a qualifier.
     *
     * @param context The editing context to use
     * @param qualifier The qualifier to use
     *
     * @return the count of objects matching the qualifier
     */
    public static int countOfObjectsMatchingQualifier(
        EOEditingContext context, EOQualifier qualifier)
    {
        return ERXEOControlUtilities.objectCountWithQualifier(
                context, ENTITY_NAME, qualifier);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the count of objects using a list of keys and values to match.
     *
     * @param context The editing context to use
     * @param keysAndValues a list of keys and values to match, alternating
     *     "key", "value", "key", "value"...
     *
     * @return the count of objects that match the specified values
     */
    public static int countOfObjectsMatchingValues(
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

            valueDictionary.setObjectForKey(value, (String)key);
        }

        return countOfObjectsMatchingValues(context, valueDictionary);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the count of objects using a dictionary of keys and values to
     * match.
     *
     * @param context The editing context to use
     * @param keysAndValues a dictionary of keys and values to match
     *
     * @return the count of objects that matched the specified values
     */
    public static int countOfObjectsMatchingValues(
        EOEditingContext context,
        NSDictionary<String, Object> keysAndValues)
    {
        return countOfObjectsMatchingQualifier(context,
                EOQualifier.qualifierToMatchAllValues(keysAndValues));
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

    static Logger log = Logger.getLogger(ReportDataSet.class);
}
