package fr.isen.cir58.teamregalad.regaplay.database;

import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.MediaStore;

/**
 * Created by aymeric on 10/29/15.
 */
public class MediaStoreContract implements BaseColumns {
    /*
    *
    * ARTISTS fields and usages
    *
     */

    // Field names
    public static final String ARTISTS_ID = "_id";
    public static final String ARTISTS_ARTIST = "artist";
    public static final String ARTISTS_ARTIST_KEY = "artist_key";
    public static final String ARTISTS_NUMBER_OF_ALBUMS = "number_of_albums";
    public static final String ARTISTS_NUMBER_OF_TRACKS = "number_of_tracks";

    // Table name
    public static final Uri TABLE_ARTISTS = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;

    // Projections
    public static final String[] ARTISTS_PROJECTION_FULL = new String[]{
            ARTISTS_ID,
            ARTISTS_ARTIST,
            ARTISTS_ARTIST_KEY,
            ARTISTS_NUMBER_OF_ALBUMS,
            ARTISTS_NUMBER_OF_TRACKS
    };

    // Selections
    public static final String ARTISTS_SELCTION_BY_ID = ARTISTS_ID + "=?";
    public static final String ARTISTS_SELCTION_BY_ARTIST = ARTISTS_ARTIST + "=?";
    public static final String ARTISTS_SELCTION_BY_ARTIST_KEY = ARTISTS_ARTIST_KEY + "=?";
    public static final String ARTISTS_SELCTION_BY_NUMBER_OF_ALBUMS = ARTISTS_NUMBER_OF_ALBUMS + "=?";
    public static final String ARTISTS_SELCTION_BY_NUMBER_OF_TRACKS = ARTISTS_NUMBER_OF_TRACKS + "=?";

    // Sort order
    public static final String ARTISTS_ORDER_BY_ID_ASC = ARTISTS_ID + " ASC";
    public static final String ARTISTS_ORDER_BY_ARTIST_ASC = ARTISTS_ARTIST + " ASC";
    public static final String ARTISTS_ORDER_BY_ARTIST_KEY_ASC = ARTISTS_ARTIST_KEY + " ASC";
    public static final String ARTISTS_ORDER_BY_NUMBER_OF_ALBUMS_ASC = ARTISTS_NUMBER_OF_ALBUMS + " ASC";
    public static final String ARTISTS_ORDER_BY_NUMBER_OF_TRACKS_ASC = ARTISTS_NUMBER_OF_TRACKS + " ASC";
    public static final String ARTISTS_ORDER_BY_ID_DESC = ARTISTS_ID + " DESC";
    public static final String ARTISTS_ORDER_BY_ARTIST_DESC = ARTISTS_ARTIST + " DESC";
    public static final String ARTISTS_ORDER_BY_ARTIST_KEY_DESC = ARTISTS_ARTIST_KEY + " DESC";
    public static final String ARTISTS_ORDER_BY_NUMBER_OF_ALBUMS_DESC = ARTISTS_NUMBER_OF_ALBUMS + " DESC";
    public static final String ARTISTS_ORDER_BY_NUMBER_OF_TRACKS_DESC = ARTISTS_NUMBER_OF_TRACKS + " DESC";

    /*
    *
    * ALBUMS fields and usages
    *
     */

    // Field names
    public static final String ALBUMS_ID = "_id";
    public static final String ALBUMS_ALBUM = "album";
    public static final String ALBUMS_ALBUM_ART = "album_art";
    public static final String ALBUMS_ALBUM_KEY = "album_key";
    public static final String ALBUMS_ARTIST = "artist";
    public static final String ALBUMS_FIRST_YEAR = "minyear";
    public static final String ALBUMS_LAST_YEAR = "maxyear";
    public static final String ALBUMS_NUMBER_OF_SONGS = "numsongs";

    // Table name
    public static final Uri TABLE_ALBUMS = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;

    // Projections
    public static final String[] ALBUMS_PROJECTION_FULL = new String[]{
            ALBUMS_ID,
            ALBUMS_ALBUM,
            ALBUMS_ALBUM_ART,
            ALBUMS_ALBUM_KEY,
            ALBUMS_ARTIST,
            ALBUMS_FIRST_YEAR,
            ALBUMS_LAST_YEAR,
            ALBUMS_NUMBER_OF_SONGS
    };

    // Selections
    public static final String ALBUMS_SELCTION_BY_ID = ALBUMS_ID + "=?";
    public static final String ALBUMS_SELCTION_BY_ALBUM = ALBUMS_ALBUM + "=?";
    public static final String ALBUMS_SELCTION_BY_ALBUM_ART = ALBUMS_ALBUM_ART + "=?";
    public static final String ALBUMS_SELCTION_BY_ALBUM_KEY = ALBUMS_ALBUM_KEY + "=?";
    public static final String ALBUMS_SELCTION_BY_ARTIST = ALBUMS_ARTIST + "=?";
    public static final String ALBUMS_SELCTION_BY_FIRST_YEAR = ALBUMS_FIRST_YEAR + "=?";
    public static final String ALBUMS_SELCTION_BY_LAST_YEAR = ALBUMS_LAST_YEAR + "=?";
    public static final String ALBUMS_SELCTION_BY_NUMBER_OF_SONGS = ALBUMS_NUMBER_OF_SONGS + "=?";

    // Sort order
    public static final String ALBUMS_ORDER_BY_ID_ASC = ALBUMS_ID + " ASC";
    public static final String ALBUMS_ORDER_BY_ALBUM_ASC = ALBUMS_ALBUM + " ASC";
    public static final String ALBUMS_ORDER_BY_ALBUM_ART_ASC = ALBUMS_ALBUM_ART + " ASC";
    public static final String ALBUMS_ORDER_BY_ALBUM_KEY_ASC = ALBUMS_ALBUM_KEY + " ASC";
    public static final String ALBUMS_ORDER_BY_ARTIST_ASC = ALBUMS_ARTIST + " ASC";
    public static final String ALBUMS_ORDER_BY_FIRST_YEAR_ASC = ALBUMS_FIRST_YEAR + " ASC";
    public static final String ALBUMS_ORDER_BY_LAST_YEAR_ASC = ALBUMS_LAST_YEAR + " ASC";
    public static final String ALBUMS_ORDER_BY_NUMBER_OF_SONGS_ASC = ALBUMS_NUMBER_OF_SONGS + " ASC";
    public static final String ALBUMS_ORDER_BY_ID_DESC = ALBUMS_ID + " DESC";
    public static final String ALBUMS_ORDER_BY_ALBUM_DESC = ALBUMS_ALBUM + " DESC";
    public static final String ALBUMS_ORDER_BY_ALBUM_ART_DESC = ALBUMS_ALBUM_ART + " DESC";
    public static final String ALBUMS_ORDER_BY_ALBUM_KEY_DESC = ALBUMS_ALBUM_KEY + " DESC";
    public static final String ALBUMS_ORDER_BY_ARTIST_DESC = ALBUMS_ARTIST + " DESC";
    public static final String ALBUMS_ORDER_BY_FIRST_YEAR_DESC = ALBUMS_FIRST_YEAR + " DESC";
    public static final String ALBUMS_ORDER_BY_LAST_YEAR_DESC = ALBUMS_LAST_YEAR + " DESC";
    public static final String ALBUMS_ORDER_BY_NUMBER_OF_SONGS_DESC = ALBUMS_NUMBER_OF_SONGS + " DESC";

    /*
    *
    * GENRES fields and usages
    *
     */

    // Field names
    public static final String GENRES_ID = "_id";
    public static final String GENRES_NAME = "name";

    // Table name
    public static final Uri TABLE_GENRES = MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI;

    // Projections
    public static final String[] GENRES_PROJECTION_FULL = new String[]{
            GENRES_ID,
            GENRES_NAME
    };

    // Selections
    public static final String GENRES_SELCTION_BY_ID = GENRES_ID + "=?";
    public static final String GENRES_SELCTION_BY_NAME = GENRES_NAME + "=?";

    // Sort order
    public static final String GENRES_ORDER_BY_ID_ASC = GENRES_ID + " ASC";
    public static final String GENRES_ORDER_BY_NAME_ASC = GENRES_NAME + " ASC";
    public static final String GENRES_ORDER_BY_ID_DESC = GENRES_ID + " DESC";
    public static final String GENRES_ORDER_BY_NAME_DESC = GENRES_NAME + " DESC";

}
