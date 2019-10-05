package net.segabank.service;

import org.w3c.dom.css.CSSValue;

import java.io.*;
import java.nio.Buffer;
import java.text.SimpleDateFormat;
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

        File file = new File(props.getProperty("scr.directory") + getNameFile() + ".csv");

        try{
            if(!file.exists()) {
                file.createNewFile();
                writeInCsv(file, "Heure;Action;Somme");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        writeInCsv(file, getTimeAction() + ";" + args[0] + ";" + args[1]);

    }

    private static void writeInCsv(File file, String data) throws IOException {
        try(FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw)){
            bw.write(data + "\n");
        }
    }

    private static String getNameFile(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    private static String getTimeAction(){
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

}
