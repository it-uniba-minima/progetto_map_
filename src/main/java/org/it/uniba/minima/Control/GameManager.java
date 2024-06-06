package org.it.uniba.minima.Control;

import org.it.uniba.minima.Entity.*;
import org.it.uniba.minima.GUI.GameGUI;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class GameManager {
    private static Map<String, Agent> allAgents;

    public static void createGame() {
        Converter converter = new Converter();
        allAgents = converter.convertJsonToJavaClass();
    }

    public void saveGame() throws IOException, ClassNotFoundException {
        Converter converter = new Converter();
        Game game = Game.getInstance();
        converter.ConvertGametoJson();
        converter.ConvertAgentstoJson();
    }

    public boolean loadGame() {
        Converter converter = new Converter();
        allAgents = converter.loadGame();
        try {
            allAgents.get(1);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Agent getAgentFromName(String name) {
        return allAgents.get(name);
    }

    public static Set<Agent> getAllAgents() {
        return new HashSet<>(allAgents.values());
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
    
}
