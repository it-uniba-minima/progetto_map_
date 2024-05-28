package org.it.uniba.minima.Control;
import org.it.uniba.minima.Entity.Agent;
import org.it.uniba.minima.Entity.Personage;
import org.it.uniba.minima.Entity.Item;
import org.it.uniba.minima.Type.ParserOutput;
import org.it.uniba.minima.Type.CommandType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.*;

public class Parser {
    public static List<Command> availableCommands = new ArrayList<>(); //MainController.getAvailableCommands();
    public static Set<Agent> availableAgents = GameManager.getAllAgents();     //MainController.getAvailableAgents();
    private static Set<String> stopWords = new HashSet<>();

    public Parser() {
        availableCommands.add(new Command("help", List.of("h", "aiuto", "comandi"), CommandType.HELP));
        availableCommands.add( new Command("nord", List.of("n", "north", "avanti"), CommandType.NORD));
        availableCommands.add( new Command("sud", List.of("s", "south", "indietro"), CommandType.SUD));
        availableCommands.add( new Command("est", List.of("e", "east", "destra"), CommandType.EST));
        availableCommands.add( new Command("ovest", List.of("o", "west", "sinstra"), CommandType.OVEST));
        availableCommands.add( new Command("inventario", List.of("i", "inventory", "borsa", "zaino"), CommandType.INVENTORY));
        availableCommands.add( new Command("guarda", List.of("l", "look", "vedi", "esamina"), CommandType.LOOK));
        availableCommands.add( new Command("prendi", List.of("t", "take", "raccogli"), CommandType.TAKE));
        availableCommands.add( new Command("usa", List.of("u", "use", "utilizza"), CommandType.USE));
        availableCommands.add( new Command("parla", List.of("talk", "p", "dialoga"), CommandType.TALK));
        availableCommands.add( new Command("dai", List.of("give", "d", "passa"), CommandType.GIVE));
        availableCommands.add( new Command("lascia", List.of("drop", "l", "abbandona"), CommandType.DROP));
        availableCommands.add( new Command("fonde", List.of("fuse", "f", "componi"), CommandType.FUSE));
    }

    public ParserOutput parse(String input) {
        ParserOutput output = new ParserOutput();
        String[] words;

        try {
            setupUselessWords();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //words = input.split(" ");

        words = Arrays.stream(input.split(" "))
                .map(String::toLowerCase)
                .filter(w -> !stopWords.contains(w))
                .toArray(String[]::new);

        if (words.length == 0) {
            return output;
        } else {
            Command command;
            int i = 0;
            while (output.getCommand() == null && i < availableCommands.size()) {
                command = availableCommands.get(i);
                if (command.getName().equals(words[0])) {
                    output.setCommand(command.getCommandType());
                } else {
                    if (command.getAlias().contains(words[0])) {
                        output.setCommand(command.getCommandType());
                    }
                }
                i++;
            }

            if (output.getCommand() == null) {
                return output;
            } else {
                output.setArgs(1);
            }

            if (words.length > 1) {
                Agent agent;
                Iterator<Agent> it = availableAgents.iterator();
                while (output.getAgent1() == null && it.hasNext()) {
                    agent = it.next();
                    // TODO: make sure all agent names and aliases are lowercase, then remove toLowerCase() from here
                    if (agent.getName().toLowerCase().equals(words[1])) {
                        output.setAgent1(agent);
                    } else if (agent.getAlias().contains(words[1])) {
                        output.setAgent1(agent);
                    }
                }

                if (output.getAgent1() == null) {
                    output.setArgs(0);
                    return output;
                } else {
                    output.setArgs(2);
                }
            }

            if (words.length > 2) {
                Agent agent;
                Iterator<Agent> it = availableAgents.iterator();
                while (output.getAgent2() == null && it.hasNext()) {
                    agent = it.next();
                    // TODO: make sure all agent names and aliases are lowercase, then remove toLowerCase() from here
                    if (agent.getName().toLowerCase().equals(words[2])) {
                        output.setAgent2(agent);
                    } else if (agent.getAlias().contains(words[2])) {
                        output.setAgent2(agent);
                    }
                }

                if (output.getAgent2() == null) {
                    output.setArgs(0);
                    return output;
                } else {
                    output.setArgs(3);
                }
            }
        }
        return output;
    }

    private void setupUselessWords() throws Exception {
        URL url = getClass().getResource("/static/stopWords.txt");
        File file = new File(url.toURI());
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (reader.ready()) {
            stopWords.add(reader.readLine().trim().toLowerCase());
        }
        reader.close();
    }
}
