package org.it.uniba.minima.InteractionManager;
import org.it.uniba.minima.DB_Web.DatabaseConnection;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.GUI.GameGUI;
import org.it.uniba.minima.GUI.HangmanGUI;

/**
 * The class that manages the hangman game.
 */
public class HangmanGame {
    /**
     * The phrase to guess.
     */
    private final String GUESSING_PHRASE = "TI TROVI NELLA PIRAMIDE DI OSIRIDE";
    /**
     * The length of the phrase.
     */
    private final int PHRASE_LENGTH = 34;
    /**
     * The instance of the HangmanGUI.
     */
    private final HangmanGUI hangmanGUI = HangmanGUI.getInstance();
    /**
     * The maximum number of attempts.
     */
    private final int MAX_ATTEMPTS = 7;
    /**
     * The current attempt.
     */
    private int currentAttempt = 0;
    /**
     * The guessed letters.
     */
    private char[] guessedLetters = new char[PHRASE_LENGTH];

    /**
     * Checks if the input is a letter or a phrase and calls the correct method.
     *
     * @param text the input
     */
    public void hangmanChecker(String text) {
        String newText = text.toUpperCase();

        // Check if the input is a letter or a phrase
        // If it's a letter, call checkLetter
        // If it's a phrase, call checkGuess
        // If it's neither, display an error message
        if (newText.length() == 1) {
            checkLetter(newText);
            currentAttempt++;
        } else if (newText.length() == PHRASE_LENGTH) {
            checkGuess(newText);
            currentAttempt++;
        } else {
            OutputDisplayManager.displayText("> Ti sembra di sentire il tuo prof. di italiano che ti dice: \"Una lettera per volta! (oppure la soluzione intera)\"");
        }

        // If the player has reached the maximum number of attempts, display a message and reset the hangaman game
        if (currentAttempt >= MAX_ATTEMPTS) {
            OutputDisplayManager.displayText("> La piuma si asciuga dell'inchiostro, hai esaurito i tentativi! Riprova.");

            currentAttempt = 0;
            UserInputFlow.Event = 0;

            Game game = Game.getInstance();

            game.setRoomState("Stanza4", "Sbagliato");
            GameGUI.setImagePanel(game.getCurrentRoom().getName());

            hangmanGUI.resetGuessedLetters();
        }
    }

    /**
     * Check if the phrase is correct.
     * If it is, the hangman game is won.
     *
     * @param text the input phrase
     */
    public void checkGuess(String text) {
        if (text.equals(GUESSING_PHRASE)) {
            hangmanGUI.setPhrase(GUESSING_PHRASE);

            DatabaseConnection.printFromDB("0", "Stanza4", "Corretto", "0", "0", "0");
            UserInputFlow.Event = 0;

            Game game = Game.getInstance();

            game.getCurrentRoom().setState("Corretto");
            game.unlockCorridor("Stanza4", "Stanza5");
            GameGUI.setImagePanel(game.getCurrentRoom().getName());

            hangmanGUI.resetGuessedLetters();
        } else {
            OutputDisplayManager.displayText("> Provi a scrivere la frase, ma si cancella immediatamente, è sbagliata!");
        }
    }

    /**
     * Run various checks on the letter.
     *
     * @param text the letter
     */
    public void checkLetter(String text) {
        char letter = text.charAt(0);

        if (currentAttempt == 0) {
            hangmanGUI.resetGuessedLetters();
        }

        // Check if the letter has already been guessed
        for (int i = 0; i < PHRASE_LENGTH; i++) {
            if (guessedLetters[i] == letter) {
                OutputDisplayManager.displayText("> Provi a scrivere la lettera, ma la piuma non si muove, è già stata scritta!");
            }
        }

        boolean found = false;

        // Check if the letter is in the phrase
        for (int i = 0; i < GUESSING_PHRASE.length(); i++) {
            if (GUESSING_PHRASE.charAt(i) == letter) {
                guessedLetters[i] = letter;
                hangmanGUI.setLetter(GUESSING_PHRASE, letter);
                found = true;
            }
        }

        // If the letter is in the phrase, display a message and set the letter
        // Otherwise, display a message
        if (found) {
            OutputDisplayManager.displayText("> Scritta la lettera, sembra che non vada più via dal foglio, è corretta!");
            hangmanGUI.setLetter(GUESSING_PHRASE, letter);
        } else {
            OutputDisplayManager.displayText("> Scritta la lettera, si cancella immediatamente dal foglio, è sbagliata!");
        }
    }
}
