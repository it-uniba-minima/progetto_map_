package org.it.uniba.minima;

import static org.it.uniba.minima.GUI.GameGUI.timerLabelsetTime;

public class Timer extends Thread{
    public static Timer instance;
    public static boolean running = false;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;

    public static Timer getInstance() {
        if (instance == null) {
            instance = new Timer();
        }
        return instance;
    }

    @Override
    public void run() {
        running = true;
        timerLabelsetTime("00:00:00");
        try {
            while (running) {
                Thread.sleep(1000);
                seconds++;
                if (seconds == 60) {
                    seconds = 0;
                    minutes++;
                }
                if (minutes == 60) {
                    minutes = 0;
                    hours++;
                }
                timerLabelsetTime(getTime());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

public String getTime() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public void stopTimer() {
        running = false;
    }

    public void killTimer() {
        running = false;
        instance = null;
    }

    public void startTimer() {
        running = true;
    }

    public void resetTimer() {
        running = false;
        seconds = 0;
        minutes = 0;
    }

    public boolean isRunning() { return running;}
}

