package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterAndLogin extends Page{

    public RegisterAndLogin(WebDriver driver) {
        super(driver, Pages.URL_REGISTER_AND_LOGIN.getURL());
    }

    //Registration testdata - VALID
    public final String REGISTER_VALID_USER = "tester";
    public final String REGISTER_VALID_PWD = "1234";
    public final String REGISTER_VALID_EMAIL = "testercc@whatever.com";
    public final String REGISTER_VALID_DESCRIPTION = "";

    //Login testdata - VALID
    private final String LOGIN_USERNAME = "beckz";
    private final String LOGIN_PWD = "30y123";
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
        findElementOnPage(BUTTON_TERMS_AND_CONDITIONS).click();
    }

    public void registration() {
        findElementOnPage(BUTTON_REGISTER_FORM).click();
        findElementOnPage(INPUT_REGISTER_NAME).sendKeys(REGISTER_VALID_USER);
        findElementOnPage(INPUT_REGISTER_PWD).sendKeys(REGISTER_VALID_PWD);
        findElementOnPage(INPUT_REGISTER_EMAIL).sendKeys(REGISTER_VALID_EMAIL);
        findElementOnPage(INPUT_REGISTER_DESCRIPTION).sendKeys(REGISTER_VALID_DESCRIPTION);
        findElementOnPage(BUTTON_REGISTER).click();
    }

    public void invalidRegistration() {
        findElementOnPage(BUTTON_REGISTER_FORM).click();
        findElementOnPage(INPUT_REGISTER_NAME).sendKeys("");
        findElementOnPage(INPUT_REGISTER_PWD).sendKeys("");
        findElementOnPage(INPUT_REGISTER_EMAIL).sendKeys("");
        findElementOnPage(INPUT_REGISTER_DESCRIPTION).sendKeys("");
        findElementOnPage(BUTTON_REGISTER).click();
    }

    public boolean registerValidation() {
        return findElementOnPage(REGISTER_ALERT).getText().equals("User registered!");
    }
    public void switchToLogin() {
        findElementOnPage(BUTTON_LOGIN_FORM).click();
    }
    public void login() {
        findElementOnPage(INPUT_LOGIN_EMAIL).sendKeys(LOGIN_USERNAME);
        findElementOnPage(INPUT_LOGIN_PWD).sendKeys(LOGIN_PWD);
        findElementOnPage(BUTTON_LOGIN).click();
    }

    public void invalidLogin() {
        findElementOnPage(INPUT_LOGIN_EMAIL).sendKeys("");
        findElementOnPage(INPUT_LOGIN_PWD).sendKeys("");
        findElementOnPage(BUTTON_LOGIN).click();
    }

    public boolean loginValidation() {
        return findElementOnPage(BUTTON_LOGOUT).isDisplayed();
    }

    public boolean loginFaildValidation() {
        return findElementOnPage(LOGIN_ALERT).isDisplayed();
    }




}
