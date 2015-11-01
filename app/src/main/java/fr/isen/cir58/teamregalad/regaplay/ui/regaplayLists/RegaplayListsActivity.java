package fr.isen.cir58.teamregalad.regaplay.ui.regaplayLists;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.receivers.SongClickedReceiver;
import fr.isen.cir58.teamregalad.regaplay.utils.Constants;

public class RegaplayListsActivity extends AppCompatActivity {
    private SongClickedReceiver songClickedReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regaplay_lists_activity);

        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.regaplay_lists_activity_toolbar);
        setSupportActionBar(toolbar);

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
    }

    @Override
    protected void onResume() {
        super.onResume();

        songClickedReceiver = new SongClickedReceiver();
        registerReceiver(songClickedReceiver, new IntentFilter(Constants.Audio.ACTION_SONG_CLICKED));
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(songClickedReceiver);
        songClickedReceiver = null;
    }
}
