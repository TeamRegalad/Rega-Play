package fr.isen.cir58.teamregalad.regaplay.ui.albumList;

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
import fr.isen.cir58.teamregalad.regaplay.ui.albumsList.AlbumsListViewHolder;
import fr.isen.cir58.teamregalad.regaplay.ui.artistLists.songsList.ArtistSongsListOnClickListener;
import fr.isen.cir58.teamregalad.regaplay.ui.artistLists.songsList.ArtistSongsListViewHolder;

/**
 * Created by Thomas Fossati on 05/11/2015.
 */
public class AlbumSongsAdapter extends CursorRecyclerViewAdapter<AlbumSongsViewHolder> {
    public AlbumSongsAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public void onBindViewHolder(AlbumSongsViewHolder viewHolder, Cursor cursor) {
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
                Log.e("AlbumSongsAdapter", "Error album art path is null.");
            }
        }
    }

    @Override
    public AlbumSongsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_list_fragment_item, parent, false);
        AlbumSongsViewHolder albumSongsViewHolder = new AlbumSongsViewHolder(itemView);
        itemView.setOnClickListener(new AlbumSongsOnClickListener(albumSongsViewHolder));

        return albumSongsViewHolder;
    }
}
