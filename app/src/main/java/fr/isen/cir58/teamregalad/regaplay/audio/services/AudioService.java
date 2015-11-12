package fr.isen.cir58.teamregalad.regaplay.audio.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.audio.Song;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreHelper;
import fr.isen.cir58.teamregalad.regaplay.ui.activities.AudioActivity;
import fr.isen.cir58.teamregalad.regaplay.utils.Constants;

/**
 * Created by Thomas Fossati on 26/10/2015.
 */
public class AudioService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {

    private static Timer timer;
    private final IBinder audioBind = new AudioBinder();
    private boolean songPaused;
    private MediaPlayer mediaPlayer;
    private Uri songUri;
    private Long songId;
    public Song song;
    private Notification notification;
    public Boolean isPlaying = false;
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mediaPlayer.isPlaying()) {

                int progress = (mediaPlayer.getCurrentPosition() * 100) / mediaPlayer.getDuration();
                Integer timeValues[] = new Integer[3];
                timeValues[0] = mediaPlayer.getCurrentPosition();
                timeValues[1] = mediaPlayer.getDuration();
                timeValues[2] = progress;

                Constants.PROGRESSBAR_HANDLER.sendMessage(Constants.PROGRESSBAR_HANDLER.obtainMessage(0, timeValues));
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        timer = new Timer();
        initAudioPlayer();
    }

    public void initAudioPlayer() {
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
    }

    public void setSongId(long songId) {
        this.songUri = ContentUris.withAppendedId(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, songId);
        this.songId = songId;
    }

    public void setSong(Song song) {
        this.song = song;
        setSongId(song.getID());
    }

    public void setSongId(String path) {
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
                Log.e("AudioService", "Error setting data source", e);
            }
            mediaPlayer.prepareAsync();
            songPaused = false;
            isPlaying = true;
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
        isPlaying = false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Toast.makeText(RegaPlayApplication.getContext(), "FINISHED", Toast.LENGTH_SHORT).show();
    }

    public void setSongAtTimeStamp(int value) {
        mediaPlayer.seekTo((value * mediaPlayer.getDuration()) / 100);
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        timer.scheduleAtFixedRate(new MainTask(), 0, 100);
    }

    public void setAsForeground() {
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(getApplicationContext(), AudioActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Notification notification = builder.setSmallIcon(R.drawable.defaultpic)
                .setContentTitle("Regaplay")
                .setContentText(MediaStoreHelper.getSong(songId).getTitle())
                .setAutoCancel(true).build();
        startForeground(0, notification);
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public class AudioBinder extends Binder {
        public AudioService getService() {
            return AudioService.this;
        }
    }

    private class MainTask extends TimerTask {
        public void run() {
            handler.sendEmptyMessage(0);
            // handler.postDelayed(this, 50);
        }
    }
}
