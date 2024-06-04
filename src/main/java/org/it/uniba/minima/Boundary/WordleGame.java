package org.it.uniba.minima.Boundary;

import com.google.gson.Gson;
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
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;


public class WordleGame {
    protected final int MaxAttempts = 6;
    protected int currentTry = 0;
    protected final int MaxLetters = 5;
    protected String GuessingWord;
    private WordleGame instance;

    public WordleGame() throws IOException {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL("https://api.wordnik.com/v4/words.json/randomWord?hasDictionaryDef=true&includePartOfSpeech=noun%2Cadjective%2Cverb&maxCorpusCount=-1&minDictionaryCount=1&maxDictionaryCount=-1&minLength=5&maxLength=5&api_key=xf9xa1hhzcl71v2q8c6di3dxyix9ou5oarrrksr6h5906harw");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    result.append(line);
                }
            }

            String json = result.toString();
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            String guessingWord = jsonObject.get("word").getAsString();

        } catch (Exception e) {
            e.printStackTrace();
        }
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




