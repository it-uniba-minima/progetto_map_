package org.it.uniba.minima.Control;

import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.Entity.Room;
import org.it.uniba.minima.GUI.GameGUI;

import java.io.IOException;
import java.util.List;

public class GameManager {

    public void createGame() {
        Game game = new Game();
        GameGUI.setGame(game);
        Converter converter = new Converter();
        converter.convertJsonToJavaClass();
    }

    public void saveGame(Game game) throws IOException, ClassNotFoundException {
        Converter converter = new Converter();
       converter.ConvertGametoJson(game);

    }

    public Game loadGame() {
        Converter converter = new Converter();
        return converter.loadGame();
    }


    /*
        1) Scrittura dei file JSON contenenti le istanziazioni di stanze, oggetti e personaggi,
        nota che le descrizioni in particolare sono una cosa di cui si occupa Marco,
        quindi puoi anche ignorarle per ora e usare la stringa vuota
        2) Creazione di una classe chiamata ad esempio GameManager che al suo interno istanzia
        tutte le stanze, tutte gli oggetti, tutti i personaggi e l'istanza di iniziale di Game
        3) Creazione di un altro/i metodo/i nella classe GameManager per la serializzazione di tutte le stanze,
        oggetti, personaggi e dell'istanza di Game per il salvataggio
        4) Creazione/modifica di metodo/i per il caricamento di una partita

     */

}
