package org.it.uniba.minima.Control;

import org.it.uniba.minima.Type.CommandType;

import java.util.List;

public class Command {
    private String name;
    private List<String> alias;
    private CommandType type;

    public Command(String name, List<String> alias, CommandType type) {
        this.name = name;
        this.alias = alias;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public List<String> getAlias() {
        return alias;
    }

    public CommandType getCommandType() {
        return type;
    }
}
