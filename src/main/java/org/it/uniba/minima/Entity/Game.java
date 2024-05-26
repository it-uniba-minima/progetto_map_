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
    private Map<Integer, String> statesMap;

    public Game() {
        this.inventory = new ArrayList<>();
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

    public List<Agent> getAllAgents() {
        Item item2 = new Item();
        item2.setName("item2");
        item2.setDescription("This is item2");
        item2.setAlias(List.of("a1", "ag1"));

        Item item1 = new Item();
        item1.setName("item1");
        item1.setDescription("This is item1");
        item1.setAlias(List.of("i1", "it1"));

        Character character1 = new Character();
        character1.setName("character1");
        character1.setDescription("This is character1");
        character1.setAlias(List.of("c1", "ch1"));

        Character character2 = new Character();
        character2.setName("character2");
        character2.setDescription("This is character2");
        character2.setAlias(List.of("c2", "ch2"));

        return new ArrayList<>(List.of(item2, item1, character1, character2));
    }

    public List<Item> getAllItems() {
        Item item2 = new Item();
        item2.setName("item2");
        item2.setDescription("This is item2");
        item2.setAlias(List.of("a1", "ag1"));

        Item item1 = new Item();
        item1.setName("item1");
        item1.setDescription("This is item1");
        item1.setAlias(List.of("i1", "it1"));

        return new ArrayList<>(List.of(item1, item2));
    }

    public List<Character> getAllCharacters() {
        Character character1 = new org.it.uniba.minima.Entity.Character();
        character1.setName("character1");
        character1.setDescription("This is character1");
        character1.setAlias(List.of("c1", "ch1"));

        Character character2 = new Character();
        character2.setName("character2");
        character2.setDescription("This is character2");
        character2.setAlias(List.of("c2", "ch2"));

        return new ArrayList<>(List.of(character1, character2));
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setStatesMap(Map<Integer, String> statesMap) {
        this.statesMap = statesMap;
    }

    public Map<Integer, String> getStatesMap() {
        return statesMap;
    }

}
