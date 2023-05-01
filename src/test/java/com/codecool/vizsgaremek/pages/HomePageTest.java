package com.codecool.vizsgaremek.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

class HomePageTest {

    WebDriver driver;
    RegisterAndLoginPage registerAndLogin;
    HomePage homePage;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito", "start-maximized", "disable-extensions", "ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        //options.addArguments("--headless");
        //options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
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
    void editProfileTest() {

        //test data for edit profile
        String name = "Test Edit";
        String bio = "test bio";
        String phoneNumber = "02345678";

        regAndLogin();

        homePage.navigateToProfile();
        homePage.editProfile(name, bio, phoneNumber);

        String actualResult = homePage.validateProfileEdit();
        String expectedResult = "Profile Edited!";

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void logoutTest() {
        regAndLogin();

        homePage.logout();
        String expectedResult = "https://lennertamas.github.io/roxo/index.html";
        String actualResult = driver.getCurrentUrl();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void deleteAccountTest() {
        regAndLogin();

        homePage.navigateToProfile();
        homePage.deleteAccount();
        registerAndLogin.login(user, pwd);

        Assertions.assertTrue(registerAndLogin.loginFailedValidation());
    }

    @AfterEach
    void tearDown() {
    }
}