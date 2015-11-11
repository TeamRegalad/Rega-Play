package fr.isen.cir58.teamregalad.regaplay.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import fr.isen.cir58.teamregalad.regaplay.audio.Song;
import fr.isen.cir58.teamregalad.regaplay.utils.Constants;

/**
 * Created by aymeric on 11/11/15.
 */
public class OnSongChangedReceiver extends BroadcastReceiver {
    private OnSongChangedListener mOnSongChangedListener;

    public OnSongChangedReceiver(OnSongChangedListener mOnSongChangedListener) {
        this.mOnSongChangedListener = mOnSongChangedListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Song song = intent.getExtras().getParcelable(Constants.Audio.ACTION_SONG_CHANGED_SONG);
        mOnSongChangedListener.onSongChanged(song);
    }

    public interface OnSongChangedListener {
        void onSongChanged(Song song);
    }
}
