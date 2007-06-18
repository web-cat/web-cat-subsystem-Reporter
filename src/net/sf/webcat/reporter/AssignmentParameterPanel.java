package net.sf.webcat.reporter;
// Generated by the WOLips Templateengine Plug-in at Feb 27, 2007 4:47:53 PM

import net.sf.webcat.grader.Assignment;

import com.webobjects.appserver.*;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;

public class AssignmentParameterPanel extends AbstractChooseTypePanel
{
	public NSArray assignments;

	public NSMutableArray selectedAssignments;

	// Used during repetition when generating the visual list
	public Assignment assignment;

    public AssignmentParameterPanel(WOContext context)
    {
        super(context);
    }

    public void appendToResponse(WOResponse response, WOContext context)
    {
    	assignments = getChoicesBasedOnDependents();

        selectedAssignments = new NSMutableArray();

    	super.appendToResponse(response, context);
    }

    public boolean assignmentSelected()
    {
    	return selectedAssignments.containsObject(assignment);
    }
    
    public void setAssignmentSelected(boolean selected)
    {
    	if(selected)
    	{
    		if(!selectedAssignments.containsObject(assignment))
    			selectedAssignments.addObject(assignment);
    	}
    	else
    	{
    		if(selectedAssignments.containsObject(assignment))
    			selectedAssignments.removeObject(assignment);
    	}
    }

    public boolean allAssignmentsSelected()
    {
    	return (selectedAssignments.count() == assignments.count());
    }
    
    public Object currentSelection()
    {
    	if(parameter.multipleSelectionOption())
    	{
    		return selectedAssignments;
    	}
    	else
    	{
    		// return the single selected course
    		return null;
    	}
    }
}