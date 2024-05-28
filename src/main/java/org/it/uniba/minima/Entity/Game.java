package org.it.uniba.minima.Entity;


import org.it.uniba.minima.Type.Corridor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game implements Serializable {
    private String nickname;
    private List<Item> inventory;
    private String currentTime;
    private Room currentRoom;
    private List<Corridor> corridorsMap;
    private Map<String, String> statesMap;

    public Game() {
        this.inventory = new ArrayList<>();
    }

    public void addInventory(Item item) {
        inventory.add(item);
    }

    public void removeInventory(Item item) {
        inventory.remove(item);
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public static Game getInstance(Game game) {
        return game;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public List<Corridor> getCorridorsMap() {
        return corridorsMap;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void printInventory() {
        System.out.println("Inventory: \n");
        for (Item item : inventory) {
            System.out.println(item.getName());
        }
    }

    public void setCorridorsMap(List<Corridor> corridors) {
        this.corridorsMap = corridors;
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

    public void setStatesMap(Map<String, String> statesMap) {
        this.statesMap = statesMap;
    }

    public void setRoomState(String room, String state) {
        statesMap.put(room, state);
    }

    public Map<String, String> getStatesMap() {
        return statesMap;
    }

}
