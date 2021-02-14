package com.example.dell.attendence;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Created by Dell on 06-06-2018.
 */

public class CustomPagerAdapter extends FragmentStatePagerAdapter {
    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f=new Fragment();
        switch (position){
            case 0:
                Log.i("fragment","zero");
                f=new TakeAttendanceFragment();
                break;
            case 1:
                f=new ViewAttendance();
                break;
        }
        return f;
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:return "TAKE ATTENDANCE";
            case 1:return "VIEW ATTENDANCE";
            default:return null;
        }
    }
}
