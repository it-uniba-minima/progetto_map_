package org.it.uniba.minima;
import java.lang.Runnable;
import java.io.File;
import javax.sound.sampled.*;

public class myRunnable extends Thread {
    private static Clip clip;
    private static boolean running = false;
    @Override
    public void run() {
        running = true;
        try {
            File file = new File("docs/audio/Avventura-nella-piramide-egizia.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void stopClip() {
        running = false;
        clip.stop();
    }

    public boolean isRunning() {
        return running;
    }
}
