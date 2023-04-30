package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends Page{

    public HomePage(WebDriver driver) {
        super(driver, Pages.URL_HOME.getURL());
    }

    private final By LINKS_HERO_SECTION = By.xpath("//*[@class='site-hero-content-buttons']/li");
    private final By LINKS_SITE_PROJECT = By.xpath("//div[@class='row']/div/div/div[@class='site-project-item-content']");
    private final By OPINIONS = By.xpath("//div[@class='row']/div/div[@class='site-testimonial-item']");

    public void moveToComment() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(OPINIONS));
    }

}
