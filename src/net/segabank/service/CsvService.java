package net.segabank.service;

import org.w3c.dom.css.CSSValue;

import java.io.*;
import java.nio.Buffer;
import java.util.Date;
import java.util.Properties;

public class CsvService {

    private static final String PROPS_FILE = "./resources/src.properties";
    private CsvService(){}

    public static void writeCsv(String[] args) throws IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(PROPS_FILE)){
            props.load(fis);
        }
        File file = new File(props.getProperty("scr.directory.file"));
        try{
            if(!file.exists())
                file.createNewFile();
        }catch (Exception e){
            e.printStackTrace();
        }

        try(FileWriter fw = new FileWriter(file.getPath(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw)){

        }
    }

}
