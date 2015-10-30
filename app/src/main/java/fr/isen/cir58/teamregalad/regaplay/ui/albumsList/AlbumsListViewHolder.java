package fr.isen.cir58.teamregalad.regaplay.ui.albumsList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fr.isen.cir58.teamregalad.regaplay.R;

/**
 * Created by aymeric on 10/30/15.
 */
public class AlbumsListViewHolder extends RecyclerView.ViewHolder {
    public TextView albumName;
    public TextView artistName;
    public ImageView albumCover;

    public AlbumsListViewHolder(View itemView) {
        super(itemView);
        albumName = (TextView) itemView.findViewById(R.id.albums_list_fragment_item_album_name);
        artistName = (TextView) itemView.findViewById(R.id.albums_list_fragment_item_artist_name);
        albumCover = (ImageView) itemView.findViewById(R.id.albums_list_fragment_item_album_cover);
    }
}
