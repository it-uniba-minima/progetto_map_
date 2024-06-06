package org.it.uniba.minima.GUI;
import org.it.uniba.minima.Boundary.outputDisplayManager;
import org.it.uniba.minima.Control.GameManager;
import org.it.uniba.minima.Control.userInputFlow;
import org.it.uniba.minima.Database.DatabaseConnection;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.Mixer;
import org.it.uniba.minima.TimerManager;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.plaf.metal.MetalButtonUI;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;


public class MenuGUI extends javax.swing.JPanel{


    /**
     * Creates new form MenuGUI
     */
    public MenuGUI() {
        initComponents();
    }


    private void initComponents() {
        backgroundPanel = new javax.swing.JPanel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("docs/img/placeholder_immagine sfondo.jpeg");
                Image image = img.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        newGame = new javax.swing.JButton();
        newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        sound = new javax.swing.JButton( );
        help = new javax.swing.JButton();
        help.setText("?");
        loadGame = new javax.swing.JButton();
        credits = new javax.swing.JButton();
        site = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));

        backgroundPanel.setMinimumSize(new java.awt.Dimension(800, 600));
        backgroundPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        backgroundPanel.setRequestFocusEnabled(false);

        site.setUI(new MetalButtonUI() {
    protected Color getSelectColor () {
        return new Color(133, 106, 5, 50);

    }
});
        site.setFocusPainted(false);
        site.setBackground(new Color(204, 173, 27));
        site.setForeground(new Color(255, 255, 255));
        site.setBorderPainted(true);
        site.setBorder(BorderFactory.createLineBorder(new Color(107, 90, 13), 5));
        site.setFont(site.getFont().deriveFont(40f));
        site.setText("\uD83C\uDF10");
        site.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        site.setMaximumSize(new java.awt.Dimension(60, 60));
        site.setMinimumSize(new java.awt.Dimension(60, 60));
        site.setPreferredSize(new java.awt.Dimension(60, 60));
        site.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        siteActionPerformed(evt);
    }
});

        newGame.setUI(new MetalButtonUI() {
    protected Color getSelectColor () {
        return new Color(133, 106, 5, 50);

    }
});
        newGame.setFocusPainted(false);
        newGame.setBackground(new Color(204, 173, 27));
        newGame.setForeground(new Color(255, 255, 255));
        newGame.setFont(new Font("Papyrus", 1, 24));
        newGame.setBorderPainted(true);
        newGame.setBorder(BorderFactory.createLineBorder(new Color(107, 90, 13), 5));
        newGame.setText("Nuova Partita");
        newGame.setMaximumSize(new java.awt.Dimension(240, 60));
        newGame.setMinimumSize(new java.awt.Dimension(240, 60));
        newGame.setPreferredSize(new java.awt.Dimension(240, 60));
        newGame.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        newGameActionPerformed(evt);
    }
});

        sound.setUI(new MetalButtonUI() {
    protected Color getSelectColor () {
        return new Color(133, 106, 5, 50);

    }
});
        sound.setFocusPainted(false);
        sound.setBackground(new Color(204, 173, 27));
        sound.setForeground(new Color(255, 255, 255));
        sound.setFont(sound.getFont().deriveFont(24f));
        sound.setBorderPainted(true);
        sound.setBorder(BorderFactory.createLineBorder(new Color(107, 90, 13), 5));
        sound.setText("🔊");
        sound.setMargin(new java.awt.Insets(0, 0, 0, 0));
        sound.setMaximumSize(new java.awt.Dimension(40, 40));
        sound.setMinimumSize(new java.awt.Dimension(40, 40));
        sound.setPreferredSize(new java.awt.Dimension(40, 40));
        sound.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        soundActionPerformed(evt);
    }
});

        help.setUI(new MetalButtonUI() {
    protected Color getSelectColor () {
        return new Color(133, 106, 5, 50);

    }
});
        help.setFocusPainted(false);
        help.setBackground(new Color(204, 173, 27));
        help.setForeground(new Color(255, 255, 255));
        help.setFont(new Font("Papyrus", 1, 24));
        help.setBorderPainted(true);
        help.setBorder(BorderFactory.createLineBorder(new Color(107, 90, 13), 5));
        help.setMargin(new java.awt.Insets(0, 0, 0, 0));
        help.setMaximumSize(new java.awt.Dimension(40, 40));
        help.setMinimumSize(new java.awt.Dimension(40, 40));
        help.setPreferredSize(new java.awt.Dimension(40, 40));
        help.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        helpActionPerformed(evt);
    }
});

        loadGame.setUI(new MetalButtonUI() {
    protected Color getSelectColor () {
        return new Color(133, 106, 5, 50);

    }
});
        loadGame.setFocusPainted(false);
        loadGame.setBackground(new Color(204, 173, 27));
        loadGame.setForeground(new Color(255, 255, 255));
        loadGame.setFont(new Font("Papyrus", 1, 24));
        loadGame.setBorderPainted(true);
        loadGame.setBorder(BorderFactory.createLineBorder(new Color(107, 90, 13), 5));
        loadGame.setText("Carica Partita");
        loadGame.setMaximumSize(new java.awt.Dimension(240, 60));
        loadGame.setMinimumSize(new java.awt.Dimension(240, 60));
        loadGame.setPreferredSize(new java.awt.Dimension(240, 60));
        loadGame.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        try {
            loadGameActionPerformed(evt);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
});

        credits.setUI(new MetalButtonUI() {
    protected Color getSelectColor () {
        return new Color(133, 106, 5, 50);

    }
});
        credits.setFocusPainted(false);
        credits.setBackground(new Color(204, 173, 27));
        credits.setForeground(new Color(255, 255, 255));
        credits.setFont(new Font("Papyrus", 1, 24));
        credits.setBorderPainted(true);
        credits.setBorder(BorderFactory.createLineBorder(new Color(107, 90, 13), 5));
        credits.setText("Riconoscimenti");
        credits.setMaximumSize(new java.awt.Dimension(240, 60));
        credits.setMinimumSize(new java.awt.Dimension(240, 60));
        credits.setPreferredSize(new java.awt.Dimension(240, 60));
        credits.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        creditsActionPerformed(evt);
    }
});

javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanelLayout.setHorizontalGroup(
        backgroundPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(backgroundPanelLayout.createSequentialGroup()
                    .addGap(25)
                    .addGroup(backgroundPanelLayout.createParallelGroup(Alignment.TRAILING)
                        .addComponent(sound, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(help, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(ComponentPlacement.RELATED, 420, Short.MAX_VALUE)
                    .addComponent(site, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(25))
        .addGroup(backgroundPanelLayout.createSequentialGroup()
                    .addGap(270)
                    .addGroup(backgroundPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(newGame, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(loadGame, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(credits, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        .addContainerGap(270, Short.MAX_VALUE))
        );
        backgroundPanelLayout.setVerticalGroup(
        backgroundPanelLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(backgroundPanelLayout.createSequentialGroup()
                    .addGap(25)
                    .addGroup(backgroundPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(site, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(backgroundPanelLayout.createSequentialGroup()
                            .addComponent(sound, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                            .addGap(12)
                            .addComponent(help, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
        .addPreferredGap(ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                    .addComponent(newGame, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                    .addGap(32)
                    .addComponent(loadGame, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                    .addGap(32)
                    .addComponent(credits, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                    .addGap(90))
        );
        backgroundPanel.setLayout(backgroundPanelLayout);

javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
                layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
                }// </editor-fold>

private void newGameActionPerformed(java.awt.event.ActionEvent evt) {
    ProgressBarGUI progressBarGUI;
    progressBarGUI = (ProgressBarGUI) this.getParent().getComponent(2);
    CardLayout cl = (CardLayout) getParent().getLayout();
    cl.show(getParent(), "ProgressBarGUI");
    GameManager.createGame();

    GameGUI gameGUI = (GameGUI) this.getParent().getComponent(3);

    progressBarGUI.addPropertyChangeListener(new PropertyChangeListener() {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("isFinished") && (boolean) evt.getNewValue()) {
                cl.show(getParent(), "GameGUI");
                TimerManager.getInstance().startTimer();
            }
        }
    });

    progressBarGUI.startProgressBar();
}

private void soundActionPerformed(java.awt.event.ActionEvent evt) {
    if (Mixer.isRunning()) {
        Mixer.stopClip();
    } else {
        Mixer.startClip();
    }
}

private void helpActionPerformed(java.awt.event.ActionEvent evt) {
    HelpGUI helpGUI = HelpGUI.getInstance();
    helpGUI.setVisible(true);
    // TODO add your handling code here:
}

    private void loadGameActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {
        GameManager gameManager = new GameManager();
        boolean loadedGameSuccessfully = gameManager.loadGame();
        if (loadedGameSuccessfully) {
            GameGUI gameGUI = (GameGUI) this.getParent().getComponent(3);
            CardLayout cl = (CardLayout) getParent().getLayout();
            cl.show(getParent(), "GameGUI");
            TimerManager.getInstance().startTimer();
        }
        else {
            showMessageDialog(null, "No saved game found", "Error", ERROR_MESSAGE);
        }
    }

private void creditsActionPerformed(java.awt.event.ActionEvent evt) {
    CardLayout cl = (CardLayout) getParent().getLayout();
    cl.show(getParent(), "RiconoscimentiGUI");    }

private void siteActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
}

public static void musicButtonSetTextMenu(String text) {
    sound.setText(text);
}

public static void setGame(GameGUI game) {
    // TODO add your handling code here:
}
// Variables declaration - do not modify
private javax.swing.JPanel backgroundPanel;
private javax.swing.JButton newGame;
private static javax.swing.JButton sound;
private javax.swing.JButton help;
private javax.swing.JButton loadGame;
private javax.swing.JButton credits;
private javax.swing.JButton site;
}
