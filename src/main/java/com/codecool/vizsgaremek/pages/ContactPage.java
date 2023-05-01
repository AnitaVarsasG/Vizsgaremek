package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactPage extends Page{

    // Constructor
    public ContactPage(WebDriver driver) {
        super(driver, Pages.URL_CONTACT.getURL());
    }

    // Locators
    private final By INPUT_FIRST_NAME = By.id("first-name");
    private final By INPUT_LAST_NAME = By.id("last-name");
    private final By INPUT_EMAIL = By.id("email");
    private final By INPUT_SELECT_PROJECT_TYPE = By.id("projectType");
    private final By INPUT_ABOUT = By.id("aboutProject");
    private final By BUTTON_SUBMIT = By.id("contact-form-button");

    // Methods
    // Fill the first name and last name fields on the contact form
    public void fillNameFields(String firstName, String lastName) {
        driver.findElement(INPUT_FIRST_NAME).sendKeys(firstName);
        driver.findElement(INPUT_LAST_NAME).sendKeys(lastName);
    }

    // Fill the email field on the contact form
    public void fillEmailField(String email) {
        driver.findElement(INPUT_EMAIL).sendKeys(email);
    }

    // Select the project type from the dropdown menu
    public void selectProjectType(String type) {
        Select projectType = new Select(driver.findElement(INPUT_SELECT_PROJECT_TYPE));
        projectType.selectByVisibleText(type);
    }

    // Fill about field
    public void fillAboutField(String about) {
        driver.findElement(INPUT_ABOUT).sendKeys(about);
    }

    // Press submit button
    public void pressSubmit() {
        driver.findElement(BUTTON_SUBMIT).click();
    }

    // Validate that the form was submitted by returning the alert text that is displayed on submission
    public String validateSubmit() {
        return driver.switchTo().alert().getText();
    }

    // Accept the alert that is displayed after submitting the contact form
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    // Refresh the page
    public void refreshPage() {
        driver.navigate().refresh();
    }

}
