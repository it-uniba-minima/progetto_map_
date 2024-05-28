package org.it.uniba.minima.Control;

import org.it.uniba.minima.Entity.Agent;
import org.it.uniba.minima.Entity.Personage;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.Entity.Item;
import org.it.uniba.minima.Type.CommandType;
import org.it.uniba.minima.Type.Corridor;
import org.it.uniba.minima.Type.ParserOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CommandExecutor {
    private Game game;
    private HashMap<CommandExecutorKey, CommandBehavior> commandMap;
    private GameLogic gameLogic;

    private CommandBehavior createDirectionCommandBehavior(CommandType direction) {
        return p -> {
            Corridor corridor = game.getCorridorsMap().stream()
                    .filter(c -> c.getStartingRoom().equals(game.getCurrentRoom()) && c.getDirection() == direction)
                    .findFirst()
                    .orElse(null);

            if (corridor != null && !corridor.isLocked()) {
                game.setCurrentRoom(corridor.getArrivingRoom());
                System.out.println("You moved to the " + direction);
            } else if (corridor != null && corridor.isLocked()) {
                System.out.println("The corridor is locked");
            } else {
                System.out.println("There is no corridor to the " + direction);
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

        commandMap.put(new CommandExecutorKey(CommandType.LOOK, null, null),
                p -> System.out.println(game.getCurrentRoom().getDescription()));

        commandMap.put(new CommandExecutorKey(CommandType.HELP, null, null),
                p -> System.out.println("List of commands"));

        commandMap.put(new CommandExecutorKey(CommandType.INVENTORY, null, null),
                p -> game.printInventory());

        Set<Agent> allAgents = GameManager.getAllAgents(); // Replace this with the actual method to get all agents
        allAgents.forEach(agent ->
                commandMap.put(new CommandExecutorKey(CommandType.LOOK, agent, null),
                        p -> {
                            if (game.getCurrentRoom().getAgents().contains(p.getAgent1())
                            //|| game.getInventory().contains((Item) p.getAgent1())
                            ) {
                                System.out.println(p.getAgent1().getDescription());
                            } else {
                                System.out.println("The agent is not in the room");
                            }
                        })
        );

        Set<Item> allItems = GameManager.getAllItems(); // Replace this with the actual method to get all agents
        allItems.forEach(item ->
                commandMap.put(new CommandExecutorKey(CommandType.TAKE, item, null),
                        p -> {
                            if (game.getCurrentRoom().getAgents().contains(p.getAgent1())) {
                                if (((Item) p.getAgent1()).isPickable()) {
                                    game.getInventory().add((Item) p.getAgent1());
                                    game.getCurrentRoom().getAgents().remove(p.getAgent1());
                                    System.out.println("Taken");
                                    // TODO: Add custom text when picking up the item !!use an external function
                                } else {
                                    System.out.println("The item is not pickable");
                                    // TODO: Add custom text when the item is not pickable !!use an external function
                                }
                            } else {
                                System.out.println("The item is not in the room");
                            }
                        })
        );

        //uses allItems
        allItems.forEach(item ->
                commandMap.put(new CommandExecutorKey(CommandType.DROP, item, null),
                        p -> {
                            if (game.getInventory().contains(p.getAgent1())) {
                                if (((Item) p.getAgent1()).isDroppable()) {
                                    game.getCurrentRoom().getAgents().add((Item) p.getAgent1());
                                    game.getInventory().remove(p.getAgent1());
                                    System.out.println("Dropped");
                                } else {
                                    System.out.println("The item is not droppable");
                                }
                            } else {
                                System.out.println("The item is not in the inventory");
                            }
                        })
        );

        Set<Personage> allPersonages = GameManager.getAllPersonages(); // Replace this with the actual method to get all agents
        allPersonages.forEach(personage ->
                commandMap.put(new CommandExecutorKey(CommandType.TALK, personage, null),
                        p -> {
                            if (game.getCurrentRoom().getAgents().contains(p.getAgent1())) {
                                    System.out.println("Talking to " + p.getAgent1().getName());
                                    // TODO: Add custom text when talking to the personage !!use an external function
                            } else {
                                System.out.println("The personage is not in the room");
                            }
                        })
        );
        //test if it works, then add custom behavior when trying to pick up the different personages

        // The use command should be different for every item
        // if you can't use the item at that moment we need to call the db to print a message
        //uses allItems
        allItems.forEach(item ->
                commandMap.put(new CommandExecutorKey(CommandType.USE, item, null),
                        p -> {
                            if (game.getInventory().contains(p.getAgent1())) {
                                if (gameLogic.executeUseSingleItem((Item) p.getAgent1())) {
                                    System.out.println("Used");
                                    //call function for custom behavior
                                } else {
                                    System.out.println("The item is not usable");
                                }
                            } else {
                                System.out.println("The item is not in the inventory");
                                //call database for custom message
                            }
                        })
        );

        // The use command with two parameters should be different for every combination
        // of agents, in case the combination isn't valid, we need call the db to print a message
        allItems.forEach(item1 ->
                allItems.forEach(item2 ->
                        commandMap.put(new CommandExecutorKey(CommandType.USE, item1, item2),
                                p -> {
                                    if (game.getInventory().contains(p.getAgent1())) {
                                        if (game.getCurrentRoom().getAgents().contains(p.getAgent2())) {
                                            if (gameLogic.executeUseCombinationInRoom((Item) p.getAgent1(), (Item) p.getAgent2())) { // Replace this with the actual method to check if the combination is valid
                                                System.out.println(p.getAgent1().getName() + " used on " + p.getAgent2().getName());
                                                //call function for custom behavior
                                            } else {
                                                System.out.println("The combination is not valid");
                                            }
                                        } else if (game.getInventory().contains(p.getAgent2())) {
                                            if (gameLogic.executeUseCombinationInInventory((Item) p.getAgent1(), (Item) p.getAgent2())) { // Replace this with the actual method to check if the combination is valid
                                                System.out.println(p.getAgent1().getName() + " used on " + p.getAgent2().getName());
                                                //call function for custom behavior
                                            } else {
                                                System.out.println("The combination is not valid");
                                            }
                                        } else {
                                            System.out.println("The second agent is not in the room or in the inventory");
                                        }
                                    } else {
                                        System.out.println("The first agent is not in the inventory");
                                    }
                                })
                )
        );

        // The fuse command should be different for every combination of items
        // in case the combination isn't valid, we need call the db to print a message
        allItems.forEach(item1 ->
                allItems.forEach(item2 ->
                        commandMap.put(new CommandExecutorKey(CommandType.FUSE, item1, item2),
                                p -> {
                                    if (game.getInventory().contains(p.getAgent1()) && game.getInventory().contains(p.getAgent2())) {
                                        if (item1 == item2) {
                                            System.out.println("You can't fuse an item with itself");
                                        } else if (gameLogic.executeFuseCombination((Item) p.getAgent1(), (Item) p.getAgent2())) { // Replace this with the actual method to check if the combination is valid
                                            System.out.println(p.getAgent1().getName() + " fused with " + p.getAgent2().getName());
                                            //call function for custom behavior
                                        } else {
                                            System.out.println("The combination is not valid");
                                        }
                                    } else {
                                        System.out.println("You're missing one or both of the items in the inventory");
                                    }
                                })
        ));

        // The give command should be different for every combination of items and agents
        // in case the combination isn't valid, we need call the db to print a message
        allItems.forEach(item ->
                allPersonages.forEach(personage ->
                        commandMap.put(new CommandExecutorKey(CommandType.GIVE, item, personage),
                                p -> {
                                    if (game.getInventory().contains(p.getAgent1())) {
                                        if (game.getCurrentRoom().getAgents().contains(p.getAgent2())) {
                                            if (gameLogic.executeGiveCombination((Item) p.getAgent1(), (Personage) p.getAgent2())) { // Replace this with the actual method to check if the combination is valid
                                                System.out.println(p.getAgent1().getName() + " given to " + p.getAgent2().getName());
                                                //call function for custom behavior
                                            } else {
                                                System.out.println("The combination is not valid");
                                            }
                                        } else {
                                            System.out.println("The personage is not in the room");
                                        }
                                    } else {
                                        System.out.println("The item is not in the inventory");
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
            System.out.println("No behavior found for the given key");
        }
    }
}
