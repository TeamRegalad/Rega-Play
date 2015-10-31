package fr.isen.cir58.teamregalad.regaplay.ui.genresList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import fr.isen.cir58.teamregalad.regaplay.R;

/**
 * Created by aymeric on 10/30/15.
 */
public class GenresListViewHolder extends RecyclerView.ViewHolder {
    public TextView genreName;

    public GenresListViewHolder(View itemView) {
        super(itemView);
        genreName = (TextView) itemView.findViewById(R.id.genres_list_fragment_item_genre_name);
    }
}
