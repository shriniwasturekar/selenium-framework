package org.shriniwas.datareader;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class TestDataManager {

    private static final ConcurrentHashMap<String,Object> CACHE =
            new ConcurrentHashMap<>();

    public static <T> List<T> getTestData(
            String className,
            String testName,
            Class<T> clazz) {

        String fileName =
                className.replace("Test","").toLowerCase();

        String cacheKey =
                fileName + "_" + testName + "_" + clazz.getName();

        return (List<T>) CACHE.computeIfAbsent(cacheKey, key -> {

            DataReader reader =
                    DataReaderFactory.getReader();

            return reader.getData(fileName,testName,clazz);

        });
    }
}