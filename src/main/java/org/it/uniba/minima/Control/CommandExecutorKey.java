package org.it.uniba.minima.Control;

import org.it.uniba.minima.Entity.Agent;
import org.it.uniba.minima.Type.CommandType;

public class CommandExecutorKey {
    private CommandType command;
    private Agent agent1;
    private Agent agent2;

    public CommandExecutorKey(CommandType c, Agent a1, Agent a2) {
        this.command = c;
        this.agent1 = a1;
        this.agent2 = a2;
    }

    public CommandExecutorKey() {
        this.command = null;
        this.agent1 = null;
        this.agent2 = null;
    }

    public void setCommand(CommandType command) {
        this.command = command;
    }

    public CommandType getCommand() {
        return command;
    }

    public void setAgent1(Agent agent1) {
        this.agent1 = agent1;
    }

    public Agent getAgent1() {
        return agent1;
    }

    public void setAgent2(Agent agent2) {
        this.agent2 = agent2;
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

