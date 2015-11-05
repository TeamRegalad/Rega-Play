package fr.isen.cir58.teamregalad.regaplay.ui.albumsList;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.io.File;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreContract;
import fr.isen.cir58.teamregalad.regaplay.external.CursorRecyclerViewAdapter;

/**
 * Created by aymeric on 10/30/15.
 */
public class AlbumsListAdapter extends CursorRecyclerViewAdapter<AlbumsListViewHolder> {
    private Context context;
    public AlbumsListAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        this.context = context;
    }

    @Override
    public void onBindViewHolder(AlbumsListViewHolder viewHolder, Cursor cursor) {
        if (cursor.getColumnIndex(MediaStoreContract.ALBUMS_ALBUM) >= 0) {
            viewHolder.albumName.setText(cursor.getString(cursor.getColumnIndex(MediaStoreContract.ALBUMS_ALBUM)));
        }

        if (cursor.getColumnIndex(MediaStoreContract.ALBUMS_ARTIST) >= 0) {
            viewHolder.artistName.setText(cursor.getString(cursor.getColumnIndex(MediaStoreContract.ALBUMS_ARTIST)));
        }

        if (cursor.getColumnIndex(MediaStoreContract.ALBUMS_ALBUM_ART) >= 0) {
            String albumArtPath = cursor.getString(cursor.getColumnIndex(MediaStoreContract.ALBUMS_ALBUM_ART));
            if (albumArtPath != null) {
                Picasso.with(RegaPlayApplication.getContext()).load(new File(albumArtPath)).into(viewHolder.albumCover);
                viewHolder.albumCover.setTag(albumArtPath);
            }
        }
    }

    @Override
    public AlbumsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.albums_list_fragment_item, parent, false);
        AlbumsListViewHolder albumsListViewHolder = new AlbumsListViewHolder(itemView);
        itemView.setOnClickListener(new AlbumsListOnClickListener(albumsListViewHolder,context));
        return new AlbumsListViewHolder(itemView);
    }
}
