package org.it.uniba.minima.Control;
import org.it.uniba.minima.Entity.*;
import org.it.uniba.minima.Type.CommandType;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The class that manages the game.
 */
public class GameManager {
    /**
     * A map containing all the agents mapped to their names.
     */
    private static Map<String, Agent> allAgents;

    /**
     * Instantiates a new game and creates all the agents.
     */
    public static void createGame() {
        Converter converter = new Converter();
        allAgents = converter.convertJsonToJavaClass();
    }

    /**
     * The method to save the game and the agents in a json file.
     *
     */
    public void saveGame() {
        Converter converter = new Converter();
        converter.ConvertGameToJson();
        converter.ConvertAgentsToJson();
    }

    /**
     * Load game boolean.
     *
     * @return the boolean
     */
    public static boolean loadGame() {
        Converter converter = new Converter();
        allAgents = converter.loadGame();

        try {
            allAgents.get(1);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Gets an agent from its name.
     *
     * @param name the name of the agent
     * @return the agent
     */
    public static Agent getAgentFromName(String name) {
        return allAgents.get(name);
    }

    /**
     * Returns a set with all the agents.
     *
     * @return the all agents set
     */
    public static Set<Agent> getAllAgents() {
        return new HashSet<>(allAgents.values());
    }

    /**
     * Returns a set with all the items.
     *
     * @return the all items set
     */
    public static Set<Item> getAllItems() {
        Set<Item> allItems = allAgents.values().stream()
                .filter(agent -> agent instanceof Item)
                .map(agent -> (Item) agent)
                .collect(Collectors.toSet());
        return allItems;
    }

    /**
     * Returns a set with all the personages.
     *
     * @return the all personages set
     */
    public static Set<Personage> getAllPersonages() {
        Set<Personage> allPersonages = allAgents.values().stream()
                .filter(agent -> agent instanceof Personage)
                .map(agent -> (Personage) agent)
                .collect(Collectors.toSet());
        return allPersonages;
    }

    /**
     * Instantiates all the commands and returns them.
     *
     * @return the all commands set
     */
    public static Set<Command> getAllCommands() {
        Set<Command> availableCommands = new HashSet<>();

        availableCommands.add(new Command("Aiuto", List.of("h", "help", "comandi", "comando", "guida"), CommandType.AIUTO));
        availableCommands.add(new Command("Nord", List.of("n", "north", "avanti", "vaiAvanti"), CommandType.NORD));
        availableCommands.add(new Command("Sud", List.of("s", "south", "indietro", "vaiIndietro"), CommandType.SUD));
        availableCommands.add(new Command("Est", List.of("e", "east", "destra", "vaiDestra", "vaiADestra"), CommandType.EST));
        availableCommands.add(new Command("Ovest", List.of("o", "west", "sinistra", "vaiSinistra", "vaiASinistra"), CommandType.OVEST));
        availableCommands.add(new Command("Inventario", List.of("i", "inventory", "borsa", "zaino", "valigia", "inv"), CommandType.INVENTARIO));
        availableCommands.add(new Command("Guarda", List.of("g", "l", "look", "vedi", "esamina", "osserva", "ammira", "ispeziona"), CommandType.OSSERVA));
        availableCommands.add(new Command("Prendi", List.of("p", "t", "take", "raccogli", "recupera", "intasca"), CommandType.PRENDI));
        availableCommands.add(new Command("Usa", List.of("u", "use", "utilizza", "poggia", "appoggia", "poni"), CommandType.USA));
        availableCommands.add(new Command("Parla", List.of("talk", "dialoga"), CommandType.PARLA));
        availableCommands.add(new Command("Dai", List.of("give", "d", "passa", "consegna", "regala", "dona", "porgi"), CommandType.DAI));
        availableCommands.add(new Command("Lascia", List.of("drop", "abbandona", "lancia", "butta", "scarta", "rimuovi"), CommandType.LASCIA));
        availableCommands.add(new Command("Unisci", List.of("fuse", "f", "componi", "fondi", "combina", "assembla", "mischia", "miscela", "incastra"), CommandType.UNISCI));

        return availableCommands;
    }

    public static void resetAllAgents() {
        allAgents = null;
    }
}
