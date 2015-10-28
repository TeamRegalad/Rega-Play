package fr.isen.cir58.teamregalad.regaplay.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.isen.cir58.teamregalad.regaplay.R;

/**
 * Created by aymeric on 10/26/15.
 */
public class SongsListFragment extends ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.songs_list_fragment, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.songs_list_fragment_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //mAdapter = new ArtistsListAdapter();
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }
}
