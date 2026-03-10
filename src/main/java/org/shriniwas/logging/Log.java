package org.shriniwas.logging;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.shriniwas.reports.ExtentTestManager;

@Slf4j
public class Log {

    private static Logger logger(){
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[3].getClassName());
    }

    public static void info(String message){
        logger().info(message);
    }
    public static void info(Object message){
        logger().info(message);
    }

    public static void info(String message, Throwable t){
        logger().info(message, t);
    }

    public static void warn(String message){
        logger().warn(message);
    }
    public static void warn(Object message){
        logger().warn(message);
    }

    public static void warn(String message, Throwable t){
        logger().warn(message, t);
    }

    public static void error(String message){
        logger().error(message);
    }
    public static void error(Object message){
        logger().error(message);
    }

    public static void error(String message, Throwable t){
        logger().error(message, t);
    }

    public static void debug(String message){
        logger().debug(message);
    }

    public static void debug(Object message){
        logger().debug(message);
    }


}
