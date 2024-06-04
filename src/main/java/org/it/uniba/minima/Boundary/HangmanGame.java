package org.it.uniba.minima.Boundary;

import org.it.uniba.minima.GUI.ImpiccatoGUI;

public class HangmanGame {
    private static String guessingPhrase = "TI TROVI NELLA PIRAMIDE DI OSIRIDE";
    private static ImpiccatoGUI impiccatoGUI = ImpiccatoGUI.getInstance();

    public static void HangmanFlow(String text) {
        String newText = text.toUpperCase();
        if (newText.length() == 1) {
            checkLetter(newText);
        } else if (newText.length() > 1 && newText.length() < 34) {
            outputDisplayManager.displayText("Si può fare o una lettera alla volta o indovinare la frase intera");
        } else {
            checkGuess(newText);
        }
    }

    public static void checkGuess(String text) {
        if (text.equals(guessingPhrase)) {
            outputDisplayManager.displayText("Hai indovinato la frase! Complimenti!");
            impiccatoGUI.setPhrase(guessingPhrase);
        } else {
            outputDisplayManager.displayText("Hai sbagliato la frase! Riprova!");
        }
    }

    public static void checkLetter(String text) {
        if (guessingPhrase.contains(text)) {
            outputDisplayManager.displayText("Hai indovinato una lettera!");
            for (int i = 0; i < guessingPhrase.length(); i++) {
                if (guessingPhrase.charAt(i) == text.charAt(0)) {
                    impiccatoGUI.setLetter(i, text.charAt(0), guessingPhrase);
                }
            }
        } else {
            outputDisplayManager.displayText("La lettera inserita non è presente nella frase");
        }
    }
}
