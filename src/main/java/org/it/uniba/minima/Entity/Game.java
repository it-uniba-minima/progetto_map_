package org.it.uniba.minima.Entity;
import org.it.uniba.minima.Boundary.OutputDisplayManager;
import org.it.uniba.minima.GUI.GameGUI;
import org.it.uniba.minima.Type.Corridor;
import java.util.List;
import java.util.Map;

/**
 * The class that represents the game.
 */
public class Game {
    /**
     * The nickname of the player.
     */
    private String nickname;
    /**
     * The inventory of the player.
     */
    private List<Item> inventory;
    /**
     * The starting time of the game.
     */
    private String currentTime;
    /**
     * The current room in which the player is in.
     */
    private Room currentRoom;
    /**
     * The map of the corridors.
     */
    private List<Corridor> corridorsMap;
    /**
     * The map of the states of the rooms.
     */
    private Map<String, String> statesMap;
    /**
     * The instance of the game.
     */
    private static Game game = new Game();

    /**
     * Sets up the instance of the game.
     *
     * @param game the game
     */
    public static void setUpGame(Game game) {
        Game.game = game;
        GameGUI.setImagePanel(game.getCurrentRoom().getName());
    }

    /**
     * Gets the instance of the game.
     *
     * @return the instance
     */
    public static Game getInstance() {
        return game;
    }

    /**
     * Gets nickname.
     *
     * @return the nickname
     */
    public String getNickname() {
        return game.nickname;
    }

    /**
     * Sets nickname.
     *
     * @param nickname the nickname
     */
    public void setNickname(String nickname) {
        game.nickname = nickname;
    }

    /**
     * Gets inventory.
     *
     * @return the inventory
     */
    public List<Item> getInventory() {
        return game.inventory;
    }

    /**
     * Add an item to the inventory.
     *
     * @param item the item
     */
    public void addInventory(Item item) {
        game.inventory.add(item);
        List<String> itemsNames = game.inventory.stream().map(Item::getName).toList();
        String[] itemsNamesArray = itemsNames.toArray(new String[0]);
        GameGUI.updateInventoryTextArea(itemsNamesArray);
    }

    /**
     * Remove an item from the inventory.
     *
     * @param item the item
     */
    public void removeInventory(Item item) {
        game.inventory.remove(item);
        List<String> itemsNames = game.inventory.stream().map(Item::getName).toList();
        String[] itemsNamesArray = itemsNames.toArray(new String[0]);
        GameGUI.updateInventoryTextArea(itemsNamesArray);
    }

    /**
     * Print the inventory.
     */
    public void printInventory() {
        OutputDisplayManager.displayText("> Inventario: ");
        for (Item item : game.inventory) {
            OutputDisplayManager.displayText(">  - " + item.getName());
        }
    }

    /**
     * Gets current room.
     *
     * @return the current room
     */
    public Room getCurrentRoom() {
        return game.currentRoom;
    }

    /**
     * Sets current room.
     *
     * @param room the room
     */
    public void setCurrentRoom(Room room) {
        for (Corridor corridor : game.corridorsMap) {
            if (corridor.getStartingRoom().equals(room)) {
                game.currentRoom = corridor.getStartingRoom();
                GameGUI.setImagePanel(game.currentRoom.getName());
                return;
            }
        }
        game.currentRoom = room;
        GameGUI.setImagePanel(game.currentRoom.getName());
    }

    /**
     * Gets the starting time.
     *
     * @return the starting time
     */
    public String getCurrentTime() {
        return game.currentTime;
    }

    /**
     * Sets starting time.
     *
     * @param currentTime the starting time
     */
    public void setCurrentTime(String currentTime) {
        game.currentTime = currentTime;
    }

    /**
     * Gets corridors map.
     *
     * @return the corridors map
     */
    public List<Corridor> getCorridorsMap() {
        return game.corridorsMap;
    }

    /**
     * Unlocks a corridor.
     *
     * @param r1 the starting room
     * @param r2 the arriving room
     */
    public void unlockCorridor(String r1, String r2) {
        for (Corridor corridor : game.corridorsMap) {
            if (corridor.getStartingRoom().getName().equals(r1) && corridor.getArrivingRoom().getName().equals(r2)) {
                corridor.setLocked(false);
            }
        }
    }

    /**
     * Gets room state.
     *
     * @param room the room
     * @return the room state
     */
    public String getRoomState(String room) {
        return game.statesMap.get(room);
    }

    /**
     * Sets room state.
     *
     * @param room  the room
     * @param state the state
     */
    public void setRoomState(String room, String state) {
        game.statesMap.replace(room, state);
        game.corridorsMap.stream()
                .filter(corridor -> corridor.getStartingRoom().getName().equals(room))
                .forEach(corridor -> corridor.getStartingRoom().setState(state));
    }
}
