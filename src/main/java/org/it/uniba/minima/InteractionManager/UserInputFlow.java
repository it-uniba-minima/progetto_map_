package org.it.uniba.minima.InteractionManager;
import org.it.uniba.minima.Logic.CommandExecutor;
import org.it.uniba.minima.Logic.Parser;
import org.it.uniba.minima.DB_Web.Client;
import org.it.uniba.minima.DB_Web.DatabaseConnection;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.Entity.Item;
import org.it.uniba.minima.GUI.GameGUI;
import org.it.uniba.minima.GUI.ManagerGUI;
import org.it.uniba.minima.Type.ParserOutput;
import org.it.uniba.minima.Util.TimerManager;
import java.util.List;

/**
 * The class that redirects the user input to the correct part of the game.
 */
public class UserInputFlow {
    /**
     * The constant Event that represents the current event.
     */
    public static int Event;
    /**
     * The parser object that parses the user input in case 0.
     */
    private static Parser parser;
    /**
     * The commandExecutor object that executes the command in case 0.
     */
    private static CommandExecutor commandExecutor;
    /**
     * The wordleGame object that manages the wordle game in case 1.
     */
    private static WordleGame wordleGame;
    /**
     * The triviaGame object that manages the trivia game in case 2.
     */
    private static TriviaGame triviaGame;
    /**
     * The hangmanGame object that manages the hangman game in case 4.
     */
    private static HangmanGame hangmanGame;
    /**
     * The flag that manages the input of the nickname.
     */
    private static boolean isNameConfirmed;
    /**
     * The flag that manages the end of the game.
     */
    private static boolean isGameEnded;

    /**
     * Manages the flow of the game based on the current event.
     *
     * @param text the user input
     */
    public static void gameFlow(final String text) {
        OutputDisplayManager.displayText(text);

        switch (Event) {
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
    private static void parserFlow(final String text) {
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
    private static void hangmanFlow(final String text) {
        hangmanGame.hangmanChecker(text);
    }

    /**
     * The method to manage the wordle game event.
     *
     * @param text the user input
     */
    public static void wordleFlow(final String text) {
        wordleGame.manageGuess(text.trim().toUpperCase());
    }

    /**
     * The method to manage the trivia game event.
     *
     * @param text the user input
     */
    public static void triviaFlow(final String text) {
        triviaGame = TriviaGame.getInstance();

        try {
            triviaGame.checkGuess(text);
            if (Event == 2) triviaGame.getQAndA();
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
    private static void nicknameFlow(final String text) {
        if (!isNameConfirmed) {
            OutputDisplayManager.displayText("> Sei sicuro che questo sia il mio nome? (S/N)");
            Game.getInstance().setNickname(text);
            isNameConfirmed = true;
        } else {
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
    private static void endingFlow(final String text) throws RuntimeException {
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
            GameGUI.setImagePanel(finale);

            try {
                Client client = new Client();
                client.sendPostRequest(Game.getInstance().getNickname(), TimerManager.getTime(), finale);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            OutputDisplayManager.displayText("");
            OutputDisplayManager.displayText("> La tua avventura nella piramide è giunta al termine! Grazie per aver giocato!");
            OutputDisplayManager.displayText("> Scrivi qualsiasi cosa per terminare la partita.");
            isGameEnded = true;
        } else {
            ManagerGUI.closeGame();
        }
    }

    /**
     * Start hangman game.
     */
    public static void startHangmanGame() {
        hangmanGame = new HangmanGame();
    }

    /**
     * Set up a new game
     */
    public static void setUpGameFlow(final Game game) {
        Event = 5;
        DatabaseConnection.printFromDB("0", "Desert", "Start", "0", "0", "0");
        isNameConfirmed = false;
        isGameEnded = false;
        // poiché la prima API è andata in down, spostiamo il setup del Wordle su un thread separato
        // così da non bloccare il flusso del gioco
        new Thread(() -> wordleGame = new WordleGame()).start();
        triviaGame = TriviaGame.getInstance();
        triviaGame.resetCorrectAnswers();
        parser = new Parser();
        commandExecutor = new CommandExecutor(game);
    }

    /*
     * Set up a saved game
     */
    public static void setUpLoadedGameFlow(final Game game) {
        Event = 0;
        isNameConfirmed = true;
        isGameEnded = false;
        // poiché la prima API è andata in down, spostiamo il setup del Wordle su un thread separato
        // così da non bloccare il flusso del gioco
        new Thread(() -> wordleGame = new WordleGame()).start();
        triviaGame = TriviaGame.getInstance();
        triviaGame.resetCorrectAnswers();
        parser = new Parser();
        commandExecutor = new CommandExecutor(game);
        List<String> itemsNames = game.getInventory().stream().map(Item::getName).toList();
        String[] itemsNamesArray = itemsNames.toArray(new String[0]);
        GameGUI.updateInventoryTextArea(itemsNamesArray);
        OutputDisplayManager.displayText("> Bentornato " + game.getNickname() + "!");
        DatabaseConnection.printFromDB("0", game.getCurrentRoom().getName(), game.getRoomState(game.getCurrentRoom().getName()).toString(), "0", "0", "0");
    }
}
