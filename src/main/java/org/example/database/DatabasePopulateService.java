package org.example.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabasePopulateService {
    public static void main(String[] args) {
        String filePath = "src/sql/populate_db.sql";

        try (Connection connection = Database.getConnection();
             BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String str;
            while ((str = br.readLine()) != null) {
                if (!str.isEmpty() && str.endsWith(";")) {
                    try (PreparedStatement statement = connection.prepareStatement(str)) {
                        statement.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException("Error executing SQL: " + str, e);
                    }
                }
            }
            System.out.println("SQL script executed successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}



