package fr.isen.cir58.teamregalad.regaplay.ui.genresList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fr.isen.cir58.teamregalad.regaplay.ui.genreList.GenreListActivity;

/**
 * Created by paul on 11/6/15.
 */
public class GenresListOnClickListener implements View.OnClickListener{
    private GenresListViewHolder genresListViewHolder;
    private Context context;

    public GenresListOnClickListener(GenresListViewHolder genresListViewHolder,Context context) {
        this.genresListViewHolder = genresListViewHolder;
        this.context = context;
    }
    @Override
    public void onClick(View v) {

        Intent intent = new Intent(context, GenreListActivity.class);
        Bundle extras = new Bundle();
        extras.putString("GenreName", genresListViewHolder.genreName.getText().toString());
        intent.putExtras(extras);
        context.startActivity(intent);


    }
}
