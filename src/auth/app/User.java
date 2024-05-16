package auth.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user in the authentication system.
 */
class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private List<Course> courses;

    /**
     * Constructs a new User object with the specified username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.courses = new ArrayList<>();
    }

    /**
     * Gets the username of the user.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Checks if the provided password matches the user's password.
     *
     * @param password The password to check.
     * @return True if the password matches, false otherwise.
     */
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    /**
     * Adds a course to the user's list of courses.
     *
     * @param course The course to add.
     */
    public void addCourse(Course course) {
        courses.add(course);
    }

    /**
     * Removes a course from the user's list of courses.
     *
     * @param course The course to remove.
     */
    public void removeCourse(Course course) {
        courses.remove(course);
    }

    /**
     * Gets the list of courses associated with the user.
     *
     * @return The list of courses.
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Returns a string representation of the user.
     *
     * @return The string representation of the user.
     */
    @Override
    public String toString() {
        return "\nUser: " + username + ", Courses: " + courses;
    }
}