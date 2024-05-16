package auth.app;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents an online education platform with user authentication and course management.
 */
public class OnlineEducationPlatform
{
    /** The file path for storing user data. */
    private static final String DATA_FILE = "users.ser";
    /** A map that stores usernames mapped to User objects. */
    static Map<String, User> users = new HashMap<>();
    /**
     * The main entry point of the application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args)
    {
        loadUsers();
        Scanner scanner = new Scanner(System.in);
        mainMenu(scanner);
    }
    /**
     * Gets the map of users.
     *
     * @return The map of users.
     */
    public static Map<String, User> getUsers()
    {
        return users;
    }
    /**
     * Displays the main menu options and handles user input.
     *
     * @param scanner The scanner object for user input.
     */
    private static void mainMenu(Scanner scanner)
    {
        while (true)
        {
            System.out.println("\n1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("\nSelect an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice)
            {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    saveUsers();
                    System.out.println("\nExiting...");
                    return;
                default:
                    System.out.println("\nInvalid option. Try again.");
            }
        }
    }
    /**
     * Allows users to create an account by providing a username and password.
     *
     * @param scanner The scanner object for user input.
     */
    public static void createAccount(Scanner scanner)
    {
        System.out.print("\nEnter username: ");
        String username = scanner.nextLine().trim();
        if (username.isEmpty())
        {
            System.out.println("\nUsername cannot be empty.");
            return;
        }
        if (users.containsKey(username))
        {
            System.out.println("\nUsername already exists.");
            return;
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        if (password.isEmpty())
        {
            System.out.println("\nPassword cannot be empty.");
            return;
        }
        User user = new User(username, password);
        users.put(username, user);
        System.out.println("\nAccount created successfully.");
        userMenu(scanner, user);
    }
    /**
     * Allows users to log in by providing their username and password.
     *
     * @param scanner The scanner object for user input.
     */
    public static void login(Scanner scanner)
    {
        System.out.print("\nEnter username: ");
        String username = scanner.nextLine();
        if (users.get(username) != null)
        {
            System.out.print("\nEnter password: ");
            String password = scanner.nextLine();
            User user = users.get(username);
            if (user.checkPassword(password))
            {
                System.out.println("\nLogin successful.");
                userMenu(scanner, user);
            }
            else
            {
                System.out.println("\nInvalid credentials.");
            }
        }
        else
        {
            System.out.println("\nUser not found.");
        }
    }
    /**
     * Displays the user menu options and handles user input.
     *
     * @param scanner The scanner object for user input.
     * @param user    The logged-in user.
     */
    private static void userMenu(Scanner scanner, User user)
    {
        boolean exit = false;
        while (!exit)
        {
            System.out.println("\n1. Open my courses");
            System.out.println("2. Check Profile");
            System.out.println("3. Logout");
            System.out.print("\nSelect an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice)
            {
                case 1:
                    checkCourses(scanner, user);
                    exit = true;
                    break;
                case 2:
                    checkProfile(scanner, user);
                    exit = true;
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("\nInvalid option. Try again.");
            }
        }
    }
    /**
     * Displays the courses associated with the user and handles course-related actions.
     *
     * @param scanner The scanner object for user input.
     * @param user    The logged-in user.
     */
    private static void checkCourses(Scanner scanner, User user)
    {
        System.out.println("\nMy courses:");
        if (user.getCourses().isEmpty())
        {
            System.out.println("\nNo courses found.\n");
        }
        else
        {
            for (Course course : user.getCourses())
            {
                System.out.println(course.getCourseName());
            }
            System.out.println("\n1. Open course");
        }
        System.out.println("2. Join course");
        System.out.println("3. Back");
        System.out.print("\nSelect an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice)
        {
            case 1:
                if (user.getCourses().isEmpty())
                {
                    checkCourses(scanner, user);
                    break;
                }
                else
                {
                    System.out.print("\nEnter course name: ");
                    String courseName = scanner.nextLine();
                    Course courseToOpen = null;
                    for (Course course : user.getCourses())
                    {
                        if (course.getCourseName().equals(courseName))
                        {
                            courseToOpen = course;
                        }
                        else
                        {
                            System.out.println("\nCourse not found.");
                            checkCourses(scanner, user);
                        }
                    }
                    courseMenu(scanner, user, courseToOpen);
                    break;
                }
            case 2:
                joinCourse(scanner, user);
                break;
            case 3:
                userMenu(scanner, user);
                break;
            default:
                System.out.println("\nInvalid option. Try again.");
        }
    }
    /**
     * Displays the menu options for a specific course and handles course-related actions.
     *
     * @param scanner The scanner object for user input.
     * @param user    The logged-in user.
     * @param course  The course to manage.
     */
    private static void courseMenu(Scanner scanner, User user, Course course)
    {
        System.out.println("\n1. Leave course");
        System.out.println("2. View assignments");
        System.out.println("3. Back");
        System.out.println("\nSelect an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice)
        {
            case 1:
                leaveCourse(scanner, user, course);
                break;
            case 2:
                viewAssignments(scanner, user, course);
                break;
            case 3:
                checkCourses(scanner, user);
                break;
            default:
                System.out.println("\nInvalid option. Try again.");
        }
    }
    /**
     * Allows users to join a course.
     *
     * @param scanner The scanner object for user input.
     * @param user    The logged-in user.
     */
    private static void joinCourse(Scanner scanner, User user)
    {
        System.out.print("\nEnter course name: ");
        String courseName = scanner.nextLine();
        Course course = new Course(courseName);
        user.addCourse(course);
        System.out.println("\nCourse joined successfully.");
        courseMenu(scanner, user, course);
    }
    /**
     * Allows users to leave a course.
     *
     * @param scanner The scanner object for user input.
     * @param user    The logged-in user.
     * @param course  The course to leave.
     */
    private static void leaveCourse(Scanner scanner, User user, Course course)
    {
        if (course != null)
        {
            user.getCourses().remove(course);
            System.out.println("\nCourse left successfully.");
        }
        else
        {
            System.out.println("\nCourse not found.");
        }
        checkCourses(scanner, user);
    }
    /**
     * Displays the assignments of a course.
     *
     * @param scanner The scanner object for user input.
     * @param user    The logged-in user.
     * @param course  The course to view assignments for.
     */
    private static void viewAssignments(Scanner scanner, User user, Course course)
    {
        System.out.println("\nAssignments: ");
        if (course.getAssignments().isEmpty())
        {
            System.out.println("\nNo assignments found.");
        }
        else
        {
            for (Assignment assignment : course.getAssignments())
            {
                System.out.println(assignment.getAssignmentName());
            }
        }
        courseMenu(scanner, user, course);
    }
    /**
     * Displays the user's profile information and handles profile-related actions.
     *
     * @param scanner The scanner object for user input.
     * @param user    The logged-in user.
     */
    private static void checkProfile(Scanner scanner, User user)
    {
        System.out.println(user);
        System.out.println("\n1. Delete profile");
        System.out.println("2. Back");
        System.out.print("\nSelect an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice)
        {
            case 1:
                deleteProfile(scanner, user);
                break;
            case 2:
                userMenu(scanner, user);
                break;
            default:
                System.out.println("\nInvalid option. Try again.");
        }
    }
    /**
     * Allows users to delete their profile.
     *
     * @param scanner The scanner object for user input.
     * @param user    The logged-in user.
     */
    private static void deleteProfile(Scanner scanner, User user)
    {
        users.remove(user.getUsername());
        saveUsers();
        System.out.println("\nProfile deleted.");
    }
    /**
     * Loads user data from the data file.
     */
    @SuppressWarnings("unchecked")
    private static void loadUsers()
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE)))
        {
            users = (Map<String, User>) ois.readObject();
        }
        catch (IOException e)
        {
            System.err.println("\nError loading users: " + e.getMessage());
            users = new HashMap<>();
        }
        catch (ClassNotFoundException e)
        {
            System.err.println("\nError loading users: Class not found - " + e.getMessage());
            users = new HashMap<>();
        }
    }
    /**
     * Saves user data to the data file.
     */
    private static void saveUsers()
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE)))
        {
            oos.writeObject(users);
        }
        catch (IOException e)
        {
            System.err.println("\nError saving users: " + e.getMessage());
        }
    }
    /**
     * Clears all user data.
     */
    public static void clearUsers()
    {
        users.clear();
        saveUsers();
    }
}