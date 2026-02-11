package org.shriniwas.constants;

import org.shriniwas.utils.ConfigReader;

import java.io.File;

public class FrameworkConstants {

    private FrameworkConstants(){

    }

    public static final String PROJECT_PATH = System.getProperty("user.dir") + File.separator;
    public static final String BROWSER = ConfigReader.get("browser");


    public static final String EXTENT_REPORT_NAME = ConfigReader.get("project.name") + "_" + ConfigReader.get("extentreport.name")+".html";
    public static final String EXTENT_REPORT_TITLE = ConfigReader.get("project.name") + "_" + ConfigReader.get("extentreport.name");
    public static final String EXTENT_REPORT_FOLDER = PROJECT_PATH + ConfigReader.get("extentreport.path");
    public static final String EXTENT_REPORT_FILE_PATH = EXTENT_REPORT_FOLDER + File.separator + EXTENT_REPORT_NAME;

    public static final String OVERRIDE_REPORT = ConfigReader.get("extentreport.override");

    public static final String MAXTRY_FAILTEST = ConfigReader.get("retry.count");


}

