package org.it.uniba.minima.Control;
import org.it.uniba.minima.Entity.Agent;
import org.it.uniba.minima.Type.ParserOutput;
import org.it.uniba.minima.Type.CommandType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.*;

public class Parser {
    public static Set<Command> availableCommands = GameManager.getAllCommands(); //MainController.getAvailableCommands();
    public static Set<Agent> availableAgents = GameManager.getAllAgents();     //MainController.getAvailableAgents();
    private static Set<String> stopWords = new HashSet<>();

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
            Iterator<Command> cm = availableCommands.iterator();
            while (output.getCommand() == null && cm.hasNext()) {
                command = cm.next();
                if (command.getName().equalsIgnoreCase(words[0])) {
                    output.setCommand(command.getCommandType());
                } else {
                    String theAlias = command.getAlias().stream()
                            .filter(alias -> alias.equalsIgnoreCase(words[0]))
                            .findFirst()
                            .orElse(null);
                    if (theAlias != null) output.setCommand(command.getCommandType());
                }
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
                    if (agent.getName().equalsIgnoreCase(words[1])) {
                        output.setAgent1(agent);
                    } else {
                        String theAlias = agent.getAlias().stream()
                                .filter(alias -> alias.equalsIgnoreCase(words[1]))
                                .findFirst()
                                .orElse(null);
                        if (theAlias != null) output.setAgent1(agent);
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
                    if (agent.getName().equalsIgnoreCase(words[2])) {
                        output.setAgent2(agent);
                    } else {
                        String theAlias = agent.getAlias().stream()
                                .filter(alias -> alias.equalsIgnoreCase(words[2]))
                                .findFirst()
                                .orElse(null);
                        if (theAlias != null) output.setAgent2(agent);
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
