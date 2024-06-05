package org.it.uniba.minima.Database;

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

public class DatabaseHandler extends HttpHandler {

    @Override
    public void service(Request request, Response response) throws Exception {
        if ("GET".equalsIgnoreCase(request.getMethod().toString())) {
            handleGet(request, response);
        } else if ("POST".equalsIgnoreCase(request.getMethod().toString())) {
            handlePost(request, response);
        }
    }

    private void handleGet(Request request, Response response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = new PrintWriter(response.getWriter());
        try (Connection conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/database/db_map", "sa", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM CLASSIFICA ORDER BY TEMPO" )) {
            out.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
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
                    "            width: 30%;\n" +
                    "            height: 100vh;\n" +
                    "            background-image: url('../../../../docs/img/immagine per sito.jpeg');\n" +
                    "            background-size: cover;\n" +
                    "            background-position: center;\n" +
                    "        }\n" +
                    "\n" +
                    "        h1, h2, h3, p, ul {\n" +
                    "            color: #3d3d3d;\n" +
                    "            text-align: center;\n" +
                    "            font-size: 30px;\n" +
                    "        }\n" +
                    "\n" +
                    "        ul {\n" +
                    "            list-style-type: none;\n" +
                    "        }\n" +
                    "\n" +
                    "        a {\n" +
                    "            text-decoration: none;\n" +
                    "            color: blue;\n" +
                    "        }\n" +
                    "\n" +
                    "        /* Egyptian-style headings */\n" +
                    "        h1, h2, h3 {\n" +
                    "            color: brown; /* Brown */\n" +
                    "            font-family: \"Papyrus\", Times, serif;\n" +
                    "            font-weight: normal;\n" +
                    "        }\n" +
                    "\n" +
                    "        /* Center paragraphs */\n" +
                    "        p {\n" +
                    "            text-align: justify;\n" +
                    "            margin: 0; /* Add some spacing between paragraphs */\n" +
                    "        }\n" +
                    "\n" +
                    "        .button-container {\n" +
                    "            margin-top: 20px;\n" +
                    "        }\n" +
                    "\n" +
                    "        button {\n" +
                    "            padding: 5px 10px;\n" +
                    "            margin: 5px;\n" +
                    "            background-color: #ff9500;\n" +
                    "            color: #f6f0e6;\n" +
                    "            border: none;\n" +
                    "            cursor: pointer;\n" +
                    "            font-family: 'Papyrus', sans-serif;\n" +
                    "            font-size: 20px;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"container\">\n" +
                    "    <div class=\"side-image\"></div>\n" +
                    "    <div class=\"content\">\n" +
                    "\n" +
                    "        <h1>AVVENTURE NELLA PIRAMIDE</h1>\n" +
                    "        <button onclick=\"playAudio()\">Play\uD83D\uDD0A</button>\n" +
                    "        <button onclick=\"stopAudio()\">Pause\uD83D\uDD07</button>\n" +
                    "        <ul>\n" +
                    "            <li><a href=\"#section1\">Migliori tempi di gioco \uD83C\uDFC6</a></li>\n" +
                    "            <li><a href=\"#section2\">Manuale utente \uD83C\uDFAE</a></li>\n" +
                    "            <li><a href=\"#section3\">Spiegazione progetto \uD83D\uDCDD</a></li>\n" +
                    "            <li><a href=\"#section4\">Sviluppatori \uD83D\uDC68\u200D\uD83D\uDCBB\uD83E\uDD13</a></li>\n" +
                    "        </ul>\n" +
                    "\n" +
                    "        <h1 id=\"section1\">MIGLIORI TEMPI DI GIOCO\uD83C\uDFC6</h1>\n" );
                                out.println("<html><body><table border='10'>");
                                out.println("<tr><th> USERNAME </th><th> TEMPO </th><th> FINALE </th></tr>");
                                while (rs.next()) {
                                    out.println("<tr><td>" + rs.getString("USERNAME") + "</td><td>" + rs.getString("TEMPO") + "</td><td>" +rs.getString("FINALE") + "</td></tr>");
                                }
                                out.println("</table></body></html>");

                            } catch (SQLException e) {
                                out.println("SQL Error: " + e.getMessage());
                                e.printStackTrace(out);
                            }
                            out.println(
                    "        <a href=\"#top\">Torna all'indice</a>\n" +
                    "\n" +
                    "        <h1 id=\"section2\">MANUALE UTENTE\uD83C\uDFAE</h1>\n" +
                    "        <h2>Comandi:</h2>\n" +
                    "        <p> Comandi di movimento:<br>\n" +
                    "            Nord - Permette all'utente di muoversi in avanti<br>\n" +
                    "            Est - Permette all'utente di muoversi a destra<br>\n" +
                    "            Sud - Permette all'utente di muoversi indietro<br>\n" +
                    "            Ovest - Permette all'utente di muoversi a sinistra<br>\n" +
                    "            <br>\n" +
                    "            Comandi di gioco:<br>\n" +
                    "            Inventario - Mostra l'inventario dell'utente<br>\n" +
                    "            Aiuto - Mostra i comandi disponibili<br>\n" +
                    "            Osserva - Mostra la descrizione della stanza.<br>\n" +
                    "            Osserva [oggetto|personaggio] - Mostra la descrizione dell'oggetto o del personaggio, presente nella stanza.<br>\n" +
                    "            Usa [oggetto] - Utilizza l'oggetto specificato.<br>\n" +
                    "            Usa [oggetto1] [oggetto2] - Utilizza l'oggetto1 sull'oggetto2.<br>\n" +
                    "            Prendi [oggetto] - Prendi l'oggetto specificato.<br>\n" +
                    "            Lascia [oggetto] - Lascia l'oggetto specificato, deve essere presente nell'inventario.<br>\n" +
                    "            Parla [personaggio] - Parla con il personaggio specificato.<br>\n" +
                    "            Fondi [oggetto1] [oggetto2] - Fonde l'oggetto1 con l'oggetto2, creando un [oggetto3] presente nell'inventario<br>\n" +
                    "            Dai [oggetto] [personaggio] - Dai l'oggetto specificato al personaggio specificato.<br>\n" +
                    "        </p>\n" +
                    "        <a href=\"#top\">Torna all'indice</a>\n" +
                    "\n" +
                    "        <h1 id=\"section3\">SPIEGAZIONE PROGETTO\uD83D\uDCDD</h1>\n" +
                    "        <p> Progetto realizzato per il corso di Programmazione ad Oggetti dell'Università degli Studi di Bari \"Aldo Moro\".<br>\n" +
                    "            Il progetto consiste in un gioco testuale in cui l'utente deve esplorare una piramide egizia, risolvendo enigmi e interagendo con personaggi e oggetti.<br>\n" +
                    "            Il gioco è stato realizzato in Java, utilizzando il framework Maven per la gestione delle dipendenze, seguendo il paradigma di programmazione ad oggetti.<br>\n" +
                    "        <a href=\"#top\">Torna all'indice</a>\n" +
                    "\n" +
                    "        <h1 id=\"section4\">SVILUPPATORI\uD83D\uDC68\u200D\uD83D\uDCBB\uD83E\uDD13</h1>\n" +
                    "        <h2>Ecco il link ufficiale alla <a href=\"https://github.com/it-uniba-minima/progetto_map_.git\">\uD83D\uDCC1\uD83D\uDCBBRepository</a> di GitHub!</h2>\n" +
                    "        <h3>I partecipanti al progetto sono:</h3>\n" +
                    "        <li>\uD83D\uDC68\u200D\uD83D\uDCBB<strong><a href=\"https://github.com/MikiMik88\">Michele Pontrelli (MikiMik88)</a></strong></li>\n" +
                    "        <li>\uD83D\uDC68\u200D\uD83D\uDCBB<strong><a href=\"https://github.com/Pascoooo\">Nicolo' Pacucci (Pascoooo)</a></strong></li>\n" +
                    "        <li>\uD83D\uDC68\u200D\uD83D\uDCBB<strong><a href=\"https://github.com/Apand0\">Ruggiero Marco Santeramo (Apand0)</a></strong></li>\n" +
                    "        <a href=\"#top\">Torna all'indice</a>\n" +
                    "\n" +
                    "    </div>\n" +
                    "    <div class=\"side-image\"></div>\n" +
                    "</div>\n" +
                    "\n" +
                    "<audio id=\"background-music\" loop autoplay>\n" +
                    "    <source src=\"../../../../docs/audio/Avventura-nella-piramide-egizia.wav\" type=\"audio/mpeg\">\n" +
                    "    Your browser does not support the audio element.\n" +
                    "</audio>\n" +
                    "\n" +
                    "<script>\n" +
                    "    document.addEventListener('DOMContentLoaded', function() {\n" +
                    "        var audio = document.getElementById('background-music');\n" +
                    "        audio.play();\n" +
                    "    });\n" +
                    "    function playAudio() {\n" +
                    "        var audio = document.getElementById('background-music');\n" +
                    "        audio.play();\n" +
                    "    }\n" +
                    "    function stopAudio() {\n" +
                    "        var audio = document.getElementById('background-music');\n" +
                    "        audio.pause();\n" +
                    "    }\n" +
                    "</script>\n" +
                    "</body>\n" +
                    "</html>\n");

    }

    private void handlePost(Request request, Response response) throws IOException {
        String param = request.getParameter("param");
        response.setContentType("text/html");
        PrintWriter out = new PrintWriter(response.getWriter());
        out.println("<html><body>");
        out.println("<h1>POST received with param: " + param + "</h1>");
        out.println("</body></html>");
    }
}