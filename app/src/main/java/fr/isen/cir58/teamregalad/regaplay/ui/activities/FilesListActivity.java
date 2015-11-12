package fr.isen.cir58.teamregalad.regaplay.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.adapters.FilesListAdapter;
import fr.isen.cir58.teamregalad.regaplay.external.DividerItemDecoration;
import fr.isen.cir58.teamregalad.regaplay.utils.DrawerUtils;

/**
 * Created by maxime on 09/11/15.
 */
public class FilesListActivity extends AudioActivity {

    private final String DEFAULT_PATH = "/storage/";
    private String path;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.files_list_activity);

        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.file_lists_activity_toolbar);
        setSupportActionBar(toolbar);

        commitPlayerFragment(R.id.files_list_activity_player_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.fileListView);
        mLayoutManger = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManger);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // Initialize the Drawer menu
        DrawerUtils drawer = new DrawerUtils(this);
        drawer.initializeDrawer(this);
        drawer.setSelected(2);

        path = DEFAULT_PATH;
        if (getIntent().hasExtra("path")) {
            path = getIntent().getStringExtra("path");
        }

        getSupportActionBar().setTitle(path);

        //TextView pathText = (TextView) findViewById(R.id.pathTextView);
        //pathText.setText("Location : " + path);
        // Read all files sorted into the values-array
        List values = new ArrayList();
        File dir = new File(path);
        String[] list = dir.list();
        if (list != null) {
            for (String file : list) {
                if (!file.startsWith(".")) {
                    values.add(file);
                }
            }
        }

        Collections.sort(values);

        //Add to make navigation easier
        values.add(0, "../");

        // Put the data into the list
        //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        FilesListAdapter adapter = new FilesListAdapter(path, values);
        mRecyclerView.setAdapter(adapter);

    }
}
