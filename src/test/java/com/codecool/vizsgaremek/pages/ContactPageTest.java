package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import javax.annotation.ParametersAreNonnullByDefault;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

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
    void multiplyContactFormTest() throws IOException {

        SoftAssert softAssert = new SoftAssert();

        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contact.txt"));
        String testData;

        while ((testData = reader.readLine()) != null) {
            String[] data = testData.split(",");
            String firstName = data[0].trim();
            String lastName = data[1].trim();
            String email = data[2].trim();
            String projectType = data[3].trim();
            String aboutProject = data[4].trim();

            contactPage.fillNameFields(firstName, lastName);
            contactPage.fillEmailField(email);
            contactPage.selectProjectType(projectType);
            contactPage.fillAboutField(aboutProject);
            contactPage.pressSubmit();

            String submitMessage = contactPage.validateSubmit();
            String expectedMessage = "Message sent!";

            softAssert.assertEquals(expectedMessage, submitMessage);

            contactPage.acceptAlert();
            contactPage.refreshPage();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        softAssert.assertAll();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Epic("Roxo")
    @Story("Verify Contact Form")
    @DisplayName("Contact Form test with empty fields")
    @Description("Contact Form test with empty fields")
    void contactFormTestInvalid() {

        contactPage.pressSubmit();

        boolean submitValidation = contactPage.validateSubmit().equals("Message sent!");

        Assertions.assertFalse(submitValidation, "This....");

    }

    @AfterEach
    void tearDown() {

    }
}