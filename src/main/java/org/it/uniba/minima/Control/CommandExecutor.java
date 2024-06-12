package org.it.uniba.minima.Control;
import org.it.uniba.minima.Boundary.OutputDisplayManager;
import org.it.uniba.minima.Database.DatabaseConnection;
import org.it.uniba.minima.Entity.Personage;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.Entity.Item;
import org.it.uniba.minima.Type.CommandExecutorKey;
import org.it.uniba.minima.Type.CommandType;
import org.it.uniba.minima.Type.Corridor;
import org.it.uniba.minima.Type.ParserOutput;
import java.util.HashMap;
import java.util.Set;

/**
 * The class that manages the execution of the commands.
 */
public class CommandExecutor {
    /**
     * The instance of the game.
     */
    private Game game;
    /**
     * The map containing all the commands and their behaviors.
     */
    private HashMap<CommandExecutorKey, CommandBehavior> commandMap;
    /**
     * The instance of the game logic.
     */
    private GameLogic gameLogic;

    /**
     * The generalized behavior of the movement commands.
     */
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
                OutputDisplayManager.displayText("> Il corridio verso " + direction + " è bloccato!");
            } else {
                OutputDisplayManager.displayText("> Non c'è un corridoio verso " + direction + "!");
            }
        };
    }

    /**
     * Instantiates a map of all the commands and their behaviors.
     *
     * @param game the game instance
     */
    public CommandExecutor(Game game) {
        this.game = game;
        this.gameLogic = new GameLogic(game);
        commandMap = new HashMap<>();

        // The command to go north
        commandMap.put(new CommandExecutorKey(CommandType.NORD, 0),
                createDirectionCommandBehavior(CommandType.NORD));

        // The command to go east
        commandMap.put(new CommandExecutorKey(CommandType.EST, 0),
                createDirectionCommandBehavior(CommandType.EST));

        // The command to go south
        commandMap.put(new CommandExecutorKey(CommandType.SUD, 0),
                createDirectionCommandBehavior(CommandType.SUD));

        // The command to go west
        commandMap.put(new CommandExecutorKey(CommandType.OVEST, 0),
                createDirectionCommandBehavior(CommandType.OVEST));

        // The command to print the description of the room
        commandMap.put(new CommandExecutorKey(CommandType.OSSERVA, 0),
                p -> game.getCurrentRoom().printDescription());

        // The command to print the help
        commandMap.put(new CommandExecutorKey(CommandType.AIUTO, 0),
                p -> {
                    OutputDisplayManager.displayText("> Comandi disponibili:");
                    GameManager gameManager = new GameManager();
                    Set<Command> commands = gameManager.getAllCommands();
                    commands.forEach(c -> OutputDisplayManager.displayText(">  - " + c.getName()));
                    OutputDisplayManager.displayText("> (Hint: per ulteriori informazioni clicca sul punto interrogativo in alto)");
                }
        );

        // The command to print the inventory
        commandMap.put(new CommandExecutorKey(CommandType.INVENTARIO, 0),
                p -> game.printInventory());

        // The command to look at an agent
        commandMap.put(new CommandExecutorKey(CommandType.OSSERVA, 1),
                p -> {
                    if (game.getCurrentRoom().getAgents().contains(p.getAgent1())) {
                        gameLogic.executeLook(p.getAgent1());
                        p.getAgent1().getDescription(game.getCurrentRoom());
                    } else if (game.getInventory().contains(p.getAgent1())) {
                        OutputDisplayManager.displayText("> La tua borsa non è trasparente!");
                    } else {
                        OutputDisplayManager.displayText("> " + p.getAgent1().getName() + " non è nella stanza!");
                    }
                });

        // The command to take an item
        commandMap.put(new CommandExecutorKey(CommandType.PRENDI, 1),
                p -> {
                    if (game.getInventory().contains(p.getAgent1())) {
                        OutputDisplayManager.displayText("> Hai già " + p.getAgent1().getName() + " nell'inventario!");
                    } else if (game.getCurrentRoom().getAgents().contains(p.getAgent1())) {
                        if ((p.getAgent1() instanceof Item) && ((Item) p.getAgent1()).isPickable()) {
                            game.addInventory((Item) p.getAgent1());
                            game.getCurrentRoom().removeAgent(p.getAgent1());
                            gameLogic.executeTake((Item) p.getAgent1());
                            OutputDisplayManager.displayText("> Hai raccolto: " + p.getAgent1().getName() + "!");
                        } else {
                            OutputDisplayManager.displayText("> Non puoi raccogliere " + p.getAgent1().getName() + "!");
                        }
                    } else {
                        OutputDisplayManager.displayText("> Non c'è " + p.getAgent1().getName() + " nella stanza!");
                    }
                });

        // The command to drop an item
        commandMap.put(new CommandExecutorKey(CommandType.LASCIA, 1),
                p -> {
                    if (game.getInventory().contains(p.getAgent1())) {
                        OutputDisplayManager.displayText("> Hai lasciato cadere: " + p.getAgent1().getName() + "!");
                        game.removeInventory((Item) (p.getAgent1()));
                        game.getCurrentRoom().getAgents().add(p.getAgent1());
                    } else if (game.getCurrentRoom().getAgents().contains(p.getAgent1())) {
                        OutputDisplayManager.displayText("> " + p.getAgent1().getName() + " è già per terra nella stanza!");
                    } else {
                        OutputDisplayManager.displayText("> " + p.getAgent1().getName() + " non è nell'inventario!");
                    }
                });

        // The command to talk to a personage
        commandMap.put(new CommandExecutorKey(CommandType.PARLA, 1),
                p -> {
                    if (game.getCurrentRoom().getAgents().contains(p.getAgent1())) {
                            DatabaseConnection.printFromDB("Parla", game.getCurrentRoom().getName(), game.getCurrentRoom().getState(), p.getAgent1().getName(), "0", "0");
                            gameLogic.talkToPersonage((Personage) p.getAgent1());
                    } else {
                        OutputDisplayManager.displayText("> " + p.getAgent1().getName() + " non è nella stanza!");
                    }
                });

        // The command to use an item alone
        commandMap.put(new CommandExecutorKey(CommandType.USA, 1),
                p -> {
                    if (game.getInventory().contains(p.getAgent1()) || game.getCurrentRoom().getAgents().contains(p.getAgent1())) {
                        String statusBeforeAction = game.getCurrentRoom().getState();
                        if (gameLogic.executeUseSingleItem((Item) p.getAgent1())) {
                            DatabaseConnection.printFromDB("Usa", game.getCurrentRoom().getName(), statusBeforeAction, "0", p.getAgent1().getName(), "0");
                        } else {
                            OutputDisplayManager.displayText("> Non puoi usare " + p.getAgent1().getName() + " da solo!");
                        }
                    } else {
                        OutputDisplayManager.displayText("> " + p.getAgent1().getName() + " non è nell'inventario!");
                    }
                });

        // The command to use an item on another item
        commandMap.put(new CommandExecutorKey(CommandType.USA, 2),
                p -> {
                    if (game.getInventory().contains(p.getAgent1())) {
                        if (game.getCurrentRoom().getAgents().contains(p.getAgent2())) {
                            String statusBeforeAction = game.getCurrentRoom().getState();
                            if (gameLogic.executeUseCombinationInRoom((Item) p.getAgent1(), (Item) p.getAgent2())) {
                                DatabaseConnection.printFromDB("Usa", game.getCurrentRoom().getName(), statusBeforeAction, "0", p.getAgent1().getName(), p.getAgent2().getName());
                            } else {
                                OutputDisplayManager.displayText("> Non puoi usare " + p.getAgent1().getName() + " su " + p.getAgent2().getName() + "!");
                            }
                        } else if (game.getInventory().contains(p.getAgent2())) {
                            if (gameLogic.executeUseCombinationInInventory((Item) p.getAgent1(), (Item) p.getAgent2())) {
                                DatabaseConnection.printFromDB("Usa", "0", "0", "0", p.getAgent1().getName(), p.getAgent2().getName());
                            } else {
                                OutputDisplayManager.displayText("> Non puoi usare " + p.getAgent1().getName() + " su " + p.getAgent2().getName() + "!");
                            }
                        } else {
                            OutputDisplayManager.displayText("> " + p.getAgent2().getName() + " non è qui con noi!");
                        }
                    } else {
                        OutputDisplayManager.displayText("> " + p.getAgent1().getName() + " non è nell'inventario!");
                    }
                });

        // The command to fuse two items
        commandMap.put(new CommandExecutorKey(CommandType.UNISCI, 2),
                p -> {
                    if (game.getInventory().contains(p.getAgent1()) && game.getInventory().contains(p.getAgent2())) {
                        if (p.getAgent1() == p.getAgent2()) {
                            OutputDisplayManager.displayText("> Ti rivelerò un segreto: " + p.getAgent1().getName() + " non può fondersi con se stesso!");
                        } else if (gameLogic.executeFuseCombination((Item) p.getAgent1(), (Item) p.getAgent2())) {
                            DatabaseConnection.printFromDB("Unisci", "0", "0", "0", p.getAgent1().getName(), p.getAgent2().getName());
                        } else {
                            OutputDisplayManager.displayText("> Non puoi unire " + p.getAgent1().getName() + " con " + p.getAgent2().getName() + "!");
                        }
                    } else {
                        OutputDisplayManager.displayText("> La tua borsa non è quella di Mary Poppins! " + p.getAgent1().getName() + " o " + p.getAgent2().getName() + " non sono nell'inventario!");
                    }
                });

        // The give command to give an item to a personage
        commandMap.put(new CommandExecutorKey(CommandType.DAI, 2),
                p -> {
                    if (game.getInventory().contains(p.getAgent1())) {
                        if (game.getCurrentRoom().getAgents().contains(p.getAgent2())) {
                            String statusBeforeAction = game.getCurrentRoom().getState();
                            if (gameLogic.executeGiveCombination((Item) p.getAgent1(), (Personage) p.getAgent2())) { // Replace this with the actual method to check if the combination is valid
                                DatabaseConnection.printFromDB("Dai", game.getCurrentRoom().getName(), statusBeforeAction, p.getAgent2().getName(), p.getAgent1().getName(), "0");
                            } else {
                                OutputDisplayManager.displayText("> Non puoi dare " + p.getAgent1().getName() + " a " + p.getAgent2().getName() + "!");
                            }
                        } else {
                            OutputDisplayManager.displayText("> Se non è invisibile, allora " + p.getAgent2().getName() + " non è qui con noi!");
                        }
                    } else {
                        OutputDisplayManager.displayText("> Non puoi dare qualcosa che non possiedi!");
                    }
                });
    }

    /**
     * Execute.
     *
     * @param p the p
     */
    public void execute(ParserOutput p) {
        int args = (p.getAgent1() != null) ? ((p.getAgent2() != null) ? 2 : 1) : 0;

        CommandExecutorKey key = new CommandExecutorKey(p.getCommand(), args);
        CommandBehavior behavior = commandMap.get(key);
        if (behavior != null) {
            behavior.execute(p);
        } else {
            OutputDisplayManager.displayText("> Non penso tu possa agire in questa maniera!");
        }
    }
}
