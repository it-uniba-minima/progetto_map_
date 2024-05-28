package org.it.uniba.minima.Type;
import org.it.uniba.minima.Control.CommandExecutorKey;

public class ParserOutput extends CommandExecutorKey {
    private int args;

    public ParserOutput() {
        args = 0;
    }

    public int getArgs() {
        return args;
    }

    public void setArgs(int args) {
        this.args = args;
    }
}
