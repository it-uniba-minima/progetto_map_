package org.it.uniba.minima.Entity;

import org.it.uniba.minima.Database.DatabaseConnection;
import org.it.uniba.minima.Type.CommandType;

import java.util.*;

public class Room {
    private String name;
    private String currentState;
    private List<Agent> agents;

    public Room() {
        this.agents = new ArrayList<>();
    }

    public void printDescription() {
        DatabaseConnection.printFromDB("Osserva", name, currentState, "0", "0", "0");
    }

    public void setState(String state) {
         currentState = state;
    }

    public String getState() {
        return currentState;
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

    public void removeAgent(Agent agent) {
        agents.remove(agent);
    }

    public boolean hasAgent(Agent agent) {
        return agents.contains(agent);
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }
}