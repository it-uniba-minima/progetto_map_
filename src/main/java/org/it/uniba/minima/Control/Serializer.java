package org.it.uniba.minima.Control;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.Entity.Room;

import java.io.*;

public class Serializer  {
    public static void serialize(Game game) throws IOException {
        FileOutputStream fileOut = new FileOutputStream("game.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(game);
        out.close();
        fileOut.close();
        System.out.println("You saved the game!");
    }

    public static Game deserialize() throws IOException, ClassNotFoundException{
        Game game = null;
        FileInputStream fileIn = new FileInputStream("game.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        game = (Game) in.readObject();
        in.close();
        fileIn.close();
        System.out.println("You loaded the game!, Welcome back " + game.getNickname());
        return game;
    }

}
