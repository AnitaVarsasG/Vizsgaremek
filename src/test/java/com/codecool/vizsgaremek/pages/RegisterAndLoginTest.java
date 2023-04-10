package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.WebDriverFactory;
import com.codecool.vizsgaremek.enums.Pages;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class RegisterAndLoginTest {

    RegisterAndLogin registerAndLogin;
    WebDriver driver;

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
        registerAndLogin.registration();

        Assertions.assertTrue(registerAndLogin.registerValidation());
    }

    @Test
    void testRegistrationWithInvalidData() {
        registerAndLogin.acceptTermsAndConditions();
        registerAndLogin.invalidRegistration();

        Assertions.assertFalse(registerAndLogin.registerValidation());
    }

    @Test
    void testLoginAfterRegistration() {
        registerAndLogin.acceptTermsAndConditions();
        registerAndLogin.registration();
        registerAndLogin.switchToLogin();
        registerAndLogin.login();

        Assertions.assertTrue(registerAndLogin.loginValidation());
    }

    @Test
    void testLoginFromLandingPage() {
        registerAndLogin.acceptTermsAndConditions();
        registerAndLogin.login();

        Assertions.assertTrue(registerAndLogin.loginValidation());
    }

    @Test
    void testLoginFromLandingPageWithInvalidDate() {
        registerAndLogin.acceptTermsAndConditions();
        registerAndLogin.invalidLogin();

        Assertions.assertTrue(registerAndLogin.loginFaildValidation());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}