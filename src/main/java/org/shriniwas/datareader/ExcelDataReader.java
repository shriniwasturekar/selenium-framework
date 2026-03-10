package org.shriniwas.datareader;


import org.apache.poi.ss.usermodel.*;
import org.shriniwas.constants.FrameworkConstants;
import org.shriniwas.utils.ConfigReader;
import tools.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.*;



public class ExcelDataReader implements DataReader {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> List<T> getData(String fileName,
                               String testName,
                               Class<T> clazz) {

        List<Map<String,String>> mapData = new ArrayList<>();

        try {

            String path =
                    FrameworkConstants.getExcelTestDataPath(fileName);

            InputStream stream =
                    Thread.currentThread()
                            .getContextClassLoader()
                            .getResourceAsStream(path);

            Workbook workbook = WorkbookFactory.create(stream);

            Sheet sheet = workbook.getSheetAt(0);

            DataFormatter formatter = new DataFormatter();

            Row header = sheet.getRow(0);

            for(int i=1;i<=sheet.getLastRowNum();i++){

                Row row = sheet.getRow(i);

                if(row == null)
                    continue;

                String tcName =
                        formatter.formatCellValue(row.getCell(0));

                if(!testName.equalsIgnoreCase(tcName))
                    continue;

                Map<String,String> map = new LinkedHashMap<>();

                for(int j=0;j<header.getLastCellNum();j++){

                    String key =
                            formatter.formatCellValue(header.getCell(j));

                    String value =
                            formatter.formatCellValue(row.getCell(j));

                    map.put(key,value);
                }

                mapData.add(map);
            }

            workbook.close();

        } catch (Exception e) {

            throw new RuntimeException("Excel read failed", e);
        }

        // Decide Map or POJO
        if(clazz.equals(Map.class)){
            return (List<T>) mapData;
        }

        List<T> result = new ArrayList<>();

        for(Map<String,String> row : mapData){
            result.add(mapper.convertValue(row,clazz));
        }

        return result;
    }
}