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

abstract class Page {
    WebDriver driver;
    private final Wait<WebDriver> wait;
    private final String url;

    protected Page(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
        this.wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
    }

    public final void navigateToUrl() {
        driver.navigate().to(url);
    }

    protected final WebElement findElementOnPage(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public final boolean validateUrl(String Url, Pages url) {
        return Url.equals(url.getURL());
    }
}

