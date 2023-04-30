package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;

abstract class Page {
    WebDriver driver;
    private final String url;

    protected Page(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    public final void navigateToUrl() {
        driver.navigate().to(url);
    }

}

