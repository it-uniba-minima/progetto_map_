package org.it.uniba.minima.Control;
import org.it.uniba.minima.Entity.Agent;
import org.it.uniba.minima.Entity.Character;
import org.it.uniba.minima.Entity.Item;
import org.it.uniba.minima.Type.ParserOutput;
import org.it.uniba.minima.Type.CommandType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    public static List<Command> availableCommands = new ArrayList<>(); //MainController.getAvailableCommands();
    public static List<Agent> availableAgents = new ArrayList<>();     //MainController.getAvailableAgents();

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

        availableAgents.add( new Character("guardia", "Un uomo con una spada", List.of("soldato", "uomo")));
        availableAgents.add( new Character("principessa", "Una donna con un vestito elegante", List.of("donna", "ragazza")));
        availableAgents.add( new Character("strega", "Una donna con un cappello a punta", List.of("vecchia", "donna")));
        availableAgents.add( new Item("spada", "Un'arma affilata", List.of("arma", "oggetto")));
        availableAgents.add( new Item("chiave", "Un oggetto di metallo", List.of("oggetto", "oggettino")));
        availableAgents.add( new Item("anello", "Un oggetto di metallo", List.of("oggetto", "oggettino")));

    }

    public ParserOutput parse(String input) {
        ParserOutput output = new ParserOutput();
        String[] words;

        words = Arrays.stream(input.split(" "))
                .map(String::toLowerCase)
                .toArray(String[]::new);
        removeUselessWords(words);

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

    private static void removeUselessWords(String[] text) {}
}
