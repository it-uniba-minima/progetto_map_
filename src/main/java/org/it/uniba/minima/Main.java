package org.it.uniba .minima;
import org.it.uniba.minima.GUI.ManagerGUI;
import org.it.uniba.minima.Database.DatabaseConnection;

/**
 * The Main class.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new ManagerGUI();
        // The starting message of the game
        DatabaseConnection.printFromDB("0", "Desert", "Start", "0", "0", "0");
    }
}