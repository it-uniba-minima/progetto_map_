/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.it.uniba.minima.GUI;



import com.mashape.unirest.http.exceptions.UnirestException;
import org.it.uniba.minima.Boundary.WordleGame;
import org.it.uniba.minima.Control.Serializer;
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

import static javax.swing.SwingUtilities.invokeLater;

/**
 *
 * @author miche
 */
public class GameGUI extends javax.swing.JPanel {
    private static CardLayout cardLayout;
    /**
     * Creates new form GameGUI
     */

    public static Wordle getWordle() {
        return (Wordle) imagePanel.getComponent(0);
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
        imagePanel.add(new Wordle(), "Wordle");
        imagePanel.add(new MattonelleGUI(), "Mattonelle");
        imagePanel.add(new ImpiccatoGUI(), "Impiccato");

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

        toolBar = new javax.swing.JToolBar();
        goBackButton = new javax.swing.JButton();
        saveGameButton = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();
        musicButton = new javax.swing.JButton();
        timerLabel = new javax.swing.JLabel();
        userInputField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        inventoryTextArea = new javax.swing.JTextArea();
        imagePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        displayTextPane = new javax.swing.JTextPane();

        setPreferredSize(new java.awt.Dimension(800, 600));

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
        goBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
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
        saveGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveGameButtonActionPerformed(evt);
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
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
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
        musicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
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
        inventoryTextArea.setColumns(20);
        inventoryTextArea.setRows(5);
        inventoryTextArea.setAutoscrolls(false);
        inventoryTextArea.setBorder(null);
        inventoryTextArea.setEnabled(false);
        inventoryTextArea.setFocusable(false);
        inventoryTextArea.setOpaque(false);
        inventoryTextArea.setMargin(new Insets(0, 0, 0, 0));
        inventoryTextArea.setPreferredSize(new Dimension(440, 100));
        inventoryTextArea.setMaximumSize(new Dimension(440, 100));
        inventoryTextArea.setMinimumSize(new Dimension(440, 100));
        inventoryTextArea.setFont(new Font("Papyrus", Font.PLAIN, 20));
        inventoryTextArea.setForeground(new Color(0, 0, 0));
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


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(toolBar, 800, 800, 800)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(userInputFieldPanel, 335, 335, 335))
                                        .addComponent(jScrollPane1))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, 440, 440, 440)
                                        .addComponent(imagePanel, 440, 440, 440))
                                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane1)
                                                .addComponent(userInputFieldPanel, 31, 31, 31)))
                                .addGap(5, 5, 5))
        );
    }// </editor-fold>

    private void saveGameButtonActionPerformed(java.awt.event.ActionEvent evt) {
        /*
        Serializer serializer = new Serializer();
        try {
            serializer.serialize(game);
        } catch (Exception e) {
            e.printStackTrace();
        }
         */
    }

    private void goBackButtonActionPerformed(java.awt.event.ActionEvent evt) {
        TimerManager.getInstance().stopTimer();
        //Get timer -> insert in File
        TimerManager.getInstance().killTimer();
        CardLayout cl = (CardLayout) getParent().getLayout();
        cl.show(getParent(), "MenuGUI");
    }

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {
        HelpGUI helpGUI = HelpGUI.getInstance();
        helpGUI.setVisible(true);
    }

    private void musicButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if(Mixer.isRunning()) {
            Mixer.stopClip();
        } else {
            Mixer.startClip();
        }
    }

    private void userInputFieldActionPerformed(java.awt.event.ActionEvent evt) {
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

    // Variables declaration - do not modify
    private javax.swing.JButton goBackButton;
    private javax.swing.JButton saveGameButton;
    private javax.swing.JButton helpButton;
    private static javax.swing.JButton musicButton;
    private static javax.swing.JPanel imagePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea inventoryTextArea;
    private javax.swing.JTextField userInputField;
    private static javax.swing.JTextPane displayTextPane;
    private javax.swing.JToolBar toolBar;
    private static javax.swing.JLabel timerLabel;
    // End of variables declaration
}
