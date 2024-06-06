package org.it.uniba.minima.GUI;
import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;

public class RiconoscimentiGUI extends javax.swing.JPanel {
    /**
     * Creates new form RiconoscimentiGUI
     */
    public RiconoscimentiGUI() {
        initComponents();
    }

    private void initComponents() {

        backgroundPanel = new javax.swing.JPanel();
        backgroundPanel = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("docs/img/placeholder_immagine sfondo.jpeg");
                Image image = img.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        goBack = new javax.swing.JButton();
        marcoIcon = new javax.swing.JPanel();
        pascoIcon = new javax.swing.JPanel();
        mikIcon = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        contentLabel = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 600));

        backgroundPanel.setMinimumSize(new java.awt.Dimension(800, 600));
        backgroundPanel.setPreferredSize(new java.awt.Dimension(800, 600));

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
        goBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goBackActionPerformed(evt);
            }
        });

        marcoIcon = new javax.swing.JPanel() {
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

        javax.swing.GroupLayout marcoIconLayout = new javax.swing.GroupLayout(marcoIcon);
        marcoIcon.setLayout(marcoIconLayout);
        marcoIconLayout.setHorizontalGroup(
                marcoIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );
        marcoIconLayout.setVerticalGroup(
                marcoIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );

        pascoIcon = new javax.swing.JPanel() {
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

        javax.swing.GroupLayout pascoIconLayout = new javax.swing.GroupLayout(pascoIcon);
        pascoIcon.setLayout(pascoIconLayout);
        pascoIconLayout.setHorizontalGroup(
                pascoIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );
        pascoIconLayout.setVerticalGroup(
                pascoIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );

        mikIcon = new javax.swing.JPanel() {
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

        javax.swing.GroupLayout mikIconLayout = new javax.swing.GroupLayout(mikIcon);
        mikIcon.setLayout(mikIconLayout);
        mikIconLayout.setHorizontalGroup(
                mikIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );
        mikIconLayout.setVerticalGroup(
                mikIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );

        titleLabel.setFont(new java.awt.Font("Papyrus", 0, 34));
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(204, 173, 27));
        titleLabel.setText("Riconoscimenti");
        titleLabel.setPreferredSize(new java.awt.Dimension(220, 39));

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

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
                backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundPanelLayout.createSequentialGroup()
                                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(backgroundPanelLayout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(goBack, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(backgroundPanelLayout.createSequentialGroup()
                                                .addGap(105, 105, 105)
                                                .addComponent(marcoIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(70, 70, 70)
                                                .addComponent(pascoIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(70, 70, 70)
                                                .addComponent(mikIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                                .addGap(143, 143, 143)
                                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                                                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(300, 300, 300))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                                                .addComponent(contentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(160, 160, 160))))
        );
        backgroundPanelLayout.setVerticalGroup(
                backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundPanelLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(goBack, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(pascoIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(mikIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(marcoIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)
                                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)
                                .addComponent(contentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(112, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(backgroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(backgroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

    }// </editor-fold>

    private void goBackActionPerformed(java.awt.event.ActionEvent evt) {
        CardLayout cl = (CardLayout) getParent().getLayout();
        cl.show(getParent(), "MenuGUI");
    }

    // Variables declaration - do not modify
    private javax.swing.JButton goBack;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JPanel marcoIcon;
    private javax.swing.JPanel pascoIcon;
    private javax.swing.JPanel mikIcon;
    private javax.swing.JLabel contentLabel;
    // End of variables declaration
}
