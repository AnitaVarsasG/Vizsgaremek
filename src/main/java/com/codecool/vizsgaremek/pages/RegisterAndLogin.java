package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.WebDriver;

public class RegisterAndLogin extends Page{

    public RegisterAndLogin(WebDriver driver) {
        super(driver, Pages.URL_REGISTER_AND_LOGIN.getURL());
    }

}
