package org.it.uniba.minima.Type;
import org.it.uniba.minima.Entity.Agent;

/**
 * The class representing the command executor key.
 */
public class CommandExecutorKey {
    /**
     * The command type
     */
    private CommandType command;
    /**
     * The first agent
     */
    private Agent agent1;
    /**
     * The second agent
     */
    private Agent agent2;

    /**
     * Instantiates a new Command executor key with the agents.
     *
     * @param c  the command type
     * @param a1 the first agent
     * @param a2 the second agent
     */
    public CommandExecutorKey(CommandType c, Agent a1, Agent a2) {
        this.command = c;
        this.agent1 = a1;
        this.agent2 = a2;
    }

    /**
     * Instantiates a new Command executor key without the agents.
     */
    public CommandExecutorKey() {
        this.command = null;
        this.agent1 = null;
        this.agent2 = null;
    }

    /**
     * Sets command type.
     *
     * @param command the command type
     */
    public void setCommand(CommandType command) {
        this.command = command;
    }

    /**
     * Gets command type.
     *
     * @return the command type
     */
    public CommandType getCommand() {
        return command;
    }

    /**
     * Sets the first agent.
     *
     * @param agent1 the first agent
     */
    public void setAgent1(Agent agent1) {
        this.agent1 = agent1;
    }

    /**
     * Gets the first agent.
     *
     * @return the first agent
     */
    public Agent getAgent1() {
        return agent1;
    }

    /**
     * Sets second agent.
     *
     * @param agent2 the second agent
     */
    public void setAgent2(Agent agent2) {
        this.agent2 = agent2;
    }

    /**
     * Gets second agent.
     *
     * @return the second agent
     */
    public Agent getAgent2() {
        return agent2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandExecutorKey)) return false;
        CommandExecutorKey that = (CommandExecutorKey) o;
        return command == that.command &&
                (agent1 != null ? agent1.equals(that.agent1) : that.agent1 == null) &&
                (agent2 != null ? agent2.equals(that.agent2) : that.agent2 == null);
    }

    @Override
    public int hashCode() {
        int result = command != null ? command.hashCode() : 0;
        result = 31 * result + (agent1 != null ? agent1.hashCode() : 0);
        result = 31 * result + (agent2 != null ? agent2.hashCode() : 0);
        return result;
    }
}

