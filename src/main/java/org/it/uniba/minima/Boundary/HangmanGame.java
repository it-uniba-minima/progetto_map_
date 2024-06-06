package org.it.uniba.minima.Boundary;

import org.it.uniba.minima.Control.UserInputFlow;
import org.it.uniba.minima.Database.DatabaseConnection;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.GUI.GameGUI;
import org.it.uniba.minima.GUI.ImpiccatoGUI;

/**
 * The type Hangman game.
 */
public class HangmanGame {
    private final String GUESSING_PHRASE = "TI TROVI NELLA PIRAMIDE DI OSIRIDE";
    private final int PHRASE_LENGTH = 34;
    private final ImpiccatoGUI impiccatoGUI = ImpiccatoGUI.getInstance();
    private final int MAX_ATTEMPTS = 7;
    private int currentAttempt = 0;
    private char[] guessedLetters = new char[PHRASE_LENGTH];

    /**
     * Hangman flow.
     *
     * @param text the text
     */
    public void HangmanFlow(String text) {
        String newText = text.toUpperCase();
        if (newText.length() == 1) {
            if (!checkLetter(newText)) {
                currentAttempt++;
            }
        } else if (newText.length() == PHRASE_LENGTH) {
            if (!checkGuess(newText)) {
                currentAttempt++;
            }
        } else {
            outputDisplayManager.displayText("> Ti sembra di sentire il tuo prof. di italiano che ti dice: \"Una lettera per volta! (oppure la soluzione intera)\"");
        }

        if (currentAttempt >= MAX_ATTEMPTS) {
            outputDisplayManager.displayText("> La piuma si asciuga dell'inchiostro, hai esaurito i tentativi! Riprova.");
            currentAttempt = 0;
            UserInputFlow.Event = 0;
            Game game = Game.getInstance();
            game.setRoomState("Stanza4", "Sbagliato");
            GameGUI.setImagePanel(game.getCurrentRoom().getName());
        }
    }

    /**
     * Check guess boolean.
     *
     * @param text the text
     * @return the boolean
     */
    public boolean checkGuess(String text) {
        if (text.equals(GUESSING_PHRASE)) {
            impiccatoGUI.setPhrase(GUESSING_PHRASE);
            DatabaseConnection.printFromDB("0", "Stanza4", "Start", "0", "0", "0");
            UserInputFlow.Event = 0;
            Game game = Game.getInstance();
            game.getCurrentRoom().setState("Corretto");
            game.unlockCorridor("Stanza4", "Stanza5");
            GameGUI.setImagePanel(game.getCurrentRoom().getName());
            return true;
        } else {
            outputDisplayManager.displayText("> Provi a scrivere la frase, ma si cancella immediatamente, è sbagliata!");
            return false;
        }
    }

    /**
     * Check letter boolean.
     *
     * @param text the text
     * @return the boolean
     */
    public boolean checkLetter(String text) {
        char letter = text.charAt(0);
        for (int i = 0; i < PHRASE_LENGTH; i++) {
            if (guessedLetters[i] == letter) {
                outputDisplayManager.displayText("> Provi a scrivere la lettera, ma la piuma non si muove, è già stata scritta!");
                return true;
            }
        }

        boolean found = false;
        for (int i = 0; i < GUESSING_PHRASE.length(); i++) {
            if (GUESSING_PHRASE.charAt(i) == letter) {
                guessedLetters[i] = letter;
                impiccatoGUI.setLetter(GUESSING_PHRASE, letter);
                found = true;
            }
        }

        if (found) {
            outputDisplayManager.displayText("> Scritta la lettera, sembra che non vada più via dal foglio, è corretta!");
            impiccatoGUI.setLetter(GUESSING_PHRASE, letter);
            return true;
        } else {
            outputDisplayManager.displayText("> Scritta la lettera, si cancella immediatamente dal foglio, è sbagliata!");
            return false;
        }
    }
}
