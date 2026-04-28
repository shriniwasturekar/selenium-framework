package org.shriniwas.datareader;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class TestDataManager {

    private static final ConcurrentHashMap<String, List<?>> CACHE =
            new ConcurrentHashMap<>();

    public static <T> List<T> getTestData(
            String className,
            String testName,
            Class<T> clazz) {

        String fileName =
                className.replaceFirst("Tests?$", "");

        DataReader reader =
                DataReaderFactory.getReader();

        return reader.getData(fileName, testName, clazz);


    }
}
