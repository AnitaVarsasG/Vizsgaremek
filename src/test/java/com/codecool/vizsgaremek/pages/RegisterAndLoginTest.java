package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.WebDriverFactory;
import com.codecool.vizsgaremek.enums.Pages;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.checkerframework.checker.signature.qual.Identifier;
import org.junit.jupiter.api.*;
import org.junit.platform.launcher.TestIdentifier;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.time.Duration;


@Story("User Registration and Login")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RegisterAndLoginTest {

    RegisterAndLoginPage registerAndLogin;
    WebDriver driver;

    @BeforeEach
    void setUp() {

        WebDriverManager.chromedriver().setup();

        driver = WebDriverFactory.getWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        registerAndLogin = new RegisterAndLoginPage(driver);
        registerAndLogin.navigateToUrl();
    }

    //Registration testdata - VALID
    public final String REGISTER_VALID_USER = "tester";
    public final String REGISTER_VALID_PWD = "1234";
    public final String REGISTER_VALID_EMAIL = "testercc@gmail.com";
    public final String REGISTER_VALID_DESCRIPTION = "";

    //Login testdata - VALID
    private final String LOGIN_USERNAME = "beckz";
    private final String LOGIN_PWD = "30y123";

    @Test
    @DisplayName("TC01 - Test URL validation")
    @Order(1)
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that the current URL matches the expected URL")
    void urlValidation() {
        Assertions.assertEquals(Pages.URL_REGISTER_AND_LOGIN.getURL(), driver.getCurrentUrl(), "Unexpected URL");
    }

    @Test
    @DisplayName("TC02 - Registration with valid data")
    @Order(2)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a user can register successfully with valid data")
    void testRegistrationWithValidData() {
        registerAndLogin.acceptTermsAndConditions();
        registerAndLogin.registration(REGISTER_VALID_USER, REGISTER_VALID_PWD, REGISTER_VALID_EMAIL, REGISTER_VALID_DESCRIPTION);

        Assertions.assertTrue(registerAndLogin.registerValidation(), "Registration failed");
    }

    @Test
    @DisplayName("TC03 - Test registration with invalid data")
    @Order(3)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that registration with invalid data fails")
    void testRegistrationWithInvalidData() {
        registerAndLogin.acceptTermsAndConditions();
        registerAndLogin.registration("","", "", "");

        Allure.addAttachment("TC03 - Screenshot", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));

        Assertions.assertFalse(registerAndLogin.registerValidation(), "Expected registration to fail with invalid data");
    }

    @Test
    @DisplayName("TC04 - Test login after successful registration")
    @Order(4)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that a user can log in after successfully registering")
    void testLoginAfterRegistration() {
        registerAndLogin.acceptTermsAndConditions();
        registerAndLogin.registration(REGISTER_VALID_USER, REGISTER_VALID_PWD, REGISTER_VALID_EMAIL, REGISTER_VALID_DESCRIPTION);
        registerAndLogin.switchToLogin();
        registerAndLogin.login(REGISTER_VALID_USER, REGISTER_VALID_PWD);

        Assertions.assertTrue(registerAndLogin.loginValidation(), "Expected login to succeed after successful registration");
    }

    @Test
    @DisplayName("TC05 - Test login from landing page")
    @Order(5)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that a user can log in from the landing page")
    void testLoginFromLandingPage() {
        registerAndLogin.acceptTermsAndConditions();
        registerAndLogin.login(LOGIN_USERNAME, LOGIN_PWD);

        Assertions.assertTrue(registerAndLogin.loginValidation(), "Expected login to succeed from landing page");
    }

    @Test
    @DisplayName("TC06 - Test login from landing page with invalid data")
    @Order(6)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that login fails with invalid credentials from the landing page")
    void testLoginFromLandingPageWithInvalidDate() {
        registerAndLogin.acceptTermsAndConditions();
        registerAndLogin.login("test", "test");

        Assertions.assertTrue(registerAndLogin.loginFailedValidation(), "Expected login to fail with invalid data from landing page");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}