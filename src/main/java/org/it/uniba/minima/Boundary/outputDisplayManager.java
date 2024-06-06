package org.it.uniba.minima.Boundary;

import org.it.uniba.minima.GUI.GameGUI;

import java.awt.*;

/**
 * The type Output display manager.
 */
public class outputDisplayManager {
    private static final FontMetrics fontMetrics = GameGUI.getTextPaneFontMetrics();
    private static final int maxWidth = GameGUI.getTextPaneWidth();

    /**
     * Display text.
     *
     * @param text the text
     */
    public static void displayText(String text) {
        String formattedText = splitString(text);
        GameGUI.displayTextPaneSetText(formattedText);
    }

    private static String splitString(String text) {
        StringBuilder result = new StringBuilder();
        String[] words = text.split(" ");

        for (String word : words) {
            StringBuilder line = new StringBuilder();
            for (char c : word.toCharArray()) {
                if (fontMetrics.stringWidth(line.toString() + c + fontMetrics.charWidth('-')) > maxWidth) {
                    result.append(line).append("-\n");
                    line.setLength(0);
                }
                line.append(c);
            }
            result.append(line).append(" ");
        }

        return result.toString();
    }
}