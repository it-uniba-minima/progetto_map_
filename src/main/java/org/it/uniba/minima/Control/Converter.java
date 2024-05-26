
package org.it.uniba.minima.Control;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.Entity.Room;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Converter {
    public void convertJsonToJavaClass() {
        Gson gson = new Gson();
        try {
            URL url = getClass().getResource("/static/Rooms.json");
            File file = new File(url.toURI());
            JsonReader reader = new JsonReader(new FileReader(file));
            Type roomListType = new TypeToken<ArrayList<Room>>(){}.getType();
            List<Room> roomList = gson.fromJson(reader, roomListType);
            for (Room room : roomList) {
                System.out.println(room.getName());
                room.getAgents().forEach(agent -> System.out.println("Agent Name: " + agent.getName()));
            }
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
        //
        // chiedere se fare una funzione a parte o no per il caricamento di Game.json

        try {
            URL url = getClass().getResource("/static/Game.json");
            File file = new File(url.toURI());
            JsonReader reader = new JsonReader(new FileReader(file));
            Game game = gson.fromJson(reader, Game.class);
        } catch (FileNotFoundException | URISyntaxException e) {
    }
    }
        /*
        Path dir = Paths.get("src/main/resources/static");
        try (Stream<Path> paths = Files.walk(dir)) {
            paths.filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".json"))
                    .forEach(p -> {
                        try {
                            FileReader reader = new FileReader(p.toFile());
                            if (p.toString().contains("Game")) {
                                Game game = gson.fromJson(reader, Game.class);
                                System.out.println(game.getCurrentTime());
                            } else if (p.toString().contains("Room")) {
                                Room room = gson.fromJson(reader, Room.class);
                                System.out.println(room.getName());
                                room.getAgents().forEach(agent -> System.out.println("Agent Name: " + agent.getName()));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        } */

        public void ConvertGametoJson(Game game) throws IOException, ClassNotFoundException{
        Gson gson = new Gson();
        String json = gson.toJson(game);
        Files.write(Paths.get("src/main/resources/LoadedGame.json"), json.getBytes());
    }
    public void ConvertRoomstoJson(List<Room> rooms) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(rooms);
        Files.write(Paths.get("src/main/resources/LoadedRooms.json"), json.getBytes());
    }

    public Game loadGame() {
        Gson gson = new Gson();
        try {
            URL url = getClass().getResource("/resources/LoadedGame.json");
            File file = new File(url.toURI());
            JsonReader reader = new JsonReader(new FileReader(file));
            return gson.fromJson(reader, Game.class);
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Room> loadRooms() {
        Gson gson = new Gson();
        try {
            URL url = getClass().getResource("/resources/LoadedRooms.json");
            File file = new File(url.toURI());
            JsonReader reader = new JsonReader(new FileReader(file));
            Type roomListType = new TypeToken<ArrayList<Room>>(){}.getType();
            return gson.fromJson(reader, roomListType);
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }



}
