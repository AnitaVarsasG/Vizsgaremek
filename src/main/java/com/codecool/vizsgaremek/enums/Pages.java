package com.codecool.vizsgaremek.enums;

public enum Pages {

    URL_REGISTER_AND_LOGIN ("https://lennertamas.github.io/roxo/"),
    URL_PORTFOLIO ("https://lennertamas.github.io/roxo/portfolio/"),
    URL_CONTACT ("https://lennertamas.github.io/roxo/contact/"),
    URL_ABOUT ("https://lennertamas.github.io/roxo/about/");

    private final String URL;

    public String getURL() {
        return URL;
    }

    Pages(String URL) {
        this.URL = URL;
    }
}
