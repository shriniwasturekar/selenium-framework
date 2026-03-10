package org.shriniwas.logging;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.shriniwas.driver.DriverManager;
import org.shriniwas.reports.*;

public class ExtentLogger {

    private ExtentLogger(){

    }

    public static void log(Status status,String message){
        ExtentTestManager.getTest().log(status, message);
    }

    public static void logInfo(String message){
        log(Status.INFO,message);

    }

    public static void logPass(String message){
        log(Status.PASS,message);
    }


    public static void logFail(String message){
        ExtentTestManager.getTest().fail(message,getScreenshotMedia());
    }

    public static void logSkip(String message){
        log(Status.SKIP,message);

    }

    public static void logWarning(String message){
        log(Status.WARNING,message);
    }

    private static Media getScreenshotMedia(){
        if (DriverManager.getDriver() == null) {
            return null;
        }
        String base64Img = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
        return MediaEntityBuilder.createScreenCaptureFromBase64String(base64Img).build();
    }


    public static void addScreenShot(){
        ExtentTestManager.getTest().info(getScreenshotMedia());
    }

}
