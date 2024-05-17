package org.it.uniba.minima;
import java.io.File;
import javax.sound.sampled.*;

public class myRunnable extends Thread {
    private static Clip clip;
    private static boolean running = false;
    private static myRunnable instance;

    public static myRunnable getInstance()  {
        if (instance == null) {
            instance = new myRunnable();
        }
        return instance;
    }

    @Override
    public void run() {
        // TODO: Change the placeholder audio file with the actual audio file
        running = true;
        try {
            File file = new File("docs/audio/Marco.wav");
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
