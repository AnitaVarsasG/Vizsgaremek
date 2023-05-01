package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BlogPage extends Page{

    // Constructor
    public BlogPage(WebDriver driver) {
        super(driver, Pages.URL_BLOG.getURL());
    }

    // Locators
    private final By BUTTON_NEXT_PAGE = By.xpath("//*[@aria-label='Next']");
    private final By BLOG_ARTICLES = By.xpath("//*[@class='row']/div/article/div[@class='site-blog-post-content']");

    // Method for collect blog articles, it returns WebElement List
    public List<WebElement> getBlogTitles() {
        return driver.findElements(BLOG_ARTICLES);
    }

    // Click Next page button
    public void clickNextPage() {
        driver.findElement(BUTTON_NEXT_PAGE).click();
    }
}
