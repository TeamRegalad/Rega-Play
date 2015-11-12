package fr.isen.cir58.teamregalad.regaplay.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import fr.isen.cir58.teamregalad.regaplay.utils.Constants;

public class GenrePlaylistClickedReceiver extends BroadcastReceiver {

    private long genreId;
    private GenrePlaylistClickedListener mlistener;


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Constants.Playlist.ACTION_PLAYLIST_GENRE_CLICKED)) {
            if (mlistener != null) {
                genreId = intent.getExtras().getLong(Constants.Playlist.ACTION_PLAYLIST_GENRE_CLICKED_ID);
                mlistener.onGenrePlaylistClicked(genreId);
            }
        }

    }

    public void setListener(GenrePlaylistClickedListener listener) {
        mlistener = listener;
    }

    public interface GenrePlaylistClickedListener {
        public void onGenrePlaylistClicked(long genreId);
    }
}