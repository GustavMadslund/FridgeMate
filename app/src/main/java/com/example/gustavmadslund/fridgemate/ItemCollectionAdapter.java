package com.example.gustavmadslund.fridgemate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;

/**
 * Created by GustavMadslund on 19/06/15.
 */
public class ItemCollectionAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;

    public ItemCollectionAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
            Bundle data = new Bundle();
            Fridge fridge = new Fridge();
            data.putInt("current_page", i+1);
            fridge.setArguments(data);
            return fridge;
        }




    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
