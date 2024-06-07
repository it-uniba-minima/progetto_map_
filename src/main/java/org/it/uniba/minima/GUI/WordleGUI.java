package org.it.uniba.minima.GUI;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The class of the WordleGUI GUI
 */
public class WordleGUI extends JPanel {
    /**
     * The boxes of the wordle.
     */
    private BoxLetter[][] boxes;

    /**
     * Constructor of the class.
     */
    public WordleGUI() {
        initComponents();
    }

    /**
     * Initialize the components.
     */
    private void initComponents() {
        // Create the background panel and paints it
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
        // Set the properties and layout of the background panel
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        background.setLayout(new GridLayout(6, 5, 5, 15));
        background.setVisible(true);

        // Set the layout of the panel
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

        // Create the boxes and adds them to the background panel
        createBoxes();
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 5; c++) {
                background.add(boxes[r][c].getTextField());
            }
        }

        // Set the properties of the frame
        setPreferredSize(new Dimension(440, 400));
        setMaximumSize(new Dimension(440, 400));
        setMinimumSize(new Dimension(440, 400));
        add(background);
        setVisible(true);
    }

    /**
     * Create the boxes of the wordle.
     */
    private void createBoxes() {
        boxes = new BoxLetter[6][5];

        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 5; c++) {
                BoxLetter letterBox = new BoxLetter();
                boxes[r][c] = letterBox;
                letterBox.getTextField().setPreferredSize(new Dimension(10, 10));
                letterBox.getTextField().setBackground(new Color(201, 164, 71));
                letterBox.getTextField().setForeground(Color.WHITE);
                letterBox.getTextField().setBorder(BorderFactory.createLineBorder(new Color(119, 95, 43), 3));
                letterBox.getTextField().setFocusable(false);
                letterBox.getTextField().setEditable(false);
            }
        }
    }

    /**
     * Sets the color of a box.
     *
     * @param row   the row
     * @param col   the col
     * @param color the color
     */
    public void setBoxColor(int row, int col, Color color) {
        boxes[row][col].setBoxColor(color);
    }

    /**
     * Sets the text of a box.
     *
     * @param row  the row
     * @param col  the col
     * @param text the text
     */
    public void setBoxText(int row, int col, String text) {
        boxes[row][col].getTextField().setText(text);
    }
}
