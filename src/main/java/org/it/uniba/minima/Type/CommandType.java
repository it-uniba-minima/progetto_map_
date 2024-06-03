package org.it.uniba.minima.Type;

public enum CommandType {
    HELP, // Comando che mostra la lista di comandi disponibili

    NORD, // Comando per direzione Nord
    SUD, // Comando per direzione Sud
    EST, // Comando per direzione Est
    OVEST, // Comando per direzione Ovest

    INVENTORY, // Comando per accedere all'inventario

    DAI, // Comando per dare oggetti
    DROP, // Comando per lasciare oggetti
    OSSERVA, // Comando per osservare oggetti, stanze, personaggi
    TAKE, //Comando per prendere oggetti
    USA, // Comando per usare oggetti e usare oggetti su altri oggetti
    PARLA, // Comando per parlare con personaggi

    UNISCI, // Comando per fondere oggetti
}
