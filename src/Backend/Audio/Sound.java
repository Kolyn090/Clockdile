package Backend.Audio;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Sound {

    private Clip clip;

    public Sound() {
        setFile();
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

    public static void main(String[] args) throws Exception
    {
        AudioInputStream ais = AudioSystem.getAudioInputStream(new File("lib/star-trek-the-next-generation-theme.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(ais);

        JButton button = new JButton("Play Clip");
        button.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                FloatControl control = ((FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN));
                int vol = 10;
                control.setValue(20f * (float) Math.log10(vol) - 40);
                clip.setFramePosition(0);
                clip.start();
            }
        });

        JOptionPane.showMessageDialog(null, button);
    }

    public Clip getClip() {
        return clip;
    }
}
