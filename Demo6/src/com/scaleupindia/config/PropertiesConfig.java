package com.scaleupindia.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConfig {
    private static final Properties properties = new Properties();
    private static final PropertiesConfig propertiesConfig = new PropertiesConfig();
    private PropertiesConfig(){
        try {
            FileReader fileReader = new FileReader("Demo6\\src\\resources\\database.properties");
            properties.load(fileReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static PropertiesConfig getInstance(){
        return propertiesConfig;
    }
    public  String getProperty(String key){
        return properties.getProperty(key);
    }
}
