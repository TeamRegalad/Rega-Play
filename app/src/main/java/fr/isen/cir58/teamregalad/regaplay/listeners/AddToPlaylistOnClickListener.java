package fr.isen.cir58.teamregalad.regaplay.listeners;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.utils.Constants;
import fr.isen.cir58.teamregalad.regaplay.view.SongsListViewHolder;

public class AddToPlaylistOnClickListener implements View.OnClickListener {
    private SongsListViewHolder songsListViewHolder;
    public AddToPlaylistOnClickListener(SongsListViewHolder viewHolder){
        songsListViewHolder = viewHolder;

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        Bundle extras = new Bundle();
        intent = new Intent(Constants.Playlist.ACTION_ADD_TO_PLAYLIST_CLICKED);
        extras.putLong(Constants.Playlist.ACTION_ADD_TO_PLAYLIST_CLICKED_ID, songsListViewHolder.id);
        intent.putExtras(extras);
        RegaPlayApplication.getContext().sendBroadcast(intent);
    }
}
