package org.shriniwas.driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager(){
        super();
    }

    public static WebDriver getDriver(){
        return driver.get();
    }

    public static void setDriver(WebDriver driver){
        DriverManager.driver.set(driver);
    }

    public static void quit(){
        WebDriver currentDriver = driver.get();
        if (currentDriver != null) {
            currentDriver.quit();
            driver.remove();   // To Remove reference from ThreadLocal
        }
    }

}
