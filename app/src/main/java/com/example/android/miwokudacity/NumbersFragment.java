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
public class NumbersFragment extends Fragment {

    MediaPlayer player;
    AudioManager am;
    AudioManager.OnAudioFocusChangeListener amlistner;
    public NumbersFragment() {
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
        words.add(new Words("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Words("two", "otikko", R.drawable.number_two, R.raw.number_two));
        words.add(new Words("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Words("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Words("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Words("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Words("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Words("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Words("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Words("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));
        WordsAdapter itemsAdapter = new WordsAdapter(getActivity(), words);
        ListView list = (ListView) rootView.findViewById(R.id.list);
        list.setBackgroundResource(R.color.category_numbers);
        list.setAdapter(itemsAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Words w = words.get(position);
                releaseMediaPlayer();
                am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
                int result = am.requestAudioFocus(amlistner, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    player = MediaPlayer.create(getActivity(), words.get(position).getAudioId());
                    player.start();
                    player.setOnCompletionListener(mlistner);
                }
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
