package org.it.uniba.minima.Entity;
import org.it.uniba.minima.DB_Web.DatabaseConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Room.
 */
public class Room {
    /**
     * The name of the room.
     */
    private String name;
    /**
     * The current state of the room.
     */
    private String currentState;
    /**
     * The list of agents in the room.
     */
    private List<Agent> agents;

    /**
     * Instantiates a new Room.
     */
    public Room() {
        this.agents = new ArrayList<>();
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName (String name) {
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(String state) {
        currentState = state;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return currentState;
    }

    /**
     * Checks if the room has the agent.
     *
     * @param agent the agent
     * @return if the room has the agent
     */
    public boolean hasAgent(Agent agent) {
        return agents.contains(agent);
    }

    /**
     * Adds the agent to the room.
     *
     * @param agent the agent
     */
    public void addAgent(Agent agent) {
        agents.add(agent);
    }

    /**
     * Removes the agent from the room.
     *
     * @param agent the agent
     */
    public void removeAgent(Agent agent) {
        agents.remove(agent);
    }

    /**
     * Gets all the agents in the room.
     *
     * @return the agents
     */
    public List<Agent> getAgents() {
        return agents;
    }

    /**
     * Print description.
     */
    public void printDescription() {
        DatabaseConnection.printFromDB("Osserva", name, currentState, "0", "0", "0");
    }

    /**
     * Override of the equals method.
     *
     * @param obj the object to compare
     * @return true if the two objects are equals, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    /**
     * Override of the hashcode method.
     *
     * @return the hashcode of the object
     */
    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }
}
