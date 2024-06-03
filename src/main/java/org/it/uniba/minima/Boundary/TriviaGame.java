package org.it.uniba.minima.Boundary;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.it.uniba.minima.Control.userInputFlow;

import java.io.*;
import java.net.*;

public class TriviaGame {
    public static String[] possibleTrueAnswers = {"True", "T", "Vero", "V", "Si", "S"};
    public static String[] possibleFalseAnswers = {"False", "F", "Falso", "No", "N"};
    public static int correctAnswers = 0;
    private static String question;
    private static String correctAnswer;

    public static void getQAndA() {
        String urlToRead = "https://opentdb.com/api.php?amount=3&difficulty=easy&type=boolean";
        int maxAttempts = 3;
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            try {
                URL url = new URL(urlToRead);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                StringBuilder result = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                }

                Gson gson = new Gson();
                JsonObject jsonObject = JsonParser.parseString(result.toString()).getAsJsonObject();
                question = jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("question").getAsString();
                correctAnswer = jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("correct_answer").getAsString();
                displayQuestion(question);
                break;
            } catch (IOException e) {
                System.err.println("Attempt " + (attempt + 1) + " failed. Retrying...");
                if (attempt == maxAttempts - 1) {
                    // This was the last attempt
                    System.err.println("All attempts failed. Please check your network connection.");
                    throw new RuntimeException("Error reading from URL", e);
                }
            }
        }
    }

    public static void checkGuess(String guess) {
        int guessType = checkAnswerIsLegit(guess);
        if (guessType == 3) {
            outputDisplayManager.displayText("Risposta non valida, riprova");
            return;
        }

        guess = (guessType == 1) ? "True" : "False";

        if (guess.equalsIgnoreCase(correctAnswer)) {
            outputDisplayManager.displayText("Hai indovinato la risposta!");
            correctAnswers++;
            if (correctAnswers == 3) {
                outputDisplayManager.displayText("Hai risposto correttament1e a 3 domande, hai vinto!");
                userInputFlow.Event = 0;
            }
        } else {
            outputDisplayManager.displayText("Risposta sbagliata. Riparla con il sacerdote per riprovare.");
            userInputFlow.Event = 0;
        }
    }

    public static int checkAnswerIsLegit(String text) {
        for (String s : possibleTrueAnswers) {
            if (text.equalsIgnoreCase(s)) {
                return 1;
            }
        }
        for (String s : possibleFalseAnswers) {
            if (text.equalsIgnoreCase(s)) {
                return 2;
            }
        }
        return 3;
    }

    public static void displayQuestion(String question) {
        outputDisplayManager.displayText(question);
        outputDisplayManager.displayText("Rispondi con Vero o Falso");
    }
}