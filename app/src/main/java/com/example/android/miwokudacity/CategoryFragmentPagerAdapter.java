package com.example.android.miwokudacity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Kamal Dev Sharma on 6/25/2017.
 */

public class CategoryFragmentPagerAdapter extends FragmentPagerAdapter {
    String[] title = new String[]{"NUMBERS" , "FAMILY", "COLORS","PHRASES"};
    public CategoryFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        Context mcontext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                return new NumbersFragment();
            case 1:
                return new FamilyFragment();
            case 2:
                return new ColorsFragment();
            case 3:
                return new PhrasesFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
