/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data_Access;

import Business.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//This is the PlayerDAO
public class PlayerRepository {

    String dbURL = "jdbc:derby://localhost:1527/GameDB";
    String username = "root";
    String password = "root";

    // Initialize the database connection
    public PlayerRepository() {
        try {
            Connection conn = DriverManager.getConnection(dbURL, username, password);
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    

   
    // Method to validate user credentials and insert if user doesn't exist
    public boolean validateUser(String playerUsername, String playerPassword) {
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            // Check if the user exists in the database
            String selectQuery = "SELECT COUNT(*) FROM player WHERE username = ?";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setString(1, playerUsername);
            ResultSet resultSet = selectStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            resultSet.close();

            if (count == 0) {
                // User doesn't exist, insert into the database with score 0
                String insertQuery = "INSERT INTO player (username, password, score) VALUES (?, ?, 0)";
                PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
                insertStatement.setString(1, playerUsername);
                insertStatement.setString(2, playerPassword);
                insertStatement.executeUpdate();
                return true; // User inserted successfully
            } else {
                // User exists, check if the password matches
                String getPasswordQuery = "SELECT password FROM player WHERE username = ?";
                PreparedStatement getPasswordStatement = conn.prepareStatement(getPasswordQuery);
                getPasswordStatement.setString(1, playerUsername);
                ResultSet passwordResultSet = getPasswordStatement.executeQuery();
                passwordResultSet.next();
                String storedPassword = passwordResultSet.getString(1);
                passwordResultSet.close();
                if (storedPassword.equals(playerPassword)) {
                    return true; // Valid credentials
                } else {
                    return false; // Invalid credentials
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // Error occurred
        }
    }

    public void updatePlayerScore(String playerUsername, int score) {
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            String sqlStat = "UPDATE player SET score = ? WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(sqlStat);
            ps.setInt(1, score);
            ps.setString(2, playerUsername);
            int result = ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public int getPlayerScore(String playerUsername) {
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            String sqlStat = "SELECT SCORE FROM player WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(sqlStat);
            ps.setString(1, playerUsername);
            ResultSet result = ps.executeQuery();
            if (result.next()){
                return result.getInt("score");
            }
            result.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
