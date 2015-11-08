package fr.isen.cir58.teamregalad.regaplay.ui.genreList;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.ui.AudioActivity;

/**
 * Created by paul on 11/6/15.
 */
public class GenreListActivity extends AudioActivity {
    private String genreName;
    private GenreSongsFragment genreSongsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genre_list_activity);
        genreName = this.getIntent().getExtras().getString("GenreName");

        Toolbar toolbar = (Toolbar) findViewById(R.id.genre_list_activity_toolbar);
        toolbar.setTitle(genreName);

        this.genreSongsFragment = new GenreSongsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.genre_list_activity_relative_layout, genreSongsFragment);
        transaction.commit();
    }

    public String getGenreName() {
        return genreName;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
