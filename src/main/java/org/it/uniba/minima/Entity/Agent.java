package org.it.uniba.minima.Entity;

import java.util.List;

public class Agent {
    private String name;
    private String description;
    private List<String> alias;

    public Agent(String name, String description, List<String> alias) {
        this.name = name;
        this.description = description;
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getAlias() {
        return alias;
    }
}
