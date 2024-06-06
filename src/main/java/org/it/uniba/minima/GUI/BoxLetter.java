package org.it.uniba.minima.GUI;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import java.awt.Color;
import java.awt.Font;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * The type Box letter.
 */
public class BoxLetter {
    /**
     * The text field of the box.
     */
    private JTextField textField;

    /**
     * Instantiates a new box letter.
     */
    public BoxLetter() {
        textField = new JTextField(1); // Single character input
        textField.setFont(new Font("Papyrus", Font.BOLD, 30));
        textField.setHorizontalAlignment(JTextField.CENTER);
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new SingleCharDocumentFilter());
    }

    /**
     * Returns the textfield of the box.
     *
     * @return the textfield
     */
    public JTextField getTextField() {
        return textField;
    }

    /**
     * Sets the background color of the box.
     *
     * @param color the color
     */
    protected void setBoxColor(Color color) {
        textField.setBackground(color);
    }

    /**
     * Underclass to filter the input of the textfield to a single character.
     */
    private static class SingleCharDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if ((fb.getDocument().getLength() + string.length()) <= 1 && string.matches("[A-Z]")) {
                super.insertString(fb, offset, string, attr);
            }
        }
    }
}
