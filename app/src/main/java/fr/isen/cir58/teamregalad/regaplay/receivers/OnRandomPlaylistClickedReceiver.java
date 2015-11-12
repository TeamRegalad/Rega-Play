package fr.isen.cir58.teamregalad.regaplay.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import fr.isen.cir58.teamregalad.regaplay.utils.Constants;

public class OnRandomPlaylistClickedReceiver extends BroadcastReceiver {
    private OnRandomPlaylistClickedListener mlistener;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Constants.Playlist.ACTION_RANDOM_PLAYLIST_CLICKED)) {
            if (mlistener != null) {
                mlistener.onRandomPlaylistClicked();
            }
        }
    }

    public void setListener(OnRandomPlaylistClickedListener listener) {
        this.mlistener = listener;
    }

    public interface OnRandomPlaylistClickedListener {
        public void onRandomPlaylistClicked();
    }
}
