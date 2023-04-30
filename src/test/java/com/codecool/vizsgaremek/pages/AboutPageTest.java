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

@Story("Verify About Page")
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

    //Read team members' names from teammembers.txt and put them into a String array
    public String[] readFile() {
        List<String> members = new ArrayList<>();
        try {
            File text = new File("teammembers.txt");
            Scanner scanner = new Scanner(text);
            while (scanner.hasNextLine()){
                String name = scanner.nextLine();
                members.add(name);
            }
        } catch (Exception e) {
            System.out.println("File is not found");;
        }
        return members.toArray(new String[0]);
    }


    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Verify team member's name")
    @Description("Verify team member's name")
    @Test
    void nameCardTest() {
        Map<String, String> actualArr = aboutPage.getMembers();
        /*String[] expectedArr = readFile();

        Assertions.assertArrayEquals(expectedArr, actualArr);*/
    }


    @Test
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
    }
}