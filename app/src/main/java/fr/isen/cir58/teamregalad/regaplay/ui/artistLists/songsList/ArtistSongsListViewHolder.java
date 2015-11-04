package fr.isen.cir58.teamregalad.regaplay.ui.artistLists.songsList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fr.isen.cir58.teamregalad.regaplay.R;

/**
 * Created by Thomas Fossati on 04/11/2015.
 */

public class ArtistSongsListViewHolder extends RecyclerView.ViewHolder {
    public TextView songName;
    public ImageView songCover;
    public Long id;

    public ArtistSongsListViewHolder(View itemView) {
        super(itemView);
        songName = (TextView) itemView.findViewById(R.id.artist_songs_list_fragment_item_song_name);
        songCover = (ImageView) itemView.findViewById(R.id.artist_songs_list_fragment_item_song_cover);
    }
}

