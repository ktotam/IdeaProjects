package pr;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pr.app.Application;
import pr.models.User;
import pr.repositories.UsersRepository;
import pr.selenium.SeleniumTest;
import pr.services.UsersService;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class testingTest {

    private static SeleniumTest seleniumTest;
    private String expectedTitle = "Sign In";

    @BeforeClass
    public static void setUp() {
        seleniumTest = new SeleniumTest();
    }

    @AfterClass
    public static void tearDown() {
        seleniumTest.closeWindow();
    }

    @Test
    public void runTest() {
        String actualTitle = seleniumTest.getTitle();
        assertNotNull(actualTitle);
        assertEquals(expectedTitle, actualTitle);
    }

}
