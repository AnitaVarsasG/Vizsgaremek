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
        registerAndLogin.registration(registerAndLogin.REGISTER_VALID_USER,
                registerAndLogin.REGISTER_VALID_PWD,
                registerAndLogin.REGISTER_VALID_EMAIL,
                registerAndLogin.REGISTER_VALID_DESCRIPTION);

        Assertions.assertTrue(registerAndLogin.registerValidation());
    }
/*
    @Test
    void testRegistrationWithInvalidData() {
        registerAndLogin.acceptTermsAndConditions();
        registerAndLogin.registration();

        Assertions.assertFalse(registerAndLogin.registerValidation());
    }*/

    @Test
    void testLogin() {

    }

    @AfterEach
    void tearDown() {
    }
}