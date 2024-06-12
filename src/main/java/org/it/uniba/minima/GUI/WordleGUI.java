package org.it.uniba.minima.GUI;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The class of the WordleGUI GUI.
 */
public class WordleGUI extends JPanel {
    /**
     * The constants.
     */
    private static final int ROWS = 6;
    private static final int COLUMNS = 5;
    private static final int FRAME_WIDTH = 440;
    private static final int FRAME_HEIGHT = 400;
    private static final int TEXTFIELD_WIDTH = 10;
    private static final int TEXTFIELD_HEIGHT = 10;
    private static final Color BACKGROUND_COLOR = new Color(201, 164, 71);
    private static final Color FOREGROUND_COLOR = Color.WHITE;
    private static final Color BORDER_COLOR = new Color(119, 95, 43);
    private static final int BORDER_THICKNESS = 3;
    private static final int EMPTY_BORDER_THICKNESS = 10;
    private static final int GRID_LAYOUT_HGAP = 5;
    private static final int GRID_LAYOUT_VGAP = 15;
    private static final String BACKGROUND_IMAGE_PATH = "src/main/resources/docs/img/stoneBackground.png";

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
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage originalImage = ImageIO.read(new File(BACKGROUND_IMAGE_PATH));
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
        background.setBorder(BorderFactory.createEmptyBorder(EMPTY_BORDER_THICKNESS,
                EMPTY_BORDER_THICKNESS, EMPTY_BORDER_THICKNESS, EMPTY_BORDER_THICKNESS));
        background.setLayout(new GridLayout(ROWS, COLUMNS, GRID_LAYOUT_HGAP, GRID_LAYOUT_VGAP));
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
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                background.add(boxes[r][c].getTextField());
            }
        }

        // Set the properties of the main panel
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setMaximumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        add(background);
        setVisible(true);
    }

    /**
     * Create the boxes of the wordle.
     */
    private void createBoxes() {
        boxes = new BoxLetter[ROWS][COLUMNS];

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                BoxLetter letterBox = new BoxLetter();
                boxes[r][c] = letterBox;
                letterBox.getTextField().setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
                letterBox.getTextField().setBackground(BACKGROUND_COLOR);
                letterBox.getTextField().setForeground(FOREGROUND_COLOR);
                letterBox.getTextField().setBorder(BorderFactory.createLineBorder(BORDER_COLOR, BORDER_THICKNESS));
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
    public void setBoxColor(final int row, final int col, final Color color) {
        boxes[row][col].setBoxColor(color);
    }

    /**
     * Sets the text of a box.
     *
     * @param row  the row
     * @param col  the col
     * @param text the text
     */
    public void setBoxText(final int row, final int col, final String text) {
        boxes[row][col].getTextField().setText(text);
    }

    /**
     * Resets the boxes of the wordle.
     */
    public void resetBoxes() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                boxes[r][c].getTextField().setText("");
                boxes[r][c].getTextField().setBackground(BACKGROUND_COLOR);
            }
        }
    }
}
