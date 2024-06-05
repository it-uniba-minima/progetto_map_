package org.it.uniba.minima.Type;

public enum CommandType {
    AIUTO, // Comando che mostra la lista di comandi disponibili

    NORD, // Comando per direzione Nord
    SUD, // Comando per direzione Sud
    EST, // Comando per direzione Est
    OVEST, // Comando per direzione Ovest

    OSSERVA, // Comando per osservare oggetti, stanze, personaggi

    INVENTARIO, // Comando per accedere all'inventario

    PRENDI, //Comando per prendere oggetti
    LASCIA, // Comando per lasciare oggetti
    USA, // Comando per usare oggetti e usare oggetti su altri oggetti
    UNISCI, // Comando per fondere oggetti

    PARLA, // Comando per parlare con personaggi
    DAI, // Comando per dare oggetti

}
