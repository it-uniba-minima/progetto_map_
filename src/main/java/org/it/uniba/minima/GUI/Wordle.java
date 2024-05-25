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
        background.setBackground(Color.BLACK);
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
