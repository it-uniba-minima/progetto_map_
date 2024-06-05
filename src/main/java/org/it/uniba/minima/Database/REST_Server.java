package org.it.uniba.minima.Database;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.grizzly.http.server.ServerConfiguration;

import java.io.IOException;

public class REST_Server {

    public void startServer() {
        HttpServer server = HttpServer.createSimpleServer("/", 8080);
        ServerConfiguration config = server.getServerConfiguration();
        String staticDir = REST_Server.class.getResource("/static").getPath();
        StaticHttpHandler staticHttpHandler = new StaticHttpHandler(staticDir);
        config.addHttpHandler(staticHttpHandler, "/");
        config.addHttpHandler(new DatabaseHandler(), "/api/data");
        try {
            server.start();
            System.out.println("Server started at http://localhost:8080/api/data");
            System.out.println("Press any key to stop the server...");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            server.shutdownNow();
        }
    }

    public static void main(String[] args) throws Exception {
        new REST_Server().startServer();
        Client client = new Client();
        client.sendGetRequest();
    }
}