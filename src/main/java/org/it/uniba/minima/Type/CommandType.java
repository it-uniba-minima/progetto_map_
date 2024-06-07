package org.it.uniba.minima.Type;

/**
 * The enum of the command types.
 */
public enum CommandType {
    /**
     * Help command type, shows the list of available commands.
     */
    AIUTO,

    /**
     * Nord command type, moves the player to the north room.
     */
    NORD,

    /**
     * Sud command type, moves the player to the south room.
     */
    SUD,

    /**
     * Est command type, moves the player to the east room.
     */
    EST,

    /**
     * Ovest command type, moves the player to the west room.
     */
    OVEST,

    /**
     * Observe command type, allows the player to observe objects, rooms, and characters.
     */
    OSSERVA,

    /**
     * Inventory command type, lists the objects in the player's inventory.
     */
    INVENTARIO,

    /**
     * Take command type, allows the player to pick up objects.
     */
    PRENDI,

    /**
     * Drop command type, allows the player to drop objects.
     */
    LASCIA, // Comando per lasciare oggetti

    /**
     * Use command type, allows the player to use objects and use objects on other objects.
     */
    USA,

    /**
     * Fuse command type, allows the player to merge objects.
     */
    UNISCI,

    /**
     * Talk command type, allows the player to talk to characters.
     */
    PARLA,

    /**
     * Give command type, allows the player to give objects to characters.
     */
    DAI,
}
