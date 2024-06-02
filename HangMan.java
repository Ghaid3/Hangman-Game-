
import Presentation.LoginPage;
import Service.PlayerService;
import javax.swing.JFrame;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

public class HangMan {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
       PlayerService ps = new PlayerService();
       new LoginPage(ps).setVisible(true);
    }
}
