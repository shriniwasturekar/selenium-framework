package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.shriniwas.logging.ExtentLogger;
import org.shriniwas.logging.Log;
import org.shriniwas.reports.ExtentReportManager;
import org.shriniwas.reports.ExtentTestManager;
import org.testng.*;

public class TestListener implements ITestListener, ISuiteListener, IInvokedMethodListener {

    static int count_totalTCs;
    static int count_passedTCs;
    static int count_skippedTCs;
    static int count_failedTCs;

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

    }

    @Override
    public void onFinish(ISuite iSuite) {
        Log.info("End Suite --->"+iSuite.getName());
        Log.info("**********Run Finished**********");

        ExtentReportManager.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult){
        count_totalTCs = count_totalTCs+1;
        ExtentTestManager.createExtentTest(getTestName(iTestResult),getTestDescription(iTestResult));
        //ExtentTest test = ExtentReportManager.getInstance().createTest(getTestName(iTestResult),getTestDescription(iTestResult));
        //ExtentTestManager.setTest(test);

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult){
        Log.info("Test case : " + getTestName(iTestResult) + " is passed.");
        count_passedTCs = count_passedTCs + 1 ;
        ExtentLogger.logPass("Test case : " + getTestName(iTestResult) + " is passed.");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult){
        Log.error("Test case : " + getTestName(iTestResult) + " is failed.");
        Log.error(iTestResult.getThrowable());
        count_failedTCs = count_failedTCs + 1;
        //ExtentLogger.addScreenShot(); screenshot is being taken in logFail
        ExtentLogger.logFail("Test case : " + getTestName(iTestResult) + " is failed.");

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult){
        Log.warn("Test case : " + getTestName(iTestResult) + " is skipped.");
        count_skippedTCs = count_skippedTCs + 1;
        ExtentLogger.logSkip("Test case : " + getTestName(iTestResult) + " is skipped.");

    }


}
