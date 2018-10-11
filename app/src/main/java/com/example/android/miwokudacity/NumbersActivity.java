package com.example.android.miwokudacity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class NumbersActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new NumbersFragment())
                .commit();

//        for (int i = 0; i < 10; i++) {
//            Log.v("words[" + i + "]", words.get(i).getEnglishWord());
//        }
    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.v("Lifecycle","onStart");
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Log.v("Lifecycle","onRestart");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.v("Lifecycle","onPause");
//        releaseMediaPlayer();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.v("Lifecycle","onDestroy");
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.v("Lifecycle","onResume");
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            // Respond to the action bar's Up/Home button
//            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
