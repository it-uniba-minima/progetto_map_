package org.it.uniba.minima.Entity;

import java.util.List;

public class Item extends Agent{
    boolean isPickable = true;
    boolean isDroppable = true;

    public Item() {}

    public boolean isPickable() {
        return isPickable;
    }

    public boolean isDroppable() {
        return isDroppable;
    }

    public void setPickable(boolean b) {
        isPickable = b;
    }
}
