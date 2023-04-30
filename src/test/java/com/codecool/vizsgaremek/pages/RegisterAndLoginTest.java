package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.WebDriverFactory;
import com.codecool.vizsgaremek.enums.Pages;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@Story("Verify registration and login")
class RegisterAndLoginTest {

    RegisterAndLogin registerAndLogin;
    WebDriver driver;

    @BeforeEach
    void setUp() {

        WebDriverManager.chromedriver().setup();

        driver = WebDriverFactory.getWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        registerAndLogin = new RegisterAndLogin(driver);
        registerAndLogin.navigateToUrl();
    }

    //Registration testdata - VALID
    public final String REGISTER_VALID_USER = "tester";
    public final String REGISTER_VALID_PWD = "1234";
    public final String REGISTER_VALID_EMAIL = "testercc@whatever.com";
    public final String REGISTER_VALID_DESCRIPTION = "";

    //Login testdata - VALID
    private final String LOGIN_USERNAME = "beckz";
    private final String LOGIN_PWD = "30y123";

    @Severity(SeverityLevel.TRIVIAL)
    @DisplayName("Validate the current url")
    @Description("Validate the current url")
    @Test
    void urlValidation() {
        Assertions.assertEquals(Pages.URL_REGISTER_AND_LOGIN.getURL(), driver.getCurrentUrl());
    }

    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Registration with valid data")
    @Description("Registration with valid data")
    @Test
    void testRegistrationWithValidData() {
        registerAndLogin.acceptTermsAndConditions();
        registerAndLogin.registration(REGISTER_VALID_USER, REGISTER_VALID_PWD, REGISTER_VALID_EMAIL, REGISTER_VALID_DESCRIPTION);

        Assertions.assertTrue(registerAndLogin.registerValidation());
    }

    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Registration with empty fields")
    @Description("Registration with empty fields")
    @Test
    void testRegistrationWithInvalidData() {
        registerAndLogin.acceptTermsAndConditions();
        registerAndLogin.registration("","", "", "");

        Assertions.assertFalse(registerAndLogin.registerValidation());
    }

    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Login after registration")
    @Description("Login after registration")
    @Test
    void testLoginAfterRegistration() {
        registerAndLogin.acceptTermsAndConditions();
        registerAndLogin.registration(REGISTER_VALID_USER, REGISTER_VALID_PWD, REGISTER_VALID_EMAIL, REGISTER_VALID_DESCRIPTION);
        registerAndLogin.switchToLogin();
        registerAndLogin.login(REGISTER_VALID_USER, REGISTER_VALID_PWD);

        Assertions.assertTrue(registerAndLogin.loginValidation());
    }

    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Login from landing page")
    @Description("Login from landing page")
    @Test
    void testLoginFromLandingPage() {
        registerAndLogin.acceptTermsAndConditions();
        registerAndLogin.login(LOGIN_USERNAME, LOGIN_PWD);

        Assertions.assertTrue(registerAndLogin.loginValidation());
    }

    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Login from landing page with invalid data")
    @Description("Login from landing page with invalid data")
    @Test
    void testLoginFromLandingPageWithInvalidDate() {
        registerAndLogin.acceptTermsAndConditions();
        registerAndLogin.login("", "");

        Assertions.assertTrue(registerAndLogin.loginFailedValidation());
    }

    @Test
    void userDataModificationTest() {

    }

    @Test
    void deleteAccountTest() {

    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}