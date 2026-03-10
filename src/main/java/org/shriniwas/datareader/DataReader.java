package org.shriniwas.datareader;

import java.util.List;

public interface DataReader {

    <T> List<T> getData(String fileName, String testName ,Class<T> clazz);
}
