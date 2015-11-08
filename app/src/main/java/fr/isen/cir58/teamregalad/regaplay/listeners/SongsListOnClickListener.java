package fr.isen.cir58.teamregalad.regaplay.listeners;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.utils.Constants;
import fr.isen.cir58.teamregalad.regaplay.view.SongsListViewHolder;

/**
 * Created by aymeric on 11/1/15.
 */
public class SongsListOnClickListener implements View.OnClickListener {
    private SongsListViewHolder songsListViewHolder;

    public SongsListOnClickListener(SongsListViewHolder songsListViewHolder) {
        this.songsListViewHolder = songsListViewHolder;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Constants.Audio.ACTION_SONG_CLICKED_WITH_ID);
        Bundle extras = new Bundle();
        extras.putLong(Constants.Audio.ACTION_SONG_CLICKED_ID, songsListViewHolder.id);
        intent.putExtras(extras);
        RegaPlayApplication.getContext().sendBroadcast(intent);
    }
}
