package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page{

    //Constructor
    public HomePage(WebDriver driver) {
        super(driver, Pages.URL_HOME.getURL());
    }

    //Locators
    private final By BUTTON_PROFILE = By.id("profile-btn");
    private final By INPUT_NAME = By.id("name");
    private final By INPUT_BIO = By.id("bio");
    private final By INPUT_PHONE_NUMBER = By.id("phone-number");
    private final By BUTTON_SAVE = By.xpath("//*[@onclick='editUser()']");
    private final By BUTTON_DELETE = By.xpath("//*[@onclick='showRealDeleteAccBtn()']");
    private final By BUTTON_DELETE_CONFIRM = By.id("delete-account-btn");
    private final By TEXT_EDIT = By.id("edit-alert");
    private final By BUTTON_LOGOUT = By.id("logout-link");

    //Methods
    //Navigate to the user profile page
    public void navigateToProfile() {
        driver.findElement(BUTTON_PROFILE).click();
    }

    //Edit the user's profile
    public void editProfile(String name, String bio, String phoneNumber) {
        driver.findElement(INPUT_NAME).sendKeys(name);
        driver.findElement(INPUT_BIO).sendKeys(bio);
        driver.findElement(INPUT_PHONE_NUMBER).sendKeys(phoneNumber);
        driver.findElement(BUTTON_SAVE).click();
    }

    //Validate that the user's profile has been edited
    public String validateProfileEdit() {
        return driver.findElement(TEXT_EDIT).getText();
    }

    //Delete an exist account
    public void deleteAccount() {
        driver.findElement(BUTTON_DELETE).click();
        driver.findElement(BUTTON_DELETE_CONFIRM).click();
    }

    //Logout
    public void logout() {
        driver.findElement(BUTTON_LOGOUT).click();
    }

}
