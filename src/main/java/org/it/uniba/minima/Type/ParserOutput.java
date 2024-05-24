package org.it.uniba.minima.Type;
import org.it.uniba.minima.Entity.Agent;

public class ParserOutput {
    private CommandType command;
    private Agent agent1;
    private Agent agent2;
    private int args;

    public ParserOutput() {
        command = null;
        agent1 = null;
        agent2 = null;
        args = 0;
    }

    public CommandType getCommand() {
        return command;
    }

    public void setCommand(CommandType command) {
        this.command = command;
    }

    public Agent getAgent1() {
        return agent1;
    }

    public void setAgent1(Agent agent) {
        this.agent1 = agent;
    }

    public Agent getAgent2() {
        return agent2;
    }

    public void setAgent2(Agent agent2) {
        this.agent2 = agent2;
    }

    public int getArgs() {
        return args;
    }

    public void setArgs(int args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "Command: " + command + ", Args: " + args + ", Agent1: " + (agent1 != null ? agent1.getName() : "null") + ", Agent2: " + (agent2 != null ? agent2.getName() : "null");
    }
}
