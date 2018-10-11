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
public class PhrasesFragment extends Fragment {

    MediaPlayer player;
    AudioManager am;
    AudioManager.OnAudioFocusChangeListener amlistner;
    public PhrasesFragment() {
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
        words.add(new Words("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new Words("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        words.add(new Words("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        words.add(new Words("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        words.add(new Words("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Words("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        words.add(new Words("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        words.add(new Words("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        words.add(new Words("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        words.add(new Words("Come here.", "әnni'nem", R.raw.phrase_come_here));
        WordsAdapter itemsAdapter = new WordsAdapter(getActivity(), words);
        ListView list = (ListView) rootView.findViewById(R.id.list);
        list.setBackgroundResource(R.color.category_phrases);
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
                                player.start();
//                                play(mlistner, w);
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

