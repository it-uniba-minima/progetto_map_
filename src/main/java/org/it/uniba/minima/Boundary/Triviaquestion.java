package org.it.uniba.minima.Boundary;

import org.it.uniba.minima.Control.TriviaParser;
import org.it.uniba.minima.Control.userInputFlow;

import java.io.*;
import java.net.*;

public class Triviaquestion {

    public static String[] possibleAnswers = {"True", "False", "T", "F", "Vero", "Falso" , "V", "F", "Si" , "No", "S", "N"};
    public static int correctAnswers = 0;

    public static void getTriviaQuestion(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
         TriviaParser.parseJson(result.toString());
    }


    public static void startTrivia() throws Exception {
        Triviaquestion.getTriviaQuestion("https://opentdb.com/api.php?amount=1&category=18&difficulty=easy&type=boolean");
    }
    public static void displayQuestion(String question, String correctAnswer) throws Exception {
        outputDisplayManager.displayText("Per entrare nella stanza successiva devi rispondere correttamente a 3 domande (yes or no questions) di trivia!");
        outputDisplayManager.displayText(question);
        checkGuess(question, correctAnswer);

    }
    public static void checkGuess(String guess, String correctAnswer) throws Exception {
        for (String possibleAnswer : possibleAnswers) {
            if (guess.equalsIgnoreCase(possibleAnswer)) {
                outputDisplayManager.displayText("Hai indovinato la risposta!");
                if (correctAnswers == 3) {
                    outputDisplayManager.displayText("Hai risposto correttamente a 3 domande, hai vinto!");
                    userInputFlow.Event = 0;
                } else {
                    correctAnswers++;
                    startTrivia();
                }
            } else {
                outputDisplayManager.displayText("Hai sbagliato la risposta!");
                userInputFlow.Event = 0;
                correctAnswers = 0;
                startTrivia();
            }
        }
    }

}



