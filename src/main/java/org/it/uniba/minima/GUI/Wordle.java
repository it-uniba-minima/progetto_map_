package org.it.uniba.minima.GUI;

import javax.swing.*;
import java.awt.*;

public class Wordle extends JPanel {
    private static final int WIDTH = 440;
    private static final int HEIGHT = 400;
    private final JPanel grid;
    private BoxLetter[][] boxes;

    public Wordle() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JPanel titleContainer = new JPanel();
        titleContainer.setBackground(GameTheme.BACKGROUND);
        JLabel titleLabel = new JLabel("WORDLE", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(GameTheme.WHITE);
        titleContainer.add(titleLabel);

        JPanel gridContainer = new JPanel();
        gridContainer.setBackground(GameTheme.BACKGROUND);
        gridContainer.setLayout(new GridBagLayout());

        JPanel optionsContainer = new JPanel();
        optionsContainer.setBackground(GameTheme.BACKGROUND);
        optionsContainer.setPreferredSize(new Dimension(25, 25));
        grid = new JPanel();
        grid.setLayout(new GridLayout(6, 5, 10, 10));
        grid.setPreferredSize(new Dimension(400, 400));
        grid.setBackground(GameTheme.BACKGROUND);
        createBoxes();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;

        gridContainer.add(grid, gbc);

        add(titleContainer, BorderLayout.NORTH);
        add(gridContainer, BorderLayout.CENTER);
        add(optionsContainer, BorderLayout.SOUTH);
    }

    public void createBoxes() {
        boxes = new BoxLetter[6][5];
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 5; c++) {
                BoxLetter letters = new BoxLetter(r, c, this);
                boxes[r][c] = letters;
                letters.getBox().setPreferredSize(new Dimension(10, 10));
                letters.getBox().setBackground(Color.BLACK);
                letters.getBox().setForeground(Color.white);
                grid.add(letters.getBox());
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

    public class GameTheme {
        public static final Color BACKGROUND = Color.BLACK;
        public static final Color WHITE = Color.GRAY;
    }
}
