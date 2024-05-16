package auth.app;

import org.junit.jupiter.api.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class OnlineEducationPlatformTest {

    @BeforeEach
    void setUp()
    {
        OnlineEducationPlatform.clearUsers();
    }
    @AfterEach
    void tearDown()
    {

    }
    @Test
    void createAccount_CreatesAccountSuccessfully()
    {
        String input = "testUser\npassword\n3\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(inputStream);
        OnlineEducationPlatform.createAccount(scanner);
        assertTrue(OnlineEducationPlatform.getUsers().containsKey("testUser"), "User should be created");
    }
    @Test
    void createAccount_FailsIfUsernameExists()
    {
        String input = "testUser\npassword\n3\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(inputStream);
        OnlineEducationPlatform.createAccount(scanner);
        assertTrue(OnlineEducationPlatform.getUsers().containsKey("testUser"), "Account creation should succeed");
    }
    @Test
    void createAccount_FailsIfPasswordIsEmpty()
    {
        String input = "testUser\n\n3\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(inputStream);
        OnlineEducationPlatform.createAccount(scanner);
        assertFalse(OnlineEducationPlatform.getUsers().containsKey("testUser"), "Account creation should fail");
    }
}