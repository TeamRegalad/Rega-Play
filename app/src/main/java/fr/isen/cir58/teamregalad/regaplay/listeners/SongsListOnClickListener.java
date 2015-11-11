package fr.isen.cir58.teamregalad.regaplay.listeners;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.utils.Constants;
import fr.isen.cir58.teamregalad.regaplay.view.SongsListViewHolder;

/**
 * Created by aymeric on 11/1/15.
 */
public class SongsListOnClickListener implements View.OnClickListener {
    private SongsListViewHolder songsListViewHolder;
    private String clickOrigin;
    private String clickOriginName;
    private long genreId;

    public SongsListOnClickListener(SongsListViewHolder songsListViewHolder, String origin, String originName) {
        this.songsListViewHolder = songsListViewHolder;
        clickOrigin = origin;
        clickOriginName = originName;
    }
    public SongsListOnClickListener(SongsListViewHolder songsListViewHolder, String origin, long genreId){
        this.songsListViewHolder = songsListViewHolder;
        clickOrigin = origin;
        this.genreId = genreId;
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        Bundle extras = new Bundle();


        if (clickOrigin.equals(Constants.SongClickedOrigin.ARTIST)){
            intent = new Intent(Constants.Audio.ACTION_PLAYLIST_ARTIST_CLICKED);
            extras.putString(Constants.Audio.ACTION_PLAYLIST_ARTIST_CLICKED_NAME, clickOriginName);
        }else if(clickOrigin.equals(Constants.SongClickedOrigin.ALBUM)) {
            intent = new Intent(Constants.Audio.ACTION_PLAYLIST_ALBUM_CLICKED);
            extras.putString(Constants.Audio.ACTION_PLAYLIST_ALBUM_CLICKED_NAME, clickOriginName);
        }else if(clickOrigin.equals(Constants.SongClickedOrigin.GENRE)) {
            intent = new Intent(Constants.Audio.ACTION_PLAYLIST_GENRE_CLICKED);
            extras.putLong(Constants.Audio.ACTION_PLAYLIST_GENRE_CLICKED_ID, genreId);
        } else if(clickOrigin.equals(Constants.SongClickedOrigin.SONGS)){
            intent = new Intent(Constants.Audio.ACTION_SONG_CLICKED_WITH_ID);
            extras.putLong(Constants.Audio.ACTION_SONG_CLICKED_ID, songsListViewHolder.id);
        }else{
            return;
        }

        extras.putInt(Constants.Audio.ACTION_SONG_CLICKED_POSITION, songsListViewHolder.position);
        intent.putExtras(extras);
        RegaPlayApplication.getContext().sendBroadcast(intent);
    }
}
