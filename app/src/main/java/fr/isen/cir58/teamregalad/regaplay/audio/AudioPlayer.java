package fr.isen.cir58.teamregalad.regaplay.audio;

import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;

import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;

/**
 * Created by thomas on 22/10/15.
 */
public class AudioPlayer {
    private static AudioPlayer ourInstance = new AudioPlayer();
    private static MediaPlayer mediaPlayer = new MediaPlayer();

    public static AudioPlayer getInstance() {
        return ourInstance;
    }

    private AudioPlayer() {
        mediaPlayer.setAudioStreamType(android.media.AudioManager.STREAM_MUSIC);
    }
    public void playTrack(Uri trackPath){
        try{
            mediaPlayer.setDataSource(RegaPlayApplication.getContext(), trackPath);
            mediaPlayer.prepareAsync();
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
