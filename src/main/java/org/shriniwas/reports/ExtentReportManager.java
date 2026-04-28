package org.shriniwas.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import freemarker.template.utility.DateUtil;
import org.shriniwas.constants.FrameworkConstants;
import org.shriniwas.utils.ConfigReader;
import org.shriniwas.utils.DateUtils;

import java.io.File;
import java.util.Date;

public final class ExtentReportManager {

    private static ExtentReports extent ;
    private static String path;

    private ExtentReportManager(){}


    public static synchronized ExtentReports getInstance(){

        if(extent == null){
            if(FrameworkConstants.OVERRIDE_REPORT.trim().equalsIgnoreCase("no")){
                path = FrameworkConstants.EXTENT_REPORT_FILE_PATH;
            } else {
                path = FrameworkConstants.EXTENT_REPORT_FOLDER + File.separator + DateUtils.getCurrentDateTimeCustom("_") + "_" + FrameworkConstants.EXTENT_REPORT_NAME;

            }

            File reportFile = new File(path);
            File reportDirectory = reportFile.getParentFile();
            if (reportDirectory != null && !reportDirectory.exists() && !reportDirectory.mkdirs()) {
                throw new RuntimeException("Unable to create extent report directory: " + reportDirectory.getAbsolutePath());
            }

            extent = new ExtentReports();
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
            extent.attachReporter(sparkReporter);
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle(FrameworkConstants.EXTENT_REPORT_TITLE);
        }

        return extent;
    }

    public static String getReportPath(){
        return path;
    }

    public static synchronized void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}
