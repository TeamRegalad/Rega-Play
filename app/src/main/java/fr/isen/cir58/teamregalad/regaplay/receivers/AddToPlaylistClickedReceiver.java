package fr.isen.cir58.teamregalad.regaplay.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import fr.isen.cir58.teamregalad.regaplay.utils.Constants;

public class AddToPlaylistClickedReceiver extends BroadcastReceiver {
    private AddToPlaylistClickedListener mlistener;

    public AddToPlaylistClickedReceiver(AddToPlaylistClickedListener listener) {
        mlistener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Constants.Playlist.ACTION_ADD_TO_PLAYLIST_CLICKED)) {
            if (mlistener != null) {
                Long songId = intent.getExtras().getLong(Constants.Playlist.ACTION_ADD_TO_PLAYLIST_CLICKED_ID);
                mlistener.onAddToPlaylistClicked(songId);
            }
        }
    }

    public interface AddToPlaylistClickedListener {
        public void onAddToPlaylistClicked(long id);
    }
}
