package pr.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TestPage {

    private SeleniumConfig config;

    @FindBy(css = ".page-title")
    private WebElement title;

    public TestPage(SeleniumConfig config) {
        this.config = config;
        PageFactory.initElements(this.config.getDriver(), this);
    }

    public void navigate() {
        this.config.navigateTo("http://localhost:8082");
    }

    public String getPageTitle() {
        return title.getAttribute("title");
    }
}

