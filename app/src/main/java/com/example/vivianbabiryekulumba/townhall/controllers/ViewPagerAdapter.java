package com.example.vivianbabiryekulumba.townhall.controllers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.vivianbabiryekulumba.townhall.fragments.CommBoardsFrag;
import com.example.vivianbabiryekulumba.townhall.fragments.ServiceFacilitiesFrag;
import com.example.vivianbabiryekulumba.townhall.fragments.VolunteerFrag;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "ViewPagerAdapter";
    private static int NUM_ITEMS = 3;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return CommBoardsFrag.newInstance();
            case 1:
                return ServiceFacilitiesFrag.newInstance();
            case 2:
                return VolunteerFrag.newInstance();
            default:
                return CommBoardsFrag.newInstance();
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Community Boards";
            case 1:
                return "Service Facilities";
            case 2:
                return "Volunteer!";
            default:
                return "Community Boards";
        }
    }


    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

}
