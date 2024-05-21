package org.it.uniba.minima;
import java.util.Timer;
import java.util.TimerTask;
import static org.it.uniba.minima.GUI.GameGUI.timerLabelSetTime;

public class TimerManager {
    public static TimerManager instance;
    public static boolean running = false;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    Timer timer;

    public static synchronized TimerManager getInstance() {
        if (instance == null && !running) {
            instance = new TimerManager();
        }
        return instance;
    }

    public void startTimer() {
        running = true;
        timerLabelSetTime("00:00:00");
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seconds++;
                if (seconds == 60) {
                    seconds = 0;
                    minutes++;
                }
                if (minutes == 60) {
                    minutes = 0;
                    hours++;
                }
                timerLabelSetTime(getTime());
            }
        }, 1000, 1000);
    }

    public String getTime() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
        running = false;
    }

    public void killTimer() {
        if (timer != null) {
            timer.cancel();
        }
        timerLabelSetTime("00:00:00");
        running = false;
        instance = null;
    }
}