package fr.isen.cir58.teamregalad.regaplay.listeners;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fr.isen.cir58.teamregalad.regaplay.ui.activities.AlbumListActivity;
import fr.isen.cir58.teamregalad.regaplay.view.ArtistAlbumsListViewHolder;

/**
 * Created by Thomas Fossati on 05/11/2015.
 */
public class ArtistAlbumsListOnClickListener implements View.OnClickListener {
    private ArtistAlbumsListViewHolder artistAlbumsListViewHolder;
    private Context context;

    public ArtistAlbumsListOnClickListener(ArtistAlbumsListViewHolder artistAlbumsListViewHolder, Context context) {
        this.artistAlbumsListViewHolder = artistAlbumsListViewHolder;
        this.context = context;
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(context, AlbumListActivity.class);
        Bundle extras = new Bundle();
        extras.putString("AlbumName", artistAlbumsListViewHolder.albumName.getText().toString());
        extras.putString("AlbumCover", artistAlbumsListViewHolder.coverPath);
        intent.putExtras(extras);
        context.startActivity(intent);


    }
}
