package fr.isen.cir58.teamregalad.regaplay.audio;

import java.util.ArrayList;
import java.util.Collections;

import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreHelper;

public class Playlist {
    private ArrayList<Song> songsList;
    private Integer currentIndexSong;


    public Playlist(int beginIndex) {
        songsList = new ArrayList<Song>();
        currentIndexSong = beginIndex;
    }

    public void addSong(Long id) {
        if (id != null) {

            songsList.add(songsList.size(), MediaStoreHelper.getSong(id));

        }
    }

    public void addSong(Song song) {
        if (song != null) {
            songsList.add(songsList.size(), song);
        }
    }


    public void nextSong() {
        if (currentIndexSong < songsList.size() - 1) {
            currentIndexSong++;
        }

    }

    public void previousSong() {
        if (currentIndexSong > 0) {
            currentIndexSong--;
        }

    }

    public void randomize() {
        Collections.shuffle(songsList);
    }

    public Song getCurrentSong() {
        return songsList.get(currentIndexSong);
    }

    public Integer getCurrentIndexSong() {
        return currentIndexSong;
    }

    public ArrayList<Song> getSongsList() {
        return songsList;
    }

    public String getPlaylistSizeToString() {
        Integer size = songsList.size();
        return size.toString();
    }

    public String getCurrentIndexSongToString() {
        Integer index = currentIndexSong + 1;
        return index.toString();
    }
}
