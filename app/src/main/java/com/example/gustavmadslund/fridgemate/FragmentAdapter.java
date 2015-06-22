package com.example.gustavmadslund.fridgemate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by GustavMadslund on 19/06/15.
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                Bundle fridgeData = new Bundle();
                FridgeFragment fridgeFragment = new FridgeFragment();
                fridgeData.putInt("Fridge", i + 1);
                fridgeFragment.setArguments(fridgeData);
                return fridgeFragment;

            case 1:
                Bundle freezerData = new Bundle();
                FreezerFragment freezerFragment = new FreezerFragment();
                freezerData.putInt("Freezer", i + 1);
                freezerFragment.setArguments(freezerData);
                return freezerFragment;

            case 2:
                Bundle groceryListData = new Bundle();
                GroceryListFragment groceryListFragment = new GroceryListFragment();
                groceryListData.putInt("Grocery List", i + 1);
                groceryListFragment.setArguments(groceryListData);
                return groceryListFragment;

             default:
                return null;




        }



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
