package fr.isen.cir58.teamregalad.regaplay.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.listeners.FileListOnClickListener;
import fr.isen.cir58.teamregalad.regaplay.view.FilesListViewHolder;

/**
 * Created by maxime on 11/11/15.
 */
public class FilesListAdapter extends RecyclerView.Adapter<FilesListViewHolder> {
    private List<String> objects;
    private String path;

    public FilesListAdapter(String path, List<String> objects) {
        this.path = path;
        this.objects = objects;
    }

    @Override
    public FilesListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.files_list_item_fragment, parent, false);
        FilesListViewHolder filesListViewHolder = new FilesListViewHolder(itemView);
        itemView.setOnClickListener(new FileListOnClickListener(filesListViewHolder));
        return filesListViewHolder;
    }

    @Override
    public void onBindViewHolder(FilesListViewHolder holder, int position) {
        String filename = objects.get(position);
        holder.name.setText(filename);

        if (path.endsWith(File.separator)) {
            filename = path + filename;
        } else {
            filename = path + File.separator + filename;
        }

        holder.path = filename;

        if (new File(filename).isDirectory()) {
            Glide.with(RegaPlayApplication.getContext()).load(R.drawable.ic_folder_black).into(holder.icon);
        } else {
            String[] split = filename.split("\\.");
            String ext = "null";

            if (split.length > 0) {
                ext = split[split.length - 1];
                System.out.println(ext);
            }

            if ("mp3".equals(ext) || "MP3".equals(ext) || "wav".equals(ext) || "WAV".equals(ext) || "3gp".equals(ext) || "3GP".equals(ext) || "acc".equals(ext)
                    || "ACC".equals(ext) || "mp4".equals(ext) || "MP4".equals(ext) || "flac".equals(ext) || "FLAC".equals(ext) || "m4a".equals(ext)
                    || "M4A".equals(ext) || "FLAC".equals(ext) || "ogg".equals(ext) || "OGG".equals(ext)) {

                Glide.with(RegaPlayApplication.getContext()).load(R.drawable.ic_music_black).into(holder.icon);
            } else {
                Glide.with(RegaPlayApplication.getContext()).load(R.drawable.ic_file_black).into(holder.icon);
            }
        }
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }


}
