package com.example.android.miwokudacity;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {

    MediaPlayer player;
    AudioManager am;
    AudioManager.OnAudioFocusChangeListener amlistner;
    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.words_list, container, false);
        final MediaPlayer.OnCompletionListener mlistner = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                releaseMediaPlayer();
            }
        };
//        Intent j = getIntent();
        final ArrayList<Words> words = new ArrayList<Words>();
        words.add(new Words("father", "apa", R.drawable.family_father, R.raw.family_father));
        words.add(new Words("mother", "ata", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Words("son", "angsi", R.drawable.family_son, R.raw.family_son));
        words.add(new Words("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Words("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Words("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Words("older sister", "tete", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Words("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Words("grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Words("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));
        WordsAdapter itemsAdapter = new WordsAdapter(getActivity(), words);
        ListView list = (ListView) rootView.findViewById(R.id.list);
        list.setBackgroundResource(R.color.category_family);
        list.setAdapter(itemsAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Words w = words.get(position);
                releaseMediaPlayer();
                am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
                int result = am.requestAudioFocus(amlistner, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                    play(mlistner, w);
                player = MediaPlayer.create(getActivity(), words.get(position).getAudioId());
                player.start();
                player.setOnCompletionListener(mlistner);
                amlistner = new AudioManager.OnAudioFocusChangeListener() {
                    @Override
                    public void onAudioFocusChange(int focusChange) {
                        switch (focusChange) {
                            case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT:
                                play(mlistner, w);
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS:
                                releaseMediaPlayer();
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                                pause();
                                player.seekTo(0);
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                                pause();
                                player.seekTo(0);
                                break;
                            default:
                                play(mlistner, w);
                        }
                    }
                };
            }
        });


        return rootView;
    }

    private void pause() {
        player.pause();
    }

    private void play(MediaPlayer.OnCompletionListener mlistner, Words w) {
        if (player == null)
            player = MediaPlayer.create(getActivity(), w.getAudioId());
        player.start();
        player.setOnCompletionListener(mlistner);
    }

    private void releaseMediaPlayer() {
        if (player != null) {
            player.release();
            player = null;
            am.abandonAudioFocus(amlistner);
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}



