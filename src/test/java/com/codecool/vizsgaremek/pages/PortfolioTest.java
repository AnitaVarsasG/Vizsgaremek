package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

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
    void paginationTest() {


    }

    @AfterEach
    void tearDown() {
    }
}