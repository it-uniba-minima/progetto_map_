package org.it.uniba.minima.Control;

import org.it.uniba.minima.Boundary.WordleGame;
import org.it.uniba.minima.Boundary.outputDisplayManager;
import org.it.uniba.minima.Type.ParserOutput;

public class userInputFlow {
    public static int Event = 0;
    static Parser parser = new Parser();
    private static CommandExecutor commandExecutor; // = new CommandExecutor();

    //WordleGame wordleGame = new WordleGame();

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
                //call ParolaCriptata
                break;
            case 3:
                //call Mattonelle
                break;
            default:
                parserFlow(text);
                break;
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
        WordleGame.printSplittedText(text.trim().toUpperCase());
    }
}
