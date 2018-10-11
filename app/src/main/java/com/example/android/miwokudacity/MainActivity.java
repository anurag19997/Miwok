package com.example.android.miwokudacity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        viewPager.setAdapter(new CategoryFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this));
        tabLayout.setupWithViewPager(viewPager);
    }
}
//        numbers = (TextView) findViewById(R.id.numbers);
//        colors = (TextView) findViewById(R.id.colors);
//        phrases = (TextView) findViewById(R.id.phrases);
//        family = (TextView) findViewById(R.id.family);
////        MediaPlayer kennyG = MediaPlayer.create(MainActivity.this,R.raw.kennyg);
////        kennyG.start();
//        numbers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent();
//                i.putExtra("bgcolor", R.color.category_numbers);
//                i.setClass(MainActivity.this, NumbersActivity.class);
//                startActivity(i);
//            }
//        });
//        colors.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent();
//                i.putExtra("bgcolor", R.color.category_colors);
//                i.setClass(MainActivity.this, ColorsActivity.class);
//                startActivity(i);
//            }
//        });
//        phrases.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent();
//                i.setClass(MainActivity.this, PhrasesActivity.class);
//                startActivity(i);
//            }
//        });
//        family.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent();
//                i.setClass(MainActivity.this, FamilyActivity.class);
//                startActivity(i);
//            }
//        });
//    }
//}