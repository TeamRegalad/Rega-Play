package fr.isen.cir58.teamregalad.regaplay.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.async.SetAlbumArtAsyncTask;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreContract;
import fr.isen.cir58.teamregalad.regaplay.external.CursorRecyclerViewAdapter;
import fr.isen.cir58.teamregalad.regaplay.listeners.SongsListOnClickListener;
import fr.isen.cir58.teamregalad.regaplay.utils.Constants;
import fr.isen.cir58.teamregalad.regaplay.view.SongsListViewHolder;

/**
 * Created by aymeric on 10/31/15.
 */
public class SongsListAdapter extends CursorRecyclerViewAdapter<SongsListViewHolder> {
    private String clickOrigin;
    private String clickOriginName;
    private long genreId;

    public SongsListAdapter(Context context, Cursor cursor, String origin, String originName, long genreId) {
        super(context, cursor);
        this.clickOrigin = origin;
        this.clickOriginName = originName;
        this.genreId = genreId;
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
            viewHolder.position = cursor.getPosition();
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
        if (clickOrigin.equals(Constants.SongClickedOrigin.GENRE)){

            itemView.setOnClickListener(new SongsListOnClickListener(songsListViewHolder, clickOrigin, genreId));
        }else{
            itemView.setOnClickListener(new SongsListOnClickListener(songsListViewHolder, clickOrigin, clickOriginName));
        }


        return songsListViewHolder;
    }
}
