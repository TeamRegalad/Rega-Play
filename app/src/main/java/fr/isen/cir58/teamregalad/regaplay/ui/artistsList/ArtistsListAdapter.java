package fr.isen.cir58.teamregalad.regaplay.ui.artistsList;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreContract;
import fr.isen.cir58.teamregalad.regaplay.external.CursorRecyclerViewAdapter;

/**
 * Created by aymeric on 10/28/15.
 */

public class ArtistsListAdapter extends CursorRecyclerViewAdapter<ArtistsListViewHolder> {
    private Context context;

    public ArtistsListAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        this.context = context;
    }

    @Override
    public ArtistsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.artists_list_fragment_item, parent, false);
        ArtistsListViewHolder artistsListViewHolder = new ArtistsListViewHolder(itemView);
        itemView.setOnClickListener(new ArtistsListOnClickListener(artistsListViewHolder, context));
        return new ArtistsListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ArtistsListViewHolder viewHolder, Cursor cursor) {
        if (cursor.getColumnIndex(MediaStoreContract.ARTISTS_ARTIST) >= 0) {
            viewHolder.artistName.setText(cursor.getString(cursor.getColumnIndex(MediaStoreContract.ARTISTS_ARTIST)));
        }
    }
}
