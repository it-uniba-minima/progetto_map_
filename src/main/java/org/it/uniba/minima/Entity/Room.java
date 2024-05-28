package org.it.uniba.minima.Entity;

import java.util.*;

public class Room {
    private String name;
    private String currentState;
    private List<Agent> agents;

    public Room() {
        this.agents = new ArrayList<>();
    }

    public String getDescription() {
        //call database with currentState
        return "Substitute this with the description of the room";
    }

    public void setState(String state) {
         currentState = state;
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
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Room room = (Room) obj;
        return name.equals(room.name) && currentState.equals(room.currentState) && agents.equals(room.agents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, currentState, agents);
    }
}