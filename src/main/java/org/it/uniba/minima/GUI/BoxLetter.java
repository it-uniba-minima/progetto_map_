package org.it.uniba.minima.GUI;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BoxLetter {
    private int row;
    private int col;
    private JTextField textField;

    public BoxLetter(int row, int col, Wordle parent) {
        this.row = row;
        this.col = col;
        textField = new JTextField(1); // Single character input
        textField.setFont(new Font("Papyrus", Font.BOLD, 30));
        textField.setHorizontalAlignment(JTextField.CENTER);
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new SingleCharDocumentFilter());
    }
    public JTextField getBox() {
        return textField;
    }

    protected void setBox( Color color) {
        textField.setBackground(color);
    }
    private static class SingleCharDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if ((fb.getDocument().getLength() + string.length()) <= 1 && string.matches("[A-Z]")) {
                super.insertString(fb, offset, string, attr);
            }
        }
        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if ((fb.getDocument().getLength() - length + text.length()) <= 1 && text.matches("[A-Z]")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }
}
