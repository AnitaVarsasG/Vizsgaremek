package com.codecool.vizsgaremek.enums;

public enum Pages {

    URL_REGISTER_AND_LOGIN ("https://demo.seleniumeasy.com/"),
    URL_SIMPLE_FORM ("https://demo.seleniumeasy.com/basic-first-form-demo.html"),
    URL_CHECKBOX ("https://demo.seleniumeasy.com/basic-checkbox-demo.html"),
    URL_SELECT_DROPDOWN ("https://demo.seleniumeasy.com/basic-select-dropdown-demo.html"),
    URL_RADIO_BUTTONS ("https://demo.seleniumeasy.com/basic-radiobutton-demo.html");
    private final String URL;

    public String getURL() {
        return URL;
    }

    Pages(String URL) {
        this.URL = URL;
    }
}
