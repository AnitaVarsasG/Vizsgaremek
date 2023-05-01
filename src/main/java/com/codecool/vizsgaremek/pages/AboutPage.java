package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AboutPage extends Page{

    //Constructor
    public AboutPage(WebDriver driver) {
        super(driver, Pages.URL_ABOUT.getURL());
    }

    //Locator
    private final By CARD_TEAM_MEMBERS = By.className("site-team-member-content");

    //Method to retrieve all the team members and their professions from the About page
    //Return Map of member names and their professions
    public Map<String, String> getMembers() {
        List<WebElement> memberCards = driver.findElements(CARD_TEAM_MEMBERS);

        Map<String, String> members = new HashMap<>();

        // Loop through all the team member cards and add their name and profession to the map
        for (WebElement memberCard : memberCards) {
            members.put(memberCard.findElement(By.tagName("h3")).getText(), memberCard.findElement(By.tagName("p")).getText());
        }

        return members;
    }
}
