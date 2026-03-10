package org.shriniwas.dataprovider;

import org.shriniwas.constants.FrameworkConstants;
import org.shriniwas.utils.ConfigReader;
import org.testng.annotations.DataProvider;
import org.shriniwas.datareader.TestDataManager;

import java.lang.reflect.Method;
import java.util.List;


    public class FrameworkDataProvider {

        @DataProvider(name="getData", parallel=true)
        public static Object[][] getData(Method method){

            String className =
                    method.getDeclaringClass().getSimpleName();

            String testName =
                    method.getName();

            Class<?> paramType =
                    method.getParameterTypes()[0];

            List<?> data =
                    TestDataManager.getTestData(
                            className,
                            testName,
                            paramType
                    );

            Object[][] result =
                    new Object[data.size()][1];

            for(int i=0;i<data.size();i++){
                result[i][0] = data.get(i);
            }

            return result;
        }
    }