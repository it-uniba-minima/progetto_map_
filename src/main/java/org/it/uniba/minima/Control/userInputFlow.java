package org.it.uniba.minima.Control;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.it.uniba.minima.Boundary.Triviaquestion;
import org.it.uniba.minima.Boundary.WordleGame;
import org.it.uniba.minima.Boundary.outputDisplayManager;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.Type.ParserOutput;

public class userInputFlow {
    public static int Event = 0;
    private static Parser parser = new Parser();
    private static CommandExecutor commandExecutor = new CommandExecutor(Game.getInstance());
    private static WordleGame wordleGame = new WordleGame();

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
                TriviaquestionFlow(text);
            case 4:
                //call Mattonelle
                break;
            default:
                parserFlow(text);
                break;
        }
    }

    private static void TriviaquestionFlow(String text)  {
        try {
            Triviaquestion.startTrivia();
        } catch (Exception e) {
            throw new RuntimeException(e);
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
}
