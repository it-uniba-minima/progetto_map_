package org.it.uniba .minima;
import org.it.uniba.minima.Database.RestServer;
import org.it.uniba.minima.GUI.ManagerGUI;

/**
 * The Main class.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(final String[] args) {
        new ManagerGUI();
        RestServer restServer = new RestServer();
        try {
            restServer.startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
