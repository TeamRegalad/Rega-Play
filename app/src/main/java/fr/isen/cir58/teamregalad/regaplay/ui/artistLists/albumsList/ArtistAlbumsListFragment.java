package fr.isen.cir58.teamregalad.regaplay.ui.artistLists.albumsList;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import de.umass.lastfm.Album;
import de.umass.lastfm.Artist;
import de.umass.lastfm.ImageSize;
import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.database.MediaStoreContract;
import fr.isen.cir58.teamregalad.regaplay.external.DividerItemDecoration;
import fr.isen.cir58.teamregalad.regaplay.social.lastfm.ArtistInfosAsyncTask;
import fr.isen.cir58.teamregalad.regaplay.social.lastfm.listeners.LastFMApiAsyncTaskListner;
import fr.isen.cir58.teamregalad.regaplay.ui.albumsList.AlbumsListAdapter;
import fr.isen.cir58.teamregalad.regaplay.ui.artistLists.ArtistListsActivity;

/**
 * Created by Thomas Fossati on 03/11/2015.
 */
public class ArtistAlbumsListFragment extends Fragment  implements LoaderManager.LoaderCallbacks<Cursor> {
    private RecyclerView mRecyclerView;
    private AlbumsListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String artistName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.albums_list_fragment, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.albums_list_fragment_recycler_view);
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
        cursorLoader.setUri(MediaStoreContract.TABLE_ALBUMS);
        cursorLoader.setProjection(MediaStoreContract.ALBUMS_PROJECTION_FULL);
        cursorLoader.setSelection(MediaStoreContract.ALBUMS_SELCTION_BY_ARTIST);
        cursorLoader.setSelectionArgs(new String[]{artistName});
        cursorLoader.setSortOrder(MediaStoreContract.ALBUMS_ORDER_BY_ALBUM_ASC);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter = new AlbumsListAdapter(getActivity(), data);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
