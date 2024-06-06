package org.it.uniba.minima.Boundary;
import org.it.uniba.minima.Control.userInputFlow;
import org.it.uniba.minima.Database.DatabaseConnection;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.GUI.GameGUI;
import org.it.uniba.minima.GUI.Wordle;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WordleGame {
    private static final int MAX_LETTERS = 5;
    private static final int MAX_ATTEMPTS = 6;
    private int currentTry = 0;
    private final String GuessingWord;
    private String[] charGuessingWord;

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

    public void printSplittedText(String text) {
        if(checkLenght(text)){
            String[] newText = text.split("");
            Wordle game = GameGUI.getWordle();
            for (int j = 0; j < MAX_LETTERS; j++) {
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

        if (currentTry == MAX_ATTEMPTS) {
            DatabaseConnection.printFromDB("0", "Desert", "Sbagliato", "Sfinge", "0", "0");
            outputDisplayManager.displayText("> \"La parola corretta era: " + GuessingWord + "!\"");
            userInputFlow.Event = 0;
            Game game = Game.getInstance();
            game.getCurrentRoom().setState("Sbagliato");
            GameGUI.setImagePanel(game.getCurrentRoom().getName());
        }
    }

    public boolean checkLenght(String text) {
        if (text.length() != MAX_LETTERS) {
            outputDisplayManager.displayText("> \"Come puoi vedere, la parola è di esattamente 5 lettere!\"");
            return false;
        }
        return true;
    }

    public void changeBoxColor(int row, int col, Color color) {
        Wordle game = GameGUI.getWordle();
        game.setBoxColor(row, col, color);
    }

    public void checkGuess(String guess) {
        String[] charAlreadyGuessed = guess.split("");
        charGuessingWord = GuessingWord.split("");

        for(int i = 0; i < MAX_LETTERS; i++) {
            if (charGuessingWord[i].equals(charAlreadyGuessed[i])) {
                changeBoxColor(currentTry, i, new Color(23, 147, 10));
                charGuessingWord[i] = "";
                charAlreadyGuessed[i] = "";
            }
        }

        for (int i = 0; i < MAX_LETTERS; i++) {
            for (int j = 0; j < MAX_LETTERS; j++) {
                if (charGuessingWord[i].equals(charAlreadyGuessed[j]) && !charGuessingWord[i].isEmpty()) {
                    changeBoxColor(currentTry, j, new Color(178, 149, 22));
                    charGuessingWord[i] = "";
                    charAlreadyGuessed[j] = "";
                }
            }
        }

        for (int i = 0; i < MAX_LETTERS; i++) {
            if (!charAlreadyGuessed[i].isEmpty()) {
                changeBoxColor(currentTry, i, new Color(47, 55, 61));
            }
        }
    }
}
