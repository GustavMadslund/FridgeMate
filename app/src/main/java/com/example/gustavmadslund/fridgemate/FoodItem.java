package com.example.gustavmadslund.fridgemate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Intent;

/**
 * Created by Mikkel on 18-06-2015.
 */
public class FoodItem {

    public enum Place {
        FRIDGE, FREEZER
    }

    public final static String TITLE = "title";
    public final static String QUANTITY = "quantity";
    public final static String PLACE = "place";
    public final static String DATE = "date";

    public final static SimpleDateFormat FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.US);

    private String mTitle = new String();
    private Integer mQuantity = 1;
    private Place mPlace = Place.FRIDGE;
    private Date mDate = new Date();

    FoodItem(String title, Integer quantity, Place place, Date date){
        this.mTitle = title;
        this.mQuantity = quantity;
        this.mPlace = place;
        this.mDate = date;
    }

    FoodItem(Intent intent){
        mTitle = intent.getStringExtra(FoodItem.TITLE);
        mQuantity = intent.getIntExtra(FoodItem.QUANTITY, 1);
        mPlace = Place.valueOf(intent.getStringExtra(FoodItem.PLACE));

        try {
            mDate = FoodItem.FORMAT.parse(intent.getStringExtra(FoodItem.DATE));
        } catch (ParseException e) {
            mDate = new Date();
        }

    }

    public String getTitle() {return mTitle;}

    public Integer getQuantity() {return mQuantity;}

    public Place getPlace() {return mPlace;}

    public void setPlace(Place mPlace) {this.mPlace = mPlace;}

    public Date getDate() {return mDate;}

    public void setDate(Date mDate) {this.mDate = mDate;}





}
