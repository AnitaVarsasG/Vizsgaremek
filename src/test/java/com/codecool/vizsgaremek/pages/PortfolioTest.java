package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioTest {

    WebDriver driver;

    Portfolio portfolio;

    @BeforeEach
    void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = WebDriverFactory.getWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        portfolio = new Portfolio(driver);
        portfolio.navigateToUrl();
    }

    @Test
    void fileTest() throws IOException {
        List<String> projectTitles = portfolio.getProjectTitles();
        portfolio.saveTitlesToTxt(projectTitles);

        String[] actualResult = portfolio.readTitlesTxt();
        String[] expectedResult = new String[]{"KIO-TAPE BRAND", "USE-LESS BRAND", "OSEN CLOCK", "SEAMLESS WATCH", "KIO TAPE"};

        Assertions.assertArrayEquals(expectedResult, actualResult);
    }

    @AfterEach
    void tearDown() {
    }
}