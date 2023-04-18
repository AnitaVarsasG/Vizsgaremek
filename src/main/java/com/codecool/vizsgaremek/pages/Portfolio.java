package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class Portfolio extends Page{

    public Portfolio(WebDriver driver) {
        super(driver, Pages.URL_PORTFOLIO.getURL());
    }

    private final By BUTTON_NEXT = By.xpath("//*[@aria-label='Next']");
    private final By PROJECT_CONTENT = By.xpath("//*[@class='site-project-item-content']/span");
    private final By BUTTON_NEXT_DISABLED = By.xpath("//*[@class='page-item disabled']/a[@aria-label='Next']");

    public boolean nextDisplayed() {
        return driver.findElement(BUTTON_NEXT).isDisplayed();
        }


   /*
    public int getProjects()

        int totalProjects = 0;

        do {
             totalProjects += findElementsOnPage(PROJECT_CONTENT).size();
            if(!driver.findElements(BUTTON_NEXT).isEmpty()){
                findElementOnPage(BUTTON_NEXT).click();
            }
        } while(!driver.findElements(BUTTON_NEXT).isEmpty());

        return totalProjects;

    }

         */

}
