package me.ankhell.selenMail.SQL;

import me.ankhell.selenMail.Config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static me.ankhell.selenMail.Config.Config.*;

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
