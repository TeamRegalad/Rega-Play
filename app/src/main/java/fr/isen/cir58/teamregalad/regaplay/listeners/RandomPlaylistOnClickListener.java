package fr.isen.cir58.teamregalad.regaplay.listeners;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.utils.Constants;

public class RandomPlaylistOnClickListener implements View.OnClickListener{
    public RandomPlaylistOnClickListener(){}

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(Constants.Audio.ACTION_RANDOM_PLAYLIST_CLICKED);
        Bundle extras = new Bundle();
        extras.putInt(Constants.Audio.ACTION_SONG_CLICKED_POSITION, 0);
        intent.putExtras(extras);
        RegaPlayApplication.getContext().sendBroadcast(intent);
    }
}
