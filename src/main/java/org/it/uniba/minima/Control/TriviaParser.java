package org.it.uniba.minima.Control;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.it.uniba.minima.Boundary.Triviaquestion;

public class TriviaParser {
    public static void parseJson(String json) throws Exception {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(json).getAsJsonObject();

        // Get the first result
        JsonObject firstResult = jsonObject.getAsJsonArray("results").get(0).getAsJsonObject();

        // Get the question and the correct answer
        String question = firstResult.get("question").getAsString();
        String correctAnswer = firstResult.get("correct_answer").getAsString();
        Triviaquestion.displayQuestion(question, correctAnswer);
    }
}