package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PortfolioPage extends Page{

    // Constructor
    public PortfolioPage(WebDriver driver) {
        super(driver, Pages.URL_PORTFOLIO.getURL());
    }

    // Locators
    private final By BUTTON_NEXT = By.xpath("//*[@aria-label='Next']");
    private final By PROJECT_CONTENT = By.xpath("//*[@class='site-project-item-content']");

    // Methods
    // Return a list of titles of all projects
    public List<String> getProjectTitles() {
        List<String> titles = new ArrayList<>();

        while (true) {
            List<WebElement> projects = driver.findElements(PROJECT_CONTENT);
            for (WebElement project : projects) {
                WebElement title = project.findElement(By.xpath("./h3"));
                titles.add(title.getText());
            }
            // Verify that Next button is available
            try {
                driver.findElement(BUTTON_NEXT).click();
            } catch (Exception e) {
                break;
            }
        }
        return titles;
    }

    // Save the list of titles of projects into txt
    public void saveTitlesToTxt(List<String> list) {
        Path file = Paths.get("src/test/resources/projects.txt");
        try {
            Files.write(file, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Return a String array of saved titles
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
