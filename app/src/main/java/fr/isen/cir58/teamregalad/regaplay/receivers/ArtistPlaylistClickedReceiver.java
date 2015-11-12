package fr.isen.cir58.teamregalad.regaplay.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import fr.isen.cir58.teamregalad.regaplay.utils.Constants;

public class ArtistPlaylistClickedReceiver extends BroadcastReceiver {
    private String artistName;
    private ArtistPlaylistClickedListener mlistener;


    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Constants.Playlist.ACTION_PLAYLIST_ARTIST_CLICKED)) {
            if (mlistener != null) {
                artistName = intent.getExtras().getString(Constants.Playlist.ACTION_PLAYLIST_ARTIST_CLICKED_NAME);
                mlistener.onArtistPlaylistClicked(artistName);
            }
        }
    }


    public interface ArtistPlaylistClickedListener{
        public void onArtistPlaylistClicked(String artistName);
    }

    public void setListener(ArtistPlaylistClickedListener listener) {
        mlistener = listener;
    }
}