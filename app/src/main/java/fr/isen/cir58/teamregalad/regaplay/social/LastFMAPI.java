package fr.isen.cir58.teamregalad.regaplay.social;

import android.util.Log;

import java.net.URLEncoder;
/**
 * Created by thomas on 31/10/15.
 */
public class LastFMAPI implements LastFMApiAsyncTaskListner{
    public void getArtistInfo(String artistName){

        new LastFMApiAsyncTask(this).execute(artistName);



    }

    @Override
    public void onTextRetrieved(String text) {
        Log.d("TEST",text);
    }
}
