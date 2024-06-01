package org.it.uniba .minima;
import org.it.uniba.minima.Boundary.outputDisplayManager;
import org.it.uniba.minima.GUI.GUIManager;
import org.it.uniba.minima.Database.DatabaseConnection;

import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        new GUIManager();

        String sql_query = DatabaseConnection.querySQL_forDESC("0", "Desert", "Start", "0", "0", "0");
        Connection conn;
        conn = DatabaseConnection.connect();
        outputDisplayManager.displayText(DatabaseConnection.getDescriptionFromDatabase(conn, sql_query));
        DatabaseConnection.close(conn);

    }
}