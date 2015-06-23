package com.example.gustavmadslund.fridgemate;

import android.content.Intent;
import android.support.v4.view.ViewPager;


import android.os.Bundle;

import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {

    FragmentAdapter mFragmentAdapter;
    ViewPager mViewPager;
    private static final String FRIDGE = "Fridge";
    private static final String FREEZER = "Freezer";
    private static final String GROCERY_LIST = "Grocery List";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager());

        final ActionBar actionBar = getSupportActionBar();

        actionBar.setHomeButtonEnabled(false);



        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mFragmentAdapter);

        mViewPager.setOnPageChangeListener(new SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

        });

        actionBar.addTab(actionBar.newTab().setText(FRIDGE).setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(FREEZER).setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(GROCERY_LIST).setTabListener(this));



    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        mViewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //TODO - change result code to proper resultcode

        Log.v("Something something dark side", data.toString());
        FridgeFragment fridgeFragment = (FridgeFragment) mFragmentAdapter.getItem(0);


        /*if (requestCode == 4) {
            if(resultCode == MainActivity.RESULT_OK){
                Log.v("Something something dark side", "Result ok");
                FoodItemAdapter mAdapter = fridgeFragment.getFoodItemAdapter();
                FoodItem foodItem = new FoodItem(data);

                if(fridgeFragment.getFoodItemAdapter() == null) {
                    Log.v("Something something dark side", "mAdapter is null");
                }

                mAdapter.add(foodItem);
                mAdapter.getItemList().remove(data.getIntExtra("Index", 0));
            }
            if(resultCode == MainActivity.RESULT_CANCELED){
                FoodItemAdapter mAdapter = fridgeFragment.mAdapter;
                mAdapter.getItemList().remove(data.getIntExtra("Index", 0));
            }
        }*/
    }
}










