package org.shriniwas.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.shriniwas.driver.DriverManager;

public final class ExtentTestManager {

    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static void createExtentTest(String testName, String description){
        ExtentTest test = ExtentReportManager.getInstance().createTest(testName, description);
        setTest(test);
    }

    public static void setTest(ExtentTest test){
        extentTest.set(test);
    }

    public static ExtentTest getTest(){
        return extentTest.get();
    }

    public static void remove(){
        extentTest.remove();
    }



}
