package fr.isen.cir58.teamregalad.regaplay.ui.artistLists.albumsList;

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
import fr.isen.cir58.teamregalad.regaplay.ui.albumsList.AlbumsListViewHolder;

/**
 * Created by Thomas Fossati on 04/11/2015.
 */
public class ArtistAlbumListAdapter extends CursorRecyclerViewAdapter<ArtisitAlbumsListViewHolder> {
    public ArtistAlbumListAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public void onBindViewHolder(ArtisitAlbumsListViewHolder viewHolder, Cursor cursor) {
        if (cursor.getColumnIndex(MediaStoreContract.ALBUMS_ALBUM) >= 0) {
            viewHolder.albumName.setText(cursor.getString(cursor.getColumnIndex(MediaStoreContract.ALBUMS_ALBUM)));
        }

        if (cursor.getColumnIndex(MediaStoreContract.ALBUMS_ALBUM_ART) >= 0) {
            String albumArtPath = cursor.getString(cursor.getColumnIndex(MediaStoreContract.ALBUMS_ALBUM_ART));
            if (albumArtPath != null) {
                Picasso.with(RegaPlayApplication.getContext()).load(new File(albumArtPath)).into(viewHolder.albumCover);
            }
        }
    }

    @Override
    public ArtisitAlbumsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_albums_list_fragment_item, parent, false);
        return new ArtisitAlbumsListViewHolder(itemView);
    }
}