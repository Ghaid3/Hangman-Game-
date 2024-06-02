/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentation;


import Service.PlayerService;
import javax.swing.*;
import java.awt.event.*;

public class ResultPage extends JFrame {
    private JLabel resultLabel;
    private JLabel scoreLabel;
    private JButton newGameButton;

    public ResultPage(PlayerService ps, boolean won) {
        // Initialize components and layout
        resultLabel = new JLabel(won ? "You win!" : "You lose!");
        scoreLabel = new JLabel("Your score: " + ps.getPlayerScore());
        newGameButton = new JButton("New Game");
        
        // Add action listener for new game button
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // Navigate to CategoryPage to begin new game
                new CategoryPage(ps).setVisible(true);
                dispose();
            }
        });
        
        // Initialize new panel and add components to it
        JPanel panel = new JPanel();
        
        panel.add(resultLabel);
        panel.add(scoreLabel);
        panel.add(newGameButton);
        
        // Add panel to frame
        add(panel);

        // Set frame properties
        setSize(300,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
