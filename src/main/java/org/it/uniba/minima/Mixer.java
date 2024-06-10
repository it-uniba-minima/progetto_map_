package org.it.uniba.minima;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import static org.it.uniba.minima.GUI.GameGUI.musicButtonSetTextGame;
import static org.it.uniba.minima.GUI.MenuGUI.musicButtonSetTextMenu;

/**
 * The class that manages the audio.
 */
public class Mixer extends Thread {
    /**
     * The clip of audio.
     */
    private static Clip clip;
    /**
     * The running status of the mixer.
     */
    private static boolean running = false;
    /**
     * The instance of the mixer.
     */
    private static Mixer instance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Mixer getInstance()  {
        if (instance == null) {
            instance = new Mixer();
        }
        return instance;
    }

    /**
     * Overrides the run method of the thread.
     */
    @Override
    public void run() {
        running = true;
        try {
            File file = new File("src/main/resources/docs/audio/Desert_Menu-newSMB_DS.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Starts the audio clip.
     */
    public static void startClip() {
        running = true;
        reverseIcones();
        clip.start();
    }

    /**
     * Stops the audio clip.
     */
    public static void stopClip() {
        running = false;
        reverseIcones();
        clip.stop();
    }

    /**
     * Returns the running status of the mixer.
     *
     * @return the running status
     */
    public static boolean isRunning() {
        return running;
    }

    /**
     * Changes the icons of the music buttons.
     */
    public static void reverseIcones() {
        if (!running) {
            musicButtonSetTextGame("🔇");
            musicButtonSetTextMenu("🔇");
        } else {
            musicButtonSetTextGame("🔊");
            musicButtonSetTextMenu("🔊");
        }
    }
}
