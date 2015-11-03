package fr.isen.cir58.teamregalad.regaplay.social.lastfm.listeners;

import de.umass.lastfm.Album;
import de.umass.lastfm.Artist;

/**
 * Created by thomas on 02/11/15.
 */
public interface LastFMApiAsyncTaskListner {
    public void onArtistRetrieved(Artist artist);
    public void onAlbumRetrieved(Album album);
}
