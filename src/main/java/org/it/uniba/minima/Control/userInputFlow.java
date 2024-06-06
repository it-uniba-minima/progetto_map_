package org.it.uniba.minima.Control;

import org.it.uniba.minima.Boundary.*;
import org.it.uniba.minima.Database.DatabaseConnection;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.GUI.GUIManager;
import org.it.uniba.minima.Type.ParserOutput;
import java.io.IOException;
import java.awt.CardLayout;

public class userInputFlow {
    public static int Event = 5;
    private static Parser parser = new Parser();
    private static CommandExecutor commandExecutor = new CommandExecutor(Game.getInstance());
    private static WordleGame wordleGame;
    private static boolean isNameConfirmed = false;
    private static boolean isGameEnded = false;

    static {
        try {
            wordleGame = new WordleGame();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static HangmanGame hangmanGame = new HangmanGame();

    public static void GameFlow(String text) {
        outputDisplayManager.displayText(text);
        switch(Event) {
            case 0:
                parserFlow(text);
                break;
            case 1:
                wordleFlow(text);
                break;
            case 2:
                triviaFlow(text);
                break;
            case 3:
                mattonelleFlow();
                break;
            case 4:
                hangmanFlow(text);
                break;
            case 5:
                nicknameFlow(text);
                break;
            case 6:
                endingFlow(text);
                break;
            default:
                parserFlow(text);
                break;
        }
    }

    private static void nicknameFlow(String text) {
        if (!isNameConfirmed) {
            outputDisplayManager.displayText("> Sei sicuro che questo sia il mio nome? (S/N)");
            Game.getInstance().setNickname(text);
            isNameConfirmed = true;
        }
        else {
            if (text.equalsIgnoreCase("S")) {
                outputDisplayManager.displayText("> Perfetto! Ora possiamo iniziare!");
                outputDisplayManager.displayText("> Benvenuto " + Game.getInstance().getNickname() + "!");
                Event = 0;
            } else {
                outputDisplayManager.displayText("> Va bene, allora proviamo di nuovo! Inserisci il mio nome:");
                isNameConfirmed = false;
            }
        }
    }

    private static void endingFlow(String text) {
        if (!isGameEnded) {
            String finale;

            if (text.equalsIgnoreCase("Saggezza")) {
                finale = "Saggezza";
            } else if (text.equalsIgnoreCase("Ricchezza")) {
                finale = "Ricchezza";
            } else {
                outputDisplayManager.displayText("> \"Parla forte e chiaro! Prendi una delle due scelte e si preciso nelle tue decisioni!\"");
                outputDisplayManager.displayText("> \"Saggezza\" o \"Ricchezza\"?");
                return;
            }
            DatabaseConnection.printFromDB("0", "Stanza10", finale, "0", "0", "0");
            //TODO: chiamata post per salvataggio del tempo e tipo di finale

            outputDisplayManager.displayText("");
            outputDisplayManager.displayText("> La tua avventura nella piramide è giunta al termine! Grazie per aver giocato!");
            outputDisplayManager.displayText("> Scrivi qualsiasi cosa per terminare il gioco.");
            isGameEnded = true;
        } else {
            System.exit(0);
        }
    }

    private static void hangmanFlow(String text) {
        hangmanGame.HangmanFlow(text);
    }

    public static void triviaFlow(String text) {
        try {
            TriviaGame.checkGuess(text);
            if (Event == 2) TriviaGame.getQAndA();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parserFlow(String text) {
        ParserOutput output = parser.parse(text);
        if (output.getArgs() != 0) {
            commandExecutor.execute(output);
        } else {
            outputDisplayManager.displayText("Comando non valido");
        }

    }

    public static void wordleFlow(String text) {
        wordleGame.printSplittedText(text.trim().toUpperCase());
    }

    public static void mattonelleFlow() {
        outputDisplayManager.displayText("Risolvi il puzzle delle mattonelle per proseguire!");
    }
}
