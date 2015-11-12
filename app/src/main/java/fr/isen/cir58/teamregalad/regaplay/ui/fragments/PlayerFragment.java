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
import android.widget.SeekBar;
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
import fr.isen.cir58.teamregalad.regaplay.utils.MethodsUtils;

/**
 * Created by Thomas Fossati on 05/11/2015.
 */

public class PlayerFragment extends Fragment implements View.OnClickListener, SlidingUpPanelLayout.PanelSlideListener, SeekBar.OnSeekBarChangeListener {
    public View rootView;
    private Button buttonPause;
    private Button buttonNext;
    private Button buttonPlay;
    private Button buttonSocial;
    private Button buttonPrevious;
    private Button buttonBarPause;
    private Button buttonBarPlay;
    private Button buttonBarStop;
    private TextView textViewSongName;
    private TextView textViewPlaylist;
    private ImageView imageViewCover;
    private ImageView imageViewCoverExtended;
    private LinearLayout linearLayoutPlayer;
    private TextView textViewArtistName;
    private SeekBar seekBar;
    private TextView textBufferDuration;
    private TextView textDuration;
    private Song song;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.player_fragment, container, false);
        getViews();
        rootView.setVisibility(View.GONE);

        return rootView;
    }

    private void getViews() {

        //buttons extended
        buttonPause = (Button) rootView.findViewById(R.id.player_button_pause);
        buttonPrevious = (Button) rootView.findViewById(R.id.player_button_previous);
        buttonPlay = (Button) rootView.findViewById(R.id.player_button_play);
        buttonNext = (Button) rootView.findViewById(R.id.player_button_next);
        buttonSocial = (Button) rootView.findViewById(R.id.player_button_social);
        //bar buttons
        buttonBarPause = (Button) rootView.findViewById(R.id.player_button_bar_pause);
        buttonBarPlay = (Button) rootView.findViewById(R.id.player_button_bar_play);
        buttonBarStop = (Button) rootView.findViewById(R.id.player_button_bar_stop);


        imageViewCover = (ImageView) rootView.findViewById(R.id.player_imageview_albumart);
        imageViewCoverExtended = (ImageView) rootView.findViewById(R.id.player_imageview_albumart_extended);

        textBufferDuration = (TextView) rootView.findViewById(R.id.player_textBufferDuration);
        textDuration = (TextView) rootView.findViewById(R.id.player_textDuration);
        textViewSongName = (TextView) rootView.findViewById(R.id.player_textview_songname);
        //textViewPlaylist = (TextView) rootView.findViewById(R.id.player_textview_playlist);
        textViewArtistName = (TextView) rootView.findViewById(R.id.player_textview_artistname);

        linearLayoutPlayer = (LinearLayout) rootView.findViewById(R.id.player_root_linearlayout);

        seekBar = (SeekBar) rootView.findViewById(R.id.player_progressbar);
        seekBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);

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
    public void changeButtonBar() {
        if (((AudioActivity) getActivity()).getAudioService().isSongPaused()) {
            buttonBarPause.setVisibility(View.GONE);
            buttonBarPlay.setVisibility(View.VISIBLE);
        } else {
            buttonBarPause.setVisibility(View.VISIBLE);
            buttonBarPlay.setVisibility(View.GONE);
        }
    }

    private void setOnclickListeners() {
        buttonPause.setOnClickListener(this);
        buttonPrevious.setOnClickListener(this);
        buttonPlay.setOnClickListener(this);
        buttonNext.setOnClickListener(this);
        buttonSocial.setOnClickListener(this);
        buttonBarPause.setOnClickListener(this);
        buttonBarPlay.setOnClickListener(this);
        buttonBarStop.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(this);
    }

    public void setNewSong(Song song) {
        this.song = song;
        updateInfos();
    }

    public void updateInfos() {
        textViewSongName.setText(song.getTitle());
        textViewArtistName.setText(song.getArtist());

        if (song.getCoverPath() != null) {
            File file = new File(song.getCoverPath());
            Glide.with(RegaPlayApplication.getContext()).load(file).into(imageViewCover);
            Glide.with(RegaPlayApplication.getContext()).load(file).into(imageViewCoverExtended);
        } else {
            Log.w("PlayerFragment", "warning album art path is null.");
        }
        textDuration.setText(MethodsUtils.getDuration(song.getDuration()));

        rootView.setVisibility(View.VISIBLE);
    }

    public void updatePlaylist(String currentSong, String playlistSize){
        //textViewPlaylist.setText(currentSong + "/" + playlistSize);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.player_button_play:
                ((AudioActivity) getActivity()).playSong();
                changeButton();
                break;
            case R.id.player_button_bar_play:
                ((AudioActivity) getActivity()).playSong();
                changeButton();
                changeButtonBar();
                break;
            case R.id.player_button_pause:
                ((AudioActivity) getActivity()).pauseSong();
                changeButton();
                break;
            case R.id.player_button_bar_pause:
                ((AudioActivity) getActivity()).pauseSong();
                changeButton();
                changeButtonBar();
                break;
            case R.id.player_button_bar_stop:
                ((AudioActivity) getActivity()).stopSong();
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
            case R.id.player_button_social:
                ShareMusicInfo.shareVia(getActivity(), this.song.shareSongInfos());
                break;

        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            Log.d("PlayerFragment", String.valueOf(progress));
            ((AudioActivity) getActivity()).setSongAtTimestamp(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onPanelSlide(View view, float v) {
    }

    @Override
    public void onPanelCollapsed(View view) {
        changeButtonBar();
        buttonBarStop.setVisibility(View.GONE);

    }

    @Override
    public void onPanelExpanded(View view) {
        buttonBarPause.setVisibility(View.GONE);
        buttonBarPlay.setVisibility(View.GONE);
        buttonBarStop.setVisibility(View.VISIBLE);
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

    public ProgressBar getSeekBar() {
        return seekBar;
    }

}
