package org.it.uniba.minima.Type;

/**
 * The class representing the command executor key.
 */
public class CommandExecutorKey {
    /**
     * The command type
     */
    private CommandType command;
    /*
     * Args number
     */
    private int args;

    /**
     * Instantiates a new Command executor key with the agents.
     *
     * @param c  the command type
     */
    public CommandExecutorKey(CommandType c, int a) {
        this.command = c;
        this.args = a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandExecutorKey)) return false;
        CommandExecutorKey that = (CommandExecutorKey) o;
        return command == that.command && args == that.args;
    }

    @Override
    public int hashCode() {
        int result = command != null ? command.hashCode() : 0;
        result = 31 * result + args;
        return result;
    }
}

