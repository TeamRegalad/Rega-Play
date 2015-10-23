package fr.isen.cir58.teamregalad.regaplay.audio;

import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;

import fr.isen.cir58.teamregalad.regaplay.MainActivity;
import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;

/**
 * Created by thomas on 22/10/15.
 */
public class AudioManager {
    private static AudioManager ourInstance = new AudioManager();
    private static MediaPlayer mediaPlayer = new MediaPlayer();

    public static AudioManager getInstance() {
        return ourInstance;
    }

    private AudioManager() {
        mediaPlayer.setAudioStreamType(android.media.AudioManager.STREAM_MUSIC);
    }
    public void playTrack(Uri trackPath){
        try{
            mediaPlayer.setDataSource(RegaPlayApplication.getContext(), trackPath);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e){
            Log.e("ERROR", e.getMessage());
        }
    }
    public void pauseTrack(){
        if(mediaPlayer.isPlaying()){
            //TODO
        }
    }
    public void stopTrack(){
        //TODO
    }
    public void previousTrack(){

    }
    public void nextTrack(){

    }

}
