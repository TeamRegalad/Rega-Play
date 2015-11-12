package fr.isen.cir58.teamregalad.regaplay.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.async.SetAlbumArtAsyncTask;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreContract;
import fr.isen.cir58.teamregalad.regaplay.external.CursorRecyclerViewAdapter;
import fr.isen.cir58.teamregalad.regaplay.listeners.AddToPlaylistOnClickListener;
import fr.isen.cir58.teamregalad.regaplay.listeners.SongsListOnClickListener;
import fr.isen.cir58.teamregalad.regaplay.view.SongsListViewHolder;

/**
 * Created by aymeric on 10/31/15.
 */
public class SongsListAdapter extends CursorRecyclerViewAdapter<SongsListViewHolder> {
    private Long songId;

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
            songId = viewHolder.id;
        }

        if (cursor.getColumnIndex(MediaStoreContract.SONGS_ALBUM_KEY) >= 0) {
            String albumKey = cursor.getString(cursor.getColumnIndex(MediaStoreContract.SONGS_ALBUM_KEY));

            if (viewHolder.currentAsyncTask == null || !Objects.equals(viewHolder.albumKey, albumKey)) {
                if (viewHolder.currentAsyncTask != null) {
                    viewHolder.currentAsyncTask.cancel(true);
                }
                viewHolder.songCover.setImageDrawable(null);
                viewHolder.albumKey = albumKey;
                SetAlbumArtAsyncTask setAlbumArtAsyncTask = new SetAlbumArtAsyncTask(albumKey, viewHolder.songCover);
                viewHolder.currentAsyncTask = setAlbumArtAsyncTask;
                setAlbumArtAsyncTask.execute();
            }
        }
    }

    @Override
    public SongsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.songs_fragment_item, parent, false);
        SongsListViewHolder songsListViewHolder = new SongsListViewHolder(itemView);
        itemView.setOnClickListener(new SongsListOnClickListener(songsListViewHolder));
        Button addButton = (Button) itemView.findViewById(R.id.songs_fragment_item_button);
        addButton.setOnClickListener(new AddToPlaylistOnClickListener(songsListViewHolder));
        return songsListViewHolder;
    }
}
