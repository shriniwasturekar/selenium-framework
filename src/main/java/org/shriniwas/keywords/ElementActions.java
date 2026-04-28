package org.shriniwas.keywords;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.shriniwas.driver.DriverManager;
import org.shriniwas.logging.ExtentLogger;
import org.shriniwas.logging.Log;

import java.sql.Driver;
import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

public final class ElementActions {

    private static final int DEFAULT_TIMEOUT_SECONDS = 15;

    private ElementActions() {
    }

    /**
     * Convert the By object to the WebElement
     *
     * @param by is an element of type By
     * @return Returns a WebElement object
     */

    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    public static boolean isElementVisible(By by){

        try{
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    public static WebElement waitForElementVisible(By by) {

        try{
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
            if(isElementVisible(by)){
                return  wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            } else {
                scrollToElement(by);
                return  wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            }

        } catch (TimeoutException e) {
            String message = "Element not visible after " + DEFAULT_TIMEOUT_SECONDS + " seconds. Locator: " + by;
            Log.error(message, e);

            throw new RuntimeException(message, e); // Fail fast
        } catch (Exception e) {
            String message = "Unexpected error while waiting for element. Locator: " + by;
            Log.error(message, e);
            throw new RuntimeException(message, e);
        }

    }

    public static WebElement waitForElementClickable(By by) {
        try{
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
            return  wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (TimeoutException e) {
            String message = "Element not visible or clickable after " + DEFAULT_TIMEOUT_SECONDS + " seconds. Locator: " + by;
            Log.error(message, e);
            throw new RuntimeException(message, e);
        } catch (Exception e) {
            String message = "Unexpected error while waiting for element. Locator: " + by;
            Log.error(message, e);
            throw new RuntimeException(message, e);
        }


    }

    public static void waitForUrlContains(String value) {
        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.urlContains(value));
    }

    public static void waitForTitle(String value) {
        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.titleIs(value));
    }

    public static int waitForNumberOfElementsMoreThan(By locator, int count) {
        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, count));
        return DriverManager.getDriver().findElements(locator).size();
    }

    public static void clickElement(By by) {
        WebElement element = null;
        try {
            element = waitForElementClickable(by);
            element.click();
        } catch (ElementClickInterceptedException e) {
            if(element!=null){
                JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
                executor.executeScript("arguments[0].click();", element);
            } else{
                String message = "Unexpected error while clicking on element. Locator: " + by;
                Log.error(message, e);
                throw new RuntimeException(message, e);
            }

        } catch (Exception e) {
            String message = "Unexpected error while clicking on element. Locator: " + by;
            Log.error(message, e);
            throw new RuntimeException(message, e);
        }

    }

    public static void setText(By locator, String value) {

        WebElement element = waitForElementVisible(locator);
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.DELETE);
        if (value != null && !value.isBlank()) {
            element.sendKeys(value);
        }
    }

    public static boolean isElementDisplayed(By locator) {
        return waitForElementVisible(locator).isDisplayed();
    }

    public static List<WebElement> getElements(By locator) {
        return DriverManager.getDriver().findElements(locator);
    }

    public static String getPageText() {
        Object text = ((JavascriptExecutor) DriverManager.getDriver())
                .executeScript("return document.body ? document.body.innerText : '';");
        return text == null ? "" : text.toString();
    }

    public static String getCurrentUrl() {
        return DriverManager.getDriver().getCurrentUrl();
    }

    public static String getPageTitle() {
        return DriverManager.getDriver().getTitle();
    }

    public static void scrollToElement(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));
    }
}
