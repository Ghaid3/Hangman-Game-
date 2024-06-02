/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentation;

import Business.IPlayer;
import Business.ProxyPlayer;
import Service.PlayerService;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage extends JFrame{
    
    //----------
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    //private Player player;
    private PlayerService ps;
    
    public LoginPage(PlayerService ps) {
        // player = new Player();
        // Initialize components and layout
        usernameLabel = new JLabel("Username: ");
        usernameField = new JTextField(20);
        passwordLabel = new JLabel("Password: ");
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        // Add action listener for login button
        loginButton.addActionListener((ActionEvent e) -> {
            // Perform login logic and navigate to CategoryPage
            boolean completeLogin = ps.login(usernameField.getText(), passwordField.getText());

            if (completeLogin) {
                CategoryPage cp = new CategoryPage(ps);
                cp.setVisible(true);
                dispose();
            }
        });

        // Initialize new panel and add components to it
        JPanel panel = new JPanel();

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        
        // add panel to frame
        add(panel);
        
        // Set frame properties
        setTitle("Login");
        setSize(300,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }    
}
