package fr.isen.cir58.teamregalad.regaplay.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreContract;
import fr.isen.cir58.teamregalad.regaplay.external.CursorRecyclerViewAdapter;
import fr.isen.cir58.teamregalad.regaplay.listeners.GenresListOnClickListener;
import fr.isen.cir58.teamregalad.regaplay.view.GenresListViewHolder;

/**
 * Created by aymeric on 10/30/15.
 */
public class GenresListAdapter extends CursorRecyclerViewAdapter<GenresListViewHolder> {
    private Context context;

    public GenresListAdapter(Context context, Cursor cursor)
    {
        super(context, cursor);
        this.context = context;
    }

    @Override
    public void onBindViewHolder(GenresListViewHolder viewHolder, Cursor cursor) {
        if (cursor.getColumnIndex(MediaStoreContract.GENRES_NAME) >= 0) {
            viewHolder.genreName.setText(cursor.getString(cursor.getColumnIndex(MediaStoreContract.GENRES_NAME)));
        }
        if (cursor.getColumnIndex(MediaStoreContract.GENRES_ID) >= 0) {
            viewHolder.genreId = (cursor.getLong(cursor.getColumnIndex(MediaStoreContract.GENRES_ID)));
        }
    }

    @Override
    public GenresListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.genres_list_fragment_item, parent, false);
        GenresListViewHolder genresListViewHolder = new GenresListViewHolder(itemView);
        itemView.setOnClickListener(new GenresListOnClickListener(genresListViewHolder, context));
        return genresListViewHolder;
    }
}
