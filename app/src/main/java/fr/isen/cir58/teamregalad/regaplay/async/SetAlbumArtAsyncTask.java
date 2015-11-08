package fr.isen.cir58.teamregalad.regaplay.async;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreHelper;

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
        return MediaStoreHelper.getAlbumArt(RegaPlayApplication.getContext(), albumKey);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if (s != null) {
            File file = new File(s);
            Glide.with(RegaPlayApplication.getContext()).load(file).into(cover);
        } else {
            Log.w(getClass().getName(), "Warning: this song has no cover");
        }

    }
}
