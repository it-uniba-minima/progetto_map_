package org.it.uniba.minima.Entity;

import org.it.uniba.minima.Database.DatabaseConnection;

public class Personage extends Agent{
    private boolean isTalkable;

    public Personage() {}

    public void getDescription(Room room) {
        String name = getName();
        DatabaseConnection.printFromDB("Osserva", room.getName(), room.getState(), name, "0", "0");
    }

    public boolean isTalkable() {
        return isTalkable;
    }
}
