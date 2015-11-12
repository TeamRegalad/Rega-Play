package fr.isen.cir58.teamregalad.regaplay.ui.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
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
import fr.isen.cir58.teamregalad.regaplay.adapters.SongsListAdapter;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreContract;
import fr.isen.cir58.teamregalad.regaplay.external.DividerItemDecoration;
import fr.isen.cir58.teamregalad.regaplay.ui.activities.GenreListActivity;
import fr.isen.cir58.teamregalad.regaplay.utils.Constants;

/**
 * Created by paul on 11/6/15.
 */
public class GenreSongsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private long genreId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view_fragment, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_fragment_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        genreId = ((GenreListActivity) getActivity()).getGenreId();

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
        cursorLoader.setUri(MediaStore.Audio.Genres.Members.getContentUri("external", genreId));
        cursorLoader.setProjection(MediaStoreContract.SONGS_PROJECTION_FULL);
        cursorLoader.setSelection(MediaStoreContract.SONGS_SELECTION_IS_MUSIC);
        cursorLoader.setSelectionArgs(null);
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