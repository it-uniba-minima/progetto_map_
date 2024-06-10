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

/**
 * The class that manages the conversion of json files to java classes and vice versa.
 */
public class Converter {
    /**
     * Method that manages the conversion of json files to java classes in the case of a new game.
     *
     * @return the map of all the agents
     */
    public Map<String, Agent> convertJsonToJavaClass() {
        return processJsonFiles("src/main/resources/static/Game.json", "src/main/resources/static/Agents.json");
    }

    /**
     * Method that manages the conversion of json files to java classes in the case of a loaded game.
     *
     * @return the map of all the agents
     */
    public Map<String, Agent> loadGame() {
        return processJsonFiles("src/main/resources/LoadedGame.json", "src/main/resources/LoadedItems.json");
    }

    /**
     * Converts the json files of a game and its agents to java classes.
     * Returns a map containing all the agents mapped to their names.
     *
     * @param gameFilePath   the game file path
     * @param agentsFilePath the agents file path
     * @return the map of the agents
     */
    private Map<String, Agent> processJsonFiles(String gameFilePath, String agentsFilePath) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Agent.class, new AgentDeserializer())
                .create();
        Map<String, Agent> allAgents = new HashMap<>();
        Map<String, Room> allRooms = new HashMap<>();

        // Read the game file
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

        // Read the agents file
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

    /**
     * Converts the game instance to json file to save the game.
     */
    public void ConvertGameToJson() {
        Gson gson = new Gson();
        Game game = Game.getInstance();
        String json = gson.toJson(game);

        try {
            Files.write(Paths.get("src/main/resources/LoadedGame.json"), json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts the agents to json file to save the game.
     *
     * @throws IOException the io exception
     */
    public void ConvertAgentsToJson() {
        Gson gson = new Gson();
        Game game = Game.getInstance();
        Set<Item> allItems = GameManager.getAllItems();

        // Save only the items that are not in the inventory or in a room
        Set<Room> rooms = game.getCorridorsMap().stream()
                .map(Corridor::getStartingRoom)
                .collect(Collectors.toSet());

        Set<Item> itemsToSave = allItems.stream()
                .filter(item -> !game.getInventory().contains(item))
                .filter(item -> rooms.stream()
                        .noneMatch(room -> room.getAgents().contains(item)))
                .collect(Collectors.toSet());

        String json = gson.toJson(itemsToSave);
        try {
            Files.write(Paths.get("src/main/resources/LoadedItems.json"), json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
