package fr.isen.cir58.teamregalad.regaplay.social.lastfm;

import android.os.AsyncTask;

import de.umass.lastfm.Artist;
import de.umass.lastfm.Caller;
import fr.isen.cir58.teamregalad.regaplay.social.lastfm.LastFMAPI;
import fr.isen.cir58.teamregalad.regaplay.social.lastfm.listeners.LastFMApiAsyncTaskListner;

/**
 * Created by thomas on 02/11/15.
 */
public class ArtistInfosAsyncTask extends AsyncTask<String,Integer,Artist> {

    private LastFMApiAsyncTaskListner listner;

    public ArtistInfosAsyncTask(LastFMApiAsyncTaskListner listner) {
        this.listner = listner;
    }

    @Override
    protected Artist doInBackground(String... params) {
        String artistName = params[0];
        Caller.getInstance().setCache(null);
        Artist artist = Artist.getInfo(artistName, LastFmUtils.PUBLIC_KEY);
        return artist;
    }

    @Override
    protected void onPostExecute(Artist artist) {
        super.onPostExecute(artist);
        listner.onArtistRetrieved(artist);

    }
}
