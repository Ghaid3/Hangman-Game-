/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data_Access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class FoodCategory implements Category {
    String dbURL = "jdbc:derby://localhost:1527/GameDB";
    String username = "root";
    String password = "root";
    
    public FoodCategory(){
        try {
            Connection conn = DriverManager.getConnection(dbURL, username, password);
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public String getRandomWord() {
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            String sqlStat = "SELECT food FROM foods ORDER BY RANDOM() FETCH FIRST 1 ROW ONLY";
            PreparedStatement ps = conn.prepareStatement(sqlStat);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("food");
            } else {
                return null; // No plant names found
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null; // Error occurred
        }
    }
}
