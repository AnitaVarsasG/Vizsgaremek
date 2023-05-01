package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterAndLoginPage extends Page{

    //Constructor
    public RegisterAndLoginPage(WebDriver driver) {
        super(driver, Pages.URL_REGISTER_AND_LOGIN.getURL());
    }

    //Locators
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


    //Methods

    //Click on the "terms and conditions" button on the registration form.
    public void acceptTermsAndConditions() {
        driver.findElement(BUTTON_TERMS_AND_CONDITIONS).click();
    }

    //Fill in the registration form with the provided user, password, email, and description, and submits the form.
    public void registration(String user, String pwd, String email, String description) {
        driver.findElement(BUTTON_REGISTER_FORM).click();
        driver.findElement(INPUT_REGISTER_NAME).sendKeys(user);
        driver.findElement(INPUT_REGISTER_PWD).sendKeys(pwd);
        driver.findElement(INPUT_REGISTER_EMAIL).sendKeys(email);
        driver.findElement(INPUT_REGISTER_DESCRIPTION).sendKeys(description);
        driver.findElement(BUTTON_REGISTER).click();
    }

    //Check whether the registration was successful by looking for an element with the text "User registered!".
    public boolean registerValidation() {
        return driver.findElement(REGISTER_ALERT).getText().equals("User registered!");
    }

    //Switch the registration form to the login form by clicking on the "Login" button.
    public void switchToLogin() {
        driver.findElement(BUTTON_LOGIN_FORM).click();
    }

    //Fill in the login form with the provided email and password, and submits the form.
    public void login(String email, String pwd) {
        driver.findElement(INPUT_LOGIN_EMAIL).sendKeys(email);
        driver.findElement(INPUT_LOGIN_PWD).sendKeys(pwd);
        driver.findElement(BUTTON_LOGIN).click();
    }

    //Check whether the login was successful by looking for the presence of a "Logout" button.
    public boolean loginValidation() {
        return driver.findElement(BUTTON_LOGOUT).isDisplayed();
    }

    //Check whether the login failed by looking for the presence of alert message.
    public boolean loginFailedValidation() {
        return driver.findElement(LOGIN_ALERT).isDisplayed();
    }
}
