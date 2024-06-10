package org.it.uniba.minima.GUI;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JComponent;

/**
 * The GUI of the progress bar.
 */
public class ProgressBarGUI extends JPanel {
    /**
     * The panel of the running GIF.
     */
    private JPanel runningGIFPanel;
    /**
     * The panel of the background.
     */
    private JPanel backgroundPanel;
    /**
     * The progress bar.
     */
    private JProgressBar progressBar;
    /**
     * The counter of the progress bar.
     */
    private int counter;
    /**
     * The x position of the running GIF.
     */
    private int x;
    /**
     * The image of the running GIF.
     */
    private final ImageIcon img;
    /**
     * The property change support.
     */
    private final PropertyChangeSupport support;
    /**
     * The label of the progress bar.
     */
    private JLabel progressBarLabel;

    /**
     * Constructor of the class.
     */
    public ProgressBarGUI() {
        initComponents();
        img = new ImageIcon("src/main/resources/docs/img/runningChar.png");
        x = -img.getIconWidth();
        support = new PropertyChangeSupport(this);
    }

    /**
     * Adds a property change listener.
     *
     * @param listener the listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Removes a property change listener.
     *
     * @param listener the listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    /**
     * Sets the progress bar to finished, by firing a property change event.
     *
     * @param isFinished the is finished
     */
    public void setFinished(boolean isFinished) {
        support.firePropertyChange("isFinished", null, isFinished);
    }

    /**
     * Starts and develops the progress bar.
     */
    public void startProgressBar() {
        int imgWidth = 161;
        int panelWidth = runningGIFPanel.getWidth();
        counter = 0;

        Timer timerP = new Timer();
        TimerTask taskProgressBar = new TimerTask() {
            @Override
            public void run() {
                if (counter < 100) {
                    counter++;
                    progressBar.setValue(counter);
                    progressBarLabel.setText("Loading... " + counter + "%");
                    x = (int) ((double) counter / 100 * (panelWidth + imgWidth)) - imgWidth;
                    runningGIFPanel.repaint();
                } else {
                    progressBarLabel.setText("Get Ready to Play!");
                    Timer timerPLW = new Timer();
                    TimerTask taskProgressBarLastWord = new TimerTask() {
                        @Override
                        public void run() {
                            setFinished(true);
                            timerPLW.cancel();
                        }
                    };
                    timerPLW.schedule(taskProgressBarLastWord, 1000);
                    timerP.cancel();
                }
            }
        };
        timerP.scheduleAtFixedRate(taskProgressBar, 0, 10);
    }

    /**
     * Initializes the components.
     */
    private void initComponents() {
        backgroundPanel = new JPanel();
        runningGIFPanel = new JPanel();
        progressBar = new JProgressBar();
        progressBarLabel = new JLabel("Loading... 0%");

        // Set the properties of the frame
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));

        // Set the properties of the background panel
        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("src/main/resources/docs/img/placeholder_immagine sfondo.jpeg");
                Image image = img.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Set the properties of the running GIF panel
        runningGIFPanel.setPreferredSize(new Dimension(482, 161));
        runningGIFPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = img.getImage();
                g.drawImage(image, x, 0, 161, getHeight(), this);
            }
        };
        runningGIFPanel.setOpaque(false);

        // Set the layout of the running GIF panel
        GroupLayout runningGIFPanelLayout = new GroupLayout(runningGIFPanel);
        runningGIFPanel.setLayout(runningGIFPanelLayout);
        runningGIFPanelLayout.setHorizontalGroup(
                runningGIFPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 149, Short.MAX_VALUE)
        );
        runningGIFPanelLayout.setVerticalGroup(
                runningGIFPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 161, Short.MAX_VALUE)
        );

        // Set the properties of the progress bar
        progressBar.setFont(new java.awt.Font("Papyrus", 1, 24));
        progressBar.setForeground(new java.awt.Color(204, 173, 27));
        progressBar.setBackground(new java.awt.Color(107, 90, 13));
        progressBar.setOpaque(true);
        progressBar.setBorder(BorderFactory.createLineBorder(new java.awt.Color(107, 90, 13), 5));
        progressBar.setMaximumSize(new java.awt.Dimension(482, 48));
        progressBar.setMinimumSize(new java.awt.Dimension(482, 48));
        progressBar.setPreferredSize(new java.awt.Dimension(482, 48));
        progressBar.setStringPainted(false);

        // Set the UI of the progress bar
        progressBar.setUI(new BasicProgressBarUI() {
            // Override the paintDeterminate method to customize the painting of the progress bar
            @Override
            protected void paintDeterminate(Graphics g, JComponent c) {
                if (!(g instanceof Graphics2D)) {
                    return;
                }

                Insets b = progressBar.getInsets();
                int width = progressBar.getWidth();
                int height = progressBar.getHeight();
                int barRectWidth = width - (b.right + b.left);
                int barRectHeight = height - (b.top + b.bottom);

                if (barRectWidth <= 0 || barRectHeight <= 0) {
                    return;
                }

                // Paint the background.
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(progressBar.getBackground());
                g2d.fillRect(b.left, b.top, barRectWidth, barRectHeight);

                // Paint the progress bar
                int amountFull = getAmountFull(b, barRectWidth, barRectHeight);

                g2d.setColor(progressBar.getForeground());
                g2d.fillRect(b.left, b.top, amountFull, barRectHeight);
            }
        });

        // Configure the label of the progress bar
        progressBarLabel.setFont(new java.awt.Font("Papyrus", 1, 24));
        progressBarLabel.setForeground(new java.awt.Color(255, 255, 255));
        progressBarLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the label to the progress bar
        progressBar.setLayout(new BorderLayout());
        progressBar.add(progressBarLabel, BorderLayout.CENTER);

        // Set the layout of the background panel
        GroupLayout backgroundPanelLayout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
                backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundPanelLayout.createSequentialGroup()
                                .addGap(159, 159, 159)
                                .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 482, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                                .addGap(159, 159, 159)
                                .addComponent(runningGIFPanel, GroupLayout.PREFERRED_SIZE, 482, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        backgroundPanelLayout.setVerticalGroup(
                backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                                .addContainerGap(300, Short.MAX_VALUE)
                                .addComponent(runningGIFPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70))
        );

        // Set the layout of the panel
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backgroundPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backgroundPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }
}
