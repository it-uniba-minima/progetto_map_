package org.it.uniba.minima.Control;

import org.it.uniba.minima.Entity.Agent;
import org.it.uniba.minima.Type.CommandType;

public class CommandExecutorKey {
    private final CommandType command;
    private final Agent agent1;
    private final Agent agent2;

    public CommandExecutorKey(CommandType c, Agent a1, Agent a2) {
        this.command = c;
        this.agent1 = a1;
        this.agent2 = a2;
    }

    public CommandType getCommand() {
        return command;
    }

    public Agent getAgent1() {
        return agent1;
    }

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

