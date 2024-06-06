package org.it.uniba.minima.Control;

import org.it.uniba.minima.Boundary.outputDisplayManager;
import org.it.uniba.minima.Database.DatabaseConnection;
import org.it.uniba.minima.Entity.Agent;
import org.it.uniba.minima.Entity.Personage;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.Entity.Item;
import org.it.uniba.minima.Type.CommandType;
import org.it.uniba.minima.Type.Corridor;
import org.it.uniba.minima.Type.ParserOutput;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Set;

public class CommandExecutor {
    private Game game;
    private HashMap<CommandExecutorKey, CommandBehavior> commandMap;
    private GameLogic gameLogic;

    private CommandBehavior createDirectionCommandBehavior(CommandType direction) {
        return p -> {
            Corridor corridor = game.getCorridorsMap().stream()
                    .filter(c -> c.getStartingRoom().getName().equals(game.getCurrentRoom().getName()) && c.getDirection() == direction)
                    .findFirst()
                    .orElse(null);

            if (corridor != null && !corridor.isLocked()) {
                game.setCurrentRoom(corridor.getArrivingRoom());
                DatabaseConnection.printFromDB("0", game.getCurrentRoom().getName(), game.getCurrentRoom().getState(), "0", "0", "0");
            } else if (corridor != null && corridor.isLocked()) {
                outputDisplayManager.displayText("> Il corridio verso " + direction + " è bloccato!");
            } else {
                outputDisplayManager.displayText("> Non c'è un corridoio verso " + direction + "!");
            }
        };
    }

    public CommandExecutor(Game game) {
        this.game = game;
        this.gameLogic = new GameLogic(game);
        commandMap = new HashMap<>();

        commandMap.put(new CommandExecutorKey(CommandType.NORD, null, null),
                createDirectionCommandBehavior(CommandType.NORD));

        commandMap.put(new CommandExecutorKey(CommandType.EST, null, null),
                createDirectionCommandBehavior(CommandType.EST));

        commandMap.put(new CommandExecutorKey(CommandType.SUD, null, null),
                createDirectionCommandBehavior(CommandType.SUD));

        commandMap.put(new CommandExecutorKey(CommandType.OVEST, null, null),
                createDirectionCommandBehavior(CommandType.OVEST));

        commandMap.put(new CommandExecutorKey(CommandType.OSSERVA, null, null),
                p -> game.getCurrentRoom().printDescription());

        commandMap.put(new CommandExecutorKey(CommandType.HELP, null, null),
                p -> outputDisplayManager.displayText("List of commands"));

        commandMap.put(new CommandExecutorKey(CommandType.INVENTORY, null, null),
                p -> game.printInventory());

        Set<Agent> allAgents = GameManager.getAllAgents(); // Replace this with the actual method to get all agents
        allAgents.forEach(agent ->
                commandMap.put(new CommandExecutorKey(CommandType.OSSERVA, agent, null),
                        p -> {
                            if (game.getCurrentRoom().getAgents().contains(p.getAgent1())) {
                                gameLogic.launchSpecialEvent(p.getCommand(), p.getAgent1());
                                p.getAgent1().getDescription(game.getCurrentRoom());
                            } else if (game.getInventory().contains(p.getAgent1())) {
                                outputDisplayManager.displayText("> La tua borsa non è trasperente!");
                            } else {
                                outputDisplayManager.displayText("> " + p.getAgent1().getName() + " non è nella stanza!");
                            }
                        })
        );

        Set<Item> allItems = GameManager.getAllItems();
        allItems.forEach(item ->
                commandMap.put(new CommandExecutorKey(CommandType.TAKE, item, null),
                        p -> {
                            if (game.getInventory().contains(p.getAgent1())) {
                                outputDisplayManager.displayText("> Hai già " + p.getAgent1().getName() + " nell'inventario!");
                            } else if (game.getCurrentRoom().getAgents().contains(p.getAgent1())) {
                                if (((Item) p.getAgent1()).isPickable()) {
                                    game.getInventory().add((Item) p.getAgent1());
                                    game.getCurrentRoom().getAgents().remove(p.getAgent1());
                                    gameLogic.executeTake((Item) p.getAgent1());
                                    outputDisplayManager.displayText("> Hai raccolto: " + p.getAgent1().getName() + "!");
                                } else {
                                    outputDisplayManager.displayText("> Non puoi raccogliere " + p.getAgent1().getName() + "!");
                                }
                            } else {
                                outputDisplayManager.displayText("> Non c'è " + p.getAgent1().getName() + " nella stanza!");
                            }
                        })
        );

        //uses allItems
        allItems.forEach(item ->
                commandMap.put(new CommandExecutorKey(CommandType.DROP, item, null),
                        p -> {
                            if (game.getInventory().contains(p.getAgent1())) {
                                outputDisplayManager.displayText("> Hai lasciato cadere: " + p.getAgent1().getName() + "!");
                                game.getInventory().remove(p.getAgent1());
                                game.getCurrentRoom().getAgents().add(p.getAgent1());
                            } else if (game.getCurrentRoom().getAgents().contains(p.getAgent1())) {
                                outputDisplayManager.displayText("> " + p.getAgent1().getName() + " è già per terra nella stanza!");
                            } else {
                                outputDisplayManager.displayText("> " + p.getAgent1().getName() + " non è nell'inventario!");
                            }
                        })
        );

        Set<Personage> allPersonages = GameManager.getAllPersonages();
        allPersonages.forEach(personage ->
                commandMap.put(new CommandExecutorKey(CommandType.PARLA, personage, null),
                        p -> {
                            if (game.getCurrentRoom().getAgents().contains(p.getAgent1())) {
                                    DatabaseConnection.printFromDB("Parla", game.getCurrentRoom().getName(), game.getCurrentRoom().getState(), p.getAgent1().getName(), "0", "0");
                                    gameLogic.talkToPersonage((Personage) p.getAgent1());
                            } else {
                                outputDisplayManager.displayText("> " + p.getAgent1().getName() + " non è nella stanza!");
                            }
                        })
        );
        //test if it works, then add custom behavior when trying to pick up the different personages

        // The use command should be different for every item
        // if you can't use the item at that moment we need to call the db to print a message
        //uses allItems
        allItems.forEach(item ->
                commandMap.put(new CommandExecutorKey(CommandType.USA, item, null),
                        p -> {
                            if (game.getInventory().contains(p.getAgent1())) {
                                String statusBeforeAction = game.getCurrentRoom().getState();
                                if (gameLogic.executeUseSingleItem((Item) p.getAgent1())) {
                                    DatabaseConnection.printFromDB("Usa", game.getCurrentRoom().getName(), statusBeforeAction, "0", p.getAgent1().getName(), "0");
                                } else {
                                    outputDisplayManager.displayText("> Non puoi usare " + p.getAgent1().getName() + " da solo!");
                                    //TODO: call db to print a message
                                }
                            } else {
                                outputDisplayManager.displayText("> " + p.getAgent1().getName() + " non è nell'inventario!");
                            }
                        })
        );

        // The use command with two parameters should be different for every combination
        // of agents, in case the combination isn't valid, we need call the db to print a message
        allItems.forEach(item1 ->
                allItems.forEach(item2 ->
                        commandMap.put(new CommandExecutorKey(CommandType.USA, item1, item2),
                                p -> {
                                    if (game.getInventory().contains(p.getAgent1())) {
                                        if (game.getCurrentRoom().getAgents().contains(p.getAgent2())) {
                                            String statusBeforeAction = game.getCurrentRoom().getState();
                                            if (gameLogic.executeUseCombinationInRoom((Item) p.getAgent1(), (Item) p.getAgent2())) {
                                                DatabaseConnection.printFromDB("Usa", game.getCurrentRoom().getName(), statusBeforeAction, "0", p.getAgent1().getName(), p.getAgent2().getName());
                                            } else {
                                                outputDisplayManager.displayText("> Non puoi usare " + p.getAgent1().getName() + " su " + p.getAgent2().getName() + "!");
                                            }
                                        } else if (game.getInventory().contains(p.getAgent2())) {
                                            if (gameLogic.executeUseCombinationInInventory((Item) p.getAgent1(), (Item) p.getAgent2())) {
                                                DatabaseConnection.printFromDB("Usa", "0", "0", "0", p.getAgent1().getName(), p.getAgent2().getName());
                                            } else {
                                                outputDisplayManager.displayText("> Non puoi usare " + p.getAgent1().getName() + " su " + p.getAgent2().getName() + "!");
                                            }
                                        } else {
                                            outputDisplayManager.displayText("> " + p.getAgent2().getName() + " non è qui con noi!");
                                        }
                                    } else {
                                        outputDisplayManager.displayText("> " + p.getAgent1().getName() + " non è nell'inventario!");
                                    }
                                })
                )
        );

        // The fuse command should be different for every combination of items
        // in case the combination isn't valid, we need call the db to print a message
        allItems.forEach(item1 ->
                allItems.forEach(item2 ->
                        commandMap.put(new CommandExecutorKey(CommandType.UNISCI, item1, item2),
                                p -> {
                                    if (game.getInventory().contains(p.getAgent1()) && game.getInventory().contains(p.getAgent2())) {
                                        if (item1 == item2) {
                                            outputDisplayManager.displayText("> Ti rivelerò un segreto: " + p.getAgent1().getName() + " non può fondersi con se stesso!");
                                        } else if (gameLogic.executeFuseCombination((Item) p.getAgent1(), (Item) p.getAgent2())) {
                                            DatabaseConnection.printFromDB("Unisci", "0", "0", "0", p.getAgent1().getName(), p.getAgent2().getName());
                                        } else {
                                            outputDisplayManager.displayText("> Non puoi unire " + p.getAgent1().getName() + " con " + p.getAgent2().getName() + "!");
                                        }
                                    } else {
                                        outputDisplayManager.displayText("> La tua borsa non è quella di Mary Poppins! " + p.getAgent1().getName() + " o " + p.getAgent2().getName() + " non sono nell'inventario!");
                                    }
                                })
        ));

        // The give command should be different for every combination of items and agents
        // in case the combination isn't valid, we need call the db to print a message
        allItems.forEach(item ->
                allPersonages.forEach(personage ->
                        commandMap.put(new CommandExecutorKey(CommandType.DAI, item, personage),
                                p -> {
                                    if (game.getInventory().contains(p.getAgent1())) {
                                        if (game.getCurrentRoom().getAgents().contains(p.getAgent2())) {
                                            String statusBeforeAction = game.getCurrentRoom().getState();
                                            if (gameLogic.executeGiveCombination((Item) p.getAgent1(), (Personage) p.getAgent2())) { // Replace this with the actual method to check if the combination is valid
                                                DatabaseConnection.printFromDB("Dai", game.getCurrentRoom().getName(), statusBeforeAction, p.getAgent2().getName(), p.getAgent1().getName(), "0");
                                            } else {
                                                outputDisplayManager.displayText("> Non puoi dare " + p.getAgent1().getName() + " a " + p.getAgent2().getName() + "!");
                                            }
                                        } else {
                                            outputDisplayManager.displayText("> Se non è invisibile, allora " + p.getAgent2().getName() + " non è qui con noi!");
                                        }
                                    } else {
                                        outputDisplayManager.displayText("> Non puoi dare qualcosa che non possiedi!");
                                    }
                                })
                )
        );
    }

    public void execute(ParserOutput p) {
        CommandExecutorKey key = new CommandExecutorKey(p.getCommand(), p.getAgent1(), p.getAgent2());
        CommandBehavior behavior = commandMap.get(key);
        if (behavior != null) {
            behavior.execute(p);
        } else {
            outputDisplayManager.displayText("No behavior found for the given key");
        }
    }
}
