package fr.isen.cir58.teamregalad.regaplay.ui.albumList;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.ui.AudioActivity;

/**
 * Created by Thomas Fossati on 05/11/2015.
 */
public class AlbumListActivity extends AudioActivity{
    private String albumName;
    private String coverPath;
    private AlbumSongsFragment albumSongsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_list_activity);
        albumName = this.getIntent().getExtras().getString("AlbumName");
        coverPath = this.getIntent().getExtras().getString("AlbumCover");



        ViewCompat.setTransitionName(findViewById(R.id.album_list_activity_appbar), "Name");

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.album_list_activity_collapsing_toolbar);
        collapsingToolbarLayout.setTitle(albumName);

        ImageView albumPicture = (ImageView) findViewById(R.id.album_list_activity_picture);
        File file = new File(coverPath);
        Glide.with(RegaPlayApplication.getContext()).load(file).into(albumPicture);

        this.albumSongsFragment = new AlbumSongsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.album_list_activity_relative_layout, albumSongsFragment);
        transaction.commit();

        commitPlayerFragment(R.id.album_list_activity_root);

    }

    public String getAlbumName() {
        return albumName;
    }

    public String getCoverPath() {
        return coverPath;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

