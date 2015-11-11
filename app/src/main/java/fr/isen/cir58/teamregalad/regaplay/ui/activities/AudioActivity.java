package fr.isen.cir58.teamregalad.regaplay.ui.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import fr.isen.cir58.teamregalad.regaplay.audio.Song;
import fr.isen.cir58.teamregalad.regaplay.audio.services.AudioService;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreHelper;
import fr.isen.cir58.teamregalad.regaplay.receivers.OnSongChangedReceiver;
import fr.isen.cir58.teamregalad.regaplay.receivers.OnSongClickedWithIdReceiver;
import fr.isen.cir58.teamregalad.regaplay.receivers.OnSongClickedWithPathReceiver;
import fr.isen.cir58.teamregalad.regaplay.ui.fragments.PlayerFragment;
import fr.isen.cir58.teamregalad.regaplay.utils.Constants;

/**
 * Created by Thomas Fossati on 04/11/2015.
 */
public class AudioActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener, OnSongClickedWithIdReceiver.OnSongClickedWithIdListener, OnSongClickedWithPathReceiver.OnSongClickedWithPathListener {
    private PlayerFragment playerFragment;
    private AudioService audioService;
    private OnSongClickedWithIdReceiver onSongClickedWithIdReceiver;
    private OnSongClickedWithPathReceiver onSongClickedWithPathReceiver;
    private OnSongChangedReceiver onSongChangedReceiver;
    private Intent playIntent;
    private boolean audioBound = false;
    private ArrayList<Song> playList = new ArrayList<>();
    private Integer currentSongIndex = 0;
    private ServiceConnection audioConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AudioService.AudioBinder binder = (AudioService.AudioBinder) service;
            audioService = binder.getService();
            audioBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            audioBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (playIntent == null) {
            playIntent = new Intent(this, AudioService.class);
            bindService(playIntent, audioConnection, Context.BIND_AUTO_CREATE);
            startService(playIntent);
        }

        onSongChangedReceiver = new OnSongChangedReceiver(playerFragment);
        registerReceiver(onSongChangedReceiver, new IntentFilter(Constants.Audio.ACTION_SONG_CHANGED));
    }

    @Override
    protected void onResume() {
        super.onResume();
        showPlayerFragment();

        onSongClickedWithIdReceiver = new OnSongClickedWithIdReceiver(this);
        registerReceiver(onSongClickedWithIdReceiver, new IntentFilter(Constants.Audio.ACTION_SONG_CLICKED_WITH_ID));
        onSongClickedWithPathReceiver = new OnSongClickedWithPathReceiver(this);
        registerReceiver(onSongClickedWithPathReceiver, new IntentFilter(Constants.Audio.ACTION_SONG_CLICKED_WITH_PATH));

        Constants.PROGRESSBAR_HANDLER = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Integer timeValues[] = (Integer[]) msg.obj;
                playerFragment.getTextBufferDuration().setText(playerFragment.getDuration(timeValues[0]));
                playerFragment.getTextDuration().setText(playerFragment.getDuration(timeValues[1]));
                playerFragment.getProgressBar().setProgress(timeValues[2]);
            }
        };
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(onSongClickedWithIdReceiver);
        onSongClickedWithIdReceiver = null;
        unregisterReceiver(onSongClickedWithPathReceiver);
        onSongClickedWithPathReceiver = null;
    }

    protected void onDestroy() {
        stopService(playIntent);
        unbindService(audioConnection);
        audioService = null;
        unregisterReceiver(onSongChangedReceiver);
        onSongChangedReceiver = null;
        super.onDestroy();
    }

    public void pauseSong() {
        audioService.pauseSong();
    }

    public void playSong() {
        if (audioService.isSongPaused()) {
            audioService.pauseSong();
        } else {
            audioService.playSong();
        }
    }

    public void stopSong() {
        audioService.stopSong();
    }

    public void previousSong() {
        if (isTherePreviousSong()) {
            currentSongIndex--;
            songChanged();
        }
    }

    public void nextSong() {
        if (isThereNextSong()) {
            currentSongIndex++;
            songChanged();
        }
    }

    public void songChanged() {
        Song song = getCurrentSong();
        audioService.setSong(song.getID());
        sendBroadcastSongChanged(song);
        playSong();
    }

    public void sendBroadcastSongChanged(Song song) {
        Intent intent = new Intent(Constants.Audio.ACTION_SONG_CHANGED);
        Bundle extras = new Bundle();
        extras.putParcelable(Constants.Audio.ACTION_SONG_CHANGED_SONG, song);
        intent.putExtras(extras);
        sendBroadcast(intent);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        nextSong();
    }

    protected void commitPlayerFragment(int containerViewId) {
        playerFragment = new PlayerFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(containerViewId, playerFragment);
        transaction.commit();
    }

    protected void showPlayerFragment() {
        if (audioService != null && audioService.isPlaying) {
            playerFragment.updateInfos();
        }
    }

    protected void hidePlayerFragment() {
        getSupportFragmentManager().beginTransaction()
                .hide(playerFragment)
                .commit();
    }

    public AudioService getAudioService() {
        return audioService;
    }

    @Override
    public void onSongClickedWithId(Long id) {
        playList.clear();
        Song clickedSong = MediaStoreHelper.getSong(id);
        playList.add(clickedSong);
        songChanged();
    }

    @Override
    public void OnSongClickedWithPath(String path) {
        playList.clear();
        Song clickedSong = MediaStoreHelper.getSong(path);
        playList.add(clickedSong);
        songChanged();
    }

    public Song getCurrentSong() {
        return playList.get(currentSongIndex);
    }

    public Boolean isThereNextSong() {
        return (currentSongIndex + 1 < playList.size());
    }

    public Boolean isTherePreviousSong() {
        return (currentSongIndex > 0);
    }
}
