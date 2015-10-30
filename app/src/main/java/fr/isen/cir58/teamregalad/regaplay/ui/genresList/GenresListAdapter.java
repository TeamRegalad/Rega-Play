package fr.isen.cir58.teamregalad.regaplay.ui.genresList;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreContract;
import fr.isen.cir58.teamregalad.regaplay.external.CursorRecyclerViewAdapter;

/**
 * Created by aymeric on 10/30/15.
 */
public class GenresListAdapter extends CursorRecyclerViewAdapter<GenresListViewHolder> {
    public GenresListAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public void onBindViewHolder(GenresListViewHolder viewHolder, Cursor cursor) {
        if (cursor.getColumnIndex(MediaStoreContract.GENRES_NAME) >= 0) {
            viewHolder.genreName.setText(cursor.getString(cursor.getColumnIndex(MediaStoreContract.GENRES_NAME)));
        }
    }

    @Override
    public GenresListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.genres_list_fragment_item, parent, false);
        return new GenresListViewHolder(itemView);
    }
}
