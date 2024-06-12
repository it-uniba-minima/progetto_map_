package org.it.uniba.minima.Util;
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
    private static TimerManager instance;
    /**
     * The running status of the timer.
     */
    private static boolean running = false;
    /**
     * The seconds.
     */
    private static int seconds;
    /**
     * The minutes.
     */
    private static int minutes;
    /**
     * The hours.
     */
    private static int hours;
    /**
     * The constants.
     */
    private static final int SECONDS_IN_MINUTE = 60;
    private static final int MINUTES_IN_HOUR = 60;
    private static final int DELAY = 1000;
    private static final int PERIOD = 1000;

    /**
     * Gets instance and starts the timer.
     *
     * @return the instance
     */
    public static synchronized TimerManager getInstance() {
        if (instance == null && !running) {
            instance = new TimerManager();
            Timer timer = new Timer();
            TimerTask taskTimer = new TimerTask() {
                @Override
                public void run() {
                    seconds++;
                    if (seconds == SECONDS_IN_MINUTE) {
                        seconds = 0;
                        minutes++;
                    }
                    if (minutes == MINUTES_IN_HOUR) {
                        minutes = 0;
                        hours++;
                    }
                    GameGUI.timerLabelSetTime(getTime());
                }
            };
            timer.scheduleAtFixedRate(taskTimer, DELAY, PERIOD);
        }
        return instance;
    }

    /**
     * Start timer.
     */
    public void startTimer(final String time) {
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
