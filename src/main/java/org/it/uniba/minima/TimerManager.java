package org.it.uniba.minima;
import org.it.uniba.minima.GUI.GameGUI;
import java.util.Timer;
import java.util.TimerTask;

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
    static int seconds;
    /**
     * The minutes.
     */
    static int minutes;
    /**
     * The hours.
     */
    static int hours;
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
            TimerTask taskTimer = new TimerTask() {
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
                    GameGUI.timerLabelSetTime(getTime());
                }
            };
            timer.scheduleAtFixedRate(taskTimer, 1000, 1000);
        }
        return instance;
    }

    /**
     * Start timer.
     */
    public void startTimer(String time) {
        running = true;
        if (time.equals("00:00:00")) {
            seconds = 0;
            minutes = 0;
            hours = 0;
            GameGUI.timerLabelSetTime("00:00:00");
        } else {

            String[] split = time.trim().split(":");
            hours = Integer.parseInt(split[0]);
            minutes = Integer.parseInt(split[1]);
            seconds = Integer.parseInt(split[2]);
            GameGUI.timerLabelSetTime(time.trim());
        }
    }

    /**
     * Gets the formatted time.
     *
     * @return the time
     */
    public static String getTime() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
