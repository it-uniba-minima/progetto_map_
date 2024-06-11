package org.it.uniba.minima.Entity;
import java.util.List;
import java.util.Objects;

/**
 * Class that represents an agent in the game.
 */
public abstract class Agent {

    /**
     * Name of the agent.
     */
    private String name;
    /**
     * List of aliases of the agent.
     */
    private List<String> aliases;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets aliases.
     *
     * @return the aliases
     */
    public List<String> getAliases() {
        return aliases;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Has name name.
     *
     * @param name the name
     * @return the boolean
     */
    public boolean hasName(String name) {
        return this.name.equals(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agent)) return false;
        Agent agent = (Agent) o;
        return Objects.equals(name, agent.name) &&
                Objects.equals(aliases, agent.aliases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, aliases);
    }

    /**
     * Method to override in subclasses to print the description of the agent.
     *
     * @param room the room
     */
    public abstract void getDescription(Room room);
}
