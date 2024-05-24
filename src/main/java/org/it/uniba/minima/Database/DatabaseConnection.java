package org.it.uniba.minima.Database;

import org.it.uniba.minima.TimerManager;

import java.sql.*;

public class DatabaseConnection {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/map_server";
    static final String USER = "sa";
    static final String PASS = "";

    public static Connection connect() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void setToDatabase(Connection conn, String nome, String time) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO CLASSIFICA (NAME, TEMPO) VALUES (?, ?)");
            stmt.setString(1, nome);
            stmt.setTime(2, Time.valueOf(time));
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String querySQL_forDESC(String idStanza, String idOggetto, String idPersonaggio, String idStato) {
        return "SELECT DESCRIZIONE FROM DESCRIZIONI WHERE IDSTANZA = '" + idStanza + "' AND IDOGGETTO = '" + idOggetto + "' AND IDPERSONAGGIO = '" + idPersonaggio + "' AND IDSTATO = '" + idStato + "'";
    }

    public static String getStringFromDatabase(Connection conn, String sql_query) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql_query);
            if (rs.next()) {
                return rs.getString("DESCRIZIONE");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "No String Found";

    }


}