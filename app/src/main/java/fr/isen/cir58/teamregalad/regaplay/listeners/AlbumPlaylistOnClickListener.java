package fr.isen.cir58.teamregalad.regaplay.listeners;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.utils.Constants;

public class AlbumPlaylistOnClickListener implements View.OnClickListener {
    private String albumName;

    public AlbumPlaylistOnClickListener(String name) {
        albumName = name;
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(Constants.Playlist.ACTION_PLAYLIST_ALBUM_CLICKED);
        Bundle extras = new Bundle();
        extras.putString(Constants.Playlist.ACTION_PLAYLIST_ALBUM_CLICKED_NAME, albumName);
        intent.putExtras(extras);
        RegaPlayApplication.getContext().sendBroadcast(intent);

    }
}
