package fr.isen.cir58.teamregalad.regaplay.audio.services;

import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Thomas Fossati on 26/10/2015.
 */
public class AudioService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {

    private final IBinder audioBind = new AudioBinder();
    boolean songPaused;
    private MediaPlayer mediaPlayer;
    private Uri songUri;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        initAudioPlayer();
    }

    public void initAudioPlayer() {
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
    }

    public void setSong(long songId) {
        this.songUri = ContentUris.withAppendedId(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, songId);
    }

    public void setSong(String path) {
        this.songUri = Uri.parse(path);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return audioBind;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mediaPlayer.stop();
        mediaPlayer.release();
        return false;
    }

    public void playSong() {
        if (songUri != null) {
            mediaPlayer.reset();
            try {
                mediaPlayer.setDataSource(getApplicationContext(), songUri);
            } catch (Exception e) {
                Log.e("AUDIO SERVICE", "Error setting data source", e);
            }
            mediaPlayer.prepareAsync();
            songPaused = false;
        }
    }

    public void pauseSong() {
        if (songPaused) {
            mediaPlayer.start();
            songPaused = false;

        } else {
            mediaPlayer.pause();
            songPaused = true;
        }
    }

    public boolean isSongPlaying() {
        return mediaPlayer.isPlaying();
    }

    public boolean isSongPaused() {
        return songPaused;
    }

    public void stopSong() {
        mediaPlayer.stop();
        songPaused = false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    public class AudioBinder extends Binder {
        public AudioService getService() {
            return AudioService.this;
        }
    }

}
