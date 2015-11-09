package fr.isen.cir58.teamregalad.regaplay.ui.filesList;

import android.app.ListActivity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.utils.DrawerUtils;

/**
 * Created by maxime on 25/10/15.
 */
public class FilesListActivity extends ListActivity {

    private final String DEFAULT_PATH = "/storage/";
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.files_list_activity);
        DrawerUtils drawer = new DrawerUtils(this);
        drawer.initializeDrawer(this);
        drawer.setSelected(2);
        // Use the current directory as title
        path = DEFAULT_PATH;
        if (getIntent().hasExtra("path")) {
            path = getIntent().getStringExtra("path");
        }
        setTitle(path);

        // Read all files sorted into the values-array
        List values = new ArrayList();
        File dir = new File(path);
        if (!dir.canRead()) {
            setTitle(getTitle() + " (inaccessible)");
        }
        String[] list = dir.list();
        if (list != null) {
            for (String file : list) {
                if (!file.startsWith(".")) {
                    values.add(file);
                }
            }
        }
        Collections.sort(values);

        // Put the data into the list
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, values);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String filename = (String) getListAdapter().getItem(position);
        if (path.endsWith(File.separator)) {
            filename = path + filename;
        } else {
            filename = path + File.separator + filename;
        }
        if (new File(filename).isDirectory()) {
            Intent intent = new Intent(this, FilesListActivity.class);
            intent.putExtra("path", filename);
            startActivity(intent);
        } else {
            try {
                Uri myUri = Uri.parse(filename); // initialize Uri here
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource(getApplicationContext(), myUri);
                mediaPlayer.prepare();
                mediaPlayer.start();
                //Toast.makeText(this, URLConnection.guessContentTypeFromName(filename).split("/")[0], Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this, filename + " is not a compatible file", Toast.LENGTH_LONG).show();
            }
        }
    }
}