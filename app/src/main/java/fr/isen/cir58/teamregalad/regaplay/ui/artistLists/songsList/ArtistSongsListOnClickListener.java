package fr.isen.cir58.teamregalad.regaplay.ui.artistLists.songsList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.utils.Constants;

/**
 * Created by Thomas Fossati on 04/11/2015.
 */
public class ArtistSongsListOnClickListener implements View.OnClickListener {
    private ArtistSongsListViewHolder songsListViewHolder;

    public ArtistSongsListOnClickListener(ArtistSongsListViewHolder songsListViewHolder) {
        this.songsListViewHolder = songsListViewHolder;
    }

    @Override
    public void onClick(View view) {
        Log.d(this.getClass().toString(), "TEST");
        Intent intent = new Intent(Constants.Audio.ACTION_SONG_CLICKED);
        Bundle extras = new Bundle();
        extras.putLong(Constants.Audio.ACTION_SONG_CLICKED_ID, songsListViewHolder.id);
        intent.putExtras(extras);
        RegaPlayApplication.getContext().sendBroadcast(intent);
    }
}
