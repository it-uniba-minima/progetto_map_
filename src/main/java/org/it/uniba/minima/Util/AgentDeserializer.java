package org.it.uniba.minima.Util;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import org.it.uniba.minima.Entity.Agent;
import org.it.uniba.minima.Entity.Item;
import org.it.uniba.minima.Entity.Personage;
import java.lang.reflect.Type;

/**
 * The custom deserializer for the Agent class.
 * It checks if the agent is an Item or a Personage and deserializes it accordingly.
 */
public class AgentDeserializer implements JsonDeserializer<Agent> {
    /**
     * Overrides the deserialize method of the JsonDeserializer interface.
     * Deserializes the agent.
     *
     * @param json the json element
     * @param typeOfT the type of the agent
     * @param context the context
     * @return the agent
     */
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
