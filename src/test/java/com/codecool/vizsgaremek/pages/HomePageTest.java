package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

@Story("User profile modifications")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HomePageTest {

    WebDriver driver;
    RegisterAndLoginPage registerAndLogin;
    HomePage homePage;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = WebDriverFactory.getWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        registerAndLogin = new RegisterAndLoginPage(driver);
        homePage = new HomePage(driver);
    }

    //test data for registration
    String user = "tester23";
    String pwd = "pwd123";
    String email = "tester23@gmail.com";
    String description = "test user";

    //Method for registration and login in incognito window
    public void regAndLogin() {
        registerAndLogin.navigateToUrl();
        registerAndLogin.acceptTermsAndConditions();
        registerAndLogin.registration(user, pwd, email, description);
        registerAndLogin.switchToLogin();
        registerAndLogin.login(user, pwd);
    }

    @Test
    @DisplayName("TC08 - Test edit a user's profile")
    @Order(1)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify the ability to edit profile information")
    void editProfileTest() {

        // Test data for edit profile
        String name = "Test Edit";
        String bio = "test bio";
        String phoneNumber = "02345678";

        // Register and login user
        regAndLogin();

        // Navigate to profile and edit profile information
        homePage.navigateToProfile();
        homePage.editProfile(name, bio, phoneNumber);

        String actualResult = homePage.validateProfileEdit();
        String expectedResult = "Profile Edited!";

        Assertions.assertEquals(expectedResult, actualResult, "Profile edit success message is not matching!");
    }

    @Test
    @DisplayName("TC09 - Test for logging out")
    @Order(2)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that logging out and redirecting to the landing page")
    void logoutTest() {

        // Register and login user
        regAndLogin();

        // Logout
        homePage.logout();
        String expectedResult = "https://lennertamas.github.io/roxo/index.html";
        String actualResult = driver.getCurrentUrl();

        Assertions.assertEquals(expectedResult, actualResult, "The actual URL does not match the expected URL after logout");
    }

    @Test
    @DisplayName("TC10 - Test delete an exist account")
    @Order(3)
    @Severity(SeverityLevel.NORMAL)
    @Description("This test case verifies if a user can successfully delete their account and the account is no longer accessible afterwards.")
    void deleteAccountTest() {

        // Register and login user
        regAndLogin();

        // Delete user's account
        homePage.navigateToProfile();
        homePage.deleteAccount();

        // Try to log in with the deleted account
        registerAndLogin.login(user, pwd);

        Assertions.assertTrue(registerAndLogin.loginFailedValidation(), "The account was not successfully deleted or still accessible.");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}