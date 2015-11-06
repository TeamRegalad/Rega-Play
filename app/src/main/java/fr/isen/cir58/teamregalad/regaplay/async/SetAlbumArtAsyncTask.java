package fr.isen.cir58.teamregalad.regaplay.async;

import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreContract;

/**
 * Created by aymeric on 11/6/15.
 */
public class SetAlbumArtAsyncTask extends AsyncTask<String, Void, String> {
    private ImageView cover;
    private String albumKey;

    public SetAlbumArtAsyncTask(String albumKey, ImageView cover) {
        this.albumKey = albumKey;
        this.cover = cover;
    }

    @Override
    protected String doInBackground(String... strings) {
        String path = null;

        Uri table = MediaStoreContract.TABLE_ALBUMS;
        String[] projection = new String[]{MediaStoreContract.ALBUMS_ALBUM_ART};
        String selection = MediaStoreContract.ALBUMS_SELECTION_BY_ALBUM_KEY;
        String[] selectionArgs = new String[]{albumKey};
        String sortOrder = null;

        Cursor cursor = RegaPlayApplication.getContext().getContentResolver().query(table, projection, selection, selectionArgs, sortOrder);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(0);
            }

            if (!cursor.isClosed()) {
                cursor.close();
            }
        }

        return path;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        File file = new File(s);
        Glide.with(RegaPlayApplication.getContext()).load(file).into(cover);
    }
}
