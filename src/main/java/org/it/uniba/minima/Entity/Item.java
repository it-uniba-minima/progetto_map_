package org.it.uniba.minima.Entity;

import org.it.uniba.minima.Database.DatabaseConnection;

public class Item extends Agent{
    boolean isPickable;
    boolean isMovable;

    public Item() {}

    public void getDescription(Room room) {
        String name = getName();
        if (isMovable) {
            DatabaseConnection.printFromDB("Osserva", "0", "0", "0", name, "0");
        } else {
            DatabaseConnection.printFromDB("Osserva", room.getName(), room.getState(), "0", name, "0");
        }
    }

    public boolean isPickable() {
        return isPickable;
    }

    public boolean isMovable() {
        return isMovable;
    }

    public void setPickable(boolean b) {
        isPickable = b;
    }
}
