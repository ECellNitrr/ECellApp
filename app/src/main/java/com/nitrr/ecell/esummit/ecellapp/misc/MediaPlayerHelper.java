package com.nitrr.ecell.esummit.ecellapp.misc;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import com.nitrr.ecell.esummit.ecellapp.R;

import java.io.IOException;

public class MediaPlayerHelper {

    private MediaPlayer mediaPlayer = null;
    private Context context;

    public MediaPlayerHelper(Context context) {
        this.context = context;
    }

    public void play() {
        if (mediaPlayer == null)
            mediaPlayer = MediaPlayer.create(context, R.raw.music);

        mediaPlayer.reset();
        mediaPlayer.start();
    }

    public void stop() {
        if (mediaPlayer != null && mediaPlayer.isPlaying())
            mediaPlayer.stop();
    }

    public void pause() {
        if (mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }
}
