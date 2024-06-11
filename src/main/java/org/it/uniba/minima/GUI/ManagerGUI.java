package org.it.uniba.minima.GUI;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import org.it.uniba.minima.Mixer;

/**
 * The manager of the GUIs.
 */
public class ManagerGUI extends JFrame {
    static GameGUI game;

    /**
     * Instantiates a new Gui manager.
     */
    public ManagerGUI() {
        // Set the properties of the frame
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Avventura nella Piramide");
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        try {

            Image icon = ImageIO.read(new File("src/main/resources/docs/img/gameIcon.jpg"));
            setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create the cards
        JPanel cards = new JPanel(new CardLayout());
        MenuGUI menu = new MenuGUI();
        CreditsGUI credits = new CreditsGUI();
        ProgressBarGUI progressBar = new ProgressBarGUI();
        game = new GameGUI();

        // Add the panels to cards
        cards.add(menu, "MenuGUI");
        cards.add(credits, "CreditsGUI");
        cards.add(progressBar, "ProgressBarGUI");
        cards.add(game, "GameGUI");

        // Start the frame
        add(cards);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Start the music
        Mixer music = Mixer.getInstance();
        music.start();
    }

    /**
     * Closes the game GUI.
     */
    public static void closeGame() {
        game.goBack();
    }
}
