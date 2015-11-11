package fr.isen.cir58.teamregalad.regaplay.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

import fr.isen.cir58.teamregalad.regaplay.ui.activities.FilesListActivity;

/**
 * Created by maxime on 09/11/15.
 */
public class FileListOnClickListener implements ListView.OnItemClickListener {

    private Context c;
    private String path;

    public FileListOnClickListener(Context c, String path) {
        this.c = c;
        this.path= path;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            String filename = (String) parent.getAdapter().getItem(position);
            if (path.endsWith(File.separator)) {
                filename = path + filename;
            } else {
                filename = path + File.separator + filename;
            }
            if (new File(filename).isDirectory()) {
                Intent intent = new Intent(v.getContext(), FilesListActivity.class);
                intent.putExtra("path", filename);
                c.startActivity(intent);
            } else {
                try{
                    FilesListActivity activity = (FilesListActivity) this.c;
                    activity.onSongClicked(filename);
                }
                catch(Exception e)
                {
                    Toast.makeText(v.getContext(), filename + " is not a compatible file", Toast.LENGTH_LONG).show();
                }
            }
    }
}

