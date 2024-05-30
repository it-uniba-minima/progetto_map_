
package org.it.uniba.minima.Control;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.it.uniba.minima.Entity.Agent;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.Entity.Room;
import org.it.uniba.minima.Type.Corridor;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;
public class Converter {
    public Map<String, Agent> convertJsonToJavaClass() {
        Gson gson = new Gson();
        Map<String, Agent> allAgents = new HashMap<>();


public class Converter {
    public Map<String, Agent> convertJsonToJavaClass() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Agent.class, new AgentDeserializer())
                .create();
        Map<String, Agent> allAgents = new HashMap<>();
        Map<String, Room> allRooms = new HashMap<>();

        try {
            URL url = getClass().getResource("/static/Game.json");
            File file = new File(url.toURI());
            JsonReader reader = new JsonReader(new FileReader(file));
            Game game = gson.fromJson(reader, Game.class);

            game.getCorridorsMap().forEach(corridor -> {
                Room room = corridor.getStartingRoom();
                if (!allRooms.containsKey(room.getName())) {
                    allRooms.put(room.getName(), room);
                    room.getAgents().forEach(agent -> allAgents.put(agent.getName(), agent));
                } else {
                    Room existingRoom = allRooms.get(room.getName());
                    corridor.setStartingRoom(existingRoom);
                }
                room = corridor.getArrivingRoom();
                if (!allRooms.containsKey(room.getName())) {
                    allRooms.put(room.getName(), room);
                    room.getAgents().forEach(agent -> allAgents.put(agent.getName(), agent));
                } else {
                    Room existingRoom = allRooms.get(room.getName());
                    corridor.setArrivingRoom(existingRoom);
                }
            });
            Game.setUpGame(game);
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            URL url = getClass().getResource("/static/Agents.json");
            File file = new File(url.toURI());
            JsonReader reader = new JsonReader(new FileReader(file));
            Type agentListType = new TypeToken<ArrayList<Agent>>(){}.getType();
            List<Agent> agentList = gson.fromJson(reader, agentListType);
            agentList.forEach(agent -> allAgents.put(agent.getName(), agent));
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            URL url = getClass().getResource("/static/Game.json");
            File file = new File(url.toURI());
            JsonReader reader = new JsonReader(new FileReader(file));
            Game game = gson.fromJson(reader, Game.class);
        } catch (FileNotFoundException | URISyntaxException e) {
    }
        return allAgents;
    }
      
        public void ConvertGametoJson(Game game) throws IOException, ClassNotFoundException{
        Gson gson = new Gson();
        String json = gson.toJson(game);
        Files.write(Paths.get("src/main/resources/LoadedGame.json"), json.getBytes());
    }
    public void ConvertGametoJson() throws IOException, ClassNotFoundException{
        Gson gson = new Gson();
        Game game = Game.getInstance();
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
