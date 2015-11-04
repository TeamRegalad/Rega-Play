package fr.isen.cir58.teamregalad.regaplay.social.lastfm;

import android.os.AsyncTask;
import android.util.Log;

import de.umass.lastfm.Artist;
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
        Artist artist = null;
        try{
            artist = Artist.getInfo(artistName, LastFmUtils.PUBLIC_KEY);
        }catch (Exception e){
            Log.e("ArtistInfosAsyncTask",e.getMessage());

        }
        return artist;
    }

    @Override
    protected void onPostExecute(Artist artist) {
        super.onPostExecute(artist);
        listner.onArtistRetrieved(artist);

    }
}
