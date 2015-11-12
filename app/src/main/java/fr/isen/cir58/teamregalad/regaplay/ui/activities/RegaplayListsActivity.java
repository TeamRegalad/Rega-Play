package fr.isen.cir58.teamregalad.regaplay.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.adapters.RegaplayListsAdapter;
import fr.isen.cir58.teamregalad.regaplay.audio.Song;
import fr.isen.cir58.teamregalad.regaplay.listeners.RandomPlaylistOnClickListener;
import fr.isen.cir58.teamregalad.regaplay.utils.DrawerUtils;

public class RegaplayListsActivity extends AudioActivity {
    private Song song;
    private int timeStopped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regaplay_lists_activity);

        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.regaplay_lists_activity_toolbar);
        setSupportActionBar(toolbar);

        //Navigation slider
        DrawerUtils drawer = new DrawerUtils(this);
        drawer.initializeDrawer(this);


        // Adding Tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.regaplay_lists_activity_tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Artists"));
        tabLayout.addTab(tabLayout.newTab().setText("Albums"));
        tabLayout.addTab(tabLayout.newTab().setText("Songs"));
        tabLayout.addTab(tabLayout.newTab().setText("Genres"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Adding ViewPager
        final ViewPager viewPager = (ViewPager) findViewById(R.id.regaplay_lists_activity_pager);
        final RegaplayListsAdapter regaplayListsAdapter = new RegaplayListsAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(regaplayListsAdapter);
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

        // Adding FloatingActionButton

        ImageButton fabButton = (ImageButton) findViewById(R.id.regaplay_lists_activity_fab);
        fabButton.setOnClickListener(new RandomPlaylistOnClickListener());

        commitPlayerFragment(R.id.regaplay_lists_activity_player_layout);

        if (savedInstanceState != null) {
            song = (Song) savedInstanceState.getParcelable("Song");
            timeStopped = savedInstanceState.getInt("timeStopped");
            //sendBroadcastSongChanged(song);

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        //getAudioService().resumeSongFromNewBinding(song, timeStopped);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("Song", getAudioService().song);
        outState.putInt("timeStopped", playerFragment.getSeekBar().getProgress());
        super.onSaveInstanceState(outState);
    }
}
