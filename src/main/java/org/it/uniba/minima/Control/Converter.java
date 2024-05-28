
package org.it.uniba.minima.Control;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.it.uniba.minima.Entity.Agent;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Converter {
    List<Room> allRooms = new ArrayList<>();

    public Map<String, Agent> convertJsonToJavaClass() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Agent.class, new AgentDeserializer())
                .create();
        Map<String, Agent> allAgents = new HashMap<>();

        try {
            URL url = getClass().getResource("/static/Rooms.json");
            File file = new File(url.toURI());
            JsonReader reader = new JsonReader(new FileReader(file));
            Type roomListType = new TypeToken<ArrayList<Room>>(){}.getType();
            List<Room> roomList = gson.fromJson(reader, roomListType); // This gson is the one with the custom deserializer
            for (Room room : roomList) {
                allRooms.add(room);
                room.getAgents().forEach(agent -> allAgents.put(agent.getName(), agent));
            }
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

        //
        // chiedere se fare una funzione a parte o no per il caricamento di Game.json

        try {
            URL url = getClass().getResource("/static/Game.json");
            File file = new File(url.toURI());
            JsonReader reader = new JsonReader(new FileReader(file));
            Game.setUpGame(gson.fromJson(reader, Game.class));
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
        return allAgents;
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

    public void ConvertGametoJson() throws IOException, ClassNotFoundException{
        Gson gson = new Gson();
        Game game = Game.getInstance();
        /*
        game.setNickname("Player");

        game.setInventory(new ArrayList<>());

        game.setCurrentTime("00:00:00");

        game.setCurrentRoom(allRooms.get(0));

        List<Corridor> corridorsMap = new ArrayList<>();
        corridorsMap.add(new Corridor(allRooms.get(0), CommandType.NORD, true, allRooms.get(1)));
        corridorsMap.add(new Corridor(allRooms.get(1), CommandType.SUD, false, allRooms.get(0)));

        corridorsMap.add(new Corridor(allRooms.get(1), CommandType.EST, false, allRooms.get(3)));
        corridorsMap.add(new Corridor(allRooms.get(3), CommandType.OVEST, false, allRooms.get(1)));

        corridorsMap.add(new Corridor(allRooms.get(1), CommandType.OVEST, false, allRooms.get(2)));
        corridorsMap.add(new Corridor(allRooms.get(2), CommandType.EST, false, allRooms.get(1)));

        corridorsMap.add(new Corridor(allRooms.get(1), CommandType.NORD, true, allRooms.get(4)));
        corridorsMap.add(new Corridor(allRooms.get(4), CommandType.SUD, false, allRooms.get(1)));

        corridorsMap.add(new Corridor(allRooms.get(4), CommandType.NORD, true, allRooms.get(5)));
        corridorsMap.add(new Corridor(allRooms.get(5), CommandType.SUD, false, allRooms.get(4)));

        corridorsMap.add(new Corridor(allRooms.get(5), CommandType.NORD, true, allRooms.get(6)));
        corridorsMap.add(new Corridor(allRooms.get(6), CommandType.SUD, false, allRooms.get(5)));

        corridorsMap.add(new Corridor(allRooms.get(6), CommandType.NORD, true, allRooms.get(10)));
        corridorsMap.add(new Corridor(allRooms.get(10), CommandType.SUD, false, allRooms.get(6)));

        corridorsMap.add(new Corridor(allRooms.get(10), CommandType.NORD, false, allRooms.get(9)));
        corridorsMap.add(new Corridor(allRooms.get(9), CommandType.SUD, false, allRooms.get(10)));

        corridorsMap.add(new Corridor(allRooms.get(10), CommandType.OVEST, false, allRooms.get(8)));
        corridorsMap.add(new Corridor(allRooms.get(8), CommandType.EST, false, allRooms.get(10)));

        corridorsMap.add(new Corridor(allRooms.get(10), CommandType.EST, false, allRooms.get(7)));
        corridorsMap.add(new Corridor(allRooms.get(7), CommandType.OVEST, false, allRooms.get(10)));

        game.setCorridorsMap(corridorsMap);

        Map<String, String> roomStates = new HashMap<>();

        roomStates.put(allRooms.get(0).getName(), "Start");
        roomStates.put(allRooms.get(1).getName(), "Buio");
        roomStates.put(allRooms.get(2).getName(), "SerpentiOn");
        roomStates.put(allRooms.get(3).getName(), "AcquaOn");
        roomStates.put(allRooms.get(4).getName(), "Start");
        roomStates.put(allRooms.get(5).getName(), "OcchioIntero");
        roomStates.put(allRooms.get(6).getName(), "");
        roomStates.put(allRooms.get(7).getName(), "Start");
        roomStates.put(allRooms.get(8).getName(), "TortaOff");
        roomStates.put(allRooms.get(9).getName(), "Spento");
        roomStates.put(allRooms.get(10).getName(), "SarcofagoChiuso");

        game.setStatesMap(roomStates);
        */
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
