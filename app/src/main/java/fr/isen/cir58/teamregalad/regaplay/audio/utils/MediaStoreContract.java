package fr.isen.cir58.teamregalad.regaplay.audio.utils;

import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.MediaStore;

/**
 * Created by aymeric on 10/29/15.
 */
public class MediaStoreContract implements BaseColumns {
    // ARTISTS

    // Field names
    public static final String ARTIST_ARTIST = "artist";
    public static final String ARTIST_ARTIST_KEY = "artist_key";
    public static final String ARTIST_NUMBER_OF_ALBUMS = "number_of_albums";
    public static final String ARTIST_NUMBER_OF_TRACKS = "number_of_tracks";

    // Table name
    public static final Uri TABLE_ARTISTS = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;

    // Projections
    public static final String[] ARTIST_PROJECTION_FULL = new String[] {
            ARTIST_ARTIST,
            ARTIST_ARTIST_KEY,
            ARTIST_NUMBER_OF_ALBUMS,
            ARTIST_NUMBER_OF_TRACKS
    };

    // Selections
    public static final String ARTIST_SELCTION_BY_ARTIST = ARTIST_ARTIST + "=?";
    public static final String ARTIST_SELCTION_BY_ARTIST_KEY = ARTIST_ARTIST_KEY + "=?";
    public static final String ARTIST_SELCTION_BY_NUMBER_OF_ALBUMS = ARTIST_NUMBER_OF_ALBUMS + "=?";
    public static final String ARTIST_SELCTION_BY_NUMBER_OF_TRACKS = ARTIST_NUMBER_OF_TRACKS + "=?";

    // Sort order
    public static final String ARIST_ORDER_BY_ARTIST_ASC = ARTIST_ARTIST + " ASC";
    public static final String ARIST_ORDER_BY_ARTIST_KEY_ASC = ARTIST_ARTIST_KEY + " ASC";
    public static final String ARIST_ORDER_BY_NUMBER_OF_ALBUMS_ASC = ARTIST_NUMBER_OF_ALBUMS + " ASC";
    public static final String ARIST_ORDER_BY_NUMBER_OF_TRACKS_ASC = ARTIST_NUMBER_OF_TRACKS + " ASC";
    public static final String ARIST_ORDER_BY_ARTIST_DESC = ARTIST_ARTIST + " DESC";
    public static final String ARIST_ORDER_BY_ARTIST_KEY_DESC = ARTIST_ARTIST_KEY + " DESC";
    public static final String ARIST_ORDER_BY_NUMBER_OF_ALBUMS_DESC = ARTIST_NUMBER_OF_ALBUMS + " DESC";
    public static final String ARIST_ORDER_BY_NUMBER_OF_TRACKS_DESC = ARTIST_NUMBER_OF_TRACKS + " DESC";


}
