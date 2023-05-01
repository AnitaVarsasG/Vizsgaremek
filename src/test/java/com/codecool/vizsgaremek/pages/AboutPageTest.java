package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@Story("About Page - Team members")
class AboutPageTest {

    WebDriver driver;
    AboutPage aboutPage;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = WebDriverFactory.getWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        aboutPage = new AboutPage(driver);
        aboutPage.navigateToUrl();
    }

    @Test
    @DisplayName("TC07 - Test team members information")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that team members on the About page are displayed correctly.")
    void memberTest() throws IOException, ParseException {

        Map<String, String> actualResult = aboutPage.getMembers();

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src/test/resources/teammembers.json"));
        JSONArray members = (JSONArray) obj;
        Map<String, String> expectedResult = new HashMap<>();

        for(Object member : members) {

           String key = (String) ((JSONObject) member).get("name");
           String value = (String) ((JSONObject) member).get("profession");

           expectedResult.put(key, value);
        }

        Assertions.assertEquals(expectedResult, actualResult);
    }



    @AfterEach
    void tearDown() {
        driver.quit();
    }
}