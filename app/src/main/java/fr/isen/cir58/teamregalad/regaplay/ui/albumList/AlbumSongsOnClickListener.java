package fr.isen.cir58.teamregalad.regaplay.ui.albumList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.utils.Constants;

/**
 * Created by Thomas Fossati on 05/11/2015.
 */
public class AlbumSongsOnClickListener implements View.OnClickListener {
    private AlbumSongsViewHolder albumSongsViewHolder;

    public AlbumSongsOnClickListener(AlbumSongsViewHolder albumSongsViewHolder) {
        this.albumSongsViewHolder = albumSongsViewHolder;
    }

    @Override
    public void onClick(View view) {
        Log.d(this.getClass().toString(), "TEST");
        Intent intent = new Intent(Constants.Audio.ACTION_SONG_CLICKED);
        Bundle extras = new Bundle();
        extras.putLong(Constants.Audio.ACTION_SONG_CLICKED_ID, albumSongsViewHolder.id);
        intent.putExtras(extras);
        RegaPlayApplication.getContext().sendBroadcast(intent);
    }
}
