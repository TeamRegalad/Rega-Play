package fr.isen.cir58.teamregalad.regaplay.ui.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import de.umass.lastfm.Album;
import de.umass.lastfm.Artist;
import de.umass.lastfm.ImageSize;
import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.adapters.ArtistListsAdapter;
import fr.isen.cir58.teamregalad.regaplay.social.lastfm.ArtistInfosAsyncTask;
import fr.isen.cir58.teamregalad.regaplay.social.lastfm.listeners.LastFMApiAsyncTaskListner;
import fr.isen.cir58.teamregalad.regaplay.utils.DrawerUtils;

/**
 * Created by Thomas Fossati on 03/11/2015.
 */
public class ArtistListsActivity extends AudioActivity implements LastFMApiAsyncTaskListner {

    private String artistName;
    private Artist artist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_view_activity);
        artistName = this.getIntent().getExtras().getString("ArtistName");

        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.artist_lists_activity_toolbar);
        setSupportActionBar(toolbar);

        //Navigation slider
        DrawerUtils drawer = new DrawerUtils(this);
        drawer.initializeDrawer(this);

        ViewCompat.setTransitionName(findViewById(R.id.artist_lists_activity_appbar), "Name");

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.artist_lists_activity_collapsing_toolbar);
        collapsingToolbarLayout.setTitle(artistName);

        // Getting Artist infos from lastfm
        new ArtistInfosAsyncTask(this).execute(artistName);

        // Adding Tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.artist_lists_activity_tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Albums"));
        tabLayout.addTab(tabLayout.newTab().setText("Songs"));
        tabLayout.addTab(tabLayout.newTab().setText("Infos"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Adding ViewPager
        final ViewPager viewPager = (ViewPager) findViewById(R.id.artist_lists_activity_pager);
        final ArtistListsAdapter artistListsAdapter = new ArtistListsAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(artistListsAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        commitPlayerFragment(R.id.artist_lists_activity_player_layout);
        if (savedInstanceState != null) {
            updatePlayerFragment();
        }
    }

    public String getArtistName() {
        return artistName;
    }

    public Artist getArtist() {
        return artist;
    }

    @Override
    public void onArtistRetrieved(Artist artist) {
        if (artist != null) {
            this.artist = artist;
            ImageView artistPicture = (ImageView) findViewById(R.id.artist_lists_activity_picture);
            Glide.with(RegaPlayApplication.getContext()).load(artist.getImageURL(ImageSize.MEGA)).into(artistPicture);
        }


    }

    @Override
    public void onAlbumRetrieved(Album album) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
