package fr.isen.cir58.teamregalad.regaplay.database;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.audio.Song;

/**
 * Created by aymeric on 10/31/15.
 */
public class MediaStoreHelper implements BaseColumns {
    public static String getAlbumArt(Context context, String albumKey) {
        String path = null;

        Uri table = MediaStoreContract.TABLE_ALBUMS;
        String[] projection = new String[]{MediaStoreContract.ALBUMS_ALBUM_ART};
        String selection = MediaStoreContract.ALBUMS_SELECTION_BY_ALBUM_KEY;
        String[] selectionArgs = new String[]{albumKey};
        String sortOrder = null;

        Cursor cursor = context.getContentResolver().query(table, projection, selection, selectionArgs, sortOrder);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStoreContract.ALBUMS_ALBUM_ART));
            }

            if (!cursor.isClosed()) {
                cursor.close();
            }
        }

        return path;
    }

    public static String getGenre(Context context, Long id) {
        String genre = null;

        Uri table = MediaStoreContract.TABLE_GENRES;
        String[] projection = new String[]{MediaStoreContract.GENRES_NAME};
        String selection = MediaStoreContract.GENRES_SELECTION_BY_ID;
        String[] selectionArgs = new String[]{id.toString()};
        String sortOrder = null;

        Cursor cursor = context.getContentResolver().query(table, projection, selection, selectionArgs, sortOrder);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                genre = cursor.getString(cursor.getColumnIndex(MediaStoreContract.GENRES_NAME));
            }

            if (!cursor.isClosed()) {
                cursor.close();
            }
        }

        return genre;
    }

    public static String getSongPath(Context context, Long id) {
        String path = null;

        Uri table = MediaStoreContract.TABLE_SONGS;
        String[] projection = MediaStoreContract.SONGS_PROJECTION_FULL;
        String selection = MediaStoreContract.SONGS_SELECTION_BY_ID;
        String[] selectionArgs = new String[]{id.toString()};
        String sortOrder = null;

        Cursor cursor = context.getContentResolver().query(table, projection, selection, selectionArgs, sortOrder);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStoreContract.SONGS_DATA));
            }

            if (!cursor.isClosed()) {
                cursor.close();
            }
        }

        return path;
    }

    public static Song getSong(Long id) {
        Uri table = MediaStoreContract.TABLE_SONGS;
        String[] projection = MediaStoreContract.SONGS_PROJECTION_FULL;
        String selection = MediaStoreContract.SONGS_SELECTION_BY_ID;
        String[] selectionArgs = new String[]{id.toString()};
        String sortOrder = null;

        Song song = null;
        Cursor cursor = RegaPlayApplication.getContext().getContentResolver().query(table, projection, selection, selectionArgs, sortOrder);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                song = new Song(
                        id,
                        cursor.getString(cursor.getColumnIndex(MediaStoreContract.SONGS_TITLE)),
                        cursor.getString(cursor.getColumnIndex(MediaStoreContract.SONGS_DATA)),
                        cursor.getInt(cursor.getColumnIndex(MediaStoreContract.SONGS_ARTIST_ID)),
                        cursor.getString(cursor.getColumnIndex(MediaStoreContract.SONGS_ARTIST)),
                        cursor.getInt(cursor.getColumnIndex(MediaStoreContract.ALBUMS_ID)),
                        cursor.getString(cursor.getColumnIndex(MediaStoreContract.SONGS_ALBUM)),
                        cursor.getInt(cursor.getColumnIndex(MediaStoreContract.SONGS_YEAR)),
                        cursor.getInt(cursor.getColumnIndex(MediaStoreContract.SONGS_DURATION)),
                        MediaStoreHelper.getGenre(RegaPlayApplication.getContext(), cursor.getLong(cursor.getColumnIndex(MediaStoreContract.SONGS_ID))),
                        MediaStoreHelper.getAlbumArt(RegaPlayApplication.getContext(), cursor.getString(cursor.getColumnIndex(MediaStoreContract.SONGS_ALBUM_KEY)))
                );
            }

            if (!cursor.isClosed()) {
                cursor.close();
            }
        }

        return song;
    }
}
