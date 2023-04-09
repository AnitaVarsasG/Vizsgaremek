package com.codecool.vizsgaremek;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    private WebDriverFactory(){}

    public static WebDriver getWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito", "start-maximized", "disable-extensions", "ignore-certificate-errors");
        options.addArguments("remote-allow-origins=*");
        //options.addArguments("--headless");

        return new ChromeDriver(options);
    }
}
