package fr.isen.cir58.teamregalad.regaplay.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;

import fr.isen.cir58.teamregalad.regaplay.audio.services.AudioService;
import fr.isen.cir58.teamregalad.regaplay.receivers.SongClickedReceiver;
import fr.isen.cir58.teamregalad.regaplay.utils.Constants;

/**
 * Created by Thomas Fossati on 04/11/2015.
 */
public class AudioActivity extends AppCompatActivity implements SongClickedReceiver.SongClickedListener {
    private SongClickedReceiver songClickedReceiver;

    private AudioService audioService;
    private Intent playIntent;
    private boolean audioBound = false;
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
    protected void onStart() {
        super.onStart();
        if (playIntent == null) {
            playIntent = new Intent(this, AudioService.class);
            bindService(playIntent, audioConnection, Context.BIND_AUTO_CREATE);
            startService(playIntent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        songClickedReceiver = new SongClickedReceiver();
        registerReceiver(songClickedReceiver, new IntentFilter(Constants.Audio.ACTION_SONG_CLICKED));
        songClickedReceiver.setListener(this);
        if(audioService != null){
            audioService.resumeSong();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(songClickedReceiver);
        songClickedReceiver = null;
        audioService.pauseSong();
    }

    @Override
    public void onSongClicked(long id) {
        audioService.setSong(id);
        audioService.playSong();
    }
    @Override
    protected void onDestroy() {
        stopService(playIntent);
        unbindService(audioConnection);
        audioService = null;
        super.onDestroy();
    }

}