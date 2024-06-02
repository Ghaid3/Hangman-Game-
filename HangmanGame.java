/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import Data_Access.Category;
import Data_Access.CategoryCreator;
import Data_Access.ConcreteCategoryCreator;
import Presentation.Observer;
import java.util.ArrayList;
import java.util.List;

public class HangmanGame implements Game{

    private String catogry;
    private String word;
    // or IPlayer ??
    //private IPlayer player;
    private int chances = 3;
    private ArrayList<Character> guessedLetters;
    private Observer observer;

    public HangmanGame(String catogry) {
        this.catogry = catogry;
        CategoryCreator creator = new ConcreteCategoryCreator();
        Category theCatogry = creator.createCategory(this.catogry);
        this.word = theCatogry.getRandomWord();
        guessedLetters = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            guessedLetters.add('_');
        }
    }

    public String getWord() {
        return word;
    }

    public int getChances() {
        return chances;
    }

    public void setChances(int chances) {
        this.chances = chances;
    }

    public boolean guess(char ch) {
        boolean guessTruth = word.indexOf(ch) != -1;
        if (guessTruth) {
            List<Integer> positions = getPositionsOfChar(word, ch);
            for (int p : positions) {
                guessedLetters.set(p, ch);
            }
        } else {
            setChances(getChances() - 1);
        }
        notifyObserver();
        return (guessTruth);
    }

    public int isGameOver() {
        if (guessedLetters.indexOf('_') == -1) {
            return 1;
        } else if (chances == 0) {
            return -1;
        }
        return 0;

    }

    private List<Integer> getPositionsOfChar(String str, char ch) {
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                positions.add(i); // Positions are zero-indexed
            }
        }
        return positions;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
        notifyObserver();
    }

    @Override
    public void notifyObserver() {
        observer.update(guessedLetters, chances);
    }
}
