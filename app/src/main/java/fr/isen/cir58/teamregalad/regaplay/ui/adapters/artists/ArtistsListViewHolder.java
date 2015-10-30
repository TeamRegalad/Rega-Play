package fr.isen.cir58.teamregalad.regaplay.ui.adapters.artists;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import fr.isen.cir58.teamregalad.regaplay.R;

/**
 * Created by aymeric on 10/30/15.
 */
public class ArtistsListViewHolder extends RecyclerView.ViewHolder {
    public TextView mName;

    public ArtistsListViewHolder(View itemView) {
        super(itemView);
        mName = (TextView) itemView.findViewById(R.id.artists_list_fragment_item_text_view);
    }
}
