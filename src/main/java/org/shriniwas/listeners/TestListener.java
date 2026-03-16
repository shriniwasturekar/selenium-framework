package org.shriniwas.listeners;

import org.shriniwas.constants.FrameworkConstants;
import org.shriniwas.logging.ExtentLogger;
import org.shriniwas.logging.Log;
import org.shriniwas.reports.ExtentReportManager;
import org.shriniwas.reports.ExtentTestManager;
import org.shriniwas.utils.ConfigReader;
import org.shriniwas.utils.EmailBody;
import org.shriniwas.utils.EmailUtil;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.ArrayList;
import java.util.List;

public class TestListener implements ITestListener, ISuiteListener, IInvokedMethodListener {

    static int count_totalTCs;
    static int count_passedTCs;
    static int count_skippedTCs;
    static int count_failedTCs;
    static List<String> failedTests = new ArrayList<>();

    public String getTestName(ITestResult result){
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result){
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ISuite iSuite) {
        Log.info("**********Run Started**********");
        Log.info("Starting Suite ---> "+iSuite.getName());

        count_totalTCs = 0;
        count_passedTCs = 0;
        count_failedTCs = 0;
        count_skippedTCs = 0;

        failedTests.clear();

    }

    @Override
    public void onFinish(ISuite iSuite) {
        Log.info("End Suite --->"+iSuite.getName());
        Log.info("**********Run Finished**********");


        // Flush and send extent reports over email
        ExtentReportManager.flush();
        if(Boolean.parseBoolean(ConfigReader.get("email.enabled"))) {

            String body = EmailBody.buildExecutionSummary(
                    ConfigReader.get("env"),
                    ConfigReader.get("browser"),
                    count_totalTCs,
                    count_passedTCs,
                    count_failedTCs,
                    count_skippedTCs
            );

            EmailUtil.sendEmail(
                    body,
                    FrameworkConstants.EXTENT_REPORT_FILE_PATH
            );
        }
    }

    @Override
    public void onTestStart(ITestResult iTestResult){
        Log.info("Starting Test Execution for TC : " + getTestName(iTestResult));
        count_totalTCs++;
        ExtentTestManager.createExtentTest(getTestName(iTestResult),getTestDescription(iTestResult));

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult){
        Log.info("Test case : " + getTestName(iTestResult) + " is passed.");
        count_passedTCs++;
        ExtentLogger.logPass("Test case : " + getTestName(iTestResult) + " is passed.");

    }

    @Override
    public void onTestFailure(ITestResult iTestResult){
        Log.error("Test case : " + getTestName(iTestResult) + " is failed.");
        Log.error(iTestResult.getThrowable());
        count_failedTCs++;

        failedTests.add(
                getTestName(iTestResult) + " -> " +
                        iTestResult.getThrowable().getMessage()
        );

        ExtentLogger.logFail("Test case : " + getTestName(iTestResult) + " is failed.");

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult){
        Log.warn("Test case : " + getTestName(iTestResult) + " is skipped.");
        count_skippedTCs++;
        ExtentLogger.logSkip("Test case : " + getTestName(iTestResult) + " is skipped.");

    }

}
