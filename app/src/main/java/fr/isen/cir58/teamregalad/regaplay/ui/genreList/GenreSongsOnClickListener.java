package fr.isen.cir58.teamregalad.regaplay.ui.genreList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.utils.Constants;

/**
 * Created by paul on 11/6/15.
 */
public class GenreSongsOnClickListener implements View.OnClickListener {
    private GenreSongsViewHolder genreSongsViewHolder;

    public GenreSongsOnClickListener(GenreSongsViewHolder genreSongsViewHolder) {
        this.genreSongsViewHolder = genreSongsViewHolder;
    }

    @Override
    public void onClick(View view) {
        Log.d(this.getClass().toString(), "TEST");
        Intent intent = new Intent(Constants.Audio.ACTION_SONG_CLICKED);
        Bundle extras = new Bundle();
        extras.putLong(Constants.Audio.ACTION_SONG_CLICKED_ID, genreSongsViewHolder.id);
        intent.putExtras(extras);
        RegaPlayApplication.getContext().sendBroadcast(intent);
    }
}
