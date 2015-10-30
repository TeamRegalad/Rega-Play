package fr.isen.cir58.teamregalad.regaplay.ui.albumsList;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreContract;
import fr.isen.cir58.teamregalad.regaplay.external.CursorRecyclerViewAdapter;

/**
 * Created by aymeric on 10/30/15.
 */
public class AlbumsListAdapter extends CursorRecyclerViewAdapter<AlbumsListViewHolder> {
    public AlbumsListAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public void onBindViewHolder(AlbumsListViewHolder viewHolder, Cursor cursor) {
        if (cursor.getColumnIndex(MediaStoreContract.ALBUMS_ALBUM) >= 0) {
            viewHolder.albumName.setText(cursor.getString(cursor.getColumnIndex(MediaStoreContract.ALBUMS_ALBUM)));
        }

        if (cursor.getColumnIndex(MediaStoreContract.ALBUMS_ALBUM_ART) >= 0) {
            Picasso.with(RegaPlayApplication.getContext()).load(Uri.parse(cursor.getString(cursor.getColumnIndex(MediaStoreContract.ALBUMS_ALBUM_ART)))).into(viewHolder.albumCover);
        }
    }

    @Override
    public AlbumsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.albums_list_fragment_item, parent, false);
        return new AlbumsListViewHolder(itemView);
    }
}
