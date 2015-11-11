package fr.isen.cir58.teamregalad.regaplay.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.listeners.FileListOnClickListener;
import fr.isen.cir58.teamregalad.regaplay.ui.activities.AudioActivity;
import fr.isen.cir58.teamregalad.regaplay.utils.DrawerUtils;

/**
 * Created by maxime on 09/11/15.
 */
public class FilesListActivity extends AudioActivity {

    private String path;
    private final String DEFAULT_PATH = "/storage/";
    private  ListView fileListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.files_list_activity);
        this.commitPlayerFragment(R.id.file_lists_activity_root_layout);
        this.fileListView =(ListView) findViewById(R.id.fileListView);
        // Initialize the Drawer menu
        DrawerUtils drawer = new DrawerUtils(this);
        drawer.initializeDrawer(this);
        drawer.setSelected(2);

        path = DEFAULT_PATH;
        if (getIntent().hasExtra("path")) {
            path = getIntent().getStringExtra("path");
        }

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
        }else{
        }

        Collections.sort(values);

        //Add to make navigation easier
        values.add(0,"../");

        // Put the data into the list
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        fileListView.setAdapter(adapter);
        fileListView.setOnItemClickListener(new FileListOnClickListener(this, path));

    }
}
