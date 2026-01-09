package org.shriniwas.utils;

import java.io.InputStream;
import java.util.Properties;


public final class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        loadCommonConfig();
        loadEnvConfig();
        overrideWithSystemProps();
    }

    private static void loadCommonConfig() {
        load("config/common.properties");
    }

    private static void loadEnvConfig() {
        String env = System.getProperty("env", "qa");
        load("config/config-" + env + ".properties");
    }

    private static void load(String path) {
        try (InputStream is = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream(path)) {

            if (is == null) {
                throw new RuntimeException("Property file not found: " + path);
            }
            properties.load(is);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config: " + path, e);
        }
    }

    private static void overrideWithSystemProps() {
        System.getProperties().forEach((k, v) ->
                properties.put(k.toString(), v.toString()));
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
