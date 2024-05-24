package org.it.uniba.minima.Boundary;
import org.it.uniba.minima.Control.Parser;
import org.it.uniba.minima.Type.ParserOutput;

public class userInputManager {
    private static String currentInput = "";

    public static synchronized String getCurrentInput() {
        return currentInput;
    }

    public static synchronized void setCurrentInput(String currentInput) {
        userInputManager.currentInput = currentInput;
    }

    public static synchronized boolean isCurrentInputEmpty() {
        return currentInput.isEmpty();
    }

    public static void startInputListener(javax.swing.JTextField userInputField) {
        new Thread(() -> {
            while (true) {
                if (!isCurrentInputEmpty()) {
                    String text = getCurrentInput();
                    outputDisplayManager.displayText(text);
                    Parser parser = new Parser();
                    ParserOutput parsedText = parser.parse(text);
                    outputDisplayManager.displayText(parsedText.toString());
                    setCurrentInput("");
                }
                try {
                    Thread.sleep(100); // Sleep for a short time to reduce CPU usage
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}