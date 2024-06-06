package org.it.uniba.minima.Entity;


import org.it.uniba.minima.Boundary.outputDisplayManager;
import org.it.uniba.minima.GUI.GameGUI;
import org.it.uniba.minima.Type.Corridor;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.it.uniba.minima.GUI.GameGUI.updateInventoryTextArea;

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
        GameGUI.setImagePanel(game.getCurrentRoom().getName());
    }

    public static Game getInstance() {
        return game;
    }

    public String getNickname() {
        return game.nickname;
    }

    public void setNickname(String nickname) {
        game.nickname = nickname;
    }

    public void addInventory(Item item) {
        game.inventory.add(item);
        List<String> itemDescriptions = game.inventory.stream().map(Item::getName).collect(Collectors.toList());
        String[] itemDescriptionArray = itemDescriptions.toArray(new String[0]);
        updateInventoryTextArea(itemDescriptionArray);
    }

    public void removeInventory(Item item) {
        game.inventory.remove(item);
        List<String> itemDescriptions = game.inventory.stream().map(Item::getName).collect(Collectors.toList());
        String[] itemDescriptionArray = itemDescriptions.toArray(new String[0]);
        updateInventoryTextArea(itemDescriptionArray);
    }

    public Room getCurrentRoom() {
        return game.currentRoom;
    }

    public void setCurrentRoom(Room room) {
        // Retrieve the Room instance from the corridorsMap
        for (Corridor corridor : game.corridorsMap) {
            if (corridor.getStartingRoom().equals(room)) {
                game.currentRoom = corridor.getStartingRoom();
                GameGUI.setImagePanel(game.currentRoom.getName());
                return;
            }
        }
        // If the room is not found in the corridorsMap, set the currentRoom to the provided room
        game.currentRoom = room;
        GameGUI.setImagePanel(game.currentRoom.getName());
    }

    public List<Item> getInventory() {
        return game.inventory;
    }

    public void printInventory() {
        outputDisplayManager.displayText("> Inventario: ");
        for (Item item : game.inventory) {
            outputDisplayManager.displayText(">  - " + item.getName());
        }
    }

    public List<Corridor> getCorridorsMap() {
        return game.corridorsMap;
    }

    public void unlockCorridor(String r1, String r2) {
        for (Corridor corridor : game.corridorsMap) {
            if (corridor.getStartingRoom().getName().equals(r1) && corridor.getArrivingRoom().getName().equals(r2)) {
                corridor.setLocked(false);
            }
        }
    }

    public void setCurrentTime(String currentTime) {
         game.currentTime = currentTime;
    }

    public String getCurrentTime() {
        return game.currentTime;
    }

    public void setRoomState(String room, String state) {
        game.statesMap.replace(room, state);
        game.corridorsMap.stream()
                .filter(corridor -> corridor.getStartingRoom().getName().equals(room))
                .forEach(corridor -> corridor.getStartingRoom().setState(state));
    }

    public void setCorridorsMap(List<Corridor> corridorsMap) {
        game.corridorsMap = corridorsMap;
    }

    public void setStatesMap(Map<String, String> roomStates) {
        game.statesMap = roomStates;
    }

    public Object getRoomState(String room) {
        return game.statesMap.get(room);
    }
}
