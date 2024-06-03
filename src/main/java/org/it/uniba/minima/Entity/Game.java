package org.it.uniba.minima.Entity;


import org.it.uniba.minima.Boundary.outputDisplayManager;
import org.it.uniba.minima.GUI.GameGUI;
import org.it.uniba.minima.Type.Corridor;
import java.util.List;
import java.util.Map;

public class Game {
    private String nickname;
    private List<Item> inventory;
    private String currentTime;
    private Room currentRoom;
    private List<Corridor> corridorsMap;
    private Map<String, String> statesMap;
    public static Game game = new Game();

    public static void setUpGame(Game game) {
        Game.game = game;
        Game.game.setCurrentRoom(game.getCorridorsMap().get(0).getStartingRoom());
    }

    public static Game getInstance() {
        return game;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void addInventory(Item item) {
        inventory.add(item);
    }

    public void removeInventory(Item item) {
        inventory.remove(item);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        // Retrieve the Room instance from the corridorsMap
        for (Corridor corridor : corridorsMap) {
            if (corridor.getStartingRoom().equals(room)) {
                this.currentRoom = corridor.getStartingRoom();
                GameGUI.setImagePanel(currentRoom.getName());
                return;
            }
        }
        // If the room is not found in the corridorsMap, set the currentRoom to the provided room
        this.currentRoom = room;
        GameGUI.setImagePanel(currentRoom.getName());
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void printInventory() {
        outputDisplayManager.displayText("Inventory: \n");
        for (Item item : inventory) {
            outputDisplayManager.displayText(item.getName());
        }
    }

    public List<Corridor> getCorridorsMap() {
        return corridorsMap;
    }

    public void unlockCorridor(String r1, String r2) {
        for (Corridor corridor : corridorsMap) {
            if (corridor.getStartingRoom().getName().equals(r1) && corridor.getArrivingRoom().getName().equals(r2)) {
                corridor.setLocked(false);
            }
        }
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setRoomState(String room, String state) {
        statesMap.replace(room, state);
        corridorsMap.stream()
                .filter(corridor -> corridor.getStartingRoom().getName().equals(room))
                .forEach(corridor -> corridor.getStartingRoom().setState(state));
    }

    public void setCorridorsMap(List<Corridor> corridorsMap) {
        this.corridorsMap = corridorsMap;
    }

    public void setStatesMap(Map<String, String> roomStates) {
        this.statesMap = roomStates;
    }
}
