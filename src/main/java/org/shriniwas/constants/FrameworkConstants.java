package org.shriniwas.constants;

import org.shriniwas.utils.ConfigReader;

import java.io.File;

public class FrameworkConstants {

    private FrameworkConstants(){

    }


    // PROJECT ROOT
    public static final String PROJECT_PATH =
            System.getProperty("user.dir") + File.separator;

    // BROWSER
    public static final String BROWSER =
            ConfigReader.get("browser");

    // ENVIRONMENT
    public static final String ENV =
            ConfigReader.get("env");

    // ===============================
    // REPORT CONFIGURATION
    // ===============================


    public static final String EXTENT_REPORT_NAME = ConfigReader.get("project.name") + "_" +
            ConfigReader.get("extentreport.name")+".html";

    public static final String EXTENT_REPORT_TITLE = ConfigReader.get("project.name") + "_" +
            ConfigReader.get("extentreport.name");

    public static final String EXTENT_REPORT_FOLDER =
            PROJECT_PATH + ConfigReader.get("extentreport.folder");

    public static final String EXTENT_REPORT_FILE_PATH =
            EXTENT_REPORT_FOLDER + File.separator + EXTENT_REPORT_NAME;

    public static final String OVERRIDE_REPORT = ConfigReader.get("extentreport.override");

    // ===============================
    // RETRY CONFIGURATION
    // ===============================

    public static final String MAXTRY_FAILTEST = ConfigReader.get("retry.count");


    // ===============================
    // TEST DATA PATHS
    // ===============================

    private static final String TESTDATA_PATH =
            "testdata";

    private static final String JSON_FOLDER =
            TESTDATA_PATH + "/json";

    private static final String EXCEL_FOLDER =
            TESTDATA_PATH + "/excel";

    public static String getJsonTestDataPath(String fileName) {

        return JSON_FOLDER +
                "/" +
                ENV +
                "/" +
                fileName +
                ".json";
    }

    public static String getExcelTestDataPath(String fileName) {

        return EXCEL_FOLDER +
                "/" +
                ENV +
                "/" +
                fileName +
                ".xlsx";
    }


}

