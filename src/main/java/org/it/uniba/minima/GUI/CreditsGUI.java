package org.it.uniba.minima.GUI;
import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * The GUI of the credits.
 */
public class CreditsGUI extends JPanel {
    /**
     * The button to go back to the main menu.
     */
    private JButton goBack;
    /**
     * The label of the title.
     */
    private JLabel titleLabel;
    /**
     * The panel of the background.
     */
    private JPanel backgroundPanel;
    /**
     * The panel of the icon of Marco.
     */
    private JPanel marcoIcon;
    /**
     * The panel of the icon of Pasco.
     */
    private JPanel pascoIcon;
    /**
     * The panel of the icon of Mik.
     */
    private JPanel mikIcon;
    /**
     * The label of the credits text
     */
    private JLabel contentLabel;

    /**
     * Constructor of the class.
     */
    public CreditsGUI() {
        initComponents();
    }

    /**
     * Initialize the components.
     */
    private void initComponents() {
        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("docs/img/placeholder_immagine sfondo.jpeg");
                Image image = img.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        goBack = new JButton();
        marcoIcon = new JPanel();
        pascoIcon = new JPanel();
        mikIcon = new JPanel();
        titleLabel = new JLabel();
        contentLabel = new JLabel();

        // Set the properties of the frame
        setPreferredSize(new java.awt.Dimension(800, 600));

        // Set the properties of the background panel
        backgroundPanel.setMinimumSize(new java.awt.Dimension(800, 600));
        backgroundPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        // Set the properties of the go back button
        goBack.setUI(new MetalButtonUI() {
            protected Color getSelectColor() {
                return new Color(133, 106, 5, 50);
            }
        });
        goBack.setFocusPainted(false);
        goBack.setBackground(new Color(204, 173, 27));
        goBack.setForeground(new Color(255, 255, 255));
        goBack.setBorderPainted(true);
        goBack.setBorder(BorderFactory.createLineBorder(new Color(107, 90, 13), 5));
        goBack.setFont(goBack.getFont().deriveFont(26f));
        goBack.setText("⮜");
        goBack.setMaximumSize(new java.awt.Dimension(40, 40));
        goBack.setMinimumSize(new java.awt.Dimension(40, 40));
        goBack.setPreferredSize(new java.awt.Dimension(40, 40));
        goBack.addActionListener(this::goBackActionPerformed);

        // Set the properties of the icon of Marco
        marcoIcon = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("docs/img/marcoIcon.png");
                Image image = img.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        marcoIcon.setMinimumSize(new java.awt.Dimension(150, 150));
        marcoIcon.setPreferredSize(new java.awt.Dimension(150, 150));

        // Set layout of the icon of Marco
        GroupLayout marcoIconLayout = new GroupLayout(marcoIcon);
        marcoIcon.setLayout(marcoIconLayout);
        marcoIconLayout.setHorizontalGroup(
                marcoIconLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );
        marcoIconLayout.setVerticalGroup(
                marcoIconLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );

        // Set the properties of the icon of Pasco
        pascoIcon = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("docs/img/pascoIcon.png");
                Image image = img.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        pascoIcon.setMinimumSize(new java.awt.Dimension(150, 150));
        pascoIcon.setPreferredSize(new java.awt.Dimension(150, 150));

        // Set layout of the icon of Pasco
        GroupLayout pascoIconLayout = new GroupLayout(pascoIcon);
        pascoIcon.setLayout(pascoIconLayout);
        pascoIconLayout.setHorizontalGroup(
                pascoIconLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );
        pascoIconLayout.setVerticalGroup(
                pascoIconLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );

        // Set the properties of the icon of Mik
        mikIcon = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("docs/img/MikIcon.png");
                Image image = img.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mikIcon.setMinimumSize(new java.awt.Dimension(150, 150));
        mikIcon.setPreferredSize(new java.awt.Dimension(150, 150));

        // Set layout of the icon of Mik
        GroupLayout mikIconLayout = new GroupLayout(mikIcon);
        mikIcon.setLayout(mikIconLayout);
        mikIconLayout.setHorizontalGroup(
                mikIconLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );
        mikIconLayout.setVerticalGroup(
                mikIconLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );

        // Set the properties of the title label
        titleLabel.setFont(new java.awt.Font("Papyrus", 0, 34));
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(204, 173, 27));
        titleLabel.setText("Riconoscimenti");
        titleLabel.setPreferredSize(new java.awt.Dimension(220, 39));

        // Set the properties of the content label
        contentLabel.setBackground(new Color(204, 173, 27));
        contentLabel.setForeground(new Color(255, 255, 255));
        contentLabel.setOpaque(true);
        contentLabel.setVerticalAlignment(SwingConstants.TOP);
        contentLabel.setFont(new Font("Georgia", 0, 14));
        contentLabel.setText(
                "<html><center>"
                + "<p>\"Avventura nella Piramide\" è una avventura testuale sviluppata da Marco Ruggiero Santeramo, Nicolo' Pacucci e Michele Pontrelli nell'ambito del corso universitario di \"Metodi Avanzati di Programmazione\". Seppure il progetto è incentrato sull'applicazione dei concetti chiave visti a lezione, gli sviluppatori non hanno trascurato l'aspetto videoludico, creando qualcosa di ben fatto e divertente, al limite delle loro possibilità.</p>"
                + "</html><center>"
        );
        contentLabel.setPreferredSize(new Dimension(500, 132));
        contentLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Set the layout of the background panel
        GroupLayout backgroundPanelLayout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
                backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundPanelLayout.createSequentialGroup()
                                .addGroup(backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(backgroundPanelLayout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(goBack, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(backgroundPanelLayout.createSequentialGroup()
                                                .addGap(105, 105, 105)
                                                .addComponent(marcoIcon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(70, 70, 70)
                                                .addComponent(pascoIcon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(70, 70, 70)
                                                .addComponent(mikIcon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                                .addGap(143, 143, 143)
                                .addGroup(backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                                                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
                                                .addGap(300, 300, 300))
                                        .addGroup(GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                                                .addComponent(contentLabel, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                                                .addGap(160, 160, 160))))
        );
        backgroundPanelLayout.setVerticalGroup(
                backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundPanelLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(goBack, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addGroup(backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(pascoIcon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(mikIcon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(marcoIcon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)
                                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)
                                .addComponent(contentLabel, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(112, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(backgroundPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(backgroundPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

    }

    /**
     * The button to go back to the main menu.
     * 
     * @param evt the evt
     */
    private void goBackActionPerformed(ActionEvent evt) {
        CardLayout cl = (CardLayout) getParent().getLayout();
        cl.show(getParent(), "MenuGUI");
    }
}
