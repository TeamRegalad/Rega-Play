package fr.isen.cir58.teamregalad.regaplay.audio;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * Created by Thomas Fossati on 26/10/2015.
 */
public class Song {

    private int ID;
    private String title;
    private String path;
    private int artistID;
    private String artist;
    private int albumID;
    private String album;
    private int year;
    private int duration;
    private String genre;
    private Bitmap albumArt;
    private static String albumArtPath;

    public Song(int ID, String title, String path, int artistID, String artist, int albumID, String album, int year, int duration, String genre, String albumArtPath) {
        this.ID = ID;
        this.title = title;
        this.path = path;
        this.artistID = artistID;
        this.artist = artist;
        this.albumID = albumID;
        this.album = album;
        this.year = year;
        this.duration = duration;
        this.genre = genre;
        this.albumArtPath = albumArtPath;
        this.albumArt = null;
    }

    public Song(int ID, String title, String path, int artistID, String artist, int albumID, String album, int year, int duration) {
        this.ID = ID;
        this.title = title;
        this.path = path;
        this.artistID = artistID;
        this.artist = artist;
        this.albumID = albumID;
        this.album = album;
        this.year = year;
        this.duration = duration;
        this.genre = "";
        this.albumArtPath = "";
        this.albumArt = null;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getArtistID() {
        return artistID;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAlbumArtPath() {
        return albumArtPath;
    }

    public void setAlbumArtPath(String albumArtPath) {
        this.albumArtPath = albumArtPath;
    }

    public Bitmap getAlbumArt() {
        return albumArt;
    }

    public void setAlbumArt(Bitmap albumArt) {
        this.albumArt = albumArt;
    }

    @Override
    public String toString() {
        return title + " (ID " + String.valueOf(ID) + ") " + "\n" +
                artist + " (ID " + String.valueOf(artistID) + ") " + "\n" +
                album + " (ID " + String.valueOf(albumID) + ") " + "\n" +
                year + "\n" +
                duration + "\n" +
                genre + "\n " +
                path;
    }
}
