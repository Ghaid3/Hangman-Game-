/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentation;

import Service.PlayerService;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CategoryPage extends JFrame{
    private JComboBox<String> categoryComboBox;
    private JButton selectButton;

    public CategoryPage(PlayerService ps) {
         // Initialize components and layout
        String[] categories = {"Foods", "Plants", "Names"};
        categoryComboBox = new JComboBox<>(categories);
        categoryComboBox.setPreferredSize(new Dimension(150, categoryComboBox.getPreferredSize().height));
        selectButton = new JButton("Select");

        // Add action listener for select button
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Navigate to GuessingPage with selected category
                ps.playGame((String) categoryComboBox.getSelectedItem());
                new GamePage(ps, (String) categoryComboBox.getSelectedItem()).setVisible(true);
                //new GuessingPage((String) categoryComboBox.getSelectedItem(), username).setVisible(true); //send player object to category page instead username
                dispose();
            }
        });
        
        // Initialize new panel and add components to it
        JPanel panel = new JPanel();
        
        panel.add(categoryComboBox);
        panel.add(selectButton);
        
        // Add panel to frame
        add(panel);

        // Set frame properties
        setSize(300,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
