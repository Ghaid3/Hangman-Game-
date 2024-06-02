/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Data_Access;

/**
 *
 * @author s130690
 */
//DAO class
public interface DAO<T>
{
     void  validateUser(String playerUsername, String playerPassword);
     void updatePlayerScore(String playerUsername, int score);
     int getPlayerScore(String playerUsername);
    
}
