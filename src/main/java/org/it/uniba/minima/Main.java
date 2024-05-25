package org.it.uniba .minima;

import org.it.uniba.minima.Database.DatabaseConnection;
import org.it.uniba.minima.GUI.GUIManager;
import org.it.uniba.minima.Boundary.outputDisplayManager;
import javax.xml.crypto.Data;
import java.sql.Connection;
import org.it.uniba.minima.Entity.Character;
import org.it.uniba.minima.Control.CommandExecutor;
import org.it.uniba.minima.Control.Parser;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.Entity.Item;
import org.it.uniba.minima.Entity.Room;
import org.it.uniba.minima.Type.CommandType;
import org.it.uniba.minima.Type.Corridor;
import org.it.uniba.minima.Type.ParserOutput;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static Connection conn;
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        new GUIManager();

        conn = DatabaseConnection.connect();

        String sql_query = DatabaseConnection.querySQL_forDESC("0", "Deserto1", "Start", "0", "0", "0");
        Connection conn;
        conn = DatabaseConnection.connect();
        outputDisplayManager.displayText(DatabaseConnection.getStringFromDatabase(conn, sql_query));
        //DatabaseConnection.setToDatabase(conn, "test", "00:00:00");
        DatabaseConnection.close(conn);
        runTests();
    }


    private static void runTests() {
        Item item2 = new Item();
        item2.setName("item2");
        item2.setDescription("This is item2");
        item2.setAlias(List.of("a1", "ag1"));

        Item item1 = new Item();
        item1.setName("item1");
        item1.setDescription("This is item1");
        item1.setAlias(List.of("i1", "it1"));

        org.it.uniba.minima.Entity.Character character1 = new org.it.uniba.minima.Entity.Character();
        character1.setName("character1");
        character1.setDescription("This is character1");
        character1.setAlias(List.of("c1", "ch1"));

        org.it.uniba.minima.Entity.Character character2 = new Character();
        character2.setName("character2");
        character2.setDescription("This is character2");
        character2.setAlias(List.of("c2", "ch2"));

        Room room1 = new Room();
        room1.setName("room1");
        room1.setDescription("This is room1");
        room1.addAgent(item2);
        room1.addAgent(character1);

        Room room2 = new Room();
        room2.setName("room2");
        room2.setDescription("This is room2");
        room2.addAgent(item1);
        room2.addAgent(character2);

        // Create some Corridor instances
        Corridor corridor1 = new Corridor(room1, CommandType.NORD, false, room2);
        Corridor corridor2 = new Corridor(room2, CommandType.SUD, false, room1);

        // Create a list of Corridors and add the Corridors to it
        List<Corridor> corridors = new ArrayList<>();
        corridors.add(corridor1);
        corridors.add(corridor2);

        // Create a Game instance and set the current room and corridors map
        Game game = new Game();
        game.setCurrentRoom(room1);
        game.setCorridorsMap(corridors);

        // Create a CommandExecutor instance
        CommandExecutor commandExecutor = new CommandExecutor(game);

        // Create a Parser instance
        Parser parser = new Parser();

        // Create some Agent instances
        /*
        // Test the CommandExecutor with some commands
        testCommand(game, parser, commandExecutor, "inventory");
        testCommand(game, parser, commandExecutor, "talk item2 ");
        testCommand(game, parser, commandExecutor, "look item2");
        testCommand(game, parser, commandExecutor, "look character1");
        testCommand(game, parser, commandExecutor, "take item2");
        testCommand(game, parser, commandExecutor, "inventory");
        testCommand(game, parser, commandExecutor, "take character1");
        testCommand(game, parser, commandExecutor, "inventory");
        testCommand(game, parser, commandExecutor, "talk character1");
        testCommand(game, parser, commandExecutor, "nord");
        testCommand(game, parser, commandExecutor, "talk character1");
        testCommand(game, parser, commandExecutor, "talk item1");
        testCommand(game, parser, commandExecutor, "look item1");
        testCommand(game, parser, commandExecutor, "look character1");
        testCommand(game, parser, commandExecutor, "look item2");
        testCommand(game, parser, commandExecutor, "look character2");
        testCommand(game, parser, commandExecutor, "take item1");
        testCommand(game, parser, commandExecutor, "inventory");
        testCommand(game, parser, commandExecutor, "talk character2");
        testCommand(game, parser, commandExecutor, "look item2");
        testCommand(game, parser, commandExecutor, "sud");
        testCommand(game, parser, commandExecutor, "look item1");

        testCommand(game, parser, commandExecutor, "inventory");
        testCommand(game, parser, commandExecutor, "use item1 item2");
        testCommand(game, parser, commandExecutor, "fuse item2 item1");
        testCommand(game, parser, commandExecutor, "give item2 character2");
        testCommand(game, parser, commandExecutor, "inventory");
        testCommand(game, parser, commandExecutor, "take item2");
        testCommand(game, parser, commandExecutor, "inventory");
        testCommand(game, parser, commandExecutor, "drop item2");
        testCommand(game, parser, commandExecutor, "inventory");
        testCommand(game, parser, commandExecutor, "take item2");
        testCommand(game, parser, commandExecutor, "give item2 character2");
        testCommand(game, parser, commandExecutor, "give item2 character1");
        testCommand(game, parser, commandExecutor, "use item2 item1");
        testCommand(game, parser, commandExecutor, "use item2");
        testCommand(game, parser, commandExecutor, "fuse item2 item1");
        */

        testCommand(game, parser, commandExecutor, "take item2");
        testCommand(game, parser, commandExecutor, "nord");
        testCommand(game, parser, commandExecutor, "give item2 character2");
        testCommand(game, parser, commandExecutor, "use item1 item2");
        testCommand(game, parser, commandExecutor, "fuse item2 item1");
        testCommand(game, parser, commandExecutor, "take item1");
        testCommand(game, parser, commandExecutor, "use item1 item2");
        testCommand(game, parser, commandExecutor, "fuse item2 item1");
        testCommand(game, parser, commandExecutor, "fuse item1 item2");
        testCommand(game, parser, commandExecutor, "use item2 item1");
        testCommand(game, parser, commandExecutor, "use item1 item2");
        testCommand(game, parser, commandExecutor, "drop item2");
        testCommand(game, parser, commandExecutor, "use item1 item2");
    }

    private static void testCommand(Game game, Parser parser, CommandExecutor commandExecutor, String command) {
        System.out.println("Testing command: " + command);
        ParserOutput output = parser.parse(command);
        commandExecutor.execute(output);
    }
}