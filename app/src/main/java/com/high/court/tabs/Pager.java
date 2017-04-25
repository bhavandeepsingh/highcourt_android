package com.high.court.tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class Pager extends FragmentStatePagerAdapter {

    int tabCount;
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount= tabCount;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Subscription_Frag singleBenchFrag = new Subscription_Frag();
                return singleBenchFrag;
            case 1:
                WelfareFund_Frag divisionBenchFrag = new WelfareFund_Frag();
                return divisionBenchFrag;

            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return tabCount;
    }
}