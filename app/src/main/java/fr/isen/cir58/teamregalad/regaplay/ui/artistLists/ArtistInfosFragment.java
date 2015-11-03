package fr.isen.cir58.teamregalad.regaplay.ui.artistLists;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.isen.cir58.teamregalad.regaplay.R;

/**
 * Created by Thomas Fossati on 03/11/2015.
 */
public class ArtistInfosFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.artist_infos_fragment, container, false);

        return rootView;
    }
}
