package auth.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AssignmentTest
{
    @Test
    void getAssignmentName_ReturnsCorrectAssignmentName()
    {
        String expectedAssignmentName = "Test Assignment";
        Assignment assignment = new Assignment(expectedAssignmentName);
        String actualAssignmentName = assignment.getAssignmentName();
        assertEquals(expectedAssignmentName, actualAssignmentName);
    }
    @Test
    void open_PrintsCorrectMessage()
    {
        String assignmentName = "Test Assignment";
        Assignment assignment = new Assignment(assignmentName);
        assertDoesNotThrow(() -> assignment.open());
    }
    @Test
    void close_PrintsCorrectMessage()
    {
        String assignmentName = "Test Assignment";
        Assignment assignment = new Assignment(assignmentName);
        assertDoesNotThrow(() -> assignment.close());
    }
}