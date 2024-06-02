/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Business.HangmanGame;
import Business.IPlayer;
import Business.ProxyPlayer;
import Data_Access.PlayerRepository;
import Presentation.Observer;

public class PlayerService {
    private ProxyPlayer playerProxy;
    private GameService gameService;
    private HangmanGame game;
    PlayerRepository pr = new PlayerRepository();

    public PlayerService(){
        gameService = new GameService();
    }

    
    public boolean login(String username, String password) 
    {
        if(pr.validateUser(username, password)){
            playerProxy = new ProxyPlayer(username);
            playerProxy.login(username, password);
            playerProxy.addScore(pr.getPlayerScore(username));
            return true;
        }
        else{
            return false;
        }
    }
    
    
    public int getPlayerScore(){
        return playerProxy.getScore();
    }
    
    public void addPlayerScore(int score){
        playerProxy.addScore(score);
        pr.updatePlayerScore(playerProxy.getUsername(), playerProxy.getScore());
    }
    
    // if we need it in other pages (in presentation)
    public IPlayer getPlayerProxy() {
        return playerProxy;
    }
    
    public void playGame(String catogry) {
        game = gameService.createGame(catogry); 
    }
    
    public int checkGameOver() {
      return game.isGameOver();
    }
    
    public boolean guess(char c){
        boolean guessTruth = game.guess(c);
        if (guessTruth){addPlayerScore(1);}
        return guessTruth;
    }
    
    public void setGameObserver(Observer o){
        game.setObserver(o);
    }
}