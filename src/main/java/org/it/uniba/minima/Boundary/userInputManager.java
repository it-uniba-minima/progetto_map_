package org.it.uniba.minima.Boundary;
import org.it.uniba.minima.Control.userInputFlow;

public class userInputManager {
    private static String currentInput = "";

    public static synchronized String getCurrentInput() {
        String temp = currentInput;
        currentInput = "";
        return temp;
    }

    public static synchronized void setCurrentInput(String currentInput) {
        userInputManager.currentInput = currentInput;
    }

    public static synchronized boolean isCurrentInputEmpty() {
        return currentInput.isEmpty();
    }

    /*
    public static void resetCurrentInput() {
        currentInput = "";
    }
    */

    public static void startInputListener(javax.swing.JTextField userInputField) {
        new Thread(() -> {
            while (true) {
                if (!isCurrentInputEmpty()) {
                    userInputFlow.GameFlow(getCurrentInput());
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