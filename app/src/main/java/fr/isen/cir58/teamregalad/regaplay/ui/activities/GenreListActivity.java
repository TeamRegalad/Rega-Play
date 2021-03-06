package fr.isen.cir58.teamregalad.regaplay.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.listeners.GenrePlaylistOnClickListener;
import fr.isen.cir58.teamregalad.regaplay.ui.fragments.GenreSongsFragment;
import fr.isen.cir58.teamregalad.regaplay.utils.DrawerUtils;

/**
 * Created by paul on 11/6/15.
 */
public class GenreListActivity extends AudioActivity {
    private String genreName;
    private long genreId;
    private GenreSongsFragment genreSongsFragment;
    private SlidingUpPanelLayout slidingUpPanelLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genre_list_activity);

        //Navigation slider
        DrawerUtils drawer = new DrawerUtils(this);
        drawer.initializeDrawer(this);


        genreName = this.getIntent().getExtras().getString("GenreName");
        genreId = this.getIntent().getExtras().getLong("GenreId");

        Toolbar toolbar = (Toolbar) findViewById(R.id.genre_list_activity_toolbar);
        toolbar.setTitle(genreName);

        // Adding FloatingActionButton

        ImageButton fabButton = (ImageButton) findViewById(R.id.genre_list_activity_fab);
        fabButton.setOnClickListener(new GenrePlaylistOnClickListener(genreId));

        commitPlayerFragment(R.id.genre_list_activity_player_layout);

        if (savedInstanceState == null) {
            this.genreSongsFragment = new GenreSongsFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.genre_list_activity_relative_layout, genreSongsFragment);
            transaction.commit();
        } else {
            updatePlayerFragment();
        }
    }

    public long getGenreId() {
        return genreId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
