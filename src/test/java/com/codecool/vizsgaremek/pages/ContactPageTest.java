package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import jdk.jfr.Description;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import javax.annotation.ParametersAreNonnullByDefault;

import java.io.*;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Story("Contact Form")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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

    @Test
    @DisplayName("TC11 - Contact Form test with valid Data")
    @Order(1)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that filling contact form with valid data can be sent")
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

        Assertions.assertEquals(expectedMessage, submitMessage, "Expected alert message 'Message sent!' does not match actual alert message");
    }


    @Test
    @DisplayName("TC12 - Test multiply contact form sending")
    @Order(2)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify if multiple submissions of the contact form are successful")
    void multiplyContactFormTest() throws IOException {

        SoftAssert softAssert = new SoftAssert();

        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contact.txt"));
        String testData;

        // Multiply data sending
        while ((testData = reader.readLine()) != null) {

            // Test data from contact.txt
            String[] data = testData.split(",");
            String firstName = data[0].trim();
            String lastName = data[1].trim();
            String email = data[2].trim();
            String projectType = data[3].trim();
            String aboutProject = data[4].trim();

            // Fill contact form with test data
            contactPage.fillNameFields(firstName, lastName);
            contactPage.fillEmailField(email);
            contactPage.selectProjectType(projectType);
            contactPage.fillAboutField(aboutProject);
            contactPage.pressSubmit();

            String submitMessage = contactPage.validateSubmit();
            String expectedMessage = "Message sent!";

            // Verify that the contact form has been successfully sent in all rounds
            softAssert.assertEquals(expectedMessage, submitMessage);

            // Accept alert message and refresh page
            contactPage.acceptAlert();
            contactPage.refreshPage();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        softAssert.assertAll();
    }

    @Test
    @DisplayName("TC13 - Test for contact form with empty fields")
    @Order(3)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify if submission of the contact with empty fields is successful")
    void contactFormTestInvalid() {

        contactPage.pressSubmit();

        Allure.addAttachment("TC13 - Screenshot", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));

        boolean submitValidation = contactPage.validateSubmit().equals("Message sent!");

        Assertions.assertFalse(submitValidation, "The submission of the contact with empty fields should not be successful");

    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}