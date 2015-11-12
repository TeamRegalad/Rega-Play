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
        if (intent.getAction().equals(Constants.Playlist.ACTION_PLAYLIST_ALBUM_CLICKED)) {
            if (mlistener != null) {
                albumName = intent.getExtras().getString(Constants.Playlist.ACTION_PLAYLIST_ALBUM_CLICKED_NAME);
                mlistener.onAlbumPlaylistClicked(albumName);
            }
        }

    }

    public void setListener(AlbumPlaylistClickedListener listener) {
        mlistener = listener;
    }

    public interface AlbumPlaylistClickedListener {
        public void onAlbumPlaylistClicked(String albumName);
    }
}