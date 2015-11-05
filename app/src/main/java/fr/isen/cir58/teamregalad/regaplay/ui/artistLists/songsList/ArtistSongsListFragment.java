package fr.isen.cir58.teamregalad.regaplay.ui.artistLists.songsList;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreContract;
import fr.isen.cir58.teamregalad.regaplay.external.DividerItemDecoration;
import fr.isen.cir58.teamregalad.regaplay.ui.artistLists.ArtistListsActivity;
import fr.isen.cir58.teamregalad.regaplay.ui.songsList.SongsListAdapter;

/**
 * Created by Thomas Fossati on 04/11/2015.
 */

public class ArtistSongsListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
        private RecyclerView mRecyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager mLayoutManager;
        private String artistName;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.songs_list_fragment, container, false);

            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.songs_list_fragment_recycler_view);
            mLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(mLayoutManager);

            mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            artistName = ((ArtistListsActivity) getActivity()).getArtistName();

            return rootView;
        }

        @Override
        public void onStart() {
            super.onStart();
            getLoaderManager().initLoader(0, null, this);
        }

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            final CursorLoader cursorLoader = new CursorLoader(getContext());
            cursorLoader.setUri(MediaStoreContract.TABLE_SONGS);
            cursorLoader.setProjection(MediaStoreContract.SONGS_PROJECTION_FULL);
            cursorLoader.setSelection(MediaStoreContract.SONGS_SELECTION_BY_ARTIST);
            cursorLoader.setSelectionArgs(new String[]{artistName});
            cursorLoader.setSortOrder(MediaStoreContract.SONGS_ORDER_BY_TITLE_ASC);
            return cursorLoader;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            mAdapter = new SongsListAdapter(getActivity(), data);
            mRecyclerView.setAdapter(mAdapter);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
}

