package org.it.uniba.minima.GUI;
import javax.swing.*;
import java.awt.*;
import org.it.uniba.minima.myRunnable;

public class GUIManager extends JFrame {
    public GUIManager() {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));

        JPanel cards = new JPanel(new CardLayout());

        MenuGUI menu = new MenuGUI();
        RiconoscimentiGUI credits = new RiconoscimentiGUI();

        cards.add(menu, "MenuGUI");
        cards.add(credits, "RiconoscimentiGUI");

        this.add(cards);
        this.pack();
        this.setVisible(true);

        myRunnable music = myRunnable.getInstance();
        music.start();
    }
}
