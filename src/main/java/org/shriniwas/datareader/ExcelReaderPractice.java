package org.shriniwas.datareader;

import org.apache.poi.*;
import org.apache.poi.ss.usermodel.*;
import org.shriniwas.constants.FrameworkConstants;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ExcelReaderPractice implements DataReader {

    private static final ObjectMapper mapper = new ObjectMapper();

    public <T> List<T> getData(String fileName, String testName, Class<T> tClass){

        List<Map<String,String>> mapList = new ArrayList<>();

        String filePath = FrameworkConstants.getExcelTestDataPath(fileName);

        try{
            InputStream stream = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream(filePath);

            Workbook workbook = WorkbookFactory.create(stream);

            Sheet sheet = workbook.getSheetAt(0);

            Row header = sheet.getRow(0);

            DataFormatter dataFormatter = new DataFormatter();

            for(int i=1; i<=sheet.getLastRowNum();i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }

                String tcName = dataFormatter.formatCellValue(row.getCell(0));

                if (!tcName.equalsIgnoreCase(testName)) {
                    continue;
                }

                Map<String, String> map = new LinkedHashMap<>();
                for (int j = 1; j < header.getLastCellNum(); j++) {


                    String key = dataFormatter.formatCellValue(header.getCell(j));
                    String value = dataFormatter.formatCellValue(row.getCell(j));

                    map.put(key, value);


                }
                mapList.add(map);

            }

            workbook.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if(tClass.equals(Map.class)){
            return (List<T>)mapList;
        }

        List<T> jsonResult = new ArrayList<>();

        for (Map<String, String> row : mapList){
            jsonResult.add(mapper.convertValue(row,tClass));
        }
        return jsonResult ;
    }

}
