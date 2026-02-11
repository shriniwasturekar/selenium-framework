package org.shriniwas.logging;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.shriniwas.driver.DriverManager;
import org.shriniwas.reports.*;

public class ExtentLogger {

    public static void logInfo(String message){
        ExtentTestManager.getTest().info(message);
    }

    public static void logPass(String message){
        ExtentTestManager.getTest().pass(message);
    }

    public static void logFail(String message){
        ExtentTestManager.getTest().fail(message);
    }

    public static void logWarning(String message){
        ExtentTestManager.getTest().warning(message);
    }

    public static void addScreenShot(){
        String base64Image = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
        ExtentTestManager.getTest().log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());

    }

}
