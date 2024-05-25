package org.it.uniba.minima.Control;

import org.it.uniba.minima.Entity.Agent;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.Type.CommandType;
import org.it.uniba.minima.Type.ParserOutput;

public interface CommandBehavior {
    void execute(ParserOutput parsedText);
}