package org.it.uniba.minima;
import java.io.File;
import javax.sound.sampled.*;
import java.util.HashMap;
import static org.it.uniba.minima.GUI.GameGUI.musicButtonSetTextGame;
import static org.it.uniba.minima.GUI.MenuGUI.musicButtonSetTextMenu;

public class Mixer extends Thread {
    private static Clip[] clips;
    private static int currentClip;
    private static boolean running = false;
    private static HashMap<String, Integer> roomToClipIndex;
    private static Mixer instance;

    private Mixer() {
        clips = new Clip[8];
        // menu music
        loadClip(0, "src/main/resources/docs/audio/Desert_Menu-newSMB_DS.wav");
        // desert music
        loadClip(1, "src/main/resources/docs/audio/DesertTheme-SMBWonder.wav");
        // minigames music
        loadClip(2, "src/main/resources/docs/audio/4S-Ultrakill.wav");
        // first four rooms music
        loadClip(3, "src/main/resources/docs/audio/Agrabah-KH1.wav");
        // eye room music
        loadClip(4, "src/main/resources/docs/audio/M64-DesertLevel.wav");
        // pharaon room starting music
        loadClip(5, "src/main/resources/docs/audio/TLOZ_TwPr-GerudoDesert.wav");
        // cake room music
        loadClip(6, "src/main/resources/docs/audio/Yoshi-DesertLevel.wav");
        // hole room music
        loadClip(7, "src/main/resources/docs/audio/MKDS_DesertHill.wav");

        // map room to clip instantiation
        roomToClipIndex = new HashMap<>();
        roomToClipIndex.put("Menu", 0);
        roomToClipIndex.put("Desert", 1);
        roomToClipIndex.put("WordleGUI", 2);
        roomToClipIndex.put("Mattonelle", 2);
        roomToClipIndex.put("Impiccato", 2);
        roomToClipIndex.put("Stanza6", 2);
        roomToClipIndex.put("Stanza1", 3);
        roomToClipIndex.put("Stanza2", 3);
        roomToClipIndex.put("Stanza3", 3);
        roomToClipIndex.put("Stanza4", 3);
        roomToClipIndex.put("Stanza5", 4);
        roomToClipIndex.put("Stanza10", 5);
        roomToClipIndex.put("Stanza7", 7);
        roomToClipIndex.put("Stanza8", 6);
        roomToClipIndex.put("Stanza9", 3);

        //TODO: INSERIRE DELLE ENTRY PER I DUE FINALI!!!
    }

    private void loadClip(int index, String filePath) {
        try {
            File file = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clips[index] = AudioSystem.getClip();
            clips[index].open(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Mixer getInstance() {
        if (instance == null) {
            instance = new Mixer();
        }
        return instance;
    }

    @Override
    public void run() {
        running = true;
        try {
            if (clips[0] != null) {
                clips[0].start();
                clips[0].loop(Clip.LOOP_CONTINUOUSLY);
                currentClip = 0;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void startClip() {
        if (clips[currentClip] != null) {
            running = true;
            reverseIcons();
            clips[currentClip].start();
        }
    }

    public static void stopClip() {
        if (clips[currentClip] != null) {
            running = false;
            reverseIcons();
            clips[currentClip].stop();
        }
    }

    private static void changeClip(int i) {
        if (clips[currentClip] != null) {
            clips[currentClip].stop();
        }
        if (clips[i] != null) {
            clips[i].start();
            clips[i].loop(Clip.LOOP_CONTINUOUSLY);
            currentClip = i;
        }
    }

    public static boolean isRunning() {
        return running;
    }

    public static void reverseIcons() {
        if (!running) {
            musicButtonSetTextGame("🔇");
            musicButtonSetTextMenu("🔇");
        } else {
            musicButtonSetTextGame("🔊");
            musicButtonSetTextMenu("🔊");
        }
    }

    /*
     * Changes the music based on the room.
     */
    public static void changRoomMusic(String room) {
        changeClip(roomToClipIndex.getOrDefault(room, 3));
    }
}