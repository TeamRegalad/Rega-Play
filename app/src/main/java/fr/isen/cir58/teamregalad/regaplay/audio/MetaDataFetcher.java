package fr.isen.cir58.teamregalad.regaplay.audio;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;

/**
 * Created by Thomas Fossati on 26/10/2015.
 */
public class MetaDataFetcher {
    public static ArrayList<Song> getAudioFilesFromMediaStore(ContentResolver contentResolver){
        ArrayList<Song> songsList = new ArrayList<>();
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0 " ;

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


        Cursor cursor = contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                MediaStore.Audio.Media._ID);

        int _ID_Column = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
        int DATA_Column = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
        int TRACK_Column = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int YEAR_Column = cursor.getColumnIndex(MediaStore.Audio.Media.YEAR);
        int DURATION_Column = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
        int ALBUM_ID_Column = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
        int ALBUM_Column = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
        int ARTIST_ID_Column = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID);
        int ARTIST_Column = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);


        while(cursor.moveToNext())
        {
            Song song = new Song(cursor.getInt(_ID_Column),
                    cursor.getString(TRACK_Column),
                    cursor.getString(DATA_Column),
                    cursor.getInt(ARTIST_ID_Column),
                    cursor.getString(ARTIST_Column),
                    cursor.getInt(ALBUM_ID_Column),
                    cursor.getString(ALBUM_Column),
                    cursor.getInt(YEAR_Column),
                    cursor.getInt(DURATION_Column));
            songsList.add(song);

        }
        return songsList;
    }
}
