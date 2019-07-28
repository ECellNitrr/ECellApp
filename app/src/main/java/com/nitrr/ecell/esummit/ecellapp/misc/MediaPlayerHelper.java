package com.nitrr.ecell.esummit.ecellapp.misc;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

public class MediaPlayerHelper {

    private MediaPlayer mediaPlayer = null;
    private Context context;

    public MediaPlayerHelper(Context context) {
        this.context = context;
    }

    public void play(Uri uri) throws IOException {
        if (mediaPlayer == null)
            mediaPlayer = new MediaPlayer();

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDataSource(context, uri);
        mediaPlayer.prepare();
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
