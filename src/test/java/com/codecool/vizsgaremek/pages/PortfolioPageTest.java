package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Story("Project titles")
class PortfolioPageTest {

    WebDriver driver;

    PortfolioPage portfolio;

    @BeforeEach
    void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = WebDriverFactory.getWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        portfolio = new PortfolioPage(driver);
        portfolio.navigateToUrl();
    }

    @Test
    @DisplayName("TC14 - Test project titles")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that project titles on the page are displayed correctly")
    void fileTest() throws IOException {
        List<String> projectTitles = portfolio.getProjectTitles();
        portfolio.saveTitlesToTxt(projectTitles);

        String[] actualResult = portfolio.readTitlesTxt();
        String[] expectedResult = new String[]{"KIO-TAPE BRAND", "USE-LESS BRAND", "OSEN CLOCK", "SEAMLESS WATCH", "KIO TAPE"};

        Assertions.assertArrayEquals(expectedResult, actualResult);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}