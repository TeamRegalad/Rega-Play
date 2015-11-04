package fr.isen.cir58.teamregalad.regaplay.ui.artistLists.songsList;


import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.io.File;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreContract;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreHelper;
import fr.isen.cir58.teamregalad.regaplay.external.CursorRecyclerViewAdapter;

/**
 * Created by Thomas Fossati on 04/11/2015.
 */

public class ArtistSongsListAdapter extends CursorRecyclerViewAdapter<ArtistSongsListViewHolder> {
    public ArtistSongsListAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public void onBindViewHolder(ArtistSongsListViewHolder viewHolder, Cursor cursor) {
        if (cursor.getColumnIndex(MediaStoreContract.SONGS_TITLE) >= 0) {
            viewHolder.songName.setText(cursor.getString(cursor.getColumnIndex(MediaStoreContract.SONGS_TITLE)));

        }
        if (cursor.getColumnIndex(MediaStoreContract.SONGS_ID) >= 0) {
            viewHolder.id = cursor.getLong(cursor.getColumnIndex(MediaStoreContract.SONGS_ID));
        }

        if (cursor.getColumnIndex(MediaStoreContract.SONGS_ALBUM_KEY) >= 0) {

            String albumArtPath = MediaStoreHelper.getAlbumArt(RegaPlayApplication.getContext(), cursor.getString(cursor.getColumnIndex(MediaStoreContract.SONGS_ALBUM_KEY)));
            if (albumArtPath != null) {
                File file = new File(albumArtPath);
                Glide.with(RegaPlayApplication.getContext()).load(file).into(viewHolder.songCover);
            } else {
                Log.e("SongListAdapter", "Error album art path is null.");
            }
        }
    }

    @Override
    public ArtistSongsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_songs_list_fragment_item, parent, false);
        ArtistSongsListViewHolder artistSongsListViewHolder = new ArtistSongsListViewHolder(itemView);
        itemView.setOnClickListener(new ArtistSongsListOnClickListener(artistSongsListViewHolder));

        return artistSongsListViewHolder;
    }
}

