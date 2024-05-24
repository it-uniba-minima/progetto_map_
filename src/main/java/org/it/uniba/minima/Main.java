package org.it.uniba .minima;
import org.it.uniba.minima.GUI.GUIManager;
import org.it.uniba.minima.Boundary.outputDisplayManager;

import java.sql.Connection;
import static org.it.uniba.minima.DatabaseConnection.connect;
import static org.it.uniba.minima.DatabaseConnection.getStringFromDatabase;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        new GUIManager();

        String sql_query = "SELECT DESCRIZIONE FROM DESCRIZIONI";
        Connection conn;
        conn = DatabaseConnection.connect();
        outputDisplayManager.displayText(DatabaseConnection.getStringFromDatabase(conn, sql_query));
    }
}