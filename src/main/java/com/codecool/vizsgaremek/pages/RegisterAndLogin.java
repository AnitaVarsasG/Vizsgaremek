package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterAndLogin extends Page{


    private final By BUTTON_TERMS_AND_CONDITIONS = By.id("terms-and-conditions-button");

    //Registration fields and buttons
    private final By BUTTON_REGISTER_FORM = By.xpath("//div[@id='login']/button[@id='register-form-button']");
    private final By INPUT_REGISTER_NAME = By.id("register-username");
    private final By INPUT_REGISTER_PWD = By.id("register-password");
    private final By INPUT_REGISTER_EMAIL = By.id("register-email");
    private final By INPUT_REGISTER_DESCRIPTION = By.id("register-description");
    private final By BUTTON_REGISTER = By.xpath("//*[@onclick='registerUser()']");
    private final By REGISTER_ALERT = By.xpath("//p[@id='register-alert']");

    //Login fields and buttons
    private final By BUTTON_LOGIN_FORM = By.xpath("//div[@id='register']/button[@onclick='showLogin()']");
    private final By INPUT_LOGIN_EMAIL = By.id("email");
    private final By INPUT_LOGIN_PWD = By.id("password");
    private final By BUTTON_LOGIN = By.xpath("//*[@onclick='myFunction()']");
    private final By BUTTON_LOGOUT = By.xpath("//*[@onclick='logout()']");


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
    public void switchToLogin() {
        findElementOnPage(BUTTON_LOGIN_FORM).click();
    }
    public void login(String email, String pwd) {
        findElementOnPage(INPUT_LOGIN_EMAIL).sendKeys(email);
        findElementOnPage(INPUT_LOGIN_PWD).sendKeys(pwd);
        findElementOnPage(BUTTON_LOGIN).click();
    }

    public boolean loginValidation() {
        return findElementOnPage(BUTTON_LOGOUT).isDisplayed();
    }




}
