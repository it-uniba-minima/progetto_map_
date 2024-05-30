package org.it.uniba.minima.GUI;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Wordle extends JPanel {
    private BoxLetter[][] boxes;

    public Wordle() {
        initComponents();
    }

    private void initComponents() {
        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage originalImage = ImageIO.read(new File("docs/img/stoneBackground.png"));
                    BufferedImage scaledImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
                    Graphics2D graphics2D = scaledImage.createGraphics();
                    graphics2D.drawImage(originalImage, 0, 0, getWidth(), getHeight(), null);
                    graphics2D.dispose();
                    g.drawImage(scaledImage, 0, 0, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        background.setLayout(new GridLayout(6, 5, 5, 15));

        setPreferredSize(new Dimension(440, 400));
        setMaximumSize(new Dimension(440, 400));
        setMinimumSize(new Dimension(440, 400));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(background, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(background, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        createBoxes();
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 5; c++) {
                background.add(boxes[r][c].getBox());
            }
        }

        background.setVisible(true);
        add(background);
        setVisible(true);
    }

    private void createBoxes() {
        boxes = new BoxLetter[6][5];
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 5; c++) {
                BoxLetter letterBox = new BoxLetter(r, c, this);
                boxes[r][c] = letterBox;
                letterBox.getBox().setPreferredSize(new Dimension(10, 10));
                letterBox.getBox().setBackground(new Color(201, 164, 71));
                letterBox.getBox().setForeground(Color.WHITE);
                letterBox.getBox().setBorder(BorderFactory.createLineBorder(new Color(119, 95, 43), 3));
                letterBox.getBox().setFocusable(false);
                letterBox.getBox().setEditable(false);
            }
        }
    }


    public void setBoxColor(int row, int col, Color color) {
        boxes[row][col].setBox(color);
    }
    public void setBoxText(int row, int col, String text) {
        boxes[row][col].getBox().setText(text);
    }

}
