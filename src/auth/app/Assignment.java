package auth.app;

import java.io.Serializable;

/**
 * Represents an assignment in a course.
 */
class Assignment implements Serializable {

    private static final long serialVersionUID = 1L;
    private String assignmentName;

    /**
     * Constructs a new Assignment object with the specified assignment name.
     *
     * @param assignmentName The name of the assignment.
     */
    public Assignment(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    /**
     * Gets the name of the assignment.
     *
     * @return The name of the assignment.
     */
    public String getAssignmentName() {
        return assignmentName;
    }

    /**
     * Simulates opening the assignment by printing a message.
     */
    public void open() {
        System.out.println("Assignment " + assignmentName + " opened.");
    }

    /**
     * Simulates closing the assignment by printing a message.
     */
    public void close() {
        System.out.println("Assignment " + assignmentName + " closed.");
    }

    /**
     * Returns a string representation of the assignment.
     *
     * @return The string representation of the assignment.
     */
    @Override
    public String toString() {
        return assignmentName;
    }
}