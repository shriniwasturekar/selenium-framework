package org.shriniwas.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.shriniwas.reports.ExtentTestManager;

public class Log {

    private static Logger logger(){
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[3].getClassName());
    }

    public static void info(String message){
        logger().info(message);
    }
}
