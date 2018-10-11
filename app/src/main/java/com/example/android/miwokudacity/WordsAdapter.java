package com.example.android.miwokudacity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kamal Dev Sharma on 6/15/2017.
 */

public class WordsAdapter extends ArrayAdapter<Words> {
    Context ncontext;
    ArrayList<Words> nwords;
    public WordsAdapter(@NonNull Context context, ArrayList<Words> words) {
        super(context,0,words);
        ncontext = context;
        nwords = words;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) ncontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item,null);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        TextView miwokTextView = (TextView) convertView.findViewById(R.id.miwok_text_view);
        TextView englishTextView = (TextView) convertView.findViewById(R.id.english_text_view);
        miwokTextView.setText(nwords.get(position).getMiwokWord());
        englishTextView.setText(nwords.get(position).getEnglishWord());
//        LinearLayout root = (LinearLayout) miwokTextView.getParent();
//        root.setBackgroundResource(R.color.category_numbers);
        if(nwords.get(position).getImageId()!=0) {
            imageView.setImageResource(nwords.get(position).getImageId());
            imageView.setBackgroundResource(R.color.tan_background);
        }
        else imageView.setVisibility(View.GONE);
        return convertView;
    }
}
