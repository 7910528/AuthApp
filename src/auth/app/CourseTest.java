package auth.app;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CourseTest
{
    @Test
    void getCourseName_ReturnsCorrectCourseName()
    {
        String expectedCourseName = "Test Course";
        Course course = new Course(expectedCourseName);
        String actualCourseName = course.getCourseName();
        assertEquals(expectedCourseName, actualCourseName);
    }
    @Test
    void addAssignment_AddsAssignmentToList()
    {
        Course course = new Course("Test Course");
        Assignment assignment = new Assignment("Test Assignment");
        course.addAssignment(assignment);
        List<Assignment> assignments = course.getAssignments();
        assertTrue(assignments.contains(assignment));
    }
    @Test
    void removeAssignment_RemovesAssignmentFromList()
    {
        Course course = new Course("Test Course");
        Assignment assignment = new Assignment("Test Assignment");
        course.addAssignment(assignment);
        course.removeAssignment(assignment);
        List<Assignment> assignments = course.getAssignments();
        assertFalse(assignments.contains(assignment));
    }
    @Test
    void getAssignments_ReturnsListOfAssignments()
    {
        Course course = new Course("Test Course");
        Assignment assignment1 = new Assignment("Assignment 1");
        Assignment assignment2 = new Assignment("Assignment 2");
        course.addAssignment(assignment1);
        course.addAssignment(assignment2);
        List<Assignment> assignments = course.getAssignments();
        assertEquals(2, assignments.size());
        assertTrue(assignments.contains(assignment1));
        assertTrue(assignments.contains(assignment2));
    }
}