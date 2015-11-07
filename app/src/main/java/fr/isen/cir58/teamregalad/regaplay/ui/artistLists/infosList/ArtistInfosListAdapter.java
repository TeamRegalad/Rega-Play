package fr.isen.cir58.teamregalad.regaplay.ui.artistLists.infosList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.umass.lastfm.Artist;
import fr.isen.cir58.teamregalad.regaplay.R;

/**
 * Created by Thomas Fossati on 04/11/2015.
 */
public class ArtistInfosListAdapter extends RecyclerView.Adapter<ArtistInfosListViewHolder> {
    private Context context;
    private Artist artist;


    public ArtistInfosListAdapter(Context context, Artist artist) {
        this.artist = artist;
    }

    @Override
    public ArtistInfosListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_view_albums_list_fragment_item, parent, false);
        return new ArtistInfosListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ArtistInfosListViewHolder viewHolder, int position) {
        //viewHolder.albumName.setText();

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
