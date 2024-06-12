package org.it.uniba.minima.DB_Web;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The class that handles the database requests.
 */
public class DatabaseHandler extends HttpHandler {

    /**
     * Handles the service request.
     *
     * @param request  the request
     * @param response the response
     * @throws Exception the exception
     */
    @Override
    public void service(final Request request, final Response response) throws Exception {
        if ("GET".equalsIgnoreCase(request.getMethod().toString())) {
            handleGet(request, response);
        } else if ("POST".equalsIgnoreCase(request.getMethod().toString())) {
            handlePost(request, response);
        }
    }

    /**
     * Handles the GET request.
     *
     * @param request  the request
     * @param response the response
     * @throws IOException the io exception
     */
    private void handleGet(final Request
                                   request, final  Response response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = new PrintWriter(response.getWriter());
        try (Connection conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/database/db_map", "sa", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(DatabaseConnection.querySQL_forCLASSIFICA())) {
            out.println("<!DOCTYPE html>\n" +
                    "<html lang=\"it\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Document</title>\n" +
                    "    <style>\n" +
                    "        body {\n" +
                    "            margin: 0;\n" +
                    "            padding: 0;\n" +
                    "            font-family: Arial, sans-serif;\n" +
                    "            background-color: #f5f5dc; /* Egyptian background color */\n" +
                    "        }\n" +
                    "\n" +
                    "        .container {\n" +
                    "            display: flex;\n" +
                    "            justify-content: center;\n" +
                    "            align-items: center;\n" +
                    "        }\n" +
                    "\n" +
                    "        .content {\n" +
                    "            width: 80%;\n" +
                    "            text-align: center;\n" +
                    "            display: flex;\n" +
                    "            flex-direction: column;\n" +
                    "            align-items: center;\n" +
                    "        }\n" +
                    "\n" +
                    "        .side-image {\n" +
                    "            width: 25%;\n" +
                    "            height: 100vh;\n" +
                    "            background-image: url('../../../../docs/img/immagine per sito.jpeg');\n" +
                    "            background-size: cover;\n" +
                    "            background-position: center;\n" +
                    "        }\n" +
                    "\n" +
                    "        h1, h2, h3 {\n" +
                    "            color: brown;\n" +
                    "            text-align: center;\n" +
                    "            font-family: \"Papyrus\", Times, serif;\n" +
                    "        }\n" +
                    "\n" +
                    "        a {\n" +
                    "            text-decoration: none;\n" +
                    "            color: purple;\n" +
                    "        }\n" +
                    "\n" +
                    "        /* Egyptian-style headings */\n" +
                    "        h1 {\n" +
                    "            font-weight: bold;\n" +
                    "            font-size: 46px;\n" +
                    "        }\n" +
                    "\n" +
                    "        /* Egyptian-style headings */\n" +
                    "        h2 {\n" +
                    "            font-weight: normal;\n" +
                    "            font-size: 40px;\n" +
                    "            margin-top: 80px;\n" +
                    "            margin-bottom: 15px;\n" +
                    "        }\n" +
                    "\n" +
                    "        /* Egyptian-style headings */\n" +
                    "        h3 {\n" +
                    "            font-weight: normal;\n" +
                    "            font-size: 30px;\n" +
                    "            margin-top: 5px;\n" +
                    "            margin-bottom: 5px;\n" +
                    "        }\n" +
                    "\n" +
                    "        /* Center paragraphs */\n" +
                    "        p {\n" +
                    "            text-align: center;\n" +
                    "            line-height: 2; /* Add some spacing between paragraphs */\n" +
                    "            font-family: \"Georgia\"; /* Add some spacing between paragraphs */\n" +
                    "            font-size: 20px; /* Add some spacing between paragraphs */\n" +
                    "        }\n" +
                    "        /* Center paragraphs */\n" +
                    "        p.link {\n" +
                    "            text-align: center;\n" +
                    "            line-height: 0; /* Add some spacing between paragraphs */\n" +
                    "            font-family: \"Georgia\"; /* Add some spacing between paragraphs */\n" +
                    "            font-size: 24px; /* Add some spacing between paragraphs */\n" +
                    "        }\n" +
                    "        table {\n" +
                    "            width: 100%;\n" +
                    "            border-collapse: collapse;\n" +
                    "            margin: 20px 0;\n" +
                    "            background-color: #fff;\n" +
                    "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                    "        }\n" +
                    "        th, td {\n" +
                    "            border: 1px solid #ddd;\n" +
                    "            padding: 8px 11px;\n" +
                    "            text-align: center;\n" +
                    "        }\n" +
                    "        th {\n" +
                    "            background-color: #f2f2f2;\n" +
                    "            color: #333;\n" +
                    "            text-transform: uppercase;\n" +
                    "            font-family: \"Papyrus\", Times, serif;\n" +
                    "            font-size: 20px;\n" +
                    "            color: brown;\n" +
                    "        }\n" +
                    "        tr:nth-child(even) {\n" +
                    "           background-color: #f9f9f9;\n" +
                    "           font-family: \"Georgia\"; /* Add some spacing between paragraphs */\n" +
                    "           font-size: 18px; /* Add some spacing between paragraphs */\n" +
                    "        }\n" +
                    "        tr:hover {\n" +
                    "            background-color: #f1f1f1;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"container\">\n" +
                    "    <div class=\"side-image\"></div>\n" +
                    "    <div class=\"content\">\n" +
                    "\n" +
                    "        <h1>AVVENTURA NELLA PIRAMIDE</h1>\n" +
                    "            <p class=\"link\"><a href=\"#section1\">Migliori tempi di gioco \uD83C\uDFC6</a></p>\n" +
                    "            <p class=\"link\"><a href=\"#section2\">Manuale utente \uD83C\uDFAE</a></p>\n" +
                    "            <p class=\"link\"><a href=\"#section3\">Spiegazione progetto \uD83D\uDCDD</a></p>\n" +
                    "            <p class=\"link\"><a href=\"#section4\">Sviluppatori \uD83D\uDC68\u200D\uD83D\uDCBB\uD83E\uDD13</a></p>\n" +
                    "\n" +
                    "        <h2 id=\"section1\">MIGLIORI TEMPI DI GIOCO\uD83C\uDFC6</h2>\n" );
            out.println("<table>");
            out.println("<tr><th> USERNAME </th><th> TEMPO </th><th> FINALE </th></tr>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getString("USERNAME") + "</td><td>" + rs.getString("TEMPO") + "</td><td>" +rs.getString("FINALE") + "</td></tr>");
            }
            out.println("</table><br>");

        } catch (SQLException e) {
            out.println("SQL Error: " + e.getMessage());
            e.printStackTrace(out);
        }
        out.println(
                "        <br><p class=\"link\"><a href=\"#top\">Torna all'indice</a></p>\n" +
                        "\n" +
                        "        <h2 id=\"section2\">MANUALE UTENTE\uD83C\uDFAE</h2>\n" +
                        "        <p> <b>Comandi di movimento:</b><br>\n" +
                        "            <b>Nord</b> - Permette all'utente di muoversi in avanti<br>\n" +
                        "            <b>Est</b> - Permette all'utente di muoversi a destra<br>\n" +
                        "            <b>Sud</b> - Permette all'utente di muoversi indietro<br>\n" +
                        "            <b>Ovest</b> - Permette all'utente di muoversi a sinistra<br>\n" +
                        "            <br>\n" +
                        "            <b>Comandi di gioco:</b><br>\n" +
                        "            <b>Inventario</b> - Mostra l'inventario dell'utente<br>\n" +
                        "            <b>Aiuto</b> - Mostra i comandi disponibili<br>\n" +
                        "            <b>Osserva</b> - Mostra la descrizione della stanza.<br>\n" +
                        "            <b>Osserva [oggetto|personaggio]</b> - Mostra la descrizione dell'oggetto o del personaggio, presente nella stanza.<br>\n" +
                        "            <b>Usa [oggetto]</b> - Utilizza l'oggetto specificato.<br>\n" +
                        "            <b>Usa [oggetto1] [oggetto2]</b> - Utilizza l'oggetto1 sull'oggetto2.<br>\n" +
                        "            <b>Prendi [oggetto]</b> - Prendi l'oggetto specificato.<br>\n" +
                        "            <b>Lascia [oggetto]</b> - Lascia l'oggetto specificato, deve essere presente nell'inventario.<br>\n" +
                        "            <b>Parla [personaggio]</b> - Parla con il personaggio specificato.<br>\n" +
                        "            <b>Fondi [oggetto1] [oggetto2]</b> - Fonde l'oggetto1 con l'oggetto2, creando un [oggetto3] presente nell'inventario<br>\n" +
                        "            <b>Dai [oggetto] [personaggio]</b> - Dai l'oggetto specificato al personaggio specificato.<br>\n" +
                        "        </p><br>\n" +
                        "        <p class=\"link\"><a href=\"#top\">Torna all'indice</a></p>\n" +
                        "\n" +
                        "        <h2 id=\"section3\">SPIEGAZIONE PROGETTO\uD83D\uDCDD</h2>\n" +
                        "        <p>Questo progetto, intitolato \"Avventura nella Piramide\", è stato sviluppato come esame finale del corso di Metodi Avanzati di Programmazione, tenuto dal Prof. Pierpaolo Basile presso l'Università degli Studi di Bari \"Aldo Moro\".</p>\n" +
                        "        <p>L'obiettivo del corso è stato quello di introdurre, conoscere e approfondire il paradigma di programmazione ad oggetti e di verificarne l'apprendimento mediante la realizzazione di un videogioco.</p>\n" +
                        "        <p>In particolare è stato chiesto di realizzare un'avventura testuale, ovvero un gioco che si svolge quasi interamente per linea di comando. Nel nostro caso è presente un'interfaccia grafica che fa da cornice al gioco, oltre ad alcuni eventi puramente grafici.</p>\n" +
                        "        <p>Parlando del gioco, è ambientato in una piramide egizia e l'obiettivo ultimo è quella di esplorarla nella sua totalità, nel farlo interpreterai Indiana Jones e dovrai affrontare enigmi e sfide che ti si porranno davanti.<br><b>Quindi gambe in spalla e parti per l'avventura!</b></p>\n" +
                        "            <br>\n" +
                        "        <p class=\"link\"><a href=\"#top\">Torna all'indice</a></p>\n" +
                        "\n" +
                        "        <h2 id=\"section4\">SVILUPPO\uD83D\uDC68\u200D\uD83D\uDCBB\uD83E\uDD13</h2>\n" +
                        "        <h3>Link alla repository del progetto su GitHub: <h3>" +
                        "        <p class=\"link\"><a href=\"https://github.com/it-uniba-minima/progetto_map_.git\">\uD83D\uDCC1\uD83D\uDCBBAvventura nella Piramide</a></p><br>\n" +
                        "        <h3>I partecipanti al progetto sono:</h3>\n" +
                        "        <p class=\"link\">\uD83D\uDC68\u200D\uD83D\uDCBB<a href=\"https://github.com/MikiMik88\">Michele Pontrelli (MikiMik88)</a></p>\n" +
                        "        <p class=\"link\">\uD83D\uDC68\u200D\uD83D\uDCBB<a href=\"https://github.com/Pascoooo\">Nicolo' Pacucci (Pascoooo)</a></p>\n" +
                        "        <p class=\"link\">\uD83D\uDC68\u200D\uD83D\uDCBB<a href=\"https://github.com/Apand0\">Ruggiero Marco Santeramo (Apand0)</a></p><br><br>\n" +
                        "        <p class=\"link\"><a href=\"#top\">Torna all'indice</a></p>\n" +
                        "\n" +
                        "    </div>\n" +
                        "    <div class=\"side-image\"></div>\n" +
                        "</div>\n" +
                        "</body>\n" +
                        "<foot><h2> </h2></foot>\n" +
                        "</html>\n");
    }

    /**
     * Handles the POST request.
     *
     * @param request  the request
     * @param response the response
     * @throws IOException the io exception
     */
    private void handlePost(final Request request, final Response response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = new PrintWriter(response.getWriter());
        try (Connection conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/database/db_map", "sa", "");
             Statement stmt = conn.createStatement()) {
             stmt.executeUpdate("INSERT INTO CLASSIFICA (USERNAME, TEMPO, FINALE) VALUES ('" + request.getParameter("username") + "', '" + request.getParameter("tempo") + "', '" + request.getParameter("finale") + "')");
        } catch (SQLException e) {
            out.println("SQL Error: " + e.getMessage() + "\n");
            e.printStackTrace(out);
        }
    }
}

