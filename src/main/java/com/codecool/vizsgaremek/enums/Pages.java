package com.codecool.vizsgaremek.enums;

public enum Pages {

    URL_REGISTER_AND_LOGIN ("https://lennertamas.github.io/roxo/");

    private final String URL;

    public String getURL() {
        return URL;
    }

    Pages(String URL) {
        this.URL = URL;
    }
}
