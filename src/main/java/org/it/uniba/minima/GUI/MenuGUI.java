package org.it.uniba.minima.GUI;
import org.it.uniba.minima.Control.GameManager;
import org.it.uniba.minima.Control.UserInputFlow;
import org.it.uniba.minima.Database.REST_Server;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.Mixer;
import org.it.uniba.minima.TimerManager;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * The GUI of the menu.
 */
public class MenuGUI extends JPanel{
    /**
     * The background panel.
     */
    private JPanel backgroundPanel;
    /**
     * The new game button.
     */
    private JButton newGame;
    /**
     * The sound button.
     */
    private static JButton sound;
    /**
     * The help button.
     */
    private JButton help;
    /**
     * The load game button.
     */
    private JButton loadGame;
    /**
     * The credits button.
     */
    private JButton credits;
    /**
     * The site button.
     */
    private JButton site;

    /**
     * Constructor of the class.
     */
    public MenuGUI() {
        initComponents();
    }

    /**
     * Initializes the components.
     */
    private void initComponents() {
        // Create the components
        backgroundPanel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("docs/img/placeholder_immagine sfondo.jpeg");
                Image image = img.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        newGame = new JButton();
        newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        sound = new JButton( );
        help = new JButton();
        help.setText("?");
        loadGame = new JButton();
        credits = new JButton();
        site = new JButton();

        // Set the properties of the panel
        setPreferredSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));

        // Set the properties of the background panel
        backgroundPanel.setMinimumSize(new java.awt.Dimension(800, 600));
        backgroundPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        backgroundPanel.setRequestFocusEnabled(false);

        // Set the properties of the site button
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
        site.setHorizontalTextPosition(SwingConstants.CENTER);
        site.setMaximumSize(new java.awt.Dimension(60, 60));
        site.setMinimumSize(new java.awt.Dimension(60, 60));
        site.setPreferredSize(new java.awt.Dimension(60, 60));
        site.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            try {
                siteActionPerformed(evt);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    });

        // Set the properties of the new game button
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
        newGame.addActionListener(this::newGameActionPerformed);

        // Set the properties of the sound button
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
        sound.addActionListener(this::soundActionPerformed);

        // Set the properties of the help button
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
        help.addActionListener(this::helpActionPerformed);

        // Set the properties of the load game button
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
        loadGame.addActionListener(evt -> {
            try {
                loadGameActionPerformed(evt);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        // Set the properties of the credits button
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
        credits.addActionListener(this::creditsActionPerformed);

        GroupLayout backgroundPanelLayout = new GroupLayout(backgroundPanel);
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

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backgroundPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backgroundPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    /**
     * Start a new game.
     *
     * @param evt the event
     */
    private void newGameActionPerformed(ActionEvent evt) {
        ProgressBarGUI progressBarGUI;
        progressBarGUI = (ProgressBarGUI) this.getParent().getComponent(2);
        CardLayout cl = (CardLayout) getParent().getLayout();
        cl.show(getParent(), "ProgressBarGUI");
        GameManager.createGame();

        progressBarGUI.addPropertyChangeListener(evt1 -> {
            if (evt1.getPropertyName().equals("isFinished") && (boolean) evt1.getNewValue()) {
                cl.show(getParent(), "GameGUI");
                TimerManager.getInstance().startTimer("00:00:00");
            }
        });

        progressBarGUI.startProgressBar();

        //new thread to set up a new game during the charge of the progress bar
        new Thread(UserInputFlow::setUpGameFlow).start();
    }

    /**
     * Start or stop the music.
     */
    private void soundActionPerformed(ActionEvent evt) {
        if (Mixer.isRunning()) {
            Mixer.stopClip();
        } else {
            Mixer.startClip();
        }
    }

    /**
     * Open the help dialog.
     *
     * @param evt the event
     */
    private void helpActionPerformed(ActionEvent evt) {
        HelpGUI helpGUI = HelpGUI.getInstance();
        helpGUI.setVisible(true);
    }

    /**
     * Load a saved game.
     *
     * @param evt the event
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    private void loadGameActionPerformed(ActionEvent evt) throws IOException, ClassNotFoundException {
        GameManager.resetAllAgents();
        boolean loadedGameSuccessfully = GameManager.loadGame();

        if (loadedGameSuccessfully) {
            Game game = Game.getInstance();
            ProgressBarGUI progressBarGUI;
            progressBarGUI = (ProgressBarGUI) this.getParent().getComponent(2);
            CardLayout cl = (CardLayout) getParent().getLayout();
            cl.show(getParent(), "ProgressBarGUI");

            progressBarGUI.addPropertyChangeListener(evt1 -> {
                if (evt1.getPropertyName().equals("isFinished") && (boolean) evt1.getNewValue()) {
                    cl.show(getParent(), "GameGUI");
                    TimerManager.getInstance().startTimer(game.getCurrentTime());
                }
            });
            progressBarGUI.startProgressBar();

            //new thread to set up a saved game during the charge of the progress bar
            new Thread(() -> UserInputFlow.setUpLoadedGameFlow(game)).start();
        }
        else {
            showMessageDialog(null, "No saved game found", "Error", ERROR_MESSAGE);
        }
    }
  
    /**
     * Switch to the credits panel.
     *
     * @param evt the event
     */
    private void creditsActionPerformed(ActionEvent evt) {
        CardLayout cl = (CardLayout) getParent().getLayout();
        cl.show(getParent(), "CreditsGUI");
    }

    /**
     * Open the site.
     *
     * @param evt the event
     */
private void siteActionPerformed(java.awt.event.ActionEvent evt) throws URISyntaxException, IOException {
    REST_Server server = new REST_Server();
    try {
        server.startServer();

        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(new URI("http://localhost:8080/api/data"));
        }
    } catch (IOException | URISyntaxException e) {
        e.printStackTrace();
    }
}

    /**
     * Method to set the text of the sound button.
     *
     * @param text the text
     */
    public static void musicButtonSetTextMenu(String text) {
        sound.setText(text);
    }
}
