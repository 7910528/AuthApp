package auth.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a course in the online education platform.
 */
class Course implements Serializable {

    private static final long serialVersionUID = 1L;
    private String courseName;
    private List<Assignment> assignments;

    /**
     * Constructs a new Course object with the specified course name.
     *
     * @param courseName The name of the course.
     */
    public Course(String courseName) {
        this.courseName = courseName;
        this.assignments = new ArrayList<>();
    }

    /**
     * Gets the name of the course.
     *
     * @return The name of the course.
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Adds an assignment to the course's list of assignments.
     *
     * @param assignment The assignment to add.
     */
    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    /**
     * Removes an assignment from the course's list of assignments.
     *
     * @param assignment The assignment to remove.
     */
    public void removeAssignment(Assignment assignment) {
        assignments.remove(assignment);
    }

    /**
     * Gets the list of assignments associated with the course.
     *
     * @return The list of assignments.
     */
    public List<Assignment> getAssignments() {
        return assignments;
    }

    /**
     * Returns a string representation of the course.
     *
     * @return The string representation of the course.
     */
    @Override
    public String toString() {
        return courseName;
    }
}