package org.it.uniba.minima.Boundary;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.it.uniba.minima.GUI.GameGUI;
import org.it.uniba.minima.GUI.Wordle;

import java.awt.*;


public class WordleGame {
    protected static final int MaxAttempts = 6;
    protected static int currentTry = 0;
    protected static final int MaxLetters = 5;
    protected static String GuessingWord;
    private static WordleGame instance;

    public WordleGame() throws UnirestException {
        HttpResponse<String> response = Unirest.get("https://random-words5.p.rapidapi.com/getRandom?wordLength=5")
                .header("X-RapidAPI-Key", "5339375a8fmshfa0b0925e33764fp13145cjsn20bc586a6e62")
                .header("X-RapidAPI-Host", "random-words5.p.rapidapi.com")
                .asString();
        GuessingWord = response.getBody().toUpperCase();
    }


    public void checkAttempts (int attempts) {
        if (attempts > MaxAttempts) {
           outputDisplayManager.displayText("Sei a corto di tentativi, la parola era " + GuessingWord);
        }
        //return 0;
    }

    public static void printSplittedText(String text) {
        if (currentTry == MaxAttempts) {
            outputDisplayManager.displayText("Hai esaurito i tentativi, la parola era " + GuessingWord);
            return;
        }
        if(checkLenght(text)){
            String[] newText = SplittedText(text);
            Wordle game = GameGUI.getWordle();
            for (int j = 0; j < 5; j++) {
                game.setBoxText(currentTry, j, newText[j]);
            }
            checkGuess(text);
            currentTry++;
        }
    }

    public static boolean checkLenght(String text) {
        if (text.length() != 5) {
        outputDisplayManager.displayText("La parola inserita non è valida, inserire una parola di 5 lettere.");
            return false;
        }
         return true;
    }

    public static String[] SplittedText(String text) {
        return text.split("");
    }

    public static void changeBoxColor(int row, int col, Color color) {
        Wordle game = GameGUI.getWordle();
        game.setBoxColor(row, col, color);
    }

    public static void checkGuess(String guess) {
        for (int i = 0; i < 5; i++) {
            if (guess.charAt(i) == GuessingWord.charAt(i)) {
                changeBoxColor(currentTry, i, Color.GREEN);
            } else if (GuessingWord.contains(String.valueOf(guess.charAt(i)))) {
                changeBoxColor(currentTry, i, Color.YELLOW);
            }
            else {
                changeBoxColor(currentTry, i, Color.GRAY);
            }
        }
    }
}




