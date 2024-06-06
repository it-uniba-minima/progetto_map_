package org.it.uniba.minima;
import java.util.Timer;
import java.util.TimerTask;
import static org.it.uniba.minima.GUI.GameGUI.timerLabelSetTime;

/**
 * The class that manages the timer.
 */
public class TimerManager {
    /**
     * The instance of the TimerManager.
     */
    public static TimerManager instance;
    /**
     * The running status of the timer.
     */
    public static boolean running = false;
    /**
     * The seconds.
     */
    static int seconds = 0;
    /**
     * The minutes.
     */
    static int minutes = 0;
    /**
     * The hours.
     */
    static int hours = 0;
    /**
     * The timer instance.
     */
    static Timer timer;

    /**
     * Gets instance and starts the timer.
     *
     * @return the instance
     */
    public static synchronized TimerManager getInstance() {
        if (instance == null && !running) {
            instance = new TimerManager();
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
        return instance;
    }

    /**
     * Start timer.
     */
    public void startTimer() {
        running = true;
        timerLabelSetTime("00:00:00");
    }

    /**
     * Gets the formatted time.
     *
     * @return the time
     */
    public static String getTime() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * Stops timer.
     */
    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
        running = false;
    }

    /**
     * Kills timer.
     */
    public void killTimer() {
        if (timer != null) {
            timer.cancel();
        }
        timerLabelSetTime("00:00:00");
        seconds = 0;
        minutes = 0;
        hours = 0;
        running = false;
        instance = null;
    }
}
