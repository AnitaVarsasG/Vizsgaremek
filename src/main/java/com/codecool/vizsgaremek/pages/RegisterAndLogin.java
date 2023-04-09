package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterAndLogin extends Page{

    //Registration testdata - VALID
    public final String REGISTER_VALID_USER = "tester";
    public final String REGISTER_VALID_PWD = "1234";
    public final String REGISTER_VALID_EMAIL = "testercc@whatever.com";
    public final String REGISTER_VALID_DESCRIPTION = "";

    //Login testdata - VALID
    private final String LOGIN_USERNAME = "beckz";
    private final String LOGIN_PWD = "30y123";
    private final By BUTTON_TERMS_AND_CONDITIONS = By.id("terms-and-conditions-button");
    private final By BUTTON_REGISTER_FORM = By.xpath("//div[@id='login']/button[@id='register-form-button']");
    private final By INPUT_REGISTER_NAME = By.id("register-username");
    private final By INPUT_REGISTER_PWD = By.id("register-password");
    private final By INPUT_REGISTER_EMAIL = By.id("register-email");
    private final By INPUT_REGISTER_DESCRIPTION = By.id("register-description");
    private final By BUTTON_REGISTER = By.xpath("//*[@onclick='registerUser()']");
    private final By REGISTER_ALERT = By.xpath("//p[@id='register-alert']");
    public RegisterAndLogin(WebDriver driver) {
        super(driver, Pages.URL_REGISTER_AND_LOGIN.getURL());
    }

    public void acceptTermsAndConditions() {
        findElementOnPage(BUTTON_TERMS_AND_CONDITIONS).click();
    }

    public void registration(String username, String pwd, String email, String description) {
        findElementOnPage(BUTTON_REGISTER_FORM).click();
        findElementOnPage(INPUT_REGISTER_NAME).sendKeys(username);
        findElementOnPage(INPUT_REGISTER_PWD).sendKeys(pwd);
        findElementOnPage(INPUT_REGISTER_EMAIL).sendKeys(email);
        findElementOnPage(INPUT_REGISTER_DESCRIPTION).sendKeys(description);
        findElementOnPage(BUTTON_REGISTER).click();
    }

    public boolean registerValidation() {
        return findElementOnPage(REGISTER_ALERT).getText().equals("User registered!");
    }



}
