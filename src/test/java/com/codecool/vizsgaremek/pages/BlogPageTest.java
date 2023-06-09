package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Story("Verify Blogpage")
class BlogPageTest {

    WebDriver driver;
    BlogPage blogPage;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = WebDriverFactory.getWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        blogPage = new BlogPage(driver);
        blogPage.navigateToUrl();
    }


    @Test
    @DisplayName("TC15 - Test for all blogposts title")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that all blogposts title on the page are displayed on the page")
    void blogTitleTest() {

        List<String> titles = new ArrayList<>();

        while (true) {
            List<WebElement> posts = blogPage.getBlogTitles();
            for (WebElement post : posts) {
                WebElement title = post.findElement(By.xpath(".//h3/a"));
                titles.add(title.getText());
            }
            try {
                blogPage.clickNextPage();
            } catch (Exception e) {
                break;
            }
        }

        int postNumber = titles.size();

        Assertions.assertEquals(5, postNumber);

    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}