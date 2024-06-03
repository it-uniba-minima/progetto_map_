package org.it.uniba.minima.Control;

import com.google.gson.*;
import org.it.uniba.minima.Entity.Item;
import org.it.uniba.minima.Entity.Personage;
import org.it.uniba.minima.Entity.Room;

import java.lang.reflect.Type;

public class RoomDeserializer implements JsonDeserializer<Room> {
    @Override
    public Room deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        // Deserialize common Room properties here
        String name = jsonObject.get("name").getAsString();
        String currentState = jsonObject.get("currentState").getAsString();

        Room room = new Room();
        room.setName(name);
        room.setState(currentState);

        // Deserialize the agents
        JsonArray agentsArray = jsonObject.get("agents").getAsJsonArray();
        for (JsonElement element : agentsArray) {
            JsonObject obj = element.getAsJsonObject();
            try {
                obj.get("isPickable").getAsString();
                Item item = context.deserialize(obj, Item.class);
                room.getAgents().add(item);
            } catch (NullPointerException e) {
                Personage personage = context.deserialize(obj, Personage.class);
                room.getAgents().add(personage);
            }
        }

        return room;
    }
}