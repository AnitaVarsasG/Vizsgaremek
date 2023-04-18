package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BlogPage extends Page{

    public BlogPage(WebDriver driver) {
        super(driver, Pages.URL_BLOG.getURL());
    }

    private final By BUTTON_NEXT_PAGE = By.xpath("//*[@aria-label='Next']");
    private final By BLOG_ARTICLES = By.xpath("//*[@class='row']/div/article/div[@class='site-blog-post-content']");

    public List<WebElement> getBlogTitles() {
        return findElementsOnPage(BLOG_ARTICLES);

        /*
        List<String> blogTitles = new ArrayList<>();

        for (WebElement post : blogPosts) {
            WebElement title = post.findElement(By.xpath(".//h3/a"));
            blogTitles.add(title.getText());
        }
        return blogTitles.toArray(new String[0]); */
    }

    public void clickNextPage() {
        findElementOnPage(BUTTON_NEXT_PAGE).click();
    }
}
