package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AboutPage extends Page{

    public AboutPage(WebDriver driver) {
        super(driver, Pages.URL_ABOUT.getURL());
    }

    private final By CARD_TEAM_MEMBERS = By.className("site-team-member-content");

    public Map<String, String> getMembers() {
        List<WebElement> memberCards = driver.findElements(CARD_TEAM_MEMBERS);

        Map<String, String> members = new HashMap<>();


        for (WebElement memberCard : memberCards) {
            members.put(memberCard.findElement(By.tagName("h3")).getText(), memberCard.findElement(By.tagName("p")).getText());

            /* WebElement name = memberCard.findElement(By.xpath(".//h3"));
            memberNames[i] = name.getText();
            i++;*/
        }
        return members;
    }
}
