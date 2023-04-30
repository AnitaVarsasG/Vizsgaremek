package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Portfolio extends Page{

    public Portfolio(WebDriver driver) {
        super(driver, Pages.URL_PORTFOLIO.getURL());
    }

    private final By BUTTON_NEXT = By.xpath("//*[@aria-label='Next']");
    private final By PROJECT_CONTENT = By.xpath("//*[@class='site-project-item-content']");

    public List<String> getProjectTitles() {
        List<String> titles = new ArrayList<>();

        while (true) {
            List<WebElement> projects = driver.findElements(PROJECT_CONTENT);
            for (WebElement project : projects) {
                WebElement title = project.findElement(By.xpath("./h3"));
                titles.add(title.getText());
            }
            try {
                driver.findElement(BUTTON_NEXT).click();
            } catch (Exception e) {
                break;
            }
        }
        return titles;
    }

    public void saveTitlesToTxt(List<String> list) {
        Path file = Paths.get("src/test/resources/projects.txt");
        try {
            Files.write(file, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] readTitlesTxt() throws IOException {
        List<String> projects = new ArrayList<>();
        try {
            File text = new File("src/test/resources/projects.txt");
            Scanner scanner = new Scanner(text);
            while (scanner.hasNextLine()){
                String name = scanner.nextLine();
                projects.add(name);
            }
        } catch (Exception e) {
            System.out.println("File is not found");;
        }
        return projects.toArray(new String[0]);
    }

}
