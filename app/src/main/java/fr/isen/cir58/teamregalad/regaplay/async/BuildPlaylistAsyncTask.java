package fr.isen.cir58.teamregalad.regaplay.async;

import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.audio.Playlist;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreContract;
import fr.isen.cir58.teamregalad.regaplay.ui.activities.AudioActivity;

public class BuildPlaylistAsyncTask extends AsyncTask<Long, Integer, Playlist> {

    private Uri table;
    private String[] projection;
    private String selection;
    private String[] arguments;
    private String sortOrder;
    private AudioActivity audioActivity;
    private int beginIndex;

    public BuildPlaylistAsyncTask(AudioActivity audioAct, int position, Uri tab, String[] proj, String sel, String[] args, String order) {
        table = tab;
        projection = proj;
        selection = sel;
        arguments = args;
        sortOrder = order;
        audioActivity = audioAct;
        beginIndex = position;

    }

    @Override
    protected Playlist doInBackground(Long... params) {
        Playlist playlist = new Playlist(beginIndex);

        Cursor cursor = RegaPlayApplication.getContext().getContentResolver().query(table, projection, selection, arguments, sortOrder);

        if (cursor != null) {

            while (cursor.moveToNext()) {
                playlist.addSong(cursor.getLong(cursor.getColumnIndex(MediaStoreContract.SONGS_ID)));
            }
        }

        if (!cursor.isClosed()) {
            cursor.close();
        }
        return playlist;
    }

    @Override
    protected void onPostExecute(Playlist playlist) {
        super.onPostExecute(playlist);
        audioActivity.setPlaylist(playlist);
        audioActivity.songChanged();

    }
}
