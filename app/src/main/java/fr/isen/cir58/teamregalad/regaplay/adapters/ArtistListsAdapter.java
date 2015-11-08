package fr.isen.cir58.teamregalad.regaplay.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import fr.isen.cir58.teamregalad.regaplay.ui.fragments.ArtistAlbumsListFragment;
import fr.isen.cir58.teamregalad.regaplay.ui.fragments.ArtistInfosListFragment;
import fr.isen.cir58.teamregalad.regaplay.ui.fragments.ArtistSongsListFragment;

/**
 * Created by Thomas Fossati on 03/11/2015.
 */
public class ArtistListsAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public ArtistListsAdapter(android.support.v4.app.FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new ArtistAlbumsListFragment();
            case 1:
                return new ArtistSongsListFragment();
            case 2:
                return new ArtistInfosListFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
