package fr.isen.cir58.teamregalad.regaplay.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreHelper;
import fr.isen.cir58.teamregalad.regaplay.utils.Constants;

/**
 * Created by aymeric on 11/1/15.
 */
public class SongClickedReceiver extends BroadcastReceiver {
    private long id;
    private SongClickedListener mlistener;

    @Override
    public void onReceive(Context context, Intent intent) {
        id = intent.getExtras().getLong(Constants.Audio.ACTION_SONG_CLICKED_ID);
        String path = MediaStoreHelper.getSongPathById(RegaPlayApplication.getContext(), id);
        Log.d(this.getClass().toString(), path);

        if(mlistener != null){
            mlistener.onSongClicked(id);
        }



    }
    public interface SongClickedListener{
        public void onSongClicked(long id);
    }
    public void setListener(SongClickedListener listener){
        mlistener = listener;

    }
}
