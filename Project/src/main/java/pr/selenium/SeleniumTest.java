package pr.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class SeleniumTest {

    private SeleniumConfig config;
    private String url = "http://localhost:8082";

    public SeleniumTest() {
        config = new SeleniumConfig();
        config.getDriver().get(url);
    }

    public void closeWindow() {
        this.config.getDriver().close();
    }

    public String getTitle() {
        return this.config.getDriver().getTitle();
    }

}