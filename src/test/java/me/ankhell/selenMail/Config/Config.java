package me.ankhell.selenMail.Config;

import me.ankhell.selenMail.SQL.GetEmailDataFromDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

final public class Config {

    public static ArrayList<Email> emailList;
    public static DBData dbData;
    public static Message message;
    public static Map<String,String> driverPaths = new HashMap<>();

    public static class Email{
        public String username;
        public String password;

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
        dbData.url = dbConfig.getProperty("URL");
        dbData.username = dbConfig.getProperty("USERNAME");
        dbData.password = dbConfig.getProperty("PASSWORD");
        emailList = GetEmailDataFromDB.getData();
        message = new Message();
        message.messageText = configFile.getProperty("MESSAGE");
        message.topic = configFile.getProperty("MESSAGE_TOPIC");
        driverPaths.put("gecko",configFile.getProperty("GECKO"));
        driverPaths.put("chromedriver",configFile.getProperty("CHROMEDRIVER"));
    }
}
