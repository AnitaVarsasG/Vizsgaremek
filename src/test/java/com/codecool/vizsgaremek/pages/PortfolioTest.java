package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioTest {

    WebDriver driver;

    Portfolio portfolio;

    @BeforeEach
    void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = WebDriverFactory.getWebDriver();

        portfolio = new Portfolio(driver);
        portfolio.navigateToUrl();
    }

    @Test
    void paginationTest() {
        portfolio.getProjects();

    }

    @AfterEach
    void tearDown() {
    }
}