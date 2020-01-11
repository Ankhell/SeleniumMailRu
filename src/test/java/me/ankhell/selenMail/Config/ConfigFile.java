package me.ankhell.selenMail.Config;

import java.io.IOException;
import java.util.Properties;

public class ConfigFile {
    final Properties configFile;

    public ConfigFile(String filename){
        configFile = new java.util.Properties();
        try {
            configFile.load(this.getClass().getClassLoader().
                    getResourceAsStream(filename));
        } catch (IOException eta) {
            System.out.println("Can't load config file! Terminating program");
            System.exit(1);
        }
    }

    public String getProperty(String key) {
        return this.configFile.getProperty(key);
    }
}
