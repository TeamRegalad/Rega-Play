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

import java.util.ArrayList;

import fr.isen.cir58.teamregalad.regaplay.audio.Song;

/**
 * Created by Thomas Fossati on 26/10/2015.
 */
public class AudioServiceThomas extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {

    private final IBinder audioBind = new AudioBinder();
    private MediaPlayer mediaPlayer;
    private ArrayList<Song> songsList;
    private int songsPosition;

    @Override
    public void onCreate() {
        super.onCreate();
        songsPosition = 0;
        mediaPlayer = new MediaPlayer();
        initAudioPlayer();
    }

    public void initAudioPlayer() {
        mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
    }

    public void setSongsList(ArrayList<Song> songs) {
        songsList = songs;
    }

    public void setSong(int songIndex) {
        songsPosition = songIndex;
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
        mediaPlayer.reset();
        Song playSong = songsList.get(songsPosition);
        long currentSong = playSong.getID();
        Uri trackUri = ContentUris.withAppendedId(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, currentSong);
        try {
            mediaPlayer.setDataSource(getApplicationContext(), trackUri);
        } catch (Exception e) {
            Log.e("ADUIO SERVICE", "Error setting data source", e);
        }
        mediaPlayer.prepareAsync();

    }

    public boolean pauseSong() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            return true;
        }
        return false;
    }

    public boolean resumeSong() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            return true;
        }
        return false;
    }

    public int getSongsPosition() {
        return songsPosition;
    }

    public void stopSong() {
        mediaPlayer.stop();
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
        public AudioServiceThomas getService() {
            return AudioServiceThomas.this;
        }
    }
}
