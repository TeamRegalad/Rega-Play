package fr.isen.cir58.teamregalad.regaplay.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import fr.isen.cir58.teamregalad.regaplay.utils.Constants;

/**
 * Created by aymeric on 11/8/15.
 */
public class OnSongClickedWithIdReceiver extends BroadcastReceiver {
    private OnSongClickedWithIdListener mListener;

    public OnSongClickedWithIdReceiver(OnSongClickedWithIdListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Long id = intent.getExtras().getLong(Constants.Audio.ACTION_SONG_CLICKED_ID);
        mListener.onSongClickedWithId(id);
    }

    public interface OnSongClickedWithIdListener {
        void onSongClickedWithId(Long id);
    }
}
