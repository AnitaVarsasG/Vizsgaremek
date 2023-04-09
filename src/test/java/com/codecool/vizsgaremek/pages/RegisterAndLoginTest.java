package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.WebDriverFactory;
import com.codecool.vizsgaremek.enums.Pages;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

class RegisterAndLoginTest {

    RegisterAndLogin registerAndLogin;
    WebDriver driver;

    //Registration testdata - VALID
    public final String REGISTER_VALID_USER = "tester";
    public final String REGISTER_VALID_PWD = "1234";
    public final String REGISTER_VALID_EMAIL = "testercc@whatever.com";
    public final String REGISTER_VALID_DESCRIPTION = "";

    //Login testdata - VALID
    private final String LOGIN_USERNAME = "beckz";
    private final String LOGIN_PWD = "30y123";

    @BeforeEach
    void setUp() {

        WebDriverManager.chromedriver().setup();

        driver = WebDriverFactory.getWebDriver();

        registerAndLogin = new RegisterAndLogin(driver);
        registerAndLogin.navigateToUrl();
    }

    @Test
    void urlValidation() {
        Assertions.assertEquals(Pages.URL_REGISTER_AND_LOGIN.getURL(), driver.getCurrentUrl());
    }

    @Test
    void testRegistrationWithValidData() {
        registerAndLogin.acceptTermsAndConditions();
        registerAndLogin.registration(REGISTER_VALID_USER, REGISTER_VALID_PWD, REGISTER_VALID_EMAIL, REGISTER_VALID_DESCRIPTION);

        Assertions.assertTrue(registerAndLogin.registerValidation());
    }

    @Test
    void testRegistrationWithInvalidData() {
        registerAndLogin.acceptTermsAndConditions();
        registerAndLogin.registration("", "", "", "");

        Assertions.assertFalse(registerAndLogin.registerValidation());
    }

    @Test
    void testLoginAfterRegistration() {
        registerAndLogin.acceptTermsAndConditions();
        registerAndLogin.registration(REGISTER_VALID_USER, REGISTER_VALID_PWD, REGISTER_VALID_EMAIL, REGISTER_VALID_DESCRIPTION);
        registerAndLogin.switchToLogin();
        registerAndLogin.login(REGISTER_VALID_USER, REGISTER_VALID_PWD);

        Assertions.assertTrue(registerAndLogin.loginValidation());
    }

    @Test
    void testLoginFromLandingPage() {
        registerAndLogin.acceptTermsAndConditions();
        registerAndLogin.login(LOGIN_USERNAME, LOGIN_PWD);

        Assertions.assertTrue(registerAndLogin.loginValidation());
    }

    @AfterEach
    void tearDown() {
        //driver.quit();
    }
}