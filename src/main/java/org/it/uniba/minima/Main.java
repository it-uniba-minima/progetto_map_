package org.it.uniba .minima;
import org.it.uniba.minima.DB_Web.DatabaseConnection;
import org.it.uniba.minima.DB_Web.RestServer;
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
            try {
                new RestServer().startServer();
                DatabaseConnection.connect();
            } catch (Exception e) {
                e.printStackTrace();
        }
    }
}
