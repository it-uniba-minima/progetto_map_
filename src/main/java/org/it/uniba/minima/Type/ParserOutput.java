package org.it.uniba.minima.Type;
import org.it.uniba.minima.Entity.Agent;

/**
 * The class representing the parser output.
 * It extends the CommandExecutorKey class by adding the number of arguments.
 */
public class ParserOutput {
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
     * The number of arguments.
     */
    private int args;

    /**
     * Constructor of the class.
     */
    public ParserOutput() {
        args = 0;
    }

    /**
     * Gets the number of arguments.
     *
     * @return the args
     */
    public int getArgs() {
        return args;
    }

    /**
     * Sets the number of arguments.
     *
     * @param args the args
     */
    public void setArgs(int args) {
        this.args = args;
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

    /**
     * Override of the equals method.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParserOutput)) return false;
        ParserOutput that = (ParserOutput) o;
        return command == that.command &&
                (agent1 != null ? agent1.equals(that.agent1) : that.agent1 == null) &&
                (agent2 != null ? agent2.equals(that.agent2) : that.agent2 == null);
    }

    /**
     * Override of the hashcode method.
     *
     * @return the hashcode
     */
    @Override
    public int hashCode() {
        int result = command != null ? command.hashCode() : 0;
        result = 31 * result + (agent1 != null ? agent1.hashCode() : 0);
        result = 31 * result + (agent2 != null ? agent2.hashCode() : 0);
        return result;
    }
}
