package fr.isen.cir58.teamregalad.regaplay;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import fr.isen.cir58.teamregalad.regaplay.audio.services.AudioService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.playButton).setOnClickListener(this);
        findViewById(R.id.stopButton).setOnClickListener(this);
        findViewById(R.id.previousButton).setOnClickListener(this);
        findViewById(R.id.nextButton).setOnClickListener(this);


    }

    /*@Override
    protected void onStart() {
        super.onStart();
        if (playIntent == null) {
            playIntent = new Intent(this, AudioService.class);
            bindService(playIntent, audioConnection, Context.BIND_AUTO_CREATE);
            startService(playIntent);
        }
    }

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
    }*/

    @Override
    public void onClick(View v) {

        /*switch (v.getId()) {
            case R.id.playButton:

                songPicked();
                Button b = (Button) v;
                /*Bitmap bitmap = songsList.get(audioService.getSongsPosition()).getAlbumArt();
                if(bitmap != null){
                    ((ImageView) findViewById(R.id.imageView)).setImageBitmap(bitmap);
                }
                //File file = new File(songsList.get(audioService.getSongsPosition()).getAlbumArtPath());
                //Log.d("test", String.valueOf(file.exists()));
                //Picasso.with(RegaPlayApplication.getContext()).load(file).into(((ImageView) findViewById(R.id.imageView)));


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
                if (audioService.getSongId() > -1) {
                    audioService.setSong(audioService.getSongId() - 1);
                    audioService.playSong();
                    ((TextView) findViewById(R.id.track)).setText(songsList.get(audioService.getSongId()).getTitle() + " - " + songsList.get(audioService.getSongsPosition()).getArtist());
                    //Picasso.with(RegaPlayApplication.getContext()).load(songsList.get(audioService.getSongsPosition()).getAlbumArtURI()).into(((ImageView) findViewById(R.id.imageView)));
                }
                break;

            case R.id.nextButton:
                if (audioService.getSongId() < songsList.size()) {
                    audioService.setSong(audioService.getSongId() + 1);
                    audioService.playSong();
                    ((TextView) findViewById(R.id.track)).setText(songsList.get(audioService.getSongId()).getTitle() + " - " + songsList.get(audioService.getSongsPosition()).getArtist());
                    //Picasso.with(RegaPlayApplication.getContext()).load(songsList.get(audioService.getSongsPosition()).getAlbumArtURI()).into(((ImageView) findViewById(R.id.imageView)));
                }
                break;
        }*/

    }
}
