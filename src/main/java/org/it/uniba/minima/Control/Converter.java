
package org.it.uniba.minima.Control;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.it.uniba.minima.Entity.Agent;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.Entity.Item;
import org.it.uniba.minima.Entity.Room;
import org.it.uniba.minima.Type.CommandType;
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
import java.util.*;
import java.util.stream.Collectors;

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

        return allAgents;

    }


    public void ConvertGametoJson() {
        Gson gson = new Gson();
        Game game = Game.getInstance(); //new Game();
        String json = gson.toJson(game);
        try {
            Files.write(Paths.get("src/main/resources/LoadedGame.json"), json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ConvertRoomstoJson(List<Room> rooms) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(rooms);
        Files.write(Paths.get("src/main/resources/LoadedRooms.json"), json.getBytes());
    }

    public void ConvertAgentstoJson() throws IOException {
        Gson gson = new Gson();
        Game game = Game.getInstance();
        Set<Item> allItems =GameManager.getAllItems();
        Set<Room> rooms = game.getCorridorsMap().stream()
                .map(Corridor::getStartingRoom)
                .collect(Collectors.toSet());
        Set<Item> itemsToSave = allItems.stream()
                .filter(item -> !game.getInventory().contains(item))
                .filter(item -> rooms.stream()
                .noneMatch(room -> room.getAgents().contains(item))).peek(item -> System.out.println(item.getName()))
                .collect(Collectors.toSet());

        String json = gson.toJson(itemsToSave);
        Files.write(Paths.get("src/main/resources/LoadedItems.json"), json.getBytes());
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
