package org.it.uniba.minima.Boundary;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.it.uniba.minima.Control.GameManager;
import org.it.uniba.minima.Control.userInputFlow;
import org.it.uniba.minima.Database.DatabaseConnection;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.GUI.GameGUI;
import org.it.uniba.minima.GUI.Wordle;

import javax.xml.crypto.Data;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class WordleGame {
    protected final int MaxAttempts = 6;
    protected int currentTry = 0;
    protected final int MaxLetters = 5;
    protected String GuessingWord;
    private WordleGame instance;

    public WordleGame() throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL("https://random-word-api.herokuapp.com/word?lang=it&length=5");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        GuessingWord = result.toString().replace("[", "").replace("]", "").replace("\"", "").toUpperCase();
    }

    public void checkAttempts (int attempts) {
        if (attempts > MaxAttempts) {
           outputDisplayManager.displayText("Sei a corto di tentativi, la parola era " + GuessingWord);
        }
        //return 0;
    }

    public void printSplittedText(String text) {
        if(checkLenght(text)){
            String[] newText = SplittedText(text);
            Wordle game = GameGUI.getWordle();
            for (int j = 0; j < 5; j++) {
                game.setBoxText(currentTry, j, newText[j]);
            }
            checkGuess(text);
            currentTry++;
        }

        if (text.equals(GuessingWord)) {
            DatabaseConnection.printFromDB("0", "Desert", "Corretto", "Sfinge", "0", "0");
            userInputFlow.Event = 0;
            Game game = Game.getInstance();
            game.getCurrentRoom().setState("Corretto");
            game.unlockCorridor("Desert", "Stanza1");
            GameGUI.setImagePanel(game.getCurrentRoom().getName());
            return;
        }

        if (currentTry == MaxAttempts) {
            DatabaseConnection.printFromDB("0", "Desert", "Sbagliato", "Sfinge", "0", "0");
            outputDisplayManager.displayText("> \"La parola corretta era: " + GuessingWord + "!\"");
            userInputFlow.Event = 0;
            Game game = Game.getInstance();
            game.getCurrentRoom().setState("Sbagliato");
            GameGUI.setImagePanel(game.getCurrentRoom().getName());
        }
    }

    public boolean checkLenght(String text) {
        if (text.length() != 5) {
        outputDisplayManager.displayText("La parola inserita non è valida, inserire una parola di 5 lettere.");
            return false;
        }
         return true;
    }

    public String[] SplittedText(String text) {
        return text.split("");
    }

    public void changeBoxColor(int row, int col, Color color) {
        Wordle game = GameGUI.getWordle();
        game.setBoxColor(row, col, color);
    }

    public void checkGuess(String guess) {
        for (int i = 0; i < 5; i++) {
            if (guess.charAt(i) == GuessingWord.charAt(i)) {
                changeBoxColor(currentTry, i, new Color(24, 159, 10));
            } else if (GuessingWord.contains(String.valueOf(guess.charAt(i)))) {
                changeBoxColor(currentTry, i, new Color(213, 173, 0));
            }
            else {
                changeBoxColor(currentTry, i, new Color(52, 59, 66));
            }
        }
    }
}




