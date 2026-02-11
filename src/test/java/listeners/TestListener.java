package listeners;

import com.aventstack.extentreports.ExtentTest;
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
    public void onStart(ISuite suite) {

    }

    @Override
    public void onFinish(ISuite suite) {

    }

    public void onTestStart(ITestResult iTestResult){
        count_totalTCs = count_totalTCs+1;
        ExtentTest test = ExtentReportManager.getInstance().createTest(getTestName(iTestResult),getTestDescription(iTestResult));
        ExtentTestManager.setTest(test);

    }


}
