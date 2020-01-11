package me.ankhell.selenMail.sql;

import me.ankhell.selenMail.config.Config;

import java.sql.*;
import java.util.ArrayList;

import static me.ankhell.selenMail.config.Config.dbData;

public class GetEmailDataFromDB {

    public static ArrayList<Config.Email> getData() {

        ArrayList<Config.Email> localEmailList = new ArrayList<>();

        String query = "SELECT * FROM emails";

        try (
                Connection connection = DriverManager.getConnection(dbData.url, dbData.username, dbData.password);
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)
        ) {
            while (rs.next()) {
                localEmailList.add(
                        new Config.Email(rs.getString("username"), rs.getString("password")));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        return localEmailList;
    }

}
