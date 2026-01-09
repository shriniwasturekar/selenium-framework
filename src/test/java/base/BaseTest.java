package base;

import org.openqa.selenium.WebDriver;
import org.shriniwas.driver.BrowserFactory;
import org.shriniwas.driver.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.shriniwas.utils.ConfigReader;

import java.time.Duration;

public class BaseTest {



    @BeforeMethod
    public void setup() {

        WebDriver driver = BrowserFactory.valueOf(ConfigReader.get("browser").toUpperCase()).createDriver();
        DriverManager.setDriver(driver);
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().get(ConfigReader.get("app.url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }
}
