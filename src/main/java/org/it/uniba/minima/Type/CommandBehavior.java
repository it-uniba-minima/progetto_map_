package org.it.uniba.minima.Type;

/**
 * The interface of the command behavior.
 */
public interface CommandBehavior {
    /**
     * The method that executes the command.
     *
     * @param parsedText the parsed text
     */
    void execute(ParserOutput parsedText);
}
