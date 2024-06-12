package org.it.uniba.minima.GUI;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Graphics;
import java.util.Arrays;

/**
 * The panel of the hangman game.
 */
public class HangmanGUI extends JPanel {
    /**
     * The hidden text label.
     */
    private JLabel hiddenText;
    /**
     * The guessed letters.
     */
    private final char[] guessedLetters = new char[34];
    /**
     * The background image.
     */
    private final Image backgroundImage;
    /**
     * The instance of the class.
     */
    private static HangmanGUI hangmanGUI;

    /**
     * Constructor of the class.
     */
    public HangmanGUI() {
        backgroundImage = new ImageIcon("src/main/resources/docs/img/hangman.png").getImage();
        initComponents();
        hangmanGUI = this;
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static HangmanGUI getInstance() {
        return hangmanGUI;
    }

    /**
     * Initialize the components.
     */
    private void initComponents() {
        // Create and set the properties of the label
        hiddenText = new JLabel();

        setMaximumSize(new Dimension(440, 400));
        setMinimumSize(new Dimension(440, 400));
        setPreferredSize(new Dimension(440, 400));
        hiddenText.setFont(new Font("Papyrus", Font.BOLD, 24)); // NOI18N
        hiddenText.setFocusable(false);
        hiddenText.setText("<html><center>__ _____ _____ ________ __ _______</center></html>");
        hiddenText.setIconTextGap(1);
        hiddenText.setMaximumSize(new Dimension(250, 301));
        hiddenText.setMinimumSize(new Dimension(250, 301));
        hiddenText.setPreferredSize(new Dimension(250, 301));
        hiddenText.setVerifyInputWhenFocusTarget(false);

        // Create a panel for the background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Create a panel and set the properties for the label
        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        labelPanel.setOpaque(false);
        labelPanel.add(hiddenText, BorderLayout.CENTER);

        backgroundPanel.add(labelPanel, BorderLayout.CENTER);

        // Set the layout of the panel
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backgroundPanel, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backgroundPanel, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
    }

    /**
     * Sets the letter of the text to be displayed singularly.
     *
     * @param phrase        the phrase
     * @param guessedLetter the guessed letter
     */
    public void setLetter(String phrase, char guessedLetter) {
        SwingUtilities.invokeLater(() -> {
            for (int i = 0; i < phrase.length(); i++) {
                if (phrase.charAt(i) == guessedLetter || guessedLetters[i] != 0) {
                    guessedLetters[i] = phrase.charAt(i);
                }
            }
            StringBuilder sb = new StringBuilder();
            for (char letter : guessedLetters) {
                if (letter == guessedLetter) {
                    sb.append(guessedLetter);
                } else if (letter != 0) {
                    sb.append(letter);
                } else if (phrase.charAt(sb.length()) == ' '){
                    sb.append(' ');
                } else {
                    sb.append('_');
                }
            }
            hiddenText.setText("<html><center>" + sb + "</center></html>");
        });
    }

    /**
     * Set the phrase to be displayed.
     *
     * @param phrase the phrase
     */
    public void setPhrase(String phrase) {
        SwingUtilities.invokeLater(() -> hiddenText.setText("<html><center>" + phrase + "</center></html>"));
    }

    /**
     * Reset the guessed letters.
     */
    public void resetGuessedLetters() {
        Arrays.fill(guessedLetters, (char) 0);
        SwingUtilities.invokeLater(() -> hiddenText.setText("<html><center>__ _____ _____ ________ __ _______</center></html>"));
    }
}
