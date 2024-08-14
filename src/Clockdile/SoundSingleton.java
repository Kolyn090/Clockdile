package Clockdile;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundSingleton {
    private Clip clip;

    private static SoundSingleton instance;
    private SoundSingleton() {
        setFile();
    }
    public static synchronized SoundSingleton instance() {
        if (instance == null) {
            instance = new SoundSingleton();
        }
        return instance;
    }

    private void setFile() {
        String soundFile = "lib/star-trek-the-next-generation-theme.wav";
        File file = new File(soundFile);
        AudioInputStream stream = null;
        try {
            stream = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        try {
            clip = AudioSystem.getClip();
            try {
                clip.open(stream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    private void adjustSoundVolume(int volume) {
        ((FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(20f * (float) Math.log10(volume) - 40);
    }

    public void play(int volume) {
        adjustSoundVolume(volume);
        clip.setFramePosition(0);
        clip.start();
    }

    public void reset() {
        clip.stop();
    }
}
