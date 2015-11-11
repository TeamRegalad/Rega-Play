package fr.isen.cir58.teamregalad.regaplay.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import fr.isen.cir58.teamregalad.regaplay.R;

/**
 * Created by maxime on 11/11/15.
 */
public class FilesListAdapter extends ArrayAdapter<String> {

    private List<String> objects;
    private String path;

    public FilesListAdapter(Context context, int resource, List<String> objects, String path) {
        super(context, resource, objects);
        this.objects = objects;
        this.path = path;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        View v = view;
        if(v ==null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.files_list_item_fragment, null);
        }
        String filename = getItem(position);
        TextView textView = (TextView) v.findViewById(R.id.filesListViewText);
        textView.setText(filename);

        ImageView imageView = (ImageView) v.findViewById(R.id.filesListViewIcon);
        if (path.endsWith(File.separator)) {
            filename = path + filename;
        } else {
            filename = path + File.separator + filename;
        }

        if (new File(filename).isDirectory()) {
            imageView.setImageResource(R.drawable.ic_folder_black);
        }else{
            String[] splt = filename.split("\\.");
            String ext = "null";
            System.out.println(filename);
            if(splt.length > 0){
                ext = splt[splt.length-1];
                System.out.println(ext);
            }
            if("mp3".equals(ext) ||"MP3".equals(ext) ||"wav".equals(ext)||"WAV".equals(ext)|| "3gp".equals(ext) ||"3GP".equals(ext) ||"acc".equals(ext)
                    ||"ACC".equals(ext)||"mp4".equals(ext) ||"MP4".equals(ext) ||"flac".equals(ext) ||"FLAC".equals(ext) ||"m4a".equals(ext)
                    ||"M4A".equals(ext) ||"FLAC".equals(ext)||"ogg".equals(ext) ||"OGG".equals(ext)){
                imageView.setImageResource(R.drawable.ic_music_black);
            }else{
                imageView.setImageResource(R.drawable.ic_file_black);
            }
        }
        return v;
    }
}
