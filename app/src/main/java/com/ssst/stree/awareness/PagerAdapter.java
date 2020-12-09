//For Awareness Tabbed View

package com.ssst.stree.awareness;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    private int numberOfTabs;

    public PagerAdapter(FragmentManager fragmentManager, int numberOfTabs){
        super(fragmentManager);
        this.numberOfTabs = numberOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:{
                return new HelplinesFragment();
            }
            case 1:{
                return new SchemesFragment();
            }
            default:{
                return null;
            }
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
