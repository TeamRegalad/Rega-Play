package fr.isen.cir58.teamregalad.regaplay.database;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

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
                path = cursor.getString(0);
            }

            if (!cursor.isClosed()) {
                cursor.close();
            }
        }

        return path;
    }
}
