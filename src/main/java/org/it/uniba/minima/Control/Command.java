package org.it.uniba.minima.Control;
import org.it.uniba.minima.Type.CommandType;
import java.util.List;

/**
 * The class that represents a command.
 */
public class Command {
    /**
     * The name of the command.
     */
    private String name;
    /**
     * The aliaseses of the command.
     */
    private List<String> aliases;
    /**
     * The type of the command.
     */
    private CommandType type;

    /**
     * Instantiates a new Command.
     *
     * @param name  the name
     * @param aliases the aliases
     * @param type  the type
     */
    public Command(String name, List<String> aliases, CommandType type) {
        this.name = name;
        this.aliases = aliases;
        this.type = type;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the aliases.
     *
     * @return the aliases
     */
    public List<String> getAliases() {
        return aliases;
    }

    /**
     * Gets command type.
     *
     * @return the command type
     */
    public CommandType getCommandType() {
        return type;
    }
}
