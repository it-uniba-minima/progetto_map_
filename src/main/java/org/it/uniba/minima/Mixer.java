package org.it.uniba.minima;
import java.io.File;
import javax.sound.sampled.*;

public class Mixer extends Thread {
    private static Clip clip;
    private static boolean running = false;
    private static Mixer instance;

    public static Mixer getInstance()  {
        if (instance == null) {
            instance = new Mixer();
        }
        return instance;
    }

    @Override
    public void run() {
        // TODO: Change the placeholder audio file with the actual audio file
        running = true;
        try {
            File file = new File("docs/audio/Desert_Menu-newSMB_DS.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void startClip() {
        running = true;
        clip.start();
    }

    public static void stopClip() {
        running = false;
        clip.stop();
    }

    public static boolean isRunning() {
        return running;
    }
}
