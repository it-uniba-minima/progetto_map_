package org.it.uniba.minima.GUI;

import javax.swing.*;
import java.awt.*;

public class Wordle extends JPanel {
    private BoxLetter[][] boxes;

    public Wordle() {
        initComponents();
    }

    private void initComponents() {
        JPanel background = new JPanel();
        background.setBackground(Color.GRAY);
        background.setLayout(new GridLayout(6, 5, 10, 10));

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
                letterBox.getBox().setBackground(Color.GRAY);
                letterBox.getBox().setForeground(Color.WHITE);
            }
        }
    }

    public void moveToNextBox(int row, int col) {
        if (col < 4) {
            boxes[row][col + 1].getBox().requestFocus();
        } else if (row < 5 && col == 4) {
            boxes[row + 1][0].getBox().requestFocus();
        }
    }

    public void moveToPreviousBox(int row, int col) {
        if (col > 0) {
            boxes[row][col - 1].getBox().requestFocus();
        } else if (row > 0 && col == 0) {
            boxes[row - 1][4].getBox().requestFocus();
        }
    }

    public void setBoxColor(int row, int col, Color color) {
        boxes[row][col].setBox(color);
    }

    public static class GameTheme {
        public static final Color BACKGROUND = Color.BLACK;
        public static final Color WHITE = Color.GRAY;
    }
}
