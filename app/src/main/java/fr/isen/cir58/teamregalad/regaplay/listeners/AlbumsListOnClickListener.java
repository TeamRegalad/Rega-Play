package fr.isen.cir58.teamregalad.regaplay.listeners;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fr.isen.cir58.teamregalad.regaplay.ui.activities.AlbumListActivity;
import fr.isen.cir58.teamregalad.regaplay.view.AlbumsListViewHolder;

/**
 * Created by Thomas Fossati on 05/11/2015.
 */
public class AlbumsListOnClickListener implements View.OnClickListener {
    private AlbumsListViewHolder albumsListViewHolder;
    private Context context;

    public AlbumsListOnClickListener(AlbumsListViewHolder albumsListViewHolder, Context context) {
        this.albumsListViewHolder = albumsListViewHolder;
        this.context = context;
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(context, AlbumListActivity.class);
        Bundle extras = new Bundle();
        extras.putString("AlbumName", albumsListViewHolder.albumName.getText().toString());
        extras.putString("AlbumCover", albumsListViewHolder.coverPath);
        intent.putExtras(extras);
        context.startActivity(intent);
    }
}
