package org.shriniwas.datareader;

import org.shriniwas.utils.ConfigReader;

public class DataReaderFactory {

    public static DataReader getReader(){

        String source =
                ConfigReader.get("testdata.source");

        switch(source.toLowerCase()){

            case "json":
                return new JsonDataReader();

            case "excel":
                return new ExcelDataReader();

            default:
                throw new RuntimeException("Invalid source");
        }
    }
}
