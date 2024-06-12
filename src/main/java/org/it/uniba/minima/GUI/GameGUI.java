package org.it.uniba.minima.GUI;
import org.it.uniba.minima.Logic.GameManager;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.Util.Mixer;
import org.it.uniba.minima.InteractionManager.UserInputManager;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout;
import javax.swing.Timer;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.awt.Image;

/**
 * The GUI of the game.
 */
public class GameGUI extends JPanel {
    /**
     * The button to go back to the menu.
     */
    private JButton goBackButton;
    /**
     * The button to save the game.
     */
    private JButton saveGameButton;
    /**
     * The button to show the help dialog.
     */
    private JButton helpButton;
    /**
     * The button to start or stop the music.
     */
    private static JButton musicButton;
    /**
     * The timer label.
     */
    private static JLabel timerLabel;
    /**
     * The image panel.
     */
    private static JPanel imagePanel;
    /**
     * The text pane to display the text of the game.
     */
    private static JTextPane displayTextPane;
    private JScrollPane scrollPaneDisplayText;
    /**
     * The text area to display the inventory.
     */
    private static JTextArea inventoryTextArea;
    private JScrollPane scrollPaneInventoryText;
    /**
     * The user input field.
     */
    private JTextField userInputField;
    /**
     * The toolbar containing the buttons and the timer label
     */
    private JToolBar toolBar;
    /**
     * The imagePanel card layout.
     */
    private static CardLayout cardLayout;

    /**
     * Instantiates a new Game GUI.
     */
    public GameGUI() {
        UIManager.put("ScrollBar.width", 0); // Set the width of the scroll bar to 0
        SwingUtilities.updateComponentTreeUI(this); // Update the UI of the component
        initComponents();
        initImagePanel();
    }

    /**
     * Initialize the image panel.
     */
    private void initImagePanel() {
        imagePanel.setPreferredSize(new Dimension(440, 400));
        imagePanel.setMaximumSize(new Dimension(440, 400));
        imagePanel.setMinimumSize(new Dimension(440, 400));
        imagePanel.setBorder(BorderFactory.createLineBorder(new Color(107, 90, 13), 5));
        imagePanel.setBackground(new Color(107, 90, 13));

        cardLayout = new CardLayout();

        cardLayout.setVgap(0);
        cardLayout.setHgap(0);
        cardLayout.minimumLayoutSize(imagePanel);
        cardLayout.preferredLayoutSize(imagePanel);
        cardLayout.maximumLayoutSize(imagePanel);

        imagePanel.setLayout(cardLayout);

        imagePanel.add(new JPanel(null) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon image = new ImageIcon("src/main/resources/docs/img/Desert.png");
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }, "Desert");

        imagePanel.add(new WordleGUI(), "WordleGUI");
        imagePanel.add(new TilesGUI(), "Mattonelle");
        imagePanel.add(new HangmanGUI(), "Impiccato");

        for (int i = 1; i <= 10; i++) {
            final String imagePath = "src/main/resources/docs/img/Stanza" + i + ".png";
            imagePanel.add(new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    ImageIcon image = new ImageIcon(imagePath);
                    g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }, "Stanza" + i);
        }

        imagePanel.add(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon image = new ImageIcon("src/main/resources/docs/img/Saggezza.png");
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }, "Saggezza");

        imagePanel.add(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon image = new ImageIcon("src/main/resources/docs/img/Ricchezza.png");
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }, "Ricchezza");
    }

    /**
     * Gets the instance of the WordleGUI.
     *
     * @return the wordle GUI
     */
    public static WordleGUI getWordle() {
        return (WordleGUI) imagePanel.getComponent(1);
    }

    /**
     * Gets text pane font metrics.
     *
     * @return the text pane font metrics
     */
    public static FontMetrics getTextPaneFontMetrics() {
        return displayTextPane.getFontMetrics(displayTextPane.getFont());
    }

    /**
     * Gets text pane width.
     *
     * @return the text pane width
     */
    public static int getTextPaneWidth() {
        return displayTextPane.getWidth();
    }

    /**
     * Initialize the components of the GUI of the game.
     */
    private void initComponents() {
        // Setting properties of the panel
        setBackground(new Color(25, 30, 66));
        setPreferredSize(new Dimension(800, 600));

        // Initialization of the components
        imagePanel = new JPanel();
        toolBar = new JToolBar();
        goBackButton = new JButton();
        saveGameButton = new JButton();
        helpButton = new JButton();
        musicButton = new JButton();
        timerLabel = new JLabel();
        userInputField = new JTextField();
        scrollPaneInventoryText = new JScrollPane();
        inventoryTextArea = new JTextArea();
        scrollPaneDisplayText = new JScrollPane();
        displayTextPane = new JTextPane();

        // Setting the properties of the toolbar and its components
        toolBar.setBorderPainted(false);
        toolBar.setFloatable(false);
        toolBar.setBackground(new Color(25, 30, 66));
        toolBar.add(Box.createHorizontalStrut(5));

        // Setting the properties of the go back button
        goBackButton.setUI(new MetalButtonUI() {
            protected Color getSelectColor () {
                return new Color(133, 106, 5, 50);

            }
        });
        goBackButton.setFocusPainted(false);
        goBackButton.setBackground(new Color(204, 173, 27));
        goBackButton.setForeground(new Color(255, 255, 255));
        goBackButton.setBorderPainted(true);
        goBackButton.setBorder(BorderFactory.createLineBorder(new Color(107, 90, 13), 2));
        goBackButton.setFont(new Font("Papyrus", 1, 16));
        goBackButton.setText(" Indietro ");
        goBackButton.setFocusable(false);
        goBackButton.setHorizontalTextPosition(SwingConstants.CENTER);
        goBackButton.setVerticalTextPosition(SwingConstants.CENTER);
        goBackButton.addActionListener(this::goBackButtonActionPerformed);
        toolBar.add(goBackButton);

        // Adding a horizontal strut to the toolbar
        toolBar.add(Box.createHorizontalStrut(10));

        // Setting the properties of the save game button
        saveGameButton.setUI(new MetalButtonUI() {
            protected Color getSelectColor () {
                return new Color(133, 106, 5, 50);

            }
        });
        saveGameButton.setFocusPainted(false);
        saveGameButton.setBackground(new Color(204, 173, 27));
        saveGameButton.setForeground(new Color(255, 255, 255));
        saveGameButton.setBorderPainted(true);
        saveGameButton.setBorder(BorderFactory.createLineBorder(new Color(107, 90, 13), 2));
        saveGameButton.setFont(new Font("Papyrus", 1, 16));
        saveGameButton.setText(" Salva ");
        saveGameButton.setToolTipText("");
        saveGameButton.setFocusable(false);
        saveGameButton.setHorizontalTextPosition(SwingConstants.CENTER);
        saveGameButton.setVerticalTextPosition(SwingConstants.CENTER);
        saveGameButton.addActionListener(evt -> {
            try {
                saveGameButtonActionPerformed();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        toolBar.add(saveGameButton);

        // Adding a horizontal strut to the toolbar
        toolBar.add(Box.createHorizontalStrut(10));

        // Setting the properties of the help button
        helpButton.setUI(new MetalButtonUI() {
            protected Color getSelectColor () {
                return new Color(133, 106, 5, 50);

            }
        });
        helpButton.setFocusPainted(false);
        helpButton.setBackground(new Color(204, 173, 27));
        helpButton.setForeground(new Color(255, 255, 255));
        helpButton.setBorderPainted(true);
        helpButton.setBorder(BorderFactory.createLineBorder(new Color(107, 90, 13), 2));
        helpButton.setFont(new Font("Papyrus", 1, 20));
        helpButton.setMargin(new Insets(0, 10, 0, 0));
        helpButton.setText("  ?  ");
        helpButton.setFocusable(false);
        helpButton.setHorizontalTextPosition(SwingConstants.CENTER);
        helpButton.setVerticalTextPosition(SwingConstants.CENTER);
        helpButton.addActionListener(this::helpButtonActionPerformed);
        toolBar.add(helpButton);

        // Adding a horizontal strut to the toolbar
        toolBar.add(Box.createHorizontalStrut(10));

        // Setting the properties of the music button
        Dimension musicButtonDim = new Dimension(34, 24);
        musicButton.setPreferredSize(musicButtonDim);
        musicButton.setMaximumSize(musicButtonDim);
        musicButton.setMinimumSize(musicButtonDim);
        musicButton.setUI(new MetalButtonUI() {
            protected Color getSelectColor () {
                return new Color(133, 106, 5, 50);

            }
        });
        musicButton.setFocusPainted(false);
        musicButton.setBackground(new Color(204, 173, 27));
        musicButton.setForeground(new Color(255, 255, 255));
        musicButton.setBorderPainted(true);
        musicButton.setBorder(BorderFactory.createLineBorder(new Color(107, 90, 13), 2));
        musicButton.setFont(musicButton.getFont().deriveFont(18f));
        musicButton.setText(" 🔊 ");
        musicButton.setFocusable(false);
        musicButton.setHorizontalTextPosition(SwingConstants.CENTER);
        musicButton.setVerticalTextPosition(SwingConstants.CENTER);
        musicButton.addActionListener(this::musicButtonActionPerformed);
        toolBar.add(musicButton);

        // Adding a horizontal strut to the toolbar
        toolBar.add(Box.createHorizontalStrut(483));

        // Setting the properties of the timer label
        timerLabel.setOpaque(true);
        timerLabel.setBackground(new Color(204, 173, 27));
        timerLabel.setForeground(new Color(255, 255, 255));
        timerLabel.setBorder(BorderFactory.createLineBorder(new Color(107, 90, 13), 2));
        timerLabel.setFont(new Font("Papyrus", 1, 16));
        timerLabel.setFocusable(false);
        timerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        timerLabel.setVerticalTextPosition(SwingConstants.CENTER);
        timerLabel.setText(" 00:00:00 ");
        toolBar.add(timerLabel);

        // Setting the properties of the inventory text area
        Image inventoryImg = new ImageIcon("src/main/resources/docs/img/bagTextArea.png").getImage();

        JViewport inventoryView = new JViewport() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(inventoryImg, 0, 0, getWidth(), getHeight(), this);
            }
        };

        inventoryTextArea.setEditable(false);
        inventoryTextArea.setAutoscrolls(false);
        inventoryTextArea.setBorder(null);
        inventoryTextArea.setEnabled(false);
        inventoryTextArea.setFocusable(false);
        inventoryTextArea.setOpaque(false);
        inventoryTextArea.setPreferredSize(new Dimension(440, 100));
        inventoryTextArea.setMaximumSize(new Dimension(440, 100));
        inventoryTextArea.setMinimumSize(new Dimension(440, 100));
        inventoryTextArea.setFont(new Font("Georgia", 0, 16));
        inventoryTextArea.setText(" Inventario:\n");

        scrollPaneInventoryText.setViewport(inventoryView);
        scrollPaneInventoryText.setViewportView(inventoryTextArea);
        scrollPaneInventoryText.setPreferredSize(new Dimension(440, 100));
        scrollPaneInventoryText.setMaximumSize(new Dimension(440, 100));
        scrollPaneInventoryText.setMinimumSize(new Dimension(440, 100));
        scrollPaneInventoryText.setBorder(BorderFactory.createMatteBorder(0, 5, 5, 5, new Color(107, 90, 13)));

        // Setting the properties of the display text pane
        Image img = new ImageIcon("src/main/resources/docs/img/papyrTextPane.png").getImage();

        JViewport view = new JViewport() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };

        displayTextPane.setEditable(false);
        displayTextPane.setFocusable(false);
        displayTextPane.setAutoscrolls(false);
        displayTextPane.setFont(new Font("Georgia", 0, 13));
        displayTextPane.setBorder(null);
        displayTextPane.setOpaque(false);
        displayTextPane.setForeground(new Color(0, 0, 0));

        scrollPaneDisplayText.setBackground(new Color(204, 173, 27));
        scrollPaneDisplayText.setViewport(view);
        scrollPaneDisplayText.setViewportView(displayTextPane);
        scrollPaneDisplayText.setPreferredSize(new Dimension(335, 550));
        scrollPaneDisplayText.setMaximumSize(new Dimension(335, 550));
        scrollPaneDisplayText.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneDisplayText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneDisplayText.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 0, new Color(107, 90, 13)));

        // Setting the properties of the user input field
        userInputField.addActionListener(this::userInputFieldActionPerformed);
        userInputField.setMargin(new Insets(0, 0, 0, 0));
        userInputField.setForeground(new Color(0, 0, 0));
        userInputField.setFont(new Font("Georgia", 0, 13));
        userInputField.setOpaque(false);
        userInputField.setPreferredSize(new Dimension(335, 31));
        userInputField.setMaximumSize(new Dimension(335, 31));
        userInputField.setMinimumSize(new Dimension(335, 31));
        userInputField.setBorder(BorderFactory.createMatteBorder(0, 5, 5, 0, new Color(107, 90, 13)));
        userInputField.setBounds(0, 0, 335, 31);
        UserInputManager.startInputListener();

        Image img2 = new ImageIcon("src/main/resources/docs/img/papyrUserInputField.png").getImage();

        JPanel userInputFieldPanel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img2, 0, 0, getWidth(), getHeight(), this);
            }
        };
        userInputFieldPanel.setLayout(null);
        userInputFieldPanel.setPreferredSize(new Dimension(335, 31));
        userInputFieldPanel.setMaximumSize(new Dimension(335, 31));
        userInputFieldPanel.setMinimumSize(new Dimension(335, 31));
        userInputFieldPanel.setBorder(null);
        userInputFieldPanel.add(userInputField);

        // Setting the layout of the panel
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(toolBar, 800, 800, 800)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(userInputFieldPanel, 335, 335, 335))
                                        .addComponent(scrollPaneDisplayText))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(scrollPaneInventoryText, 440, 440, 440)
                                        .addComponent(imagePanel, 440, 440, 440))
                                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(imagePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(scrollPaneInventoryText, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(scrollPaneDisplayText)
                                                .addComponent(userInputFieldPanel, 31, 31, 31)))
                                .addGap(5, 5, 5))
        );
    }

    /**
     * Save game button action performed.
     *
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    private void saveGameButtonActionPerformed() throws IOException, ClassNotFoundException {
            Font font = new Font("Papyrus", Font.PLAIN, 13);
            UIManager.put("OptionPane.messageFont", font);
            int save = JOptionPane.showConfirmDialog(this, "Would you like to save?", "Save", JOptionPane.YES_NO_OPTION);

            if (save == JOptionPane.YES_OPTION) {
                saveFile();
            } else {
                notSavedFile();
            }
    }

    /**
     * Method to save the file
     *
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    private void saveFile() throws IOException, ClassNotFoundException {
        GameManager gameManager = new GameManager();
        Game game = Game.getInstance();
        game.setCurrentTime(timerLabel.getText());
        gameManager.saveGame();
        JOptionPane.showMessageDialog(this, "Game saved successfully", "Save", JOptionPane.INFORMATION_MESSAGE);
        resetAllGames();
        goBack();
    }

    /**
     * Method when you don't save the file
     */
    private void notSavedFile() {
        UIManager.put("OptionPane.messageFont", new Font("Papyrus", 0, 13));
        JOptionPane.showMessageDialog(this, "Game not saved", "Save", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Go back button action performed.
     *
     * @param evt the evt
     */
    private void goBackButtonActionPerformed(ActionEvent evt) {
        UIManager.put("OptionPane.messageFont", new Font("Papyrus", 0, 13));
        int back = JOptionPane.showConfirmDialog(this, "Sei sicuro di voler tornare al Menù senza salvare?", "Back", JOptionPane.YES_NO_OPTION);

        if (back == JOptionPane.YES_OPTION) {
            goBack();
        } else {
            notGoBack();
        }
    }

    /**
     * Method to go back
     */
    public void goBack() {
        CardLayout cl = (CardLayout) getParent().getLayout();
        cl.show(getParent(), "MenuGUI");
        displayTextPane.setText("");
        inventoryTextArea.setText(" Inventory:\n");
        resetAllGames();
        Mixer.changRoomMusic("Menu");
    }

    /**
     * Method when you don't go back
     */
    private void notGoBack() {
        UIManager.put("OptionPane.messageFont", new Font("Papyrus", 0, 13));
        JOptionPane.showMessageDialog(this, "Sii più deciso la prossima volta", "Back", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Sets the help GUI dialog visible.
     *
     * @param evt the evt
     */
    private void helpButtonActionPerformed(ActionEvent evt) {
        HelpGUI helpGUI = HelpGUI.getInstance();
        helpGUI.setVisible(true);
    }

    /**
     * Sets the music on and off.
     *
     * @param evt the evt
     */
    private void musicButtonActionPerformed(ActionEvent evt) {
        if(Mixer.isRunning()) {
            Mixer.stopClip();
        } else {
            Mixer.startClip();
        }
    }

    /**
     * Calls the method to set the current input.
     *
     * @param evt the evt
     */
    private void userInputFieldActionPerformed(ActionEvent evt) {
        String text = userInputField.getText();
        userInputField.setText("");
        UserInputManager.setCurrentInput(text);
    }

    /**
     * Sets the time in the timer label.
     *
     * @param time the time
     */
    public static void timerLabelSetTime(String time) {
        timerLabel.setText(" " + time + " ");
    }

    /**
     * Displays the text in the text pane.
     *
     * @param text the text
     */
    public static void displayTextPaneSetText(String text) {
        if (displayTextPane.getText().isEmpty()) {
            displayTextPane.setText(text);
        } else {
            displayTextPane.setText(displayTextPane.getText() + "\n" + text);
        }
        displayTextPane.setCaretPosition(displayTextPane.getDocument().getLength());
    }

    /**
     * Changes the image of the image panel.
     *
     * @param panelName the panel name
     */
    public static void setImagePanel(String panelName) {
        Mixer.changRoomMusic(panelName);
        Timer timerImagePanel = new Timer(600, e -> cardLayout.show(imagePanel, panelName));
        timerImagePanel.setRepeats(false);
        timerImagePanel.start();
    }

    /**
     * Sets the text of the music button.
     *
     * @param text the text
     */
    public static void musicButtonSetTextGame(String text) {
        musicButton.setText(text);
    }

    /**
     * Updates the inventory text area.
     *
     * @param items the items
     */
    public static void updateInventoryTextArea(String[] items) {
        StringBuilder inventory = new StringBuilder(" Inventario:\n");
        int maxHorItems = 3;

        int i = 0;
        while (i < items.length) {
            int j = 0;
            while (j < maxHorItems && i < items.length) {
                inventory.append(" - ").append(items[i]).append("   ");
                j++;
                i++;
            }
            inventory.append("\n");
        }

        inventoryTextArea.setText(inventory.toString());
    }

    /**
     * Resets all the GUIs of the games.
     */
    private void resetAllGames() {
        WordleGUI wordleGUI = getWordle();
        wordleGUI.resetBoxes();
        TilesGUI tilesGUI = (TilesGUI) imagePanel.getComponent(2);
        tilesGUI.resetAllMattonelle(true);
        HangmanGUI hangmanGUI = (HangmanGUI) imagePanel.getComponent(3);
        hangmanGUI.resetGuessedLetters();
    }
}
