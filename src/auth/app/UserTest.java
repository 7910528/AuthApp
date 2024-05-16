package auth.app;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UserTest
{
    @Test
    void getUsername_ReturnsCorrectUsername()
    {
        String expectedUsername = "testUser";
        String password = "testPassword";
        User user = new User(expectedUsername, password);
        String actualUsername = user.getUsername();
        assertEquals(expectedUsername, actualUsername);
    }
    @Test
    void checkPassword_CorrectPassword_ReturnsTrue()
    {
        String username = "testUser";
        String expectedPassword = "testPassword";
        User user = new User(username, expectedPassword);
        assertTrue(user.checkPassword(expectedPassword));
    }
    @Test
    void checkPassword_IncorrectPassword_ReturnsFalse()
    {
        String username = "testUser";
        String correctPassword = "testPassword";
        String incorrectPassword = "wrongPassword";
        User user = new User(username, correctPassword);
        assertFalse(user.checkPassword(incorrectPassword));
    }
    @Test
    void addCourse_AddsCourseToList()
    {
        String username = "testUser";
        String password = "testPassword";
        User user = new User(username, password);
        Course course = new Course("Test Course");
        user.addCourse(course);
        List<Course> courses = user.getCourses();
        assertTrue(courses.contains(course));
    }
    @Test
    void removeCourse_RemovesCourseFromList()
    {
        String username = "testUser";
        String password = "testPassword";
        User user = new User(username, password);
        Course course = new Course("Test Course");
        user.addCourse(course);
        user.removeCourse(course);
        List<Course> courses = user.getCourses();
        assertFalse(courses.contains(course));
    }
    @Test
    void getCourses_ReturnsListOfCourses()
    {
        String username = "testUser";
        String password = "testPassword";
        User user = new User(username, password);
        Course course1 = new Course("Test Course 1");
        Course course2 = new Course("Test Course 2");
        user.addCourse(course1);
        user.addCourse(course2);
        List<Course> courses = user.getCourses();
        assertEquals(2, courses.size());
        assertTrue(courses.contains(course1));
        assertTrue(courses.contains(course2));
    }
}