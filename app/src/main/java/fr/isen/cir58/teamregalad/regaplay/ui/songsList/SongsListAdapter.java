package fr.isen.cir58.teamregalad.regaplay.ui.songsList;

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
import fr.isen.cir58.teamregalad.regaplay.async.setAlbumArtAsyncTask;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreContract;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreHelper;
import fr.isen.cir58.teamregalad.regaplay.external.CursorRecyclerViewAdapter;

/**
 * Created by aymeric on 10/31/15.
 */
public class SongsListAdapter extends CursorRecyclerViewAdapter<SongsListViewHolder>{
    public SongsListAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public void onBindViewHolder(SongsListViewHolder viewHolder, Cursor cursor) {
        if (cursor.getColumnIndex(MediaStoreContract.SONGS_TITLE) >= 0) {
            viewHolder.songName.setText(cursor.getString(cursor.getColumnIndex(MediaStoreContract.SONGS_TITLE)));

        }

        if (cursor.getColumnIndex(MediaStoreContract.SONGS_ARTIST) >= 0) {
            viewHolder.artistName.setText(cursor.getString(cursor.getColumnIndex(MediaStoreContract.SONGS_ARTIST)));
        }

        if (cursor.getColumnIndex(MediaStoreContract.SONGS_ID) >= 0) {
            viewHolder.id = cursor.getLong(cursor.getColumnIndex(MediaStoreContract.SONGS_ID));
        }

        if (cursor.getColumnIndex(MediaStoreContract.SONGS_ALBUM_KEY) >= 0) {
            String albumKey = cursor.getString(cursor.getColumnIndex(MediaStoreContract.SONGS_ALBUM_KEY));

            new setAlbumArtAsyncTask(albumKey, viewHolder.songCover).execute();
        }
    }

    @Override
    public SongsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.songs_list_fragment_item, parent, false);
        SongsListViewHolder songsListViewHolder = new SongsListViewHolder(itemView);
        itemView.setOnClickListener(new SongsListOnClickListener(songsListViewHolder));

        return songsListViewHolder;
    }
}
