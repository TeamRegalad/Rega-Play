package fr.isen.cir58.teamregalad.regaplay.social.lastfm;

import android.util.Log;

import de.umass.lastfm.Album;
import de.umass.lastfm.Artist;
import fr.isen.cir58.teamregalad.regaplay.social.lastfm.listeners.LastFMApiAsyncTaskListner;

/**
 * Created by thomas on 31/10/15.
 */
public class LastFMAPI implements LastFMApiAsyncTaskListner {
    public void getArtistInfo(String artistName){

        new ArtistInfosAsyncTask(this).execute(artistName);



    }


    @Override
    public void onArtistRetrieved(Artist artist) {

    }

    @Override
    public void onAlbumRetrieved(Album album) {

    }
}
