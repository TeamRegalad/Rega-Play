package fr.isen.cir58.teamregalad.regaplay.audio;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreContract;

/**
 * Created by Thomas Fossati on 26/10/2015.
 */
public class MetaDataFetcher {

    public static ArrayList<Song> getAudioFilesFromMediaStore(ContentResolver contentResolver) {
        ArrayList<Song> songsList = new ArrayList<>();
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0 ";


        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.TRACK,
                MediaStore.Audio.Media.YEAR,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST_ID,
                MediaStore.Audio.Media.ARTIST
        };


        Cursor mediaCursor = contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                MediaStore.Audio.Media._ID);


        int _ID_Column = mediaCursor.getColumnIndex(MediaStore.Audio.Media._ID);
        int DATA_Column = mediaCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
        int TRACK_Column = mediaCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int YEAR_Column = mediaCursor.getColumnIndex(MediaStore.Audio.Media.YEAR);
        int DURATION_Column = mediaCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
        int ALBUM_ID_Column = mediaCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
        int ALBUM_Column = mediaCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
        int ARTIST_ID_Column = mediaCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID);
        int ARTIST_Column = mediaCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);

        Cursor coverCursor;
        String[] coverProjection = {
                MediaStore.Audio.Albums.ALBUM_ART,
                MediaStore.Audio.Albums._ID
        };

        int albumID = 0;
        while (mediaCursor.moveToNext()) {
            albumID = mediaCursor.getInt(ALBUM_ID_Column);

            final Uri albumArtUri = Uri.parse("content://media/external/audio/albumart");
            Uri uri = ContentUris.withAppendedId(albumArtUri, albumID);


            Song song = new Song(mediaCursor.getInt(_ID_Column),
                    mediaCursor.getString(TRACK_Column),
                    mediaCursor.getString(DATA_Column),
                    mediaCursor.getInt(ARTIST_ID_Column),
                    mediaCursor.getString(ARTIST_Column),
                    albumID,
                    mediaCursor.getString(ALBUM_Column),
                    mediaCursor.getInt(YEAR_Column),
                    mediaCursor.getInt(DURATION_Column));
            //song.setAlbumArtPath(uri.getPath());
            Bitmap bitmap = null;

                /*try {
                    //bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri);
                    song.setAlbumArt(bitmap);
                } catch (Exception e) {
                    Log.e("ERROR",e.getMessage());
                }*/

            songsList.add(song);
        }
        String coverPath = "";
        String coverSelection = MediaStore.Audio.Albums._ID + "=?";
        coverCursor = contentResolver.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, coverProjection, coverSelection, new String[]{String.valueOf(albumID)}, null);

        int COVER_PATH_Column = coverCursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART);

        coverCursor.moveToNext();
        coverPath = coverCursor.getString(COVER_PATH_Column);
        //songsList.get(0).setAlbumArtPath(coverPath);


        coverCursor.close();
        return songsList;
    }

    public static ArrayList<Song> getAudioFilesFromMediaStoreWithGenres(ContentResolver contentResolver) {
        ArrayList<Song> songsList = new ArrayList<>();
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0 ";


        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.TRACK,
                MediaStore.Audio.Media.YEAR,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST_ID,
                MediaStore.Audio.Media.ARTIST
        };
        String[] genresProjection = {
                MediaStore.Audio.Genres.NAME,
                MediaStore.Audio.Genres._ID
        };
        String[] coverProjection = {
                MediaStore.Audio.Albums.ALBUM_ART,
                MediaStore.Audio.Albums._ID
        };


        Cursor mediaCursor = contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                MediaStore.Audio.Media._ID);

        Cursor genresCursor;
        Cursor coverCursor;

        int _ID_Column = mediaCursor.getColumnIndex(MediaStore.Audio.Media._ID);
        int DATA_Column = mediaCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
        int TRACK_Column = mediaCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int YEAR_Column = mediaCursor.getColumnIndex(MediaStore.Audio.Media.YEAR);
        int DURATION_Column = mediaCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
        int ALBUM_ID_Column = mediaCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
        int ALBUM_Column = mediaCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
        int ARTIST_ID_Column = mediaCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID);
        int ARTIST_Column = mediaCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);


        while (mediaCursor.moveToNext()) {
            int trackID = mediaCursor.getInt(_ID_Column);
            String genre = "";
            Uri genresUri = MediaStore.Audio.Genres.getContentUriForAudioId("external", trackID);
            genresCursor = contentResolver.query(genresUri, genresProjection, null, null, null);

            int GENRE_NAME_Column = genresCursor.getColumnIndex(MediaStore.Audio.Genres.NAME);

            while (genresCursor.moveToNext()) {
                genre = genresCursor.getString(GENRE_NAME_Column);
            }
            genresCursor.close();


            int albumID = mediaCursor.getInt(ALBUM_ID_Column);
            String coverPath = "";
            String coverSelection = MediaStore.Audio.Albums._ID + "=?";
            coverCursor = contentResolver.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, coverProjection, coverSelection, new String[]{String.valueOf(albumID)}, null);

            int COVER_PATH_Column = coverCursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART);

            while (coverCursor.moveToNext()) {
                coverPath = coverCursor.getString(COVER_PATH_Column);

            }
            coverCursor.close();

            Song song = new Song(trackID,
                    mediaCursor.getString(TRACK_Column),
                    mediaCursor.getString(DATA_Column),
                    mediaCursor.getInt(ARTIST_ID_Column),
                    mediaCursor.getString(ARTIST_Column),
                    albumID,
                    mediaCursor.getString(ALBUM_Column),
                    mediaCursor.getInt(YEAR_Column),
                    mediaCursor.getInt(DURATION_Column),
                    genre);
            songsList.add(song);
        }
        return songsList;
    }

    public static void getAlbumsFromArtist(String artistName, ContentResolver contentResolver) {

        String selection = MediaStore.Audio.Albums.ARTIST + " = " + artistName;

        String[] projection = {
                MediaStore.Audio.Albums.ALBUM,
                MediaStore.Audio.Albums.ALBUM_ID,
                MediaStore.Audio.Albums.ARTIST,
                MediaStore.Audio.Albums.NUMBER_OF_SONGS
        };
        Cursor artistCursor = contentResolver.query(
                MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                MediaStoreContract.ALBUMS_PROJECTION_FULL,
                MediaStoreContract.ALBUMS_SELECTION_BY_ARTIST,
                new String[]{artistName},
                MediaStoreContract.ALBUMS_ORDER_BY_ALBUM_ASC);
        int ALBUM_Column = artistCursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM);
        int _ID_Column = artistCursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ID);
        int ARTIST_Column = artistCursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST);
        int NBSONGS_Column = artistCursor.getColumnIndex(MediaStore.Audio.Albums.NUMBER_OF_SONGS);

        while (artistCursor.moveToNext()) {
            if (artistCursor.getColumnIndex(MediaStoreContract.ALBUMS_ALBUM) >= 0) {
                Log.d("ArtistQuery", artistCursor.getString(artistCursor.getColumnIndex(MediaStoreContract.ALBUMS_ALBUM)));
            }

            if (artistCursor.getColumnIndex(MediaStoreContract.ALBUMS_ARTIST) >= 0) {
                Log.d("ArtistQuery", artistCursor.getString(artistCursor.getColumnIndex(MediaStoreContract.ALBUMS_ARTIST)));
            }


        }
    }
}
