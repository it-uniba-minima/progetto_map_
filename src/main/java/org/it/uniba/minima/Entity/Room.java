package org.it.uniba.minima.Entity;

import java.util.*;

public class Room {
    private int id;
    private String name;
    private Map<String, String> description;
    private String state;
    private List<Agent> agents;

    public Room() {
        this.description = new HashMap<>();
        this.agents = new ArrayList<>();
    }

    public String getDescription() {
        return description.get(state);
    }

    public void setDescription(String thisIsRoom1) {
        this.description.put(state, thisIsRoom1);
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void addAgent(Agent agent) {
        agents.add(agent);
    }
}
