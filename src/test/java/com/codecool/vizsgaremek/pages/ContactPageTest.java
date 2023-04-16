package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Roxo")
@Story("Verify Contact Form")
class ContactPageTest {

    private WebDriver driver;

    private ContactPage contactPage;


    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = WebDriverFactory.getWebDriver();

        contactPage = new ContactPage(driver);
        contactPage.navigateToUrl();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Story("Verify Contact Form")
    @Description("Contact Form test with valid Data")
    @DisplayName("Contact Form test with valid Data")
    @Test
    void contactFormTestValid() {

        String firstName = "Z";
        String lastName = "Beck";
        String email = "30y@gmail.com";
        String projectType ="Web Design";
        String about = "About";

        contactPage.fillNameFields(firstName, lastName);
        contactPage.fillEmailField(email);
        contactPage.selectProjectType(projectType);
        contactPage.fillAboutField(about);
        contactPage.pressSubmit();

        String submitMessage = contactPage.validateSubmit();
        String expectedMessage = "Message sent!";

        Assertions.assertEquals(expectedMessage, submitMessage);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Epic("Roxo")
    @Story("Verify Contact Form")
    @Description("Contact Form test with empty fields")
    void contactFormTestInvalid() {

        contactPage.pressSubmit();

        boolean submitValidation = contactPage.validateSubmit().equals("Message sent!");

        Assertions.assertFalse(submitValidation, "This....");

    }

    //TODO többszörös fájlból kiolvasás

    @AfterEach
    void tearDown() {

    }
}