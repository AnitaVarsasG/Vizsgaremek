package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AboutPage extends Page{

    public AboutPage(WebDriver driver) {
        super(driver, Pages.URL_ABOUT.getURL());
    }

    private final By CARD_TEAM_MEMBERS = By.xpath("//*[@class='site-team']/div/div/*[@class='col-lg-4 col-md-6']");

    public String[] getMemberNames() {
        List<WebElement> memberCards = findElementsOnPage(CARD_TEAM_MEMBERS);

        String[] memberNames = new String[memberCards.size()];
        int i = 0;

        for (WebElement memberCard : memberCards) {
            WebElement name = memberCard.findElement(By.xpath(".//h3"));
            memberNames[i] = name.getText();
            i++;
        }
        return memberNames;
    }
}
