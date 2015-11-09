package fr.isen.cir58.teamregalad.regaplay.audio;

/**
 * Created by Thomas Fossati on 26/10/2015.
 */
public class Song {

    private long ID;
    private String title;
    private String path;
    private int artistID;
    private String artist;
    private int albumID;
    private String album;
    private int year;
    private int duration;
    private String genre;
    private String coverPath;

    public Song(long ID, String title, String path, int artistID, String artist, int albumID, String album, int year, int duration, String genre, String coverPath) {
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
        this.coverPath = coverPath;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
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

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
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
    public String shareSongInfos(){
        return "I'm listening to " + title + " - " + artist + " (" + album + " - " + year + ") with RegaPlay (https://github.com/TeamRegalad/Rega-Play)";
    }
}
