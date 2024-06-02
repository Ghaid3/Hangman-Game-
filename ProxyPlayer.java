/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;


public class ProxyPlayer implements IPlayer {

    private Player realPlayer;
    private String username;
    
     public ProxyPlayer(String username) {
        this.username = username;
    }

    public void login(String username, String password) {
        if (realPlayer == null) {
            realPlayer = new Player(username, password,0);
        }
    }

    @Override
    public int getScore() {
        if (realPlayer != null) {
            return realPlayer.getScore();
        }
        return 0;
    }
    
    @Override
    public void addScore(int score) {
        if (realPlayer != null) {
            realPlayer.addScore(score);
        }
    }

    @Override
    public String getUsername() {
        return username;
    }
}