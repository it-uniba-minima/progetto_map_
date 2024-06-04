package org.it.uniba.minima.Boundary;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.it.uniba.minima.Control.GameManager;
import org.it.uniba.minima.Control.userInputFlow;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.GUI.GameGUI;
import org.it.uniba.minima.GUI.Wordle;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class WordleGame {
    protected final int MaxAttempts = 6;
    protected int currentTry = 0;
    protected final int MaxLetters = 5;
    protected String GuessingWord;
    protected String[] charGuessingWord;
    private WordleGame instance;

    public WordleGame() {
    StringBuilder result = new StringBuilder();
        try {
            URL url = new URL("https://api.wordnik.com/v4/words.json/randomWord?hasDictionaryDef=true&includePartOfSpeech=noun%2Cadjective%2Cverb&maxCorpusCount=-1&minDictionaryCount=1&maxDictionaryCount=-1&minLength=5&maxLength=5&api_key=xf9xabc1q8xk38n6g4l9g5y20mm5ez9tkguat9pv7szu9edharw");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    result.append(line);
                }
            }
            String json = result.toString();
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            GuessingWord = jsonObject.get("word").getAsString();
            GuessingWord = GuessingWord.toUpperCase();
            charGuessingWord = GuessingWord.split("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //GuessingWord = "HELLO";
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
            outputDisplayManager.displayText("Hai indovinato la parola!");
            userInputFlow.Event = 0;
            Game game = Game.getInstance();
            game.getCurrentRoom().setState("Solved");
            game.unlockCorridor("Desert", "Stanza1");
            GameGUI.setImagePanel(game.getCurrentRoom().getName());
            return;
        }

        if (currentTry == MaxAttempts) {
            outputDisplayManager.displayText("Hai esaurito i tentativi, la parola era " + GuessingWord);
            userInputFlow.Event = 0;
            Game game = Game.getInstance();
            game.getCurrentRoom().setState("Failed");
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
        String[] charAlreadyGuessed = guess.split("");
        for(int i = 0; i < 5; i++) {
            if (charGuessingWord[i].equals(charAlreadyGuessed[i])) {
                changeBoxColor(currentTry, i, new Color(24, 159, 10));
                charGuessingWord[i] = "";
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (charGuessingWord[i].equals(charAlreadyGuessed[j])) {
                    changeBoxColor(currentTry, i, new Color(255, 0, 0));
                    charGuessingWord[i] = "";
                }
            }
        }
    }
}
