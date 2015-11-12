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
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.async.BuildPlaylistAsyncTask;
import fr.isen.cir58.teamregalad.regaplay.audio.Playlist;
import fr.isen.cir58.teamregalad.regaplay.audio.Song;
import fr.isen.cir58.teamregalad.regaplay.audio.services.AudioService;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreContract;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreHelper;
import fr.isen.cir58.teamregalad.regaplay.receivers.AddToPlaylistClickedReceiver;
import fr.isen.cir58.teamregalad.regaplay.receivers.AlbumPlaylistClickedReceiver;
import fr.isen.cir58.teamregalad.regaplay.receivers.ArtistPlaylistClickedReceiver;
import fr.isen.cir58.teamregalad.regaplay.receivers.GenrePlaylistClickedReceiver;
import fr.isen.cir58.teamregalad.regaplay.receivers.OnRandomPlaylistClickedReceiver;
import fr.isen.cir58.teamregalad.regaplay.receivers.OnSongChangedReceiver;
import fr.isen.cir58.teamregalad.regaplay.receivers.OnSongClickedWithIdReceiver;
import fr.isen.cir58.teamregalad.regaplay.receivers.OnSongClickedWithPathReceiver;
import fr.isen.cir58.teamregalad.regaplay.ui.fragments.PlayerFragment;
import fr.isen.cir58.teamregalad.regaplay.utils.Constants;
import fr.isen.cir58.teamregalad.regaplay.utils.MethodsUtils;

/**
 * Created by Thomas Fossati on 04/11/2015.
 */

public class AudioActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener, OnSongClickedWithIdReceiver.OnSongClickedWithIdListener, OnSongClickedWithPathReceiver.OnSongClickedWithPathListener, ArtistPlaylistClickedReceiver.ArtistPlaylistClickedListener, AlbumPlaylistClickedReceiver.AlbumPlaylistClickedListener, GenrePlaylistClickedReceiver.GenrePlaylistClickedListener, OnRandomPlaylistClickedReceiver.OnRandomPlaylistClickedListener, AddToPlaylistClickedReceiver.AddToPlaylistClickedListener {
    protected PlayerFragment playerFragment;
    private AudioService audioService;
    private OnSongClickedWithIdReceiver onSongClickedWithIdReceiver;
    private OnSongClickedWithPathReceiver onSongClickedWithPathReceiver;
    private OnSongChangedReceiver onSongChangedReceiver;
    private ArtistPlaylistClickedReceiver artistPlaylistClickedReceiver;
    private AlbumPlaylistClickedReceiver albumPlaylistClickedReceiver;
    private GenrePlaylistClickedReceiver genrePlaylistClickedReceiver;
    private OnRandomPlaylistClickedReceiver onRandomPlaylistClickedReceiver;
    private AddToPlaylistClickedReceiver addToPlaylistClickedReceiver;
    private Intent playIntent;
    private boolean audioBound = false;
    private Playlist playlist;
    private SlidingUpPanelLayout slidingUpPanelLayout;
    private ServiceConnection audioConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AudioService.AudioBinder binder = (AudioService.AudioBinder) service;
            audioService = binder.getService();
            audioBound = true;
            updatePlayerFragment();
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
        updatePlayerFragment();

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

        //Set add to playlist broadcast receiver
        addToPlaylistClickedReceiver = new AddToPlaylistClickedReceiver(this);
        registerReceiver(addToPlaylistClickedReceiver, new IntentFilter(Constants.Playlist.ACTION_ADD_TO_PLAYLIST_CLICKED));


        if (audioService != null) {
            audioService.pauseSong();
            audioService.getMediaPlayer().setOnCompletionListener(this);
        }

        Constants.PROGRESSBAR_HANDLER = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Integer timeValues[] = (Integer[]) msg.obj;
                playerFragment.getTextBufferDuration().setText(MethodsUtils.getDuration(timeValues[0]));
                playerFragment.getTextDuration().setText(MethodsUtils.getDuration(timeValues[1]));
                playerFragment.getSeekBar().setProgress(timeValues[2]);
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
        unregisterReceiver(addToPlaylistClickedReceiver);
        addToPlaylistClickedReceiver = null;
    }

    protected void onDestroy() {
        stopService(playIntent);
        unbindService(audioConnection);
        audioService = null;
        unregisterReceiver(onSongChangedReceiver);
        onSongChangedReceiver = null;
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if(slidingUpPanelLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED){
            slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        }else {
            super.onBackPressed();
        }

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
        hideSlidingUpFrameLayout();
        playlist = null;
    }

    public void previousSong() {
       if (playlist != null){
           playlist.previousSong();
           songChanged();
       }
    }

    public void nextSong() {
        if (playlist != null) {
            if (playlist.getCurrentIndexSong() + 1 > playlist.getSongsList().size() - 1) {
                stopSong();
                return;
            }
            playlist.nextSong();
            songChanged();
        }
    }

    public void songChanged() {
        Song song = getCurrentSong();
        playerFragment.updatePlaylist(playlist.getCurrentIndexSongToString(), playlist.getPlaylistSizeToString());
        audioService.setSong(song);
        sendBroadcastSongChanged(song);
        playSong();
        showSlidingUpFrameLayout();
    }

    public void sendBroadcastSongChanged(Song song) {
        Intent intent = new Intent(Constants.Audio.ACTION_SONG_CHANGED);
        Bundle extras = new Bundle();
        extras.putParcelable(Constants.Audio.ACTION_SONG_CHANGED_SONG, song);
        intent.putExtras(extras);
        sendBroadcast(intent);
    }
    public void setSongAtTimestamp(int value){
        audioService.setSongAtTimeStamp(value);
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
        slidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        slidingUpPanelLayout.setPanelSlideListener(playerFragment);
        hideSlidingUpFrameLayout();
    }

    protected void updatePlayerFragment() {
        if (audioService != null && audioService.isPlaying) {
            playerFragment.setNewSong(audioService.song);
        }
    }

    public AudioService getAudioService() {
        return audioService;
    }

    @Override
    public void onSongClickedWithId(Long id) {
        showSlidingUpFrameLayout();
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
        showSlidingUpFrameLayout();
    }

    public Song getCurrentSong() {
        return playlist.getCurrentSong();
    }


    @Override
    public void onAlbumPlaylistClicked(String albumName) {
        playlist = null;
        new BuildPlaylistAsyncTask(this,false, MediaStoreContract.TABLE_SONGS, MediaStoreContract.SONGS_PROJECTION_FULL, MediaStoreContract.SONGS_SELECTION_BY_ALBUM, new String[]{albumName}, MediaStoreContract.SONGS_ORDER_BY_TRACK_ASC).execute();
    }

    @Override
    public void onArtistPlaylistClicked(String artistName) {
        playlist = null;
        new BuildPlaylistAsyncTask(this, false,MediaStoreContract.TABLE_SONGS, MediaStoreContract.SONGS_PROJECTION_FULL, MediaStoreContract.SONGS_SELECTION_BY_ARTIST, new String[]{artistName}, MediaStoreContract.SONGS_ORDER_BY_TITLE_ASC).execute();
    }

    @Override
    public void onGenrePlaylistClicked(long genreId) {
        playlist = null;
        new BuildPlaylistAsyncTask(this, false, MediaStore.Audio.Genres.Members.getContentUri("external", genreId), MediaStoreContract.SONGS_PROJECTION_FULL, MediaStoreContract.SONGS_SELECTION_IS_MUSIC, null, MediaStoreContract.SONGS_ORDER_BY_TITLE_ASC).execute();
    }

    @Override
    public void onRandomPlaylistClicked() {
        playlist = null;
        new BuildPlaylistAsyncTask(this, true, MediaStoreContract.TABLE_SONGS, MediaStoreContract.SONGS_PROJECTION_FULL, MediaStoreContract.SONGS_SELECTION_IS_MUSIC, null, MediaStoreContract.SONGS_ORDER_BY_TITLE_ASC).execute();
    }

    @Override
    public void onAddToPlaylistClicked(long id) {
        boolean isFirst = false;
        if (playlist == null){
            playlist = new Playlist(0);
            isFirst = true;
        }
        playlist.addSong(id);
        Toast.makeText(RegaPlayApplication.getContext(), R.string.song_added_to_playlist, Toast.LENGTH_SHORT).show();
        playerFragment.updatePlaylist(playlist.getCurrentIndexSongToString(), playlist.getPlaylistSizeToString());
        if (isFirst){
            songChanged();
        }

    }
    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    protected void hideSlidingUpFrameLayout() {
        if(slidingUpPanelLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED){
            slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
        }
        slidingUpPanelLayout.setPanelHeight(0);
    }

    protected void showSlidingUpFrameLayout() {
        slidingUpPanelLayout.setPanelHeight(MethodsUtils.convertDpToPixel(85, RegaPlayApplication.getContext()));

    }

}
