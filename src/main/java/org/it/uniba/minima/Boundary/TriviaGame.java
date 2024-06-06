package org.it.uniba.minima.Boundary;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.it.uniba.minima.Control.UserInputFlow;
import org.it.uniba.minima.Database.DatabaseConnection;
import org.it.uniba.minima.Entity.Game;
import java.io.*;
import java.net.*;

/**
 * The class that manages the trivia game.
 */
public class TriviaGame {
    /**
     * The Possible true answers.
     */
    public static String[] possibleTrueAnswers = {"True", "T", "Vero", "V", "Si", "S"};
    /**
     * The Possible false answers.
     */
    public static String[] possibleFalseAnswers = {"False", "F", "Falso", "No", "N"};
    /**
     * The constant correctAnswers.
     */
    public static int correctAnswers = 0;
    private static String question;
    private static String correctAnswer;

    /**
     * Gets q and a.
     */
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
                question = jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("question").getAsString().replace("&quot;", "\"").replace("&amp;", "&").replace("&apos;", "'").replace("&lt;", "<").replace("&gt;", ">").replace("&#039;", "'").replace("&eacute;", "é").replace("&egrave;", "è");
                correctAnswer = jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("correct_answer").getAsString();
                displayQuestion(question);
                break;
            } catch (IOException e) {
                if (attempt == maxAttempts - 1) {
                    outputDisplayManager.displayText("> Scusa nipotino, l'età fa brutti scherzi e non riesco a ricordare la domanda. Ti dispiace ricominciare? (Il numero di domande a cui hai risposto correttamente me lo ricordo però!)");
                    UserInputFlow.Event = 0;
                }
            }
        }
    }

    /**
     * Check guess.
     *
     * @param guess the guess
     */
    public static void checkGuess(String guess) {
        int guessType = checkAnswerIsLegit(guess);
        if (guessType == 3) {
            outputDisplayManager.displayText("> Risposta non valida, riprova");
            return;
        }

        guess = (guessType == 1) ? "True" : "False";

        if (guess.equalsIgnoreCase(correctAnswer)) {
            correctAnswers++;
            if (correctAnswers == 3) {
                Game game = Game.getInstance();
                DatabaseConnection.printFromDB("0", "Stanza6", "Corretto", "Mummia", "0", "0");
                game.setRoomState("Stanza6", "Corretto");
                game.unlockCorridor("Stanza6", "Stanza10");
                UserInputFlow.Event = 0;
            } else {
                outputDisplayManager.displayText("> \"Bravo nipotino, è corretto! Ancora " + (3 - correctAnswers) + ".\"");
            }
        } else {
            correctAnswers = 0;
            Game game = Game.getInstance();
            DatabaseConnection.printFromDB("0", "Stanza6", "Sbagliato", "Mummia", "0", "0");
            game.setRoomState("Stanza6", "Sbagliato");
            UserInputFlow.Event = 0;
        }
    }

    /**
     * Check answer is legit int.
     *
     * @param text the text
     * @return the int
     */
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

    /**
     * Display question.
     *
     * @param question the question
     */
    public static void displayQuestion(String question) {
        outputDisplayManager.displayText("> " + question);
        outputDisplayManager.displayText("> Rispondi con Vero o Falso");
    }
}