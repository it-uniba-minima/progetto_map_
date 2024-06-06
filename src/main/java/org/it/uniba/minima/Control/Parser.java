package org.it.uniba.minima.Control;
import org.it.uniba.minima.Entity.Agent;
import org.it.uniba.minima.Type.ParserOutput;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.*;

/**
 * The class that manages the parsing of the input.
 */
public class Parser {
    /**
     * The available commands set.
     */
    public static Set<Command> availableCommands = GameManager.getAllCommands();
    /**
     * The available agents set
     */
    public static Set<Agent> availableAgents = GameManager.getAllAgents();
    /**
     * The stop words set.
     */
    private static Set<String> stopWords = new HashSet<>();

    /**
     * Parses the input and returns the output of the operation.
     *
     * @param input the input
     * @return the parser output
     */
    public ParserOutput parse(String input) {
        ParserOutput output = new ParserOutput();
        String[] words;

        // Load the stop words
        try {
            setupUselessWords();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Split the input into words
        words = Arrays.stream(input.split(" "))
                .map(String::toLowerCase)
                .filter(w -> !stopWords.contains(w))
                .toArray(String[]::new);

        // Start parsing the input
        // If there are no words, returns nothing
        if (words.length == 0) {
            return output;
        } else {
            // Check if the first word is a command
            Command command;
            Iterator<Command> cm = availableCommands.iterator();
            while (output.getCommand() == null && cm.hasNext()) {
                command = cm.next();
                if (command.getName().equalsIgnoreCase(words[0])) {
                    output.setCommand(command.getCommandType());
                } else {
                    String theAlias = command.getAliases().stream()
                            .filter(alias -> alias.equalsIgnoreCase(words[0]))
                            .findFirst()
                            .orElse(null);
                    if (theAlias != null) output.setCommand(command.getCommandType());
                }
            }

            // If the command is not found, returns nothing
            if (output.getCommand() == null) {
                return output;
            } else {
                output.setArgs(1);
            }

            // Check if there are agents
            if (words.length > 1) {
                // Check if the agent is in the available agents
                Agent agent;
                Iterator<Agent> it = availableAgents.iterator();
                while (output.getAgent1() == null && it.hasNext()) {
                    agent = it.next();
                    if (agent.getName().equalsIgnoreCase(words[1])) {
                        output.setAgent1(agent);
                    } else {
                        String theAlias = agent.getAliases().stream()
                                .filter(alias -> alias.equalsIgnoreCase(words[1]))
                                .findFirst()
                                .orElse(null);
                        if (theAlias != null) output.setAgent1(agent);
                    }
                }

                // If the agent is not found, returns nothing
                if (output.getAgent1() == null) {
                    output.setArgs(0);
                    return output;
                } else {
                    output.setArgs(2);
                }
            }

            // Check if there is a second agent
            if (words.length > 2) {
                // Check if the agent is in the available agents
                Agent agent;
                Iterator<Agent> it = availableAgents.iterator();
                while (output.getAgent2() == null && it.hasNext()) {
                    agent = it.next();
                    if (agent.getName().equalsIgnoreCase(words[2])) {
                        output.setAgent2(agent);
                    } else {
                        String theAlias = agent.getAliases().stream()
                                .filter(alias -> alias.equalsIgnoreCase(words[2]))
                                .findFirst()
                                .orElse(null);
                        if (theAlias != null) output.setAgent2(agent);
                    }
                }

                // If the agent is not found, returns nothing
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

    /**
     * Sets up the stop words.
     *
     * @throws Exception the exception
     */
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
