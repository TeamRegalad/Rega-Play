package fr.isen.cir58.teamregalad.regaplay.async;

import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.audio.Song;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreContract;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreHelper;
import fr.isen.cir58.teamregalad.regaplay.ui.player.PlayerFragment;

/**
 * Created by Thomas Fossati on 07/11/2015.
 */
public class GetSongInfosAsyncTask extends AsyncTask<Long, Integer, Song> {
    private PlayerFragment playerFragment;

    public GetSongInfosAsyncTask(PlayerFragment playerFragment) {
        this.playerFragment = playerFragment;
    }

    @Override
    protected Song doInBackground(Long... params) {

        Long songId = params[0];


        Uri table = MediaStoreContract.TABLE_SONGS;
        String[] projection = MediaStoreContract.SONGS_PROJECTION_FULL;
        String selection = MediaStoreContract.SONGS_SELECTION_BY_ID;
        String[] selectionArgs = new String[]{songId.toString()};
        String sortOrder = null;

        Song song = null;
        Cursor cursor = RegaPlayApplication.getContext().getContentResolver().query(table, projection, selection, selectionArgs, sortOrder);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                song = new Song(
                        songId,
                        cursor.getString(cursor.getColumnIndex(MediaStoreContract.SONGS_TITLE)),
                        cursor.getString(cursor.getColumnIndex(MediaStoreContract.SONGS_DATA)),
                        cursor.getInt(cursor.getColumnIndex(MediaStoreContract.SONGS_ARTIST_ID)),
                        cursor.getString(cursor.getColumnIndex(MediaStoreContract.SONGS_ARTIST)),
                        cursor.getInt(cursor.getColumnIndex(MediaStoreContract.ALBUMS_ID)),
                        cursor.getString(cursor.getColumnIndex(MediaStoreContract.SONGS_ALBUM)),
                        cursor.getInt(cursor.getColumnIndex(MediaStoreContract.SONGS_YEAR)),
                        cursor.getInt(cursor.getColumnIndex(MediaStoreContract.SONGS_DURATION)),
                        MediaStoreHelper.getGenre(RegaPlayApplication.getContext(), cursor.getLong(cursor.getColumnIndex(MediaStoreContract.SONGS_ID))),
                        MediaStoreHelper.getAlbumArt(RegaPlayApplication.getContext(), cursor.getString(cursor.getColumnIndex(MediaStoreContract.SONGS_ALBUM_KEY)))
                );

            }

            if (!cursor.isClosed()) {
                cursor.close();
            }
        }

        return song;
    }

    @Override
    protected void onPostExecute(Song song) {
        super.onPostExecute(song);

        playerFragment.setNewSong(song);
    }
}
