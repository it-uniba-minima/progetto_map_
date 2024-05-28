package org.it.uniba.minima.Control;
import org.it.uniba.minima.Entity.Agent;
import org.it.uniba.minima.Entity.Personage;
import org.it.uniba.minima.Entity.Item;
import org.it.uniba.minima.Type.ParserOutput;
import org.it.uniba.minima.Type.CommandType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Parser {
    public static List<Command> availableCommands = new ArrayList<>(); //MainController.getAvailableCommands();
    public static List<Agent> availableAgents = new ArrayList<>();     //MainController.getAvailableAgents();
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


        Personage personage1 = new Personage();
        personage1.setName("personage1");
        personage1.setDescription("This is personage1");
        personage1.setAlias(List.of("c1", "ch1"));

        Personage personage2 = new Personage();
        personage2.setName("personage2");
        personage2.setDescription("This is personage2");
        personage2.setAlias(List.of("c2", "ch2"));

        Item item2 = new Item();
        item2.setName("item2");
        item2.setDescription("This is item2");
        item2.setAlias(List.of("a1", "ag1"));

        Item item1 = new Item();
        item1.setName("item1");
        item1.setDescription("This is item1");
        item1.setAlias(List.of("i1", "it1"));

        availableAgents.add(item2);
        availableAgents.add(item1);
        availableAgents.add(personage1);
        availableAgents.add(personage2);
    }

    public ParserOutput parse(String input) {
        ParserOutput output = new ParserOutput();
        String[] words;

        try {
            setupUselessWords();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
                i = 0;
                while (output.getAgent1() == null && i < availableAgents.size()) {
                    agent = availableAgents.get(i);
                    if (agent.getName().equals(words[1])) {
                        output.setAgent1(agent);
                    } else if (agent.getAlias().contains(words[1])) {
                        output.setAgent1(agent);
                    }
                    i++;
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
                i = 0;
                while (output.getAgent2() == null && i < availableAgents.size()) {
                    agent = availableAgents.get(i);
                    if (agent.getName().equals(words[2])) {
                        output.setAgent2(agent);
                    } else if (agent.getAlias().contains(words[2])) {
                        output.setAgent2(agent);
                    }
                    i++;
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
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/stopwords.txt"));
        while (reader.ready()) {
            stopWords.add(reader.readLine().trim().toLowerCase());
        }
        reader.close();
    }
}
