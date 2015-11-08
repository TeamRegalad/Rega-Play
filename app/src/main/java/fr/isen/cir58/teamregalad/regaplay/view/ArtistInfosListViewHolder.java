package fr.isen.cir58.teamregalad.regaplay.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fr.isen.cir58.teamregalad.regaplay.R;

/**
 * Created by Thomas Fossati on 04/11/2015.
 */
public class ArtistInfosListViewHolder extends RecyclerView.ViewHolder {
    public TextView albumName;
    public ImageView albumCover;

    public ArtistInfosListViewHolder(View itemView) {
        super(itemView);
        albumName = (TextView) itemView.findViewById(R.id.artist_infos_list_fragment_item_album_name);
        albumCover = (ImageView) itemView.findViewById(R.id.artist_infos_list_fragment_item_album_cover);
    }

}
