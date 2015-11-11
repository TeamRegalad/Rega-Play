package fr.isen.cir58.teamregalad.regaplay.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import fr.isen.cir58.teamregalad.regaplay.utils.Constants;

public class AlbumPlaylistClickedReceiver extends BroadcastReceiver {

    private String albumName;
    private AlbumPlaylistClickedListener mlistener;


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Constants.Audio.ACTION_PLAYLIST_ALBUM_CLICKED)) {
            if (mlistener != null) {
                albumName = intent.getExtras().getString(Constants.Audio.ACTION_PLAYLIST_ALBUM_CLICKED_NAME);
                mlistener.onAlbumPlaylistClicked(albumName, intent.getExtras().getInt(Constants.Audio.ACTION_SONG_CLICKED_POSITION));
            }
        }

    }

    public interface AlbumPlaylistClickedListener {
        public void onAlbumPlaylistClicked(String albumName, int position);
    }

    public void setListener(AlbumPlaylistClickedListener listener) {
        mlistener = listener;
    }
}