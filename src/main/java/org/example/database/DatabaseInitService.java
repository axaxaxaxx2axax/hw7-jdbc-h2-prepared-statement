package org.example.database;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitService {
    public static void main(String[] args) {
        String filePath = "src/sql/init_db.sql";
        StringBuilder sql = new StringBuilder();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String str;
            while ((str = br.readLine()) != null) {
                sql.append(str).append("\n");
            }
        } catch (IIOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = Database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql.toString());
            statement.execute();
            System.out.println("goooood");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
