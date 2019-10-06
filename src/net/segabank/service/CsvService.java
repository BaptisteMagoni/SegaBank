package net.segabank.service;

import com.mysql.cj.util.Util;
import org.w3c.dom.css.CSSValue;

import java.io.*;
import java.nio.Buffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.FileHandler;

public class CsvService {

    private static final String PROPS_FILE = "./resources/src.properties";
    private static final String FORMAT_CSV = "Heure;Agence;Compte;Action;Somme";

    private CsvService(){}

    public static Properties loadProperties() throws IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(PROPS_FILE)){
            props.load(fis);
        }
        return props;
    }

    public static void writeCsv(String[] args) throws IOException {
        Properties props = loadProperties();

        File file = new File(props.getProperty("scr.directory") + getNameFile() + ".csv");

        try{
            if(!file.exists()) {
                file.createNewFile();
                writeInCsv(file, FORMAT_CSV);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        writeInCsv(file, getTimeAction() + ";" + args[0] + ";" + args[1] + ";" + args[2] + ";" + args[3]);

    }

    public static Map<Integer, String> getAllFile() throws IOException {
        Properties props = loadProperties();
        File file = new File(props.getProperty("scr.directory"));
        Map<Integer, String> l = new HashMap<>();
        int i = 0;
        for(String csv : file.list())
            l.put(++i, csv);
        return l;
    }

    public static boolean exportCsv(String sourceLocation, String destLocation, String fileName){
        try{
            File source = new File(sourceLocation + "\\" + fileName);
            File destTest = new File(destLocation);
            if(!destTest.exists()){
                destTest.mkdir();
            }
            File dest = new File(destLocation + "\\" + fileName);
            Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
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
