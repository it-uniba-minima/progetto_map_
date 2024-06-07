package org.it.uniba.minima.Type;

/**
 * The class representing the parser output.
 * It extends the CommandExecutorKey class by adding the number of arguments.
 */
public class ParserOutput extends CommandExecutorKey {
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
}
