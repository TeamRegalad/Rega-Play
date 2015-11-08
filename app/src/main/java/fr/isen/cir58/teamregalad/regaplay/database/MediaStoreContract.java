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
    public static final String ARTISTS_SELECTION_BY_ID = ARTISTS_ID + "=?";
    public static final String ARTISTS_SELECTION_BY_ARTIST = ARTISTS_ARTIST + "=?";
    public static final String ARTISTS_SELECTION_BY_ARTIST_KEY = ARTISTS_ARTIST_KEY + "=?";
    public static final String ARTISTS_SELECTION_BY_NUMBER_OF_ALBUMS = ARTISTS_NUMBER_OF_ALBUMS + "=?";
    public static final String ARTISTS_SELECTION_BY_NUMBER_OF_TRACKS = ARTISTS_NUMBER_OF_TRACKS + "=?";

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
    public static final String ALBUMS_SELECTION_BY_ID = ALBUMS_ID + "=?";
    public static final String ALBUMS_SELECTION_BY_ALBUM = ALBUMS_ALBUM + "=?";
    public static final String ALBUMS_SELECTION_BY_ALBUM_ART = ALBUMS_ALBUM_ART + "=?";
    public static final String ALBUMS_SELECTION_BY_ALBUM_KEY = ALBUMS_ALBUM_KEY + "=?";
    public static final String ALBUMS_SELECTION_BY_ARTIST = ALBUMS_ARTIST + "=?";
    public static final String ALBUMS_SELECTION_BY_FIRST_YEAR = ALBUMS_FIRST_YEAR + "=?";
    public static final String ALBUMS_SELECTION_BY_LAST_YEAR = ALBUMS_LAST_YEAR + "=?";
    public static final String ALBUMS_SELECTION_BY_NUMBER_OF_SONGS = ALBUMS_NUMBER_OF_SONGS + "=?";

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
    public static final String GENRES_SELECTION_BY_ID = GENRES_ID + "=?";
    public static final String GENRES_SELECTION_BY_NAME = GENRES_NAME + "=?";

    // Sort order
    public static final String GENRES_ORDER_BY_ID_ASC = GENRES_ID + " ASC";
    public static final String GENRES_ORDER_BY_NAME_ASC = GENRES_NAME + " ASC";
    public static final String GENRES_ORDER_BY_ID_DESC = GENRES_ID + " DESC";
    public static final String GENRES_ORDER_BY_NAME_DESC = GENRES_NAME + " DESC";

    /*
    *
    * SONGS fields and usages
    *
     */

    // Field names
    public static final String SONGS_ID = "_id";
    public static final String SONGS_ALBUM = "album";
    public static final String SONGS_ALBUM_ID = "album_id";
    public static final String SONGS_ALBUM_KEY = "album_key";
    public static final String SONGS_ARTIST = "artist";
    public static final String SONGS_ARTIST_ID = "artist_id";
    public static final String SONGS_ARTIST_KEY = "artist_key";
    public static final String SONGS_BOOKMARK = "bookmark";
    public static final String SONGS_COMPOSER = "composer";
    public static final String SONGS_DATA = "_data";
    public static final String SONGS_DURATION = "duration";
    public static final String SONGS_IS_ALARM = "is_alarm";
    public static final String SONGS_IS_MUSIC = "is_music";
    public static final String SONGS_IS_NOTIFICATION = "is_notification";
    public static final String SONGS_IS_PODCAST = "is_podcast";
    public static final String SONGS_IS_RINGTONE = "is_ringtone";
    public static final String SONGS_TITLE = "title";
    public static final String SONGS_TITLE_KEY = "title_key";
    public static final String SONGS_TRACK = "track";
    public static final String SONGS_YEAR = "year";

    // Table name
    public static final Uri TABLE_SONGS = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

    // Projections
    public static final String[] SONGS_PROJECTION_FULL = new String[]{
            SONGS_ID,
            SONGS_ALBUM,
            SONGS_ALBUM_ID,
            SONGS_ALBUM_KEY,
            SONGS_ARTIST,
            SONGS_ARTIST_ID,
            SONGS_ARTIST_KEY,
            SONGS_BOOKMARK,
            SONGS_COMPOSER,
            SONGS_DATA,
            SONGS_DURATION,
            SONGS_IS_ALARM,
            SONGS_IS_MUSIC,
            SONGS_IS_NOTIFICATION,
            SONGS_IS_PODCAST,
            SONGS_IS_RINGTONE,
            SONGS_TITLE,
            SONGS_TITLE_KEY,
            SONGS_TRACK,
            SONGS_YEAR

    };

    // Selections
    public static final String SONGS_SELECTION_BY_ID = SONGS_ID + "=?";
    public static final String SONGS_SELECTION_BY_ALBUM = SONGS_ALBUM + "=?";
    public static final String SONGS_SELECTION_BY_ALBUM_ID = SONGS_ALBUM_ID + "=?";
    public static final String SONGS_SELECTION_BY_ALBUM_KEY = SONGS_ALBUM_KEY + "=?";
    public static final String SONGS_SELECTION_BY_ARTIST = SONGS_ARTIST + "=?";
    public static final String SONGS_SELECTION_BY_ARTIST_ID = SONGS_ARTIST_ID + "=?";
    public static final String SONGS_SELECTION_BY_ARTIST_KEY = SONGS_ARTIST_KEY + "=?";
    public static final String SONGS_SELECTION_BY_BOOKMARK = SONGS_BOOKMARK + "=?";
    public static final String SONGS_SELECTION_BY_COMPOSER = SONGS_COMPOSER + "=?";
    public static final String SONGS_SELECTION_BY_DURATION = SONGS_DURATION + "=?";
    public static final String SONGS_SELECTION_IS_ALARM = SONGS_IS_ALARM + "!=0";
    public static final String SONGS_SELECTION_IS_MUSIC = SONGS_IS_MUSIC + "!=0";
    public static final String SONGS_SELECTION_IS_NOTIFICATION = SONGS_IS_NOTIFICATION + "!=0";
    public static final String SONGS_SELECTION_IS_PODCAST = SONGS_IS_PODCAST + "!=0";
    public static final String SONGS_SELECTION_IS_RINGTONE = SONGS_IS_RINGTONE + "!=0";
    public static final String SONGS_SELECTION_BY_TITLE = SONGS_TITLE + "=?";
    public static final String SONGS_SELECTION_BY_TITLE_KEY = SONGS_TITLE_KEY + "=?";
    public static final String SONGS_SELECTION_BY_TRACK = SONGS_TRACK + "=?";
    public static final String SONGS_SELECTION_BY_YEAR = SONGS_YEAR + "=?";

    // Sort order
    public static final String SONGS_ORDER_BY_ID_ASC = SONGS_ID + " ASC";
    public static final String SONGS_ORDER_BY_ALBUM_ASC = SONGS_ALBUM + " ASC";
    public static final String SONGS_ORDER_BY_ALBUM_ID_ASC = SONGS_ALBUM_ID + " ASC";
    public static final String SONGS_ORDER_BY_ALBUM_KEY_ASC = SONGS_ALBUM_KEY + " ASC";
    public static final String SONGS_ORDER_BY_ARTIST_ASC = SONGS_ARTIST + " ASC";
    public static final String SONGS_ORDER_BY_ARTIST_ID_ASC = SONGS_ARTIST_ID + " ASC";
    public static final String SONGS_ORDER_BY_ARTIST_KEY_ASC = SONGS_ARTIST_KEY + " ASC";
    public static final String SONGS_ORDER_BY_BOOKMARK_ASC = SONGS_BOOKMARK + " ASC";
    public static final String SONGS_ORDER_BY_COMPOSER_ASC = SONGS_COMPOSER + " ASC";
    public static final String SONGS_ORDER_BY_DURATION_ASC = SONGS_DURATION + " ASC";
    public static final String SONGS_ORDER_BY_TITLE_ASC = SONGS_TITLE + " ASC";
    public static final String SONGS_ORDER_BY_TITLE_KEY_ASC = SONGS_TITLE_KEY + " ASC";
    public static final String SONGS_ORDER_BY_TRACK_ASC = SONGS_TRACK + " ASC";
    public static final String SONGS_ORDER_BY_YEAR_ASC = SONGS_YEAR + " ASC";
    public static final String SONGS_ORDER_BY_ID_DESC = SONGS_ID + " DESC";
    public static final String SONGS_ORDER_BY_ALBUM_DESC = SONGS_ALBUM + " DESC";
    public static final String SONGS_ORDER_BY_ALBUM_ID_DESC = SONGS_ALBUM_ID + " DESC";
    public static final String SONGS_ORDER_BY_ALBUM_KEY_DESC = SONGS_ALBUM_KEY + " DESC";
    public static final String SONGS_ORDER_BY_ARTIST_DESC = SONGS_ARTIST + " DESC";
    public static final String SONGS_ORDER_BY_ARTIST_ID_DESC = SONGS_ARTIST_ID + " DESC";
    public static final String SONGS_ORDER_BY_ARTIST_KEY_DESC = SONGS_ARTIST_KEY + " DESC";
    public static final String SONGS_ORDER_BY_BOOKMARK_DESC = SONGS_BOOKMARK + " DESC";
    public static final String SONGS_ORDER_BY_COMPOSER_DESC = SONGS_COMPOSER + " DESC";
    public static final String SONGS_ORDER_BY_DURATION_DESC = SONGS_DURATION + " DESC";
    public static final String SONGS_ORDER_BY_TITLE_DESC = SONGS_TITLE + " DESC";
    public static final String SONGS_ORDER_BY_TITLE_KEY_DESC = SONGS_TITLE_KEY + " DESC";
    public static final String SONGS_ORDER_BY_TRACK_DESC = SONGS_TRACK + " DESC";
    public static final String SONGS_ORDER_BY_YEAR_DESC = SONGS_YEAR + " DESC";

}
