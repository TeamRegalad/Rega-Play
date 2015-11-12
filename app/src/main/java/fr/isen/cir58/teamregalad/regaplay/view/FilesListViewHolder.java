package fr.isen.cir58.teamregalad.regaplay.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fr.isen.cir58.teamregalad.regaplay.R;

/**
 * Created by aymeric on 11/12/15.
 */
public class FilesListViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public ImageView icon;
    public String path;

    public FilesListViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.filesListViewText);
        icon = (ImageView) itemView.findViewById(R.id.filesListViewIcon);
    }
}
