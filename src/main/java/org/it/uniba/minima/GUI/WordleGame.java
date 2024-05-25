package org.it.uniba.minima.GUI;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import org.it.uniba.minima.Boundary.userInputManager;

public class WordleGame {
    /*
    public static void main(String[] args) throws UnirestException {
        HttpResponse<String> response = Unirest.get("https://random-words5.p.rapidapi.com/getRandom?wordLength=5")
                .header("X-RapidAPI-Key", "5339375a8fmshfa0b0925e33764fp13145cjsn20bc586a6e62")
                .header("X-RapidAPI-Host", "random-words5.p.rapidapi.com")
                .asString();

        if (response.getStatus() == 200) {
            System.out.println("Response: " + response.getBody());
        } else {
            System.out.println("Request failed with status code: " + response.getStatus());
        }
    }
    */

    protected static final String GuessingWord = "HELLO";
    protected static final int MaxAttempts = 6;
    protected static final int MaxLetters = 5;


    public String UserInput() {
        String input = userInputManager.getCurrentInput();
        String guess = input.trim().toUpperCase();
        if (guess.length() != MaxLetters) {
            System.out.println("Please enter a 5 letter word.");
            return UserInput();
        }
        splitWord(guess);
        return guess;
    }

    public String[] splitWord(String word) {
        String[] splitWord = word.split("");
        System.out.println(Arrays.deepToString(splitWord));
        return splitWord;
    }

    public void checkGuess(String guess) {
        int correct = 0;
        int wrong_index = 0;
        for (int i = 0; i < MaxLetters; i++) {
            if (guess.equals(GuessingWord)) {
                Win();
                break;
            }
            if (guess.charAt(i) == GuessingWord.charAt(i)) {
                correct++;
                System.out.println("Letter " + guess.charAt(i) + " is in the correct position.");
            }
            if(GuessingWord.contains(String.valueOf(guess.charAt(i))) && guess.charAt(i) != GuessingWord.charAt(i)) {
                System.out.println("Letter " + guess.charAt(i) + " is in the word but in the wrong position.");
                wrong_index++;
            }
        }
    }

    public void checkAttempts (int attempts) {
        if (attempts == MaxAttempts) {
            System.out.println("You have run out of attempts. The word was " + GuessingWord);
            StopGame();
        }
    }

    public void playGame() {
        int attempts = 0;
        while (attempts < MaxAttempts) {
            String guess = UserInput();
            checkGuess(guess);
            attempts++;
            checkAttempts(attempts);
        }
    }

    public void StopGame() {
        System.out.println("Game Over!");
    }

    public void Win() {
        System.out.println("You have guessed the word correctly!");
    }

}
