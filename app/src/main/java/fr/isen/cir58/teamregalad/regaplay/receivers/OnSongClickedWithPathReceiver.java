package fr.isen.cir58.teamregalad.regaplay.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import fr.isen.cir58.teamregalad.regaplay.utils.Constants;

/**
 * Created by aymeric on 11/9/15.
 */
public class OnSongClickedWithPathReceiver extends BroadcastReceiver {
    private OnSongClickedWithPathListener mListener;

    public OnSongClickedWithPathReceiver(OnSongClickedWithPathListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String path = intent.getExtras().getString(Constants.Audio.ACTION_SONG_CLICKED_PATH);
        mListener.OnSongClickedWithPath(path);
    }

    public interface OnSongClickedWithPathListener {
        void OnSongClickedWithPath(String path);
    }
}
