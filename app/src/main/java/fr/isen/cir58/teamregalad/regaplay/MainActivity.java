package fr.isen.cir58.teamregalad.regaplay;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Selection;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import fr.isen.cir58.teamregalad.regaplay.audio.MetaDataFetcher;
import fr.isen.cir58.teamregalad.regaplay.audio.Song;
import fr.isen.cir58.teamregalad.regaplay.audio.services.AudioService;

public class MainActivity extends Activity implements View.OnClickListener {
    private AudioService audioService;
    private Intent playIntent;
    private boolean audioBound = false;
    private ArrayList<Song> songsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songsList = MetaDataFetcher.getAudioFilesFromMediaStore(getContentResolver());


        findViewById(R.id.playButton).setOnClickListener(this);
        findViewById(R.id.stopButton).setOnClickListener(this);
        findViewById(R.id.previousButton).setOnClickListener(this);
        findViewById(R.id.nextButton).setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (playIntent == null) {
            playIntent = new Intent(this, AudioService.class);
            bindService(playIntent, audioConnection, Context.BIND_AUTO_CREATE);
            startService(playIntent);
        }
    }

    private ServiceConnection audioConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AudioService.AudioBinder binder = (AudioService.AudioBinder) service;
            audioService = binder.getService();
            audioService.setSongsList(songsList);
            audioBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            audioBound = false;
        }
    };

    public void songPicked() {
        int firstSongPost = songsList.size() - 1;
        audioService.setSong(firstSongPost);
        ((TextView) findViewById(R.id.track)).setText(songsList.get(firstSongPost).getTitle() + " - " + songsList.get(firstSongPost).getArtist());
        audioService.playSong();
    }

    @Override
    protected void onDestroy() {
        stopService(playIntent);
        audioService = null;
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.playButton:

                songPicked();
                Button b = (Button) v;
                /*Bitmap bitmap = songsList.get(audioService.getSongsPosition()).getAlbumArt();
                if(bitmap != null){
                    ((ImageView) findViewById(R.id.imageView)).setImageBitmap(bitmap);
                }*/
                File file = new File(songsList.get(audioService.getSongsPosition()).getAlbumArtPath());
                Log.d("test", String.valueOf(file.exists()));
                Picasso.with(RegaPlayApplication.getContext()).load(file).into(((ImageView) findViewById(R.id.imageView)));


                if (b.getText() == "Play") {
                    if (audioService.pauseSong()) {
                        b.setText("Pause");
                    }
                } else {
                    if (audioService.resumeSong()) {
                        b.setText("Play");
                    }
                }
                break;
            case R.id.stopButton:
                audioService.stopSong();
                break;
            case R.id.previousButton:
                if (audioService.getSongsPosition() > -1) {
                    audioService.setSong(audioService.getSongsPosition() - 1);
                    audioService.playSong();
                    ((TextView) findViewById(R.id.track)).setText(songsList.get(audioService.getSongsPosition()).getTitle() + " - " + songsList.get(audioService.getSongsPosition()).getArtist());
                    //Picasso.with(RegaPlayApplication.getContext()).load(songsList.get(audioService.getSongsPosition()).getAlbumArtURI()).into(((ImageView) findViewById(R.id.imageView)));
                }
                break;

            case R.id.nextButton:
                if (audioService.getSongsPosition() < songsList.size()) {
                    audioService.setSong(audioService.getSongsPosition() + 1);
                    audioService.playSong();
                    ((TextView) findViewById(R.id.track)).setText(songsList.get(audioService.getSongsPosition()).getTitle() + " - " + songsList.get(audioService.getSongsPosition()).getArtist());
                    //Picasso.with(RegaPlayApplication.getContext()).load(songsList.get(audioService.getSongsPosition()).getAlbumArtURI()).into(((ImageView) findViewById(R.id.imageView)));
                }
                break;
        }

    }
}
