package fr.isen.cir58.teamregalad.regaplay.ui.regaplayLists;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import fr.isen.cir58.teamregalad.regaplay.ui.albumsList.AlbumsListFragment;
import fr.isen.cir58.teamregalad.regaplay.ui.artistsList.ArtistsListFragment;
import fr.isen.cir58.teamregalad.regaplay.ui.genresList.GenresListFragment;
import fr.isen.cir58.teamregalad.regaplay.ui.songsList.SongsListFragment;

/**
 * Created by aymeric on 10/28/15.
 */
public class RegaplayListsAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public RegaplayListsAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new ArtistsListFragment();
            case 1:
                return new AlbumsListFragment();
            case 2:
                return new SongsListFragment();
            case 3:
                return new GenresListFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
