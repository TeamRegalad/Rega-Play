package fr.isen.cir58.teamregalad.regaplay.ui.artistsList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.ui.artistLists.ArtistListsActivity;


/**
 * Created by Thomas Fossati on 04/11/2015.
 */
public class ArtistsListOnClickListener implements View.OnClickListener {

    private ArtistsListViewHolder artistsListViewHolder;
    private Context context;

    public ArtistsListOnClickListener(ArtistsListViewHolder artistsListViewHolder,Context context) {
        this.artistsListViewHolder = artistsListViewHolder;
        this.context = context;
    }
    @Override
    public void onClick(View v) {

        Intent intent = new Intent(context, ArtistListsActivity.class);
        Bundle extras = new Bundle();
        extras.putString("ArtistName", artistsListViewHolder.artistName.getText().toString());
        intent.putExtras(extras);
        context.startActivity(intent);


    }
}