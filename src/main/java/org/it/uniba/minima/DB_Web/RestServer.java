package org.it.uniba.minima.DB_Web;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import java.io.IOException;

/**
 * The class that starts the server.
 */
public class RestServer {

    /**
     * Start server.
     *
     * @throws IOException the io exception
     */
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
