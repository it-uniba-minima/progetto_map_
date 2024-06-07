package org.it.uniba.minima.Boundary;
import org.it.uniba.minima.GUI.GameGUI;
import java.awt.*;

/**
 * The class that manages the text to display on the text pane.
 */
public class OutputDisplayManager {
    /**
     * The font metrics of the text pane.
     */
    private static final FontMetrics fontMetrics = GameGUI.getTextPaneFontMetrics();
    /**
     * The maximum width of the text pane.
     */
    private static final int maxWidth = GameGUI.getTextPaneWidth();

    /**
     * Displays the text on the panel.
     *
     * @param text the text to display
     */
    public static void displayText(String text) {
        String formattedText = formatText(text);

        GameGUI.displayTextPaneSetText(formattedText);
    }

    /**
     * Splits the string into words and adds a new line when the width is exceeded.
     * The width is calculated based on the font metrics.
     * If the width of a word is greater than the maximum width, the word is split too.
     *
     * @param text the text to format
     * @return the formatted text
     */
    private static String formatText(String text) {
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
