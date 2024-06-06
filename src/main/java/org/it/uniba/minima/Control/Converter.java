package org.it.uniba.minima.Control;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.it.uniba.minima.Entity.Agent;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.Entity.Item;
import org.it.uniba.minima.Entity.Room;
import org.it.uniba.minima.Type.Corridor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Converter {

    private Map<String, Agent> processJsonFiles(String gameFilePath, String agentsFilePath) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Agent.class, new AgentDeserializer())
                .create();
        Map<String, Agent> allAgents = new HashMap<>();
        Map<String, Room> allRooms = new HashMap<>();

        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(gameFilePath));
            if (fileBytes.length == 0) {
                return null;
            }
            File file = new File(gameFilePath);
            JsonReader reader = new JsonReader(new FileReader(file));
            Game game = gson.fromJson(reader, Game.class);
            if (game == null) {
                return null;
            }
            Game.setUpGame(game);
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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(agentsFilePath));
            if (fileBytes.length == 0) {
                return null;
            }
            File file = new File(agentsFilePath);
            JsonReader reader = new JsonReader(new FileReader(file));
            Type agentListType = new TypeToken<ArrayList<Agent>>() {}.getType();
            List<Agent> agentList = gson.fromJson(reader, agentListType);
            agentList.forEach(agent -> allAgents.put(agent.getName(), agent));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return allAgents;
    }

    public Map<String, Agent> convertJsonToJavaClass() {
        return processJsonFiles("src/main/resources/static/Game.json", "src/main/resources/static/Agents.json");
    }

    public Map<String, Agent> loadGame() {
        return processJsonFiles("src/main/resources/LoadedGame.json", "src/main/resources/LoadedItems.json");
    }

    public void ConvertGametoJson() {
        Gson gson = new Gson();
        Game game = Game.getInstance();
        String json = gson.toJson(game);
        try {
            Files.write(Paths.get("src/main/resources/LoadedGame.json"), json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ConvertAgentstoJson() throws IOException {
        Gson gson = new Gson();
        Game game = Game.getInstance();
        Set<Item> allItems = GameManager.getAllItems();
        Set<Room> rooms = game.getCorridorsMap().stream()
                .map(Corridor::getStartingRoom)
                .collect(Collectors.toSet());
        Set<Item> itemsToSave = allItems.stream()
                .filter(item -> !game.getInventory().contains(item))
                .filter(item -> rooms.stream()
                        .noneMatch(room -> room.getAgents().contains(item)))
                .collect(Collectors.toSet());

        String json = gson.toJson(itemsToSave);
        Files.write(Paths.get("src/main/resources/LoadedItems.json"), json.getBytes());
    }
}
