package fr.isen.cir58.teamregalad.regaplay.listeners;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.utils.Constants;

public class GenrePlaylistOnClickListener implements View.OnClickListener {
    private long genreId;

    public GenrePlaylistOnClickListener(long id) {
        genreId = id;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Constants.Playlist.ACTION_PLAYLIST_GENRE_CLICKED);
        Bundle extras = new Bundle();
        extras.putLong(Constants.Playlist.ACTION_PLAYLIST_GENRE_CLICKED_ID, genreId);
        intent.putExtras(extras);
        RegaPlayApplication.getContext().sendBroadcast(intent);
    }
}
