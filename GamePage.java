package Presentation;

import Service.PlayerService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;


public class GamePage extends JFrame implements Observer {

    private JLabel wordLabel;
    private JLabel attemptsLabel;
    private JLabel resultLabel;
    private JTextField letterInput;
    private JButton submitButton;
    private int remainingAttempts = 3;
    private List<Character> guessedLetters;
    private String catogry;
    private String word;
    
    public GamePage(PlayerService ps, String category) {
        ps.setGameObserver(this);
        this.catogry = category;
        wordLabel = new JLabel("Word: " + getWord(guessedLetters));
        resultLabel = new JLabel(" ");
        letterInput = new JTextField(10);
        submitButton = new JButton("Submit");
        attemptsLabel = new JLabel("Attempts: " + remainingAttempts);

        // Add action listener for submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                // Check if the letter is in the word
                boolean letterInWord = ps.guess(letterInput.getText().charAt(0));
                if (letterInWord) {
                    wordLabel.setText("Word: " + getWord(guessedLetters));
                } else {
                    attemptsLabel.setText("Attempts: " + remainingAttempts);
                }
                //If game is over, navigate to ResultPage
                int gameOver = ps.checkGameOver(); //--> 0: game continuing 1: game over and win -1: game over and loose
                if (gameOver == 1) {
                    new ResultPage(ps, true).setVisible(true);
                    dispose();
                    
                } else if (gameOver == -1) {
                    new ResultPage(ps, false).setVisible(true);
                    dispose();
                }
            }
        });

        // Initialize new panel and add components to it
        JPanel panel = new JPanel();

        panel.add(wordLabel);
        panel.add(letterInput);
        panel.add(submitButton);
        panel.add(attemptsLabel);

        // Add panel to frame
        add(panel);
        // Set frame properties
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private String getWord(List<Character> letters) {
        String word = "";
        for (char l : letters) {
            word = word + l + " ";
        }
        return word;
    }

    public void update(List<Character> guessed, int attempts) {
        this.guessedLetters = guessed;
        this.remainingAttempts = attempts;
    }
}
