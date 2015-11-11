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

        if (intent.getAction().equals(Constants.Audio.ACTION_PLAYLIST_ARTIST_CLICKED)) {
            if (mlistener != null) {
                artistName = intent.getExtras().getString(Constants.Audio.ACTION_PLAYLIST_ARTIST_CLICKED_NAME);

                mlistener.onArtistPlaylistClicked(artistName,intent.getExtras().getInt(Constants.Audio.ACTION_SONG_CLICKED_POSITION));
            }
        }
    }


    public interface ArtistPlaylistClickedListener{
        public void onArtistPlaylistClicked(String artistName, int position);
    }

    public void setListener(ArtistPlaylistClickedListener listener) {
        mlistener = listener;
    }
}