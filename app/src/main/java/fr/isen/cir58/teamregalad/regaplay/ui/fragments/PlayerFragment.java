package fr.isen.cir58.teamregalad.regaplay.ui.fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.io.File;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.audio.Song;
import fr.isen.cir58.teamregalad.regaplay.receivers.OnSongChangedReceiver;
import fr.isen.cir58.teamregalad.regaplay.social.ShareMusicInfo;
import fr.isen.cir58.teamregalad.regaplay.ui.activities.AudioActivity;

/**
 * Created by Thomas Fossati on 05/11/2015.
 */

public class PlayerFragment extends Fragment implements View.OnClickListener, OnSongChangedReceiver.OnSongChangedListener,SlidingUpPanelLayout.PanelSlideListener {
    public View rootView;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private Button buttonBack;
    private Button buttonPause;
    private Button buttonNext;
    private Button buttonPlay;
    private Button buttonStop;
    private Button buttonSocial;
    private TextView textViewSongName;
    private ImageView imageViewCover;
    private ImageView imageViewCoverExtended;
    private LinearLayout linearLayoutPlayer;
    private TextView textViewAlbumName;
    private TextView textViewArtistName;
    private ProgressBar progressBar;
    private TextView textBufferDuration;
    private TextView textDuration;
    private Song song;

    public static String getDuration(long milliseconds) {
        long sec = (milliseconds / 1000) % 60;
        long min = (milliseconds / (60 * 1000)) % 60;
        long hour = milliseconds / (60 * 60 * 1000);

        String s = (sec < 10) ? "0" + sec : "" + sec;
        String m = (min < 10) ? "0" + min : "" + min;
        String h = "" + hour;

        String time = "";
        if (hour > 0) {
            time = h + ":" + m + ":" + s;
        } else {
            time = m + ":" + s;
        }
        return time;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.player_fragment, container, false);
        getViews();
        rootView.setVisibility(View.GONE);

        return rootView;
    }

    private void getViews() {
        buttonBack = (Button) rootView.findViewById(R.id.player_button_previous);
        buttonPause = (Button) rootView.findViewById(R.id.player_button_pause);
        buttonNext = (Button) rootView.findViewById(R.id.player_button_next);
        buttonPlay = (Button) rootView.findViewById(R.id.player_button_play);
        buttonStop = (Button) rootView.findViewById(R.id.player_button_stop);
        buttonSocial = (Button) rootView.findViewById(R.id.player_button_social);
        textViewSongName = (TextView) rootView.findViewById(R.id.player_textview_songname);
        linearLayoutPlayer = (LinearLayout) rootView.findViewById(R.id.player_linearlayout);
        progressBar = (ProgressBar) rootView.findViewById(R.id.player_progressbar);
        progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        textBufferDuration = (TextView) rootView.findViewById(R.id.player_textBufferDuration);
        textDuration = (TextView) rootView.findViewById(R.id.player_textDuration);
        imageViewCover = (ImageView) rootView.findViewById(R.id.player_imageview_albumart);
        imageViewCoverExtended =  (ImageView) rootView.findViewById(R.id.player_imageview_albumart_extended);
        textViewSongName.setSelected(true);

        setOnclickListeners();
    }

    public void changeButton() {
        if (((AudioActivity) getActivity()).getAudioService().isSongPaused()) {
            buttonPause.setVisibility(View.GONE);
            buttonPlay.setVisibility(View.VISIBLE);
        } else {
            buttonPause.setVisibility(View.VISIBLE);
            buttonPlay.setVisibility(View.GONE);
        }
    }

    private void setOnclickListeners() {
        buttonBack.setOnClickListener(this);
        buttonPause.setOnClickListener(this);
        buttonPlay.setOnClickListener(this);
        buttonNext.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
        buttonSocial.setOnClickListener(this);
    }

    public void setNewSong(Song song) {
        this.song = song;
        updateInfos();
    }

    public void updateInfos() {
        textViewSongName.setText(song.getTitle());

        if (song.getCoverPath() != null) {
            File file = new File(song.getCoverPath());
            Glide.with(RegaPlayApplication.getContext()).load(file).into(imageViewCover);
            Glide.with(RegaPlayApplication.getContext()).load(file).into(imageViewCoverExtended);
        } else {
            Log.w("PlayerFragment", "warning album art path is null.");
        }
        textDuration.setText(getDuration(song.getDuration()));

        rootView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        switch (v.getId()) {
            case R.id.player_button_play:
                ((AudioActivity) getActivity()).playSong();
                changeButton();
                break;
            case R.id.player_button_pause:
                ((AudioActivity) getActivity()).pauseSong();
                changeButton();
                break;
            case R.id.player_button_previous:
                ((AudioActivity) getActivity()).previousSong();
                changeButton();
                break;
            case R.id.player_button_next:
                ((AudioActivity) getActivity()).nextSong();
                changeButton();
                break;
            case R.id.player_button_stop:
                ((AudioActivity) getActivity()).stopSong();
                rootView.setVisibility(View.GONE);
                changeButton();
                break;
            case R.id.player_button_social:
                ShareMusicInfo.shareVia(getActivity(),this.song.shareSongInfos());
		break;

        }
    }

    @Override
    public void onPanelSlide(View view, float v) {
    }
    @Override
    public void onPanelCollapsed(View view) {
        Toast.makeText(RegaPlayApplication.getContext(),"COUCOU",Toast.LENGTH_SHORT);

    }

    @Override
    public void onPanelExpanded(View view) {
        Toast.makeText(RegaPlayApplication.getContext(),"COUCOU",Toast.LENGTH_SHORT);

    }

    @Override
    public void onPanelAnchored(View view) {
    }

    @Override
    public void onPanelHidden(View view) {
    }


    public TextView getTextDuration() {
        return textDuration;
    }

    public TextView getTextBufferDuration() {
        return textBufferDuration;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void onSongChanged(Song song) {
        setNewSong(song);
    }
}
