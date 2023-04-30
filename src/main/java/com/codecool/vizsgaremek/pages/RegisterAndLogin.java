package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterAndLogin extends Page{

    public RegisterAndLogin(WebDriver driver) {
        super(driver, Pages.URL_REGISTER_AND_LOGIN.getURL());
    }




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
    private final By LOGIN_ALERT = By.id("alert");




    public void acceptTermsAndConditions() {
        driver.findElement(BUTTON_TERMS_AND_CONDITIONS).click();
    }

    public void registration(String user, String pwd, String email, String description) {
        driver.findElement(BUTTON_REGISTER_FORM).click();
        driver.findElement(INPUT_REGISTER_NAME).sendKeys(user);
        driver.findElement(INPUT_REGISTER_PWD).sendKeys(pwd);
        driver.findElement(INPUT_REGISTER_EMAIL).sendKeys(email);
        driver.findElement(INPUT_REGISTER_DESCRIPTION).sendKeys(description);
        driver.findElement(BUTTON_REGISTER).click();
    }

    public boolean registerValidation() {
        return driver.findElement(REGISTER_ALERT).getText().equals("User registered!");
    }
    public void switchToLogin() {
        driver.findElement(BUTTON_LOGIN_FORM).click();
    }
    public void login(String email, String pwd) {
        driver.findElement(INPUT_LOGIN_EMAIL).sendKeys(email);
        driver.findElement(INPUT_LOGIN_PWD).sendKeys(pwd);
        driver.findElement(BUTTON_LOGIN).click();
    }

    public boolean loginValidation() {
        return driver.findElement(BUTTON_LOGOUT).isDisplayed();
    }

    public boolean loginFailedValidation() {
        return driver.findElement(LOGIN_ALERT).isDisplayed();
    }





}
