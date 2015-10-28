package fr.isen.cir58.teamregalad.regaplay.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import fr.isen.cir58.teamregalad.regaplay.R;

public class RegaplayListsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regaply_lists);

        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.regaplay_activity_toolbar);
        setSupportActionBar(toolbar);

        // Adding Tabs
        TabLayout tabs = (TabLayout) findViewById(R.id.regaplay_activity_tabs);
        tabs.addTab(tabs.newTab().setText("Artists"));
        tabs.addTab(tabs.newTab().setText("Albums"));
        tabs.addTab(tabs.newTab().setText("Songs"));
        tabs.addTab(tabs.newTab().setText("Genres"));
    }

}
