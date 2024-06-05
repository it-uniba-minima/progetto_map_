package org.it.uniba.minima.Database;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import java.io.IOException;

public class REST_Server {

    public void startServer() throws IOException {
        HttpServer server = HttpServer.createSimpleServer("/", 8080);
        ServerConfiguration config = server.getServerConfiguration();
        config.addHttpHandler(new DatabaseHandler(), "/api/data");

        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdownNow));

        new Thread(() -> {
            try {
                server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
