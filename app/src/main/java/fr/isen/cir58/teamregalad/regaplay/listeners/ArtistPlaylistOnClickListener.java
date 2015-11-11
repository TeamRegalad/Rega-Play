package fr.isen.cir58.teamregalad.regaplay.listeners;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.utils.Constants;

public class ArtistPlaylistOnClickListener implements View.OnClickListener {
    private String artistName;

    public ArtistPlaylistOnClickListener(String name) {
        artistName = name;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Constants.Audio.ACTION_PLAYLIST_ARTIST_CLICKED);
        Bundle extras = new Bundle();
        extras.putString(Constants.Audio.ACTION_PLAYLIST_ARTIST_CLICKED_NAME, artistName);
        extras.putInt(Constants.Audio.ACTION_SONG_CLICKED_POSITION, 0);
        intent.putExtras(extras);
        RegaPlayApplication.getContext().sendBroadcast(intent);


    }
}
