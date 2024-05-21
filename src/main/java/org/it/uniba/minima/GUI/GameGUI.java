/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.it.uniba.minima.GUI;

import org.it.uniba.minima.Mixer;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

/**
 *
 * @author miche
 */
public class GameGUI extends javax.swing.JPanel {

    /**
     * Creates new form GameGUI
     */
    public GameGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        toolBar = new javax.swing.JToolBar();
        goBackButton = new javax.swing.JButton();
        saveGameButton = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();
        musicButton = new javax.swing.JButton();
        userInputField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        inventoryTextArea = new javax.swing.JTextArea();
        imagePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        displayTextPane = new javax.swing.JTextPane();

        setPreferredSize(new java.awt.Dimension(800, 600));

        toolBar.setBorderPainted(false);
        toolBar.setFloatable(false);

        goBackButton.setText("Indietro");
        goBackButton.setFocusable(false);
        goBackButton.setHorizontalTextPosition(SwingConstants.CENTER);
        goBackButton.setVerticalTextPosition(SwingConstants.CENTER);
        goBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goBackButtonActionPerformed(evt);
            }
        });
        toolBar.add(goBackButton);

        saveGameButton.setText("Salva");
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

        helpButton.setText("?");
        helpButton.setFocusable(false);
        helpButton.setHorizontalTextPosition(SwingConstants.CENTER);
        helpButton.setVerticalTextPosition(SwingConstants.CENTER);
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });
        toolBar.add(helpButton);

        musicButton.setText("🔊");
        musicButton.setFocusable(false);
        musicButton.setHorizontalTextPosition(SwingConstants.CENTER);
        musicButton.setVerticalTextPosition(SwingConstants.CENTER);
        musicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                musicButtonActionPerformed(evt);
            }
        });
        toolBar.add(musicButton);

        inventoryTextArea.setEditable(false);
        inventoryTextArea.setColumns(20);
        inventoryTextArea.setRows(5);
        inventoryTextArea.setAutoscrolls(false);
        inventoryTextArea.setEnabled(false);
        inventoryTextArea.setFocusable(false);
        jScrollPane2.setViewportView(inventoryTextArea);

        imagePanel.setPreferredSize(new Dimension(460, 400));
        imagePanel.setMaximumSize(new Dimension(460, 400));
        imagePanel.setBackground(new java.awt.Color(100, 100, 100));

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
                imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        imagePanelLayout.setVerticalGroup(
                imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 463, Short.MAX_VALUE)
        );

        displayTextPane.setEditable(false);
        displayTextPane.setFocusable(false);
        displayTextPane.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eu rutrum velit. Mauris ac sagittis nunc. Sed id lacinia elit. Vivamus vel tellus congue, scelerisque lectus id, bibendum lacus. Vivamus eget eros arcu. Aenean turpis orci, malesuada in interdum ut, euismod ut elit. Proin tincidunt dui id velit interdum tincidunt. Sed dui elit, sagittis at nulla id, venenatis consequat nibh. Donec dolor risus, mollis in augue sit amet, lacinia blandit risus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Cras rhoncus sollicitudin eros, sit amet blandit augue lacinia scelerisque. Curabitur placerat lorem efficitur mauris volutpat pulvinar.\n" +
                "\n" +
                "In varius, sapien vitae vestibulum molestie, leo urna auctor diam, a tincidunt purus lectus vitae dolor. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi sed leo non metus auctor bibendum vestibulum quis purus. Curabitur nec vehicula dolor, non pretium leo. Nam quis nisl condimentum sapien malesuada gravida. Phasellus finibus eget justo id egestas. Nam quis libero vel ligula sodales rutrum. In hac habitasse platea dictumst. Phasellus at libero risus. Nam in finibus neque. Pellentesque pretium, orci et sollicitudin scelerisque, nulla dui molestie enim, nec bibendum leo risus eu arcu.");
        jScrollPane1.setViewportView(displayTextPane);
        jScrollPane1.setPreferredSize(new Dimension(340, 550));
        jScrollPane1.setMaximumSize(new Dimension(340, 550));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(toolBar, 800, 800, 800)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(userInputField, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                                        .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
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
                                                .addComponent(userInputField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
    }// </editor-fold>

    private void saveGameButtonActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void goBackButtonActionPerformed(java.awt.event.ActionEvent evt) {
        CardLayout cl = (CardLayout) getParent().getLayout();
        cl.show(getParent(), "MenuGUI");
    }

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {
        HelpGUI helpGUI = HelpGUI.getInstance();
        helpGUI.setVisible(true);
    }

    private void musicButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if(Mixer.isRunning()) {
            musicButton.setText("🔇");
            Mixer.stopClip();
        } else {
            musicButton.setText("🔊");
            Mixer.startClip();
        }
    }


    // Variables declaration - do not modify
    private javax.swing.JButton goBackButton;
    private javax.swing.JButton saveGameButton;
    private javax.swing.JButton helpButton;
    private javax.swing.JButton musicButton;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea inventoryTextArea;
    private javax.swing.JTextField userInputField;
    private javax.swing.JTextPane displayTextPane;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration
}
