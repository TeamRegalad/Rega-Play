package fr.isen.cir58.teamregalad.regaplay.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.async.SetAlbumArtAsyncTask;

/**
 * Created by aymeric on 10/31/15.
 */
public class SongsListViewHolder extends RecyclerView.ViewHolder {
    public TextView songName;
    public TextView artistName;
    public ImageView songCover;
    public Long id;
    public SetAlbumArtAsyncTask currentAsyncTask;
    public String albumKey;

    public SongsListViewHolder(View itemView) {
        super(itemView);
        songName = (TextView) itemView.findViewById(R.id.songs_fragment_item_song_name);
        artistName = (TextView) itemView.findViewById(R.id.songs_fragment_item_artist_name);
        songCover = (ImageView) itemView.findViewById(R.id.songs_fragment_item_song_cover);
    }
}
