package Business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import Data_Access.PlayerRepository;

@Entity
@Table(name = "Player")
public class Player implements IPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String username;
    private String password;
    private int score;
    PlayerRepository  pr;
    
    public Player() {
        // Default constructor required by Hibernate
    }
    
    public Player(String username, String password, int score) {
        this.username = username;
        this.password = password;
        this.score = score;
        this.pr = new PlayerRepository();
    }

    // Getters and setters
     public boolean login(String username, String password) {
        if(pr.validateUser(username, password)){
            addScore(pr.getPlayerScore(username));
            return true;
        }
        else{
            return false;
        }
    }
     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score = getScore()+ score;
    }
    
    // Other methods
}
