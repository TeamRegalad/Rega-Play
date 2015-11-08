package fr.isen.cir58.teamregalad.regaplay.ui.genreList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fr.isen.cir58.teamregalad.regaplay.R;

/**
 * Created by paul on 11/6/15.
 */
public class GenreSongsViewHolder extends RecyclerView.ViewHolder {
    public TextView songName;
    public ImageView songCover;
    public Long id;

    public GenreSongsViewHolder(View itemView) {
        super(itemView);
        songName = (TextView) itemView.findViewById(R.id.genre_list_fragment_item_song_name);
        songCover = (ImageView) itemView.findViewById(R.id.genre_list_fragment_item_cover);
    }
}
