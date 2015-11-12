package fr.isen.cir58.teamregalad.regaplay.listeners;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.ui.activities.FilesListActivity;
import fr.isen.cir58.teamregalad.regaplay.view.FilesListViewHolder;

/**
 * Created by maxime on 09/11/15.
 */

public class FileListOnClickListener implements View.OnClickListener {
    FilesListViewHolder viewHolder;

    public FileListOnClickListener(FilesListViewHolder viewHolder) {
        this.viewHolder = viewHolder;
    }

    @Override
    public void onClick(View view) {
        String path = viewHolder.path;
        String filename = viewHolder.name.getText().toString();

        if (new File(path).isDirectory()) {
            Intent intent = new Intent(RegaPlayApplication.getContext(), FilesListActivity.class);
            intent.putExtra("path", path);
            view.getContext().startActivity(intent);
        } else {

            try{
                FilesListActivity activity = (FilesListActivity) view.getContext();
                activity.onSongClickedWithPath(path);
            }
            catch(Exception e)
            {
                Toast.makeText(RegaPlayApplication.getContext(), filename + " is not a compatible file", Toast.LENGTH_LONG).show();
            }
        }
    }
}

