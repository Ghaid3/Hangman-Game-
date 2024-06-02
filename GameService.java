/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Business.HangmanGame;

/**
 *
 * @author smart
 */
public class GameService {
    
    public HangmanGame createGame(String catogry) {
        return new HangmanGame(catogry);
        
    }
    
}
