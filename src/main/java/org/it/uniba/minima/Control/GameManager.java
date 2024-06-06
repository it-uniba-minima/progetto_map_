package org.it.uniba.minima.Control;

import org.it.uniba.minima.Entity.*;
import org.it.uniba.minima.Type.CommandType;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class GameManager {
    private static Map<String, Agent> allAgents;

    public static void createGame() {
        Converter converter = new Converter();
        allAgents = converter.convertJsonToJavaClass();
    }

    public static Set<Command> getAllCommands() {
        Set<Command> availableCommands = new HashSet<>();

        availableCommands.add(new Command("Aiuto", List.of("a", "h", "help", "comandi", "comando", "guida"), CommandType.AIUTO));
        availableCommands.add(new Command("Nord", List.of("n", "north", "avanti", "vaiAvanti", "su"), CommandType.NORD));
        availableCommands.add(new Command("Sud", List.of("s", "south", "indietro", "vaiIndietro", "giu", "giù"), CommandType.SUD));
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
