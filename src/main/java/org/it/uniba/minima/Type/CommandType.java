package org.it.uniba.minima.Type;

public enum CommandType {
    HELP, // Comando che mostra la lista di comandi disponibili

    NORD, // Comando per direzione Nord
    SUD, // Comando per direzione Sud
    EST, // Comando per direzione Est
    OVEST, // Comando per direzione Ovest

    INVENTORY, // Comando per accedere all'inventario

    GIVE, // Comando per dare oggetti
    DROP, // Comando per lasciare oggetti
    LOOK, // Comando per osservare oggetti, stanze, personaggi
    TAKE, //Comando per prendere oggetti
    USE, // Comando per usare oggetti e usare oggetti su altri oggetti
    TALK, // Comando per parlare con personaggi

    FUSE, // Comando per fondere oggetti
}
