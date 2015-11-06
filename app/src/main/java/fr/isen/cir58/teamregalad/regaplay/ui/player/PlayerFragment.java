package fr.isen.cir58.teamregalad.regaplay.ui.player;

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

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.io.File;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.RegaPlayApplication;
import fr.isen.cir58.teamregalad.regaplay.audio.Song;
import fr.isen.cir58.teamregalad.regaplay.ui.AudioActivity;

/**
 * Created by Thomas Fossati on 05/11/2015.
 */
public class PlayerFragment extends Fragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private View rootView;
    private Button buttonBack;
    private Button buttonPause;
    private Button buttonNext;
    private Button buttonPlay;
    private Button buttonStop;
    private Button buttonExtendedMP;
    private TextView textViewSongName;
    private ImageView imageviewCover;
    private LinearLayout linearLayoutPlayer;
    private TextView textViewAlbumName;
    private TextView textViewArtistName;
    private ProgressBar progressBar;
    private TextView textBufferDuration;
    private TextView textDuration;


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
        buttonExtendedMP =(Button) rootView.findViewById(R.id.player_button_extended_musicplayer);
        textViewSongName = (TextView) rootView.findViewById(R.id.player_textview_songname);
        linearLayoutPlayer = (LinearLayout) rootView.findViewById(R.id.player_linearlayout);
        progressBar = (ProgressBar) rootView.findViewById(R.id.player_progressbar);
        textBufferDuration = (TextView) rootView.findViewById(R.id.player_textBufferDuration);
        textDuration = (TextView) rootView.findViewById(R.id.player_textDuration);
        textViewSongName.setSelected(true);

        setOnclickListeners();
    }
    public void changeButton() {
        if(((AudioActivity)getActivity()).getAudioService().isSongPlaying()){
            buttonPause.setVisibility(View.GONE);
            buttonPlay.setVisibility(View.VISIBLE);
        }else{
            buttonPause.setVisibility(View.VISIBLE);
            buttonPlay.setVisibility(View.GONE);
        }
    }


    private void setOnclickListeners(){
        buttonBack.setOnClickListener(this);
        buttonPause.setOnClickListener(this);
        buttonPlay.setOnClickListener(this);
        buttonNext.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
        buttonExtendedMP.setOnClickListener(this);
    }
    public void setNewSong(Song song){
        textViewSongName.setText(song.getTitle());

        if (song.getCoverPath() != null) {
            File file = new File(song.getCoverPath());
            Glide.with(RegaPlayApplication.getContext()).load(file).into(imageviewCover);
        } else {
            Log.w("PlayerFragment", "warning album art path is null.");
        }
        //textDuration.setText(song.getDuration());

        changeButton();
        rootView.setVisibility(View.VISIBLE);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        switch (v.getId()) {
            case R.id.player_button_play:
                ((AudioActivity)getActivity()).playSong();
                break;
            case R.id.player_button_pause:
                ((AudioActivity)getActivity()).pauseSong();
                break;
            case R.id.player_button_previous:
                ((AudioActivity)getActivity()).previousSong();
                break;
            case R.id.player_button_next:
                ((AudioActivity)getActivity()).nextSong();
                break;
            case R.id.player_button_stop:
                ((AudioActivity)getActivity()).stopSong();
                rootView.setVisibility(View.GONE);
                break;

        }
    }
}