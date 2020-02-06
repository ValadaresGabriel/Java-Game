package com.hope.engine;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class Clips {

    private Clip[] clips;

    private int index;

    private int count = 0;

    public Clips(byte[] data, int index) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        if (data == null)
            return;

        this.clips = new Clip[index];
        this.index = index;

        for (int i = 0; i < index; i++) {
            this.clips[i] = AudioSystem.getClip();
            this.clips[i].open(AudioSystem.getAudioInputStream(new ByteArrayInputStream(data)));
        }
    }

    public void play() {
        if (getClips() == null)
            return;

        getClips()[this.count].stop();
        getClips()[this.count].setFramePosition(0);
        getClips()[this.count].start();
        this.count++;

        if (getCount() >= this.index)
            this.count = 0;
    }

    public void loop() {
        if (getClips() == null)
            return;

        getClips()[getCount()].loop(Clip.LOOP_CONTINUOUSLY);
    }

    private Clip[] getClips() {
        return this.clips;
    }

    private int getCount() {
        return this.count;
    }

}
