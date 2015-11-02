package fr.isen.cir58.teamregalad.regaplay.social;

import android.os.AsyncTask;

import de.umass.lastfm.Album;
import de.umass.lastfm.Artist;
import de.umass.lastfm.Caller;
import de.umass.lastfm.User;

/**
 * Created by thomas on 02/11/15.
 */
public class LastFMApiAsyncTask extends AsyncTask<String,Integer,String> {

    private LastFMAPI listner;

    public LastFMApiAsyncTask(LastFMAPI listner) {
        this.listner = listner;
    }

    @Override
    protected String doInBackground(String... params) {
        String artistName = params[0];
        Caller.getInstance().setCache(null);
        String key = "4cb074e4b8ec4ee9ad3eb37d6f7eb240";
        Artist artist = Artist.getInfo(artistName, key);
        String text = artist.getWikiText();
        return text;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listner.onTextRetrieved(s);

    }
}
