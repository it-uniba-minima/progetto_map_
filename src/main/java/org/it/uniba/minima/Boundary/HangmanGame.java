package org.it.uniba.minima.Boundary;

import org.it.uniba.minima.GUI.ImpiccatoGUI;

public class HangmanGame {
    private static final String GUESSING_PHRASE = "TI TROVI NELLA PIRAMIDE DI OSIRIDE";
    private static final int PHRASE_LENGTH = 34;
    private static final ImpiccatoGUI impiccatoGUI = ImpiccatoGUI.getInstance();
    private static final int MAX_ATTEMPTS = 6;
    private int currentAttempt = 0;
    private char[] guessedLetters = new char[PHRASE_LENGTH];

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
            outputDisplayManager.displayText("Si può inserire solo una lettera o la frase intera!");
        }

        if (currentAttempt >= MAX_ATTEMPTS) {
            outputDisplayManager.displayText("Hai perso! La frase era: " + GUESSING_PHRASE);
            impiccatoGUI.setPhrase(GUESSING_PHRASE);
        }
    }

    public boolean checkGuess(String text) {
        if (text.equals(GUESSING_PHRASE)) {
            outputDisplayManager.displayText("Hai indovinato la frase! Complimenti!");
            impiccatoGUI.setPhrase(GUESSING_PHRASE);
            return true;
        } else {
            outputDisplayManager.displayText("Hai sbagliato la frase! Riprova!");
            return false;
        }
    }

    public boolean checkLetter(String text) {
        char letter = text.charAt(0);
        for (int i = 0; i < PHRASE_LENGTH; i++) {
            if (guessedLetters[i] == letter) {
                outputDisplayManager.displayText("Hai già indovinato questa lettera!");
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
            outputDisplayManager.displayText("Hai indovinato una lettera!");
            impiccatoGUI.setLetter(GUESSING_PHRASE, letter);
            return true;
        } else {
            outputDisplayManager.displayText("La lettera inserita non è presente nella frase");
            return false;
        }
    }
}
