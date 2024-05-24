package org.it.uniba.minima;

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

    public static String getStringFromDatabase(Connection conn, String sql_query) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql_query);
            if (rs.next()) {
                return rs.getString(1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "No String Found";

    }


}