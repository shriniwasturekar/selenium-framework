package org.shriniwas.datareader;


import org.shriniwas.constants.FrameworkConstants;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.core.type.TypeReference;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class JsonDataReader implements DataReader {

    private static final String FILE =
            "testdata/json/login.json";

    public <T> List<T> getData(
            String fileName,
            String testName,
            Class<T> clazz) {

        try {

            ObjectMapper mapper = new ObjectMapper();

            String path =
                    FrameworkConstants.getJsonTestDataPath(fileName);

            InputStream stream =
                    Thread.currentThread()
                            .getContextClassLoader()
                            .getResourceAsStream(path);
            

            Map<String,List<T>> data =
                    mapper.readValue(
                            stream,
                            new TypeReference<Map<String,List<T>>>() {}
                    );

            return data.get(testName);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}