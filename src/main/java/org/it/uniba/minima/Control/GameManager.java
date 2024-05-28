package org.it.uniba.minima.Control;

import org.it.uniba.minima.Entity.*;
import org.it.uniba.minima.GUI.GameGUI;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GameManager {
    private static Map<String, Agent> allAgents;

    public void createGame() {
        Game game = new Game();
        GameGUI.setGame(game);
        Converter converter = new Converter();
        allAgents = converter.convertJsonToJavaClass();
    }

    public void saveGame(Game game) throws IOException, ClassNotFoundException {
        Converter converter = new Converter();
        converter.ConvertGametoJson(game);

    }

    public Game loadGame() {
        Converter converter = new Converter();
        return converter.loadGame();
    }

    public static Agent getAgentFromName(String name) {
        return allAgents.get(name);
    }

    public static Set<Agent> getAllAgents() {
        return (Set<Agent>) allAgents.values();
    }

    public static Set<Item> getAllItems() {
        Set<Item> allItems = allAgents.values().stream()
                .filter(agent -> agent instanceof Item)
                .map(agent -> (Item) agent)
                .collect(Collectors.toSet());
        return allItems;
    }

    public static Set<Personage> getAllPersonages() {
        Set<Personage> allPersonages = allAgents.values().stream()
                .filter(agent -> agent instanceof Personage)
                .map(agent -> (Personage) agent)
                .collect(Collectors.toSet());
        return allPersonages;
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
