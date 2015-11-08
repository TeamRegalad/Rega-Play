package fr.isen.cir58.teamregalad.regaplay.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.ui.fragments.GenreSongsFragment;

/**
 * Created by paul on 11/6/15.
 */
public class GenreListActivity extends AudioActivity {
    private String genreName;
    private long genreId;
    private GenreSongsFragment genreSongsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genre_list_activity);
        genreName = this.getIntent().getExtras().getString("GenreName");
        genreId = this.getIntent().getExtras().getLong("GenreId");

        Toolbar toolbar = (Toolbar) findViewById(R.id.genre_list_activity_toolbar);
        toolbar.setTitle(genreName);

        this.genreSongsFragment = new GenreSongsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.genre_list_activity_relative_layout, genreSongsFragment);
        transaction.commit();

        commitPlayerFragment(R.id.genre_list_activity_root);

    }

    public long getGenreId() {
        return genreId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}