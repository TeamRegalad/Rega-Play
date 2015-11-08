package fr.isen.cir58.teamregalad.regaplay.async;

import android.os.AsyncTask;

import fr.isen.cir58.teamregalad.regaplay.audio.Song;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreHelper;
import fr.isen.cir58.teamregalad.regaplay.ui.fragments.PlayerFragment;

/**
 * Created by Thomas Fossati on 07/11/2015.
 */
public class GetSongDataAsyncTask extends AsyncTask<Long, Integer, Song> {
    private PlayerFragment playerFragment;
    private Long id;

    public GetSongDataAsyncTask(PlayerFragment playerFragment, Long id) {
        this.playerFragment = playerFragment;
        this.id = id;
    }

    @Override
    protected Song doInBackground(Long... params) {
        return MediaStoreHelper.getSong(id);
    }

    @Override
    protected void onPostExecute(Song song) {
        super.onPostExecute(song);

        playerFragment.setNewSong(song);
    }
}
