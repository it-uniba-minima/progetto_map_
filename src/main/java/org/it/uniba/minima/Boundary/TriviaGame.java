package org.it.uniba.minima.Boundary;
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
     * The aliases of the 'true' answer.
     */
    private String[] possibleTrueAnswers = {"True", "T", "Vero", "V", "Si", "S"};
    /**
     * The aliases of the 'false' answer.
     */
    private String[] possibleFalseAnswers = {"False", "F", "Falso", "No", "N"};
    /**
     * The number of correct answers so far.
     */
    private int correctAnswers = 0;
    /**
     * The question.
     */
    private String question;
    /**
     * The correct answer.
     */
    private String correctAnswer;
    /**
     * The instance of the trivia game.
     */
    private static TriviaGame instance;

    /**
     * The getter of the instance.
     */
    public static TriviaGame getInstance() {
        if (instance == null) {
            instance = new TriviaGame();
        }
        return instance;
    }

    /**
     * Gets the question and the answer from the API.
     */
    public void getQAndA() {
        String urlToRead = "https://opentdb.com/api.php?amount=3&difficulty=easy&type=boolean";
        int maxAttempts = 3;

        // Try to get the question and answer from the API
        // if it fails 3 times, the player will have to talk to the mummy again
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

                JsonObject jsonObject = JsonParser.parseString(result.toString()).getAsJsonObject();

                question = jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("question").getAsString().replace("&quot;", "\"")
                                     .replace("&amp;", "&").replace("&apos;", "'").replace("&lt;", "<")
                                     .replace("&gt;", ">").replace("&#039;", "'").replace("&eacute;", "é").replace("&egrave;", "è");
                correctAnswer = jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("correct_answer").getAsString();

                displayQuestion(question);

                break;
            } catch (IOException e) {
                if (attempt == maxAttempts - 1) {
                    OutputDisplayManager.displayText("> Scusa nipotino, l'età fa brutti scherzi e non riesco a ricordare la domanda. Ti dispiace ricominciare? (Il numero di domande a cui hai risposto correttamente me lo ricordo però!)");
                    UserInputFlow.Event = 0;
                }
            }
        }
    }

    /**
     * Checks the guess of the player.
     *
     * @param guess the guess
     */
    public void checkGuess(String guess) {
        int guessType = checkAnswerIsLegit(guess);

        // If the answer is not valid, the player has to insert it again
        if (guessType == 3) {
            OutputDisplayManager.displayText("> Risposta non valida, riprova");
            return;
        }

        // If the answer is valid, assign the correct value to the guess
        guess = (guessType == 1) ? "True" : "False";

        // Check if the answer is correct
        // If it is, increment the number of correct answers and check if the trivia is won
        // Otherwise, the trivia is lost
        if (guess.equalsIgnoreCase(correctAnswer)) {
            correctAnswers++;

            // If the player has answered correctly 3 times, the game is won
            // Otherwise, the player has to answer another question
            if (correctAnswers == 3) {
                correctAnswers = 0;
                Game game = Game.getInstance();

                DatabaseConnection.printFromDB("0", "Stanza6", "Corretto", "Mummia", "0", "0");

                game.setRoomState("Stanza6", "Corretto");
                game.unlockCorridor("Stanza6", "Stanza10");
                UserInputFlow.Event = 0;
            } else {
                OutputDisplayManager.displayText("> \"Bravo nipotino, è corretto! Ancora " + (3 - correctAnswers) + ".\"");
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
     * Checks if the answer is valid.
     *
     * @param text the answer
     * @return the type of the answer (1 = true, 2 = false, 3 = invalid)
     */
    private int checkAnswerIsLegit(String text) {
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
     * Display question on the text panel.
     *
     * @param question the question
     */
    private void displayQuestion(String question) {
        OutputDisplayManager.displayText("> " + question);
        OutputDisplayManager.displayText("> Rispondi con Vero o Falso");
    }
}
