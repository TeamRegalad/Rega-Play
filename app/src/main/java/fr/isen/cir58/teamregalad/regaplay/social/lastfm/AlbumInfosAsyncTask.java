package fr.isen.cir58.teamregalad.regaplay.social.lastfm;

import android.os.AsyncTask;

import de.umass.lastfm.Album;
import de.umass.lastfm.Caller;
import fr.isen.cir58.teamregalad.regaplay.social.lastfm.listeners.LastFMApiAsyncTaskListner;

/**
 * Created by Thomas Fossati on 03/11/2015.
 */
public class AlbumInfosAsyncTask extends AsyncTask<String,Integer,Album> {
    private LastFMApiAsyncTaskListner listner;

    public AlbumInfosAsyncTask(LastFMApiAsyncTaskListner listner) {
        this.listner = listner;
    }

    @Override
    protected Album doInBackground(String... params) {
        String artistName = params[0];
        String albumName = params[1];
        Caller.getInstance().setCache(null);
        Album album = Album.getInfo(artistName, albumName, LastFmUtils.PUBLIC_KEY);
        return album;
    }

    @Override
    protected void onPostExecute(Album album) {
        super.onPostExecute(album);
        listner.onAlbumRetrieved(album);

    }
}
