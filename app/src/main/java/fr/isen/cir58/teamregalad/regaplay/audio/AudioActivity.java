package fr.isen.cir58.teamregalad.regaplay.audio;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import java.util.ArrayList;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.audio.services.AudioServiceThomas;

public class AudioActivity extends Activity {
    private AudioServiceThomas audioService;
    private Intent playIntent;
    private boolean audioBound = false;
    private ArrayList<Song> songsList;
    private ServiceConnection audioConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AudioServiceThomas.AudioBinder binder = (AudioServiceThomas.AudioBinder) service;
            audioService = binder.getService();
            audioService.setSongsList(songsList);
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
        setContentView(R.layout.regaplay_lists_activity);

        songsList = MetaDataFetcher.getAudioFilesFromMediaStore(getContentResolver());

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (playIntent == null) {
            playIntent = new Intent(this, AudioServiceThomas.class);
            bindService(playIntent, audioConnection, Context.BIND_AUTO_CREATE);
            startService(playIntent);
        }
    }

    public void songPicked(View view) {
        audioService.setSong(Integer.parseInt(view.getTag().toString()));
        audioService.playSong();

    }

    @Override
    protected void onDestroy() {
        stopService(playIntent);
        audioService = null;
        super.onDestroy();
    }
}
