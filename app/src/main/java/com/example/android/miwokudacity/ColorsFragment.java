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
public class ColorsFragment extends Fragment {
    MediaPlayer player;
    AudioManager am;
    AudioManager.OnAudioFocusChangeListener amlistner;

    public ColorsFragment() {
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
        words.add(new Words("red", "wetetti", R.drawable.color_red, R.raw.color_red));
        words.add(new Words("mustard yellow", "chiwiita", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        words.add(new Words("dusty yellow", "topiisa", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Words("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        words.add(new Words("brown", "takaakki", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Words("grey", "topoppi", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Words("black", "kululli", R.drawable.color_black, R.raw.color_black));
        words.add(new Words("white", "kelelli", R.drawable.color_white, R.raw.color_white));
        WordsAdapter itemsAdapter = new WordsAdapter(getActivity(), words);
        ListView list = (ListView) rootView.findViewById(R.id.list);
        list.setBackgroundResource(R.color.category_colors);
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
//                                play(mlistner, w);
                                player.start();
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS:
                                releaseMediaPlayer();
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
//                                pause();
                                player.pause();
                                player.seekTo(0);
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
//                                pause();
                                player.pause();
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


