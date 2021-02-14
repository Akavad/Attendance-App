package com.example.dell.attendence;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;

public class ActivityFunction extends FragmentActivity {

    ViewPager vp;
    CustomPagerAdapter cpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp= (ViewPager) findViewById(R.id.pager);
        cpa=new CustomPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(cpa);
        PagerTabStrip pagerTabStrip= (PagerTabStrip) vp.findViewById(R.id.pager_tab_strip);
        pagerTabStrip.setTabIndicatorColor(Color.BLACK);
        pagerTabStrip.setDrawFullUnderline(true);
    }
}
