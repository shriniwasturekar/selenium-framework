package listeners;

import org.shriniwas.constants.FrameworkConstants;
import org.shriniwas.reports.ExtentTestManager;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.shriniwas.utils.*;

public class Retry implements IRetryAnalyzer {
    private int count = 0;
    private static int maxTry = Integer.parseInt(FrameworkConstants.MAXTRY_FAILTEST);

    public boolean retry(ITestResult iTestResult){
        if(!iTestResult.isSuccess() && count<maxTry){
                ExtentTestManager.remove();
                count++;
                return true;
        }
        return false;
    }

}
