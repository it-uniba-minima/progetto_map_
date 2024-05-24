package org.it.uniba.minima.Entity;


import java.io.Serializable;
import java.util.Map;

public class Game implements Serializable {
    private String nickname;
    private Item[] inventory;
    private String currentTime;
    private Room currentRoom;
    private Map<Integer, Boolean> graphMap;
    private Map<Integer, String> stateMap;

    public static Game getInstance(Game game) {
        return game;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
