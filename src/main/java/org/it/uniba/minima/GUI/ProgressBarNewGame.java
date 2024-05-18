package org.it.uniba.minima.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

public class ProgressBarNewGame {
    private JFrame frame = new JFrame();
    private JProgressBar progressBar = new JProgressBar();
    private int counter = 0;

    public ProgressBarNewGame() {
        progressBar.setValue(0);
        progressBar.setBounds(50, 50, 600, 50); // Aggiustato per adattarsi meglio alla finestra
        progressBar.setStringPainted(true);
        frame.add(progressBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLayout(null);
        frame.setVisible(true);
/**
         URL url = null;
        try {
            url = new URL("https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExajRzdDM0djFndmF6aDlreG9wdTg3N3IybDk4NXc4dGluYmJ4YnJsdiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/FlWgXEtj5aM5G/giphy.gif");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Icon icon = new ImageIcon(url);
        JLabel label = new JLabel(icon);

        frame.getContentPane().add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
*/
        updateBar();
    }

    public void updateBar() {
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (counter <= 100) {
                    progressBar.setValue(counter);
                    progressBar.setString("Loading... " + counter + "%");
                    counter++;
                } else {
                    ((Timer) e.getSource()).stop();
                    progressBar.setString("Get Ready to Play!");
                }
            }
        });
        timer.start();
    }

    public static void insertGif() {
        URL url = null;
        try {
            url = new URL("https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExajRzdDM0djFndmF6aDlreG9wdTg3N3IybDk4NXc4dGluYmJ4YnJsdiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/FlWgXEtj5aM5G/giphy.gif");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Icon icon = new ImageIcon(url);
        JLabel label = new JLabel(icon);

        JFrame f = new JFrame("Animation");
        f.getContentPane().add(label);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}