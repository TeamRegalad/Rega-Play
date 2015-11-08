package fr.isen.cir58.teamregalad.regaplay.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.umass.lastfm.Artist;
import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.ui.activities.ArtistListsActivity;

/**
 * Created by Thomas Fossati on 04/11/2015.
 */
public class ArtistInfosListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private Artist artist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.artist_view_infos_fragment, container, false);

        /*mRecyclerView = (RecyclerView) rootView.findViewById(R.id.artist_infos_list_fragment_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());*/
        artist = ((ArtistListsActivity) getActivity()).getArtist();

        if (artist != null) {

            TextView mTextView = (TextView) rootView.findViewById(R.id.artist_infos_list_fragment_bio);
            mTextView.setText(artist.getWikiText());
            //mRecyclerView.setAdapter(new ArtistInfosListAdapter(getActivity(),artist));
        }


        return rootView;
    }

}
