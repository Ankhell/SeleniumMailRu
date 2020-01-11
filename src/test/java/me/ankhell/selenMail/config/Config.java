package me.ankhell.selenMail.config;

import me.ankhell.selenMail.sql.GetEmailDataFromDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

final public class Config {

    public static ArrayList<Email> emailList;
    public static DBData dbData;
    public static Message message;
    public static final Map<String,String> driverPaths = new HashMap<>();

    public static class Email{
        public final String username;
        public final String password;

        public Email(String username, String password){
            this.username = username;
            this.password = password;
        }

        @Override
        public String toString(){
            return "Username: " + this.username + " password: " + this.password;
        }
    }

    public static class DBData{
        public String url;
        public String username;
        public String password;

    }

    public static class Message{
        public String topic;
        public String messageText;
    }

    public static void getConfigFromFile(String configFileName,String dbConfigFileName){
        ConfigFile configFile = new ConfigFile(configFileName);
        ConfigFile dbConfig = new ConfigFile(dbConfigFileName);

        dbData = new Config.DBData();
        dbData.url = dbConfig.getProperty("url");
        dbData.username = dbConfig.getProperty("username");
        dbData.password = dbConfig.getProperty("password");
        emailList = GetEmailDataFromDB.getData();
        message = new Message();
        message.messageText = configFile.getProperty("message");
        message.topic = configFile.getProperty("messageTopic");
        driverPaths.put("gecko",configFile.getProperty("gecko"));
        driverPaths.put("chromedriver",configFile.getProperty("chromedriver"));
    }
}
