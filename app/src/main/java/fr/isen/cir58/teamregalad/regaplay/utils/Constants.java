package fr.isen.cir58.teamregalad.regaplay.utils;

import android.os.Handler;

/**
 * Created by aymeric on 11/1/15.
 */
public class Constants {
    public static Handler PROGRESSBAR_HANDLER;

    public class Audio {
        public static final String ACTION_SONG_CLICKED_WITH_ID = "actionSongClickedWithId";
        public static final String ACTION_SONG_CLICKED_ID = "actionSongClickedId";
        public static final String ACTION_SONG_CLICKED_WITH_PATH = "actionSongClickedWithPath";
        public static final String ACTION_SONG_CLICKED_PATH = "actionSongClickedPath";
        public static final String ACTION_SONG_CHANGED = "actionSongChanged";
        public static final String ACTION_SONG_CHANGED_SONG = "actionSongChangedSong";
    }

    public class Playlist {
        public static final String ACTION_PLAYLIST_ARTIST_CLICKED = "actionPlaylistArtistClicked";
        public static final String ACTION_PLAYLIST_ARTIST_CLICKED_NAME = "actionPlaylistArtistClickedName";

        public static final String ACTION_PLAYLIST_ALBUM_CLICKED = "actionPlaylistAlbumClicked";
        public static final String ACTION_PLAYLIST_ALBUM_CLICKED_NAME = "actionPlaylistAlbumClickedName";

        public static final String ACTION_PLAYLIST_GENRE_CLICKED = "actionPlaylistGenreClicked";
        public static final String ACTION_PLAYLIST_GENRE_CLICKED_ID = "actionPlaylistGenreClickedId";

        public static final String ACTION_RANDOM_PLAYLIST_CLICKED = "actionRandomPlaylistClicked";

        public static final String ACTION_ADD_TO_PLAYLIST_CLICKED = "actionAddToPlaylistClicked";
        public static final String ACTION_ADD_TO_PLAYLIST_CLICKED_ID = "actionAddToPlaylistClickedId";
    }
}
