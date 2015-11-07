package fr.isen.cir58.teamregalad.regaplay.ui.albumList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fr.isen.cir58.teamregalad.regaplay.R;

/**
 * Created by Thomas Fossati on 05/11/2015.
 */
public class AlbumSongsViewHolder extends RecyclerView.ViewHolder {
    public TextView songName;
    public ImageView songCover;
    public Long id;

    public AlbumSongsViewHolder(View itemView) {
        super(itemView);
        songName = (TextView) itemView.findViewById(R.id.album_list_fragment_item_song_name);
        songCover = (ImageView) itemView.findViewById(R.id.album_list_fragment_item_song_cover);
    }
}
