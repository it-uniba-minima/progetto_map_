package org.it.uniba.minima.Control;

import com.google.gson.*;
import org.it.uniba.minima.Entity.Agent;
import org.it.uniba.minima.Entity.Item;
import org.it.uniba.minima.Entity.Personage;
import java.lang.reflect.Type;

public class AgentDeserializer implements JsonDeserializer<Agent> {
    @Override
    public Agent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        try {
            jsonObject.get("isPickable").getAsString();
            return context.deserialize(json, Item.class);
        } catch (NullPointerException e) {
            return context.deserialize(json, Personage.class);
        }
    }
}
