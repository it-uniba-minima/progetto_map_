package org.it.uniba.minima.Type;

public enum CommandType {
    HELP, // Comando che mostra la lista di comandi disponibili

    NORD, // Comando per direzione Nord
    SUD, // Comando per direzione Sud
    EST, // Comando per direzione Est
    OVEST, // Comando per direzione Ovest

    WALK, // Comando per muoversi in una direzione
    INVENTORY, // Comando per accedere all'inventario

    LOOK, // Comando per osservare oggetti, stanze, personaggi
    TAKE, //Comando per prendere oggetti
    USE, // Comando per usare oggetti
    TALK, // Comando per parlare con personaggi
}
