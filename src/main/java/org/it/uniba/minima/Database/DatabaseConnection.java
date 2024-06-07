package org.it.uniba.minima.Database;

import org.it.uniba.minima.Boundary.OutputDisplayManager;

import java.sql.*;

/**
 * The type Database connection.
 */
public class DatabaseConnection {

    /**
     * The Jdbc driver.
     */
    static final String JDBC_DRIVER = "org.h2.Driver";
    /**
     * The Db url.
     */
    static final String DB_URL = "jdbc:h2:./src/main/resources/database/db_map";
    /**
     * The User.
     */
    static final String USER = "sa";
    /**
     * The Pass.
     */
    static final String PASS = "";

    /**
     * Connect connection.
     *
     * @return the connection
     */
    public static Connection connect() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String start = "RUNSCRIPT FROM 'src/main/resources/database/db_start.sql'";
        String fill = "RUNSCRIPT FROM 'src/main/resources/database/db_info.sql'";
        boolean emptyClassifica = true;
        boolean emptyDescr = true;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
             Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             stmt = conn.prepareStatement(start);
             stmt.execute();
             stmt.close();

             String sql = "SELECT * FROM CLASSIFICA";
             stmt = conn.prepareStatement(sql);
             rs = stmt.executeQuery();
             while (rs.next()) {
                 emptyClassifica = false;
             }
             rs.close();
             String sql2 = "SELECT * FROM DESCRIZIONI";
             stmt = conn.prepareStatement(sql2);
             rs = stmt.executeQuery();
             while (rs.next()) {
                 emptyDescr = false;
             }
             rs.close();

             if (emptyClassifica && emptyDescr) {
                 stmt = conn.prepareStatement(fill);
                 stmt.execute();
                 stmt.close();
             }

             return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Close.
     *
     * @param conn the conn
     */
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Sets to database.
     *
     * @param conn the conn
     * @param nome the nome
     * @param time the time
     * @param end  the end
     */
    public static void setToDatabase(Connection conn, String nome, String time, char end) {
        try {
            String sql = "INSERT INTO CLASSIFICA (USERNAME, TEMPO, FINALE) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setTime(2, Time.valueOf(time));
            stmt.setString(3, String.valueOf(end));
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Print from db.
     *
     * @param idComando     the id comando
     * @param idStanza      the id stanza
     * @param idStato       the id stato
     * @param idPersonaggio the id personaggio
     * @param idOggetto1    the id oggetto 1
     * @param idOggetto2    the id oggetto 2
     */
    public static void printFromDB(String idComando, String idStanza, String idStato, String idPersonaggio, String idOggetto1, String idOggetto2) {
        Connection conn;
        conn = DatabaseConnection.connect();
        String sql_query = "SELECT DESCRIZIONE FROM DESCRIZIONI WHERE COMANDO = '" + idComando + "' AND STANZA = '" + idStanza + "' AND STATO = '" + idStato + "' AND PERSONAGGIO = '" + idPersonaggio + "' AND OGGETTO1 = '" + idOggetto1 + "' AND OGGETTO2 = '" + idOggetto2 + "'";
        OutputDisplayManager.displayText(DatabaseConnection.getDescriptionFromDatabase(conn, sql_query));
        DatabaseConnection.close(conn);
    }

    /**
     * Query sql for classifica string.
     *
     * @return the string
     */
    public static String querySQL_forCLASSIFICA() {
        return "SELECT * FROM CLASSIFICA ORDER BY TEMPO";
    }

    /**
     * Gets classifica from database.
     *
     * @param conn      the conn
     * @param sql_query the sql query
     * @return the classifica from database
     */
    public static String getClassificaFromDatabase(Connection conn, String sql_query) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql_query);
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append(rs.getString("USERNAME")).append(" ").append(rs.getTime("TEMPO")).append(" ").append(rs.getString("FINALE")).append("\n");
            }
            rs.close();
            stmt.close();
            return sb.toString();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets description from database.
     *
     * @param conn      the conn
     * @param sql_query the sql query
     * @return the description from database
     */
    public static String getDescriptionFromDatabase(Connection conn, String sql_query) {
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