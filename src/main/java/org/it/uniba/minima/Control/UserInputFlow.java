package org.it.uniba.minima.Control;
import org.it.uniba.minima.Boundary.*;
import org.it.uniba.minima.Database.DatabaseConnection;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.Type.ParserOutput;

/**
 * The class that redirects the user input to the correct part of the game.
 */
public class UserInputFlow {
    /**
     * The constant Event that represents the current event.
     */
    public static int Event = 5;
    /**
     * The parser object that parses the user input in case 0.
     */
    private static Parser parser = new Parser();
    /**
     * The command executor object that executes the commands in case 0.
     */
    private static CommandExecutor commandExecutor = new CommandExecutor(Game.getInstance());
    /**
     * The wordleGame object that manages the wordle game in case 1.
     */
    private static WordleGame wordleGame = new WordleGame();
    /**
     * The hangmanGame object that manages the hangman game in case 4.
     */
    private static HangmanGame hangmanGame;
    /**
     * The flag that manages the input of the nickname.
     */
    private static boolean isNameConfirmed = false;
    /**
     * The flag that manages the end of the game.
     */
    private static boolean isGameEnded = false;

    /**
     * Manages the flow of the game based on the current event.
     *
     * @param text the user input
     */
    public static void gameFlow(String text) {
        OutputDisplayManager.displayText(text);

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

    /**
     * The method to manage command parsing and execution.
     *
     * @param text the user input
     */
    private static void parserFlow(String text) {
        ParserOutput output = parser.parse(text);
        if (output.getArgs() != 0) {
            commandExecutor.execute(output);
        } else {
            OutputDisplayManager.displayText("> Non ho capito cosa vuoi fare, riprova!");
        }

    }

    /**
     * The method to manage the hangman game event.
     *
     * @param text the user input
     */
    private static void hangmanFlow(String text) {
        hangmanGame.HangmanChecker(text);
    }

    /**
     * The method to manage the wordle game event.
     *
     * @param text the user input
     */
    public static void wordleFlow(String text) {
        wordleGame.manageGuess(text.trim().toUpperCase());
    }

    /**
     * The method to manage the trivia game event.
     *
     * @param text the user input
     */
    public static void triviaFlow(String text) {
        try {
            TriviaGame.checkGuess(text);
            if (Event == 2) TriviaGame.getQAndA();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The method to manage the mattonelle game event.
     */
    public static void mattonelleFlow() {
        OutputDisplayManager.displayText("Risolvi il puzzle delle mattonelle per proseguire!");
    }

    /**
     * The method to manage the nickname input.
     *
     * @param text the user input
     */
    private static void nicknameFlow(String text) {
        if (!isNameConfirmed) {
            OutputDisplayManager.displayText("> Sei sicuro che questo sia il mio nome? (S/N)");
            Game.getInstance().setNickname(text);
            isNameConfirmed = true;
        }
        else {
            if (text.equalsIgnoreCase("S")) {
                OutputDisplayManager.displayText("> Perfetto! Ora possiamo iniziare!");
                OutputDisplayManager.displayText("> Benvenuto " + Game.getInstance().getNickname() + "!");
                Event = 0;
            } else {
                OutputDisplayManager.displayText("> Va bene, allora proviamo di nuovo! Inserisci il mio nome:");
                isNameConfirmed = false;
            }
        }
    }

    /**
     * The method to manage the ending of the game.
     *
     * @param text the user input
     */
    private static void endingFlow(String text) {
        if (!isGameEnded) {
            String finale;

            if (text.equalsIgnoreCase("Saggezza")) {
                finale = "Saggezza";
            } else if (text.equalsIgnoreCase("Ricchezza")) {
                finale = "Ricchezza";
            } else {
                OutputDisplayManager.displayText("> \"Parla forte e chiaro! Prendi una delle due scelte e si preciso nelle tue decisioni!\"");
                OutputDisplayManager.displayText("> \"Saggezza\" o \"Ricchezza\"?");
                return;
            }
            DatabaseConnection.printFromDB("0", "Stanza10", finale, "0", "0", "0");
            //TODO: chiamata post per salvataggio del tempo e tipo di finale

            OutputDisplayManager.displayText("");
            OutputDisplayManager.displayText("> La tua avventura nella piramide è giunta al termine! Grazie per aver giocato!");
            OutputDisplayManager.displayText("> Scrivi qualsiasi cosa per terminare il gioco.");
            isGameEnded = true;
        } else {
            System.exit(0);
        }
    }

    /**
     * Start hangman game.
     */
    public static void startHangmanGame() {
        hangmanGame = new HangmanGame();
    }
}
