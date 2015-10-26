package fr.isen.cir58.teamregalad.regaplay;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File sdcard = new File("/sdcard1/Musique/Textures");
        Log.d("TestA",String.valueOf(sdcard.exists()));
        File track = new File("/sdcard1/Musique/Textures/2011 Dualism (320 kbps)/03. Reaching Home.mp3");
        Log.d("TestF",track.getPath());
        Log.d("Test", Environment.getExternalStorageDirectory().getAbsolutePath());
        Log.d("Test", String.valueOf(track.exists()));
        Uri trackURI = Uri.fromFile(track);
        MediaPlayer mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try{
            mp.setDataSource(getApplicationContext(), trackURI);
            mp.prepare();
            mp.start();
        } catch (IOException e){
            Log.e("ERROR",e.getMessage());
        }

    }

}
