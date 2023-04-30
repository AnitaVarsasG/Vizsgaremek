package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactPage extends Page{

    public ContactPage(WebDriver driver) {
        super(driver, Pages.URL_CONTACT.getURL());
    }

    private final By INPUT_FIRST_NAME = By.id("first-name");
    private final By INPUT_LAST_NAME = By.id("last-name");
    private final By INPUT_EMAIL = By.id("email");
    private final By INPUT_SELECT_PROJECT_TYPE = By.id("projectType");
    private final By INPUT_ABOUT = By.id("aboutProject");

    private final By BUTTON_SUBMIT = By.id("contact-form-button");


    public void fillNameFields(String firstName, String lastName) {
        driver.findElement(INPUT_FIRST_NAME).sendKeys(firstName);
        driver.findElement(INPUT_LAST_NAME).sendKeys(lastName);
    }

    public void fillEmailField(String email) {
        driver.findElement(INPUT_EMAIL).sendKeys(email);
    }

    public void selectProjectType(String type) {
        Select projectType = new Select(driver.findElement(INPUT_SELECT_PROJECT_TYPE));
        projectType.selectByVisibleText(type);
    }

    public void fillAboutField(String about) {
        driver.findElement(INPUT_ABOUT).sendKeys(about);
    }

    public void pressSubmit() {
        driver.findElement(BUTTON_SUBMIT).click();
    }

    public String validateSubmit() {
        return driver.switchTo().alert().getText();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

}
