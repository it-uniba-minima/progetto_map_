package org.it.uniba.minima.Entity;
import org.it.uniba.minima.Type.CommandType;

/**
 * The class representing the corridors type.
 */
public class Corridor {
    /**
     * The starting room.
     */
    private Room startingRoom;
    /**
     * The direction of the corridor.
     */
    private CommandType direction;
    /**
     * The locked status of the corridor.
     */
    private boolean locked;
    /**
     * The arriving room.
     */
    private Room arrivingRoom;

    /**
     * Gets starting room.
     *
     * @return the starting room
     */
    public Room getStartingRoom() {
        return startingRoom;
    }

    /**
     * Gets direction.
     *
     * @return the direction
     */
    public CommandType getDirection() {
        return direction;
    }

    /**
     * Returns the locked status of the corridor.
     *
     * @return the locked status
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Sets the locked status of the corridor.
     *
     * @param locked the locked status
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * Gets arriving room.
     *
     * @return the arriving room
     */
    public Room getArrivingRoom() {
        return arrivingRoom;
    }

    /**
     * Sets starting room.
     *
     * @param room the room
     */
    public void setStartingRoom(Room room) {
        this.startingRoom = room;
    }

    /**
     * Sets arriving room.
     *
     * @param room the room
     */
    public void setArrivingRoom(Room room) {
        this.arrivingRoom = room;
    }
}
