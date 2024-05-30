package org.it.uniba.minima.GUI;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import org.it.uniba.minima.Mixer;

public class GUIManager extends JFrame {
    public GUIManager() {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        try {
            Image icon = ImageIO.read(new File("docs/img/gameIcon.jpg"));
            setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel cards = new JPanel(new CardLayout());

        MenuGUI menu = new MenuGUI();
        RiconoscimentiGUI credits = new RiconoscimentiGUI();
        ProgressBarGUI progressBar = new ProgressBarGUI();
        GameGUI game = new GameGUI();

        cards.add(menu, "MenuGUI");
        cards.add(credits, "RiconoscimentiGUI");
        cards.add(progressBar, "ProgressBarGUI");
        cards.add(game, "GameGUI");

        add(cards);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        Mixer music = Mixer.getInstance();
        music.start();
    }
}
