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
import android.provider.MediaStore;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import fr.isen.cir58.teamregalad.regaplay.async.BuildPlaylistAsyncTask;
import fr.isen.cir58.teamregalad.regaplay.audio.Playlist;
import fr.isen.cir58.teamregalad.regaplay.audio.Song;
import fr.isen.cir58.teamregalad.regaplay.audio.services.AudioService;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreContract;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreHelper;
import fr.isen.cir58.teamregalad.regaplay.receivers.AlbumPlaylistClickedReceiver;
import fr.isen.cir58.teamregalad.regaplay.receivers.ArtistPlaylistClickedReceiver;
import fr.isen.cir58.teamregalad.regaplay.receivers.GenrePlaylistClickedReceiver;
import fr.isen.cir58.teamregalad.regaplay.receivers.OnRandomPlaylistClickedReceiver;
import fr.isen.cir58.teamregalad.regaplay.receivers.OnSongChangedReceiver;
import fr.isen.cir58.teamregalad.regaplay.receivers.OnSongClickedWithIdReceiver;
import fr.isen.cir58.teamregalad.regaplay.receivers.OnSongClickedWithPathReceiver;
import fr.isen.cir58.teamregalad.regaplay.ui.fragments.PlayerFragment;
import fr.isen.cir58.teamregalad.regaplay.utils.Constants;

/**
 * Created by Thomas Fossati on 04/11/2015.
 */

public class AudioActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener, OnSongClickedWithIdReceiver.OnSongClickedWithIdListener, OnSongClickedWithPathReceiver.OnSongClickedWithPathListener, ArtistPlaylistClickedReceiver.ArtistPlaylistClickedListener, AlbumPlaylistClickedReceiver.AlbumPlaylistClickedListener, GenrePlaylistClickedReceiver.GenrePlaylistClickedListener, OnRandomPlaylistClickedReceiver.OnRandomPlaylistClickedListener {

    protected PlayerFragment playerFragment;
    private AudioService audioService;
    private OnSongClickedWithIdReceiver onSongClickedWithIdReceiver;
    private OnSongClickedWithPathReceiver onSongClickedWithPathReceiver;
    private OnSongChangedReceiver onSongChangedReceiver;
    private ArtistPlaylistClickedReceiver artistPlaylistClickedReceiver;
    private AlbumPlaylistClickedReceiver albumPlaylistClickedReceiver;
    private GenrePlaylistClickedReceiver genrePlaylistClickedReceiver;
    private OnRandomPlaylistClickedReceiver onRandomPlaylistClickedReceiver;
    private Intent playIntent;
    private boolean audioBound = false;
    private Playlist playlist;
    private ServiceConnection audioConnection = new ServiceConnection(){


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


        //Set artist playlist broadcast receiver
        artistPlaylistClickedReceiver = new ArtistPlaylistClickedReceiver();
        registerReceiver(artistPlaylistClickedReceiver, new IntentFilter(Constants.Playlist.ACTION_PLAYLIST_ARTIST_CLICKED));
        artistPlaylistClickedReceiver.setListener(this);

        //Set album playlist broadcast receiver
        albumPlaylistClickedReceiver = new AlbumPlaylistClickedReceiver();
        registerReceiver(albumPlaylistClickedReceiver, new IntentFilter(Constants.Playlist.ACTION_PLAYLIST_ALBUM_CLICKED));
        albumPlaylistClickedReceiver.setListener(this);

        //Set genre playlist broadcast receiver
        genrePlaylistClickedReceiver = new GenrePlaylistClickedReceiver();
        registerReceiver(genrePlaylistClickedReceiver, new IntentFilter(Constants.Playlist.ACTION_PLAYLIST_GENRE_CLICKED));
        genrePlaylistClickedReceiver.setListener(this);

        //Set random playlist broadcast receiver
        onRandomPlaylistClickedReceiver = new OnRandomPlaylistClickedReceiver();
        registerReceiver(onRandomPlaylistClickedReceiver, new IntentFilter(Constants.Playlist.ACTION_RANDOM_PLAYLIST_CLICKED));
        onRandomPlaylistClickedReceiver.setListener(this);

        if (audioService != null) {
            audioService.pauseSong();
            audioService.getMediaPlayer().setOnCompletionListener(this);
        }

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

        unregisterReceiver(artistPlaylistClickedReceiver);
        unregisterReceiver(albumPlaylistClickedReceiver);
        artistPlaylistClickedReceiver = null;
        albumPlaylistClickedReceiver = null;
        unregisterReceiver(genrePlaylistClickedReceiver);
        genrePlaylistClickedReceiver = null;
        unregisterReceiver(onRandomPlaylistClickedReceiver);
        onRandomPlaylistClickedReceiver = null;
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
        //if (audioService.isSongPlaying()) {
            audioService.stopSong();
        //}
    }

    public void previousSong() {
       if (playlist != null){
           playlist.previousSong();
           songChanged();
       }
    }

    public void nextSong() {
        if (playlist != null) {
            if (playlist.getCurrentIndexSong() >= playlist.getSongsList().size() - 1) {
                stopSong();
                return;
            }
            playlist.nextSong();
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
        if (audioService != null && audioService.isSongPlaying()) {
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
        playlist = null;
        playlist = new Playlist(0);
        playlist.addSong(id);
        songChanged();
    }

    @Override
    public void OnSongClickedWithPath(String path) {
        playlist = null;
        playlist = new Playlist(0);
        Song clickedSong = MediaStoreHelper.getSong(path);
        playlist.addSong(clickedSong);
        songChanged();
    }

    public Song getCurrentSong() {

        return playlist.getCurrentSong();
    }


    @Override
    public void onAlbumPlaylistClicked(String albumName) {
        new BuildPlaylistAsyncTask(this,false, MediaStoreContract.TABLE_SONGS, MediaStoreContract.SONGS_PROJECTION_FULL, MediaStoreContract.SONGS_SELECTION_BY_ALBUM, new String[]{albumName}, MediaStoreContract.SONGS_ORDER_BY_TRACK_ASC).execute();
    }

    @Override
    public void onArtistPlaylistClicked(String artistName) {
        new BuildPlaylistAsyncTask(this, false,MediaStoreContract.TABLE_SONGS, MediaStoreContract.SONGS_PROJECTION_FULL, MediaStoreContract.SONGS_SELECTION_BY_ARTIST, new String[]{artistName}, MediaStoreContract.SONGS_ORDER_BY_TITLE_ASC).execute();
    }

    @Override
    public void onGenrePlaylistClicked(long genreId) {
        new BuildPlaylistAsyncTask(this, false, MediaStore.Audio.Genres.Members.getContentUri("external", genreId), MediaStoreContract.SONGS_PROJECTION_FULL, MediaStoreContract.SONGS_SELECTION_IS_MUSIC, null, MediaStoreContract.SONGS_ORDER_BY_TITLE_ASC).execute();
    }

    @Override
    public void onRandomPlaylistClicked() {

        new BuildPlaylistAsyncTask(this, true, MediaStoreContract.TABLE_SONGS, MediaStoreContract.SONGS_PROJECTION_FULL, MediaStoreContract.SONGS_SELECTION_IS_MUSIC, null, MediaStoreContract.SONGS_ORDER_BY_TITLE_ASC).execute();
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
}
