package org.it.uniba.minima.Type;

import org.it.uniba.minima.Entity.Room;

public class Corridor {
    private Room room1;
    private CommandType direction;
    private boolean locked;
    private Room room2;

    public Corridor(Room room1, CommandType direction, boolean locked, Room room2) {
        this.room1 = room1;
        this.direction = direction;
        this.locked = locked;
        this.room2 = room2;
    }

    public Room getStartingRoom() {
        return room1;
    }

    public CommandType getDirection() {
        return direction;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Room getArrivingRoom() {
        return room2;
    }
}
