package fr.isen.cir58.teamregalad.regaplay.listeners;

import android.content.Intent;
import android.view.View;

import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.utils.Constants;

public class RandomPlaylistOnClickListener implements View.OnClickListener {
    public RandomPlaylistOnClickListener() {
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(Constants.Playlist.ACTION_RANDOM_PLAYLIST_CLICKED);
        RegaPlayApplication.getContext().sendBroadcast(intent);
    }
}
