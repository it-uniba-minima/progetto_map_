package org.it.uniba.minima.Boundary;
import org.it.uniba.minima.Control.UserInputFlow;
import javax.swing.JTextField;

/**
 * The class that manages the user input and calls the game flow.
 */
public class UserInputManager {
    /**
     * The current input.
     */
    private static String currentInput = "";

    /**
     * Gets the current input and resets it.
     *
     * @return the current input
     */
    public static synchronized String getCurrentInput() {
        String temp = currentInput;
        currentInput = "";
        return temp;
    }

    /**
     * Sets the current input.
     *
     * @param currentInput the current input
     */
    public static synchronized void setCurrentInput(String currentInput) {
        UserInputManager.currentInput = currentInput;
    }

    /**
     * Checks if the current input is empty.
     *
     * @return true, if the current input is empty, false otherwise
     */
    public static synchronized boolean isCurrentInputEmpty() {
        return currentInput.isEmpty();
    }

    /**
     * Starts the input listener as a new thread.
     * The input listener checks if the user has inserted a new input and calls the game flow.
     */
    public static void startInputListener() {
        new Thread(() -> {
            while (true) {
                if (!isCurrentInputEmpty()) {
                    UserInputFlow.gameFlow(getCurrentInput());
                }
                try {
                    Thread.sleep(100); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
