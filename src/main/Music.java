package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Music {

    private Clip clip;
    private boolean isPlaying = false;
    private boolean isPaused = false;
    private long clipTimePosition = 0;

    public void playMusic(String location) {
        try {
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }

            File musicPath = new File(location);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.setMicrosecondPosition(clipTimePosition);
                clip.start();
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float dB = (float) (Math.log(0.5) / Math.log(10.0) * 20.0);
                gainControl.setValue(dB);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                isPlaying = true;
                isPaused = false;
            } else {
                System.out.println("Can't find file");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void playSfx(String location) {
        try {
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }

            File musicPath = new File(location);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.setMicrosecondPosition(clipTimePosition);
                clip.start();
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float dB = (float) (Math.log(0.2) / Math.log(10.0) * 20.0);
                gainControl.setValue(dB);
            } else {
                System.out.println("Can't find file");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void pauseMusic() {
        if (clip != null && clip.isRunning()) {
            clipTimePosition = clip.getMicrosecondPosition();
            clip.stop();
            isPlaying = false;
            isPaused = true;
        }
    }

    public void resumeMusic() {
        if (clip != null && isPaused) {
            clip.setMicrosecondPosition(clipTimePosition);
            clip.start();
            isPlaying = true;
            isPaused = false;
        }
    }

    public void stopMusic() {
        if (clip != null) {
            clip.stop();
            clip.close();
            isPlaying = false;
            isPaused = false;
            clipTimePosition = 0;
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
