package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

class ContactPageTest {

    WebDriver driver;


    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = WebDriverFactory.getWebDriver();

    }

    @Test
    void contactTest() {
        ContactPage contactPage = new ContactPage(driver);

        contactPage.navigateToUrl();
        contactPage.fillNameFields("Z", "Beck");
        contactPage.fillEmailField("beckz@30y.com");
        contactPage.selectProjectType("Web Design");
        contactPage.fillAboutField("About");
        contactPage.pressSubmit();

        String submitMessage = contactPage.validateSubmit();
        String expectedMessage = "Message sent!";

        Assertions.assertEquals(expectedMessage, submitMessage);
    }

    @AfterEach
    void tearDown() {

    }
}