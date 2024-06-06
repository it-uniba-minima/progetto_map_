/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.it.uniba.minima.GUI;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.it.uniba.minima.Boundary.WordleGame;
import org.it.uniba.minima.Control.Converter;
import org.it.uniba.minima.Control.GameManager;
import org.it.uniba.minima.Database.DatabaseConnection;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.Mixer;
import org.it.uniba.minima.TimerManager;
import org.it.uniba.minima.Boundary.userInputManager;
import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 *
 * @author miche
 */
public class GameGUI extends JPanel {
    private static CardLayout cardLayout;
    /**
     * Creates new form GameGUI
     */

    public static Wordle getWordle() {
        return (Wordle) imagePanel.getComponent(1);
    }

    public GameGUI() {
        UIManager.put("ScrollBar.width", 0); // Set the width to 20 pixels
        SwingUtilities.updateComponentTreeUI(this);
        initComponents();
        initImagePanel();
    }

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
        //substitute JLabel with images
        //beacause of the getComponent method you have to set the event panels on top
        //so that you can retrieve them by index in, for example, the getWordle method


        imagePanel.add(new JPanel(null) {
            {
                setBounds(0, 0, 440, 400);
                setBorder(BorderFactory.createLineBorder(new Color(107, 90, 13), 5));

            }
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon image = new ImageIcon("docs/img/Desert.png");
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }, "Desert");

        imagePanel.add(new Wordle(), "Wordle");
        imagePanel.add(new MattonelleGUI(), "Mattonelle");
        imagePanel.add(new ImpiccatoGUI(), "Impiccato");

        imagePanel.add(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon image = new ImageIcon("docs/img/Stanza1.png");
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }, "Stanza1");

        imagePanel.add(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon image = new ImageIcon("docs/img/Stanza2.png");
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }, "Stanza2");

        imagePanel.add(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon image = new ImageIcon("docs/img/Stanza3.png");
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }, "Stanza3");

        imagePanel.add(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon image = new ImageIcon("docs/img/Stanza4.png");
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }, "Stanza4");

        imagePanel.add(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon image = new ImageIcon("docs/img/Stanza5.png");
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }, "Stanza5");

        imagePanel.add(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon image = new ImageIcon("docs/img/Stanza6.png");
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }, "Stanza6");

        imagePanel.add(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon image = new ImageIcon("docs/img/Stanza7.png");
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }, "Stanza7");

        imagePanel.add(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon image = new ImageIcon("docs/img/Stanza8.png");
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }, "Stanza8");

        imagePanel.add(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon image = new ImageIcon("docs/img/Stanza9.png");
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }, "Stanza9");

        imagePanel.add(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon image = new ImageIcon("docs/img/Stanza10.png");
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }, "Stanza10");
    }

    public static FontMetrics getTextPaneFontMetrics() {
        return displayTextPane.getFontMetrics(displayTextPane.getFont());
    }

    public static int getTextPaneWidth() {
        return displayTextPane.getWidth();
    }

    public static MattonelleGUI getMattonelleGUI() {
        return (MattonelleGUI) imagePanel.getComponent(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {
        setBackground(new Color(25, 30, 66));

        toolBar = new JToolBar();
        goBackButton = new JButton();
        saveGameButton = new JButton();
        helpButton = new JButton();
        musicButton = new JButton();
        timerLabel = new JLabel();
        userInputField = new JTextField();
        jScrollPane2 = new JScrollPane();
        inventoryTextArea = new JTextArea();
        imagePanel = new JPanel();
        jScrollPane1 = new JScrollPane();
        displayTextPane = new JTextPane();

        setPreferredSize(new Dimension(800, 600));

        toolBar.setBorderPainted(false);
        toolBar.setFloatable(false);
        toolBar.setBackground(new Color(25, 30, 66));

        toolBar.add(Box.createHorizontalStrut(5));

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
        goBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                goBackButtonActionPerformed(evt);
            }
        });
        toolBar.add(goBackButton);

        toolBar.add(Box.createHorizontalStrut(10));

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
        saveGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    saveGameButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        toolBar.add(saveGameButton);

        toolBar.add(Box.createHorizontalStrut(10));

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
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });
        toolBar.add(helpButton);

        toolBar.add(Box.createHorizontalStrut(10));

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
        musicButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                musicButtonActionPerformed(evt);
            }
        });
        toolBar.add(musicButton);

        toolBar.add(Box.createHorizontalStrut(483));

        timerLabel.setOpaque(true);
        timerLabel.setBackground(new Color(204, 173, 27));
        timerLabel.setForeground(new Color(255, 255, 255));
        timerLabel.setBorder(BorderFactory.createLineBorder(new Color(107, 90, 13), 2));
        timerLabel.setFont(new Font("Papyrus", 1, 16));
        timerLabel.setFocusable(false);
        timerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        timerLabel.setVerticalTextPosition(SwingConstants.CENTER);
        toolBar.add(timerLabel);

        Image inventoryImg = new ImageIcon("docs/img/bagTextArea.png").getImage();

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
        jScrollPane2.setViewport(inventoryView);
        jScrollPane2.setViewportView(inventoryTextArea);
        jScrollPane2.setPreferredSize(new Dimension(440, 100));
        jScrollPane2.setMaximumSize(new Dimension(440, 100));
        jScrollPane2.setMinimumSize(new Dimension(440, 100));
        jScrollPane2.setBorder(BorderFactory.createMatteBorder(0, 5, 5, 5, new Color(107, 90, 13)));

        Image img = new ImageIcon("docs/img/papyrTextPane.png").getImage();

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

        jScrollPane1.setBackground(new Color(204, 173, 27));
        jScrollPane1.setViewport(view);
        jScrollPane1.setViewportView(displayTextPane);
        jScrollPane1.setPreferredSize(new Dimension(335, 550));
        jScrollPane1.setMaximumSize(new Dimension(335, 550));
        jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 0, new Color(107, 90, 13)));

        userInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userInputFieldActionPerformed(e);
            }
        });
        userInputField.setMargin(new Insets(0, 0, 0, 0));
        userInputField.setForeground(new Color(0, 0, 0));
        userInputField.setFont(new Font("Georgia", 0, 13));
        userInputField.setOpaque(false);
        userInputField.setPreferredSize(new Dimension(335, 31));
        userInputField.setMaximumSize(new Dimension(335, 31));
        userInputField.setMinimumSize(new Dimension(335, 31));
        userInputField.setBorder(BorderFactory.createMatteBorder(0, 5, 5, 0, new Color(107, 90, 13)));
        userInputField.setBounds(0, 0, 335, 31);
        userInputManager.startInputListener(userInputField);

        Image img2 = new ImageIcon("docs/img/papyrUserInputField.png").getImage();
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
                                        .addComponent(jScrollPane1))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, 440, 440, 440)
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
                                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane1)
                                                .addComponent(userInputFieldPanel, 31, 31, 31)))
                                .addGap(5, 5, 5))
        );
    }// </editor-fold>

    private void saveGameButtonActionPerformed(ActionEvent evt) throws IOException, ClassNotFoundException {
            Font font = new Font("Papyrus", 0, 13);
            UIManager.put("OptionPane.messageFont", font);
            int save = JOptionPane.showConfirmDialog(this, "Would you like to save?", "Save",
                    JOptionPane.YES_NO_OPTION);

            if (save == JOptionPane.YES_OPTION) {
                saveFile();
            } else {
                buttonActions1();
            }
    }

    private void buttonActions1() {
        UIManager.put("OptionPane.messageFont", new Font("Papyrus", 0, 13));
        JOptionPane.showMessageDialog(this, "Game not saved", "Save", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveFile() throws IOException, ClassNotFoundException {
        GameManager gameManager = new GameManager();
        gameManager.saveGame();
        JOptionPane.showMessageDialog(this, "Game saved successfully", "Save", JOptionPane.INFORMATION_MESSAGE);
        CardLayout cl = (CardLayout) getParent().getLayout();
        cl.show(getParent(), "MenuGUI");
    }

private void goBackButtonActionPerformed(ActionEvent evt) {
        UIManager.put("OptionPane.messageFont", new Font("Papyrus", 0, 13));
        int back = JOptionPane.showConfirmDialog(this, "Sei sicuro di voler tornare al Menù senza salvare?", "Back",
                JOptionPane.YES_NO_OPTION);
        if (back == JOptionPane.YES_OPTION) {
            CardLayout cl = (CardLayout) getParent().getLayout();
            cl.show(getParent(), "MenuGUI");
            //TODO: completare questo if perchè se salvo un game , ci rivado e non risalvo il timer riparte da 0.
            // serve un attributo per verificare se si è verificato in precedenza un salvataggio in modo che prenda l'ultimo salvataggio esistente
            /*
            if (ciccio) {
                TimerManager.getInstance().stopTimer();
                TimerManager.getInstance().killTimer();
            }
            else {
                buttonActions2();
            }
             */
        } else {
            buttonActions2();
        }

    }

    private void buttonActions2() {
        UIManager.put("OptionPane.messageFont", new Font("Papyrus", 0, 13));
        JOptionPane.showMessageDialog(this, "Sii più deciso la prossima volta", "Back", JOptionPane.INFORMATION_MESSAGE);
    }

    private void helpButtonActionPerformed(ActionEvent evt) {
        HelpGUI helpGUI = HelpGUI.getInstance();
        helpGUI.setVisible(true);
    }

    private void musicButtonActionPerformed(ActionEvent evt) {
        if(Mixer.isRunning()) {
            Mixer.stopClip();
        } else {
            Mixer.startClip();
        }
    }

    private void userInputFieldActionPerformed(ActionEvent evt) {
        String text = userInputField.getText();
        userInputField.setText("");
        userInputManager.setCurrentInput(text);
    }

    public static void timerLabelSetTime(String time) {
        timerLabel.setText(" " + time + " ");
    }

    public static void displayTextPaneSetText(String text) {
        if (displayTextPane.getText().isEmpty()) {
            displayTextPane.setText(text);
        } else {
            displayTextPane.setText(displayTextPane.getText() + "\n" + text);
        }
        displayTextPane.setCaretPosition(displayTextPane.getDocument().getLength());
    }

    public static void setImagePanel(String panelName) {
        Timer timer = new Timer(600, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(imagePanel, panelName);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public static void musicButtonSetTextGame(String text) {
        musicButton.setText(text);
    }

    public static void updateInventoryTextArea(String[] items) {
        StringBuilder inventory = new StringBuilder(" Inventario:\n");
        int maxHorItems = 3; // Adjust this value based on the height of your text area

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

//        if (items.length > maxHorItems) {
//            inventory.append("... and ").append(items.length - maxHorItems).append(" more items");
//        }

        inventoryTextArea.setText(inventory.toString());
    }

    private JButton goBackButton;
    private JButton saveGameButton;
    private JButton helpButton;
    private static JButton musicButton;
    private static JPanel imagePanel;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private static JTextArea inventoryTextArea;
    private JTextField userInputField;
    private static JTextPane displayTextPane;
    private JToolBar toolBar;
    private static JLabel timerLabel;
}
