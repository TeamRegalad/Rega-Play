package fr.isen.cir58.teamregalad.regaplay.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.isen.cir58.teamregalad.regaplay.R;

/**
 * Created by aymeric on 10/26/15.
 */
public class AlbumsListFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.albums_list_fragment, container, false);
    }
}
