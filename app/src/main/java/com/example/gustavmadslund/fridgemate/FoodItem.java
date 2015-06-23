package com.example.gustavmadslund.fridgemate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Intent;

/**
 * Created by Mikkel on 18-06-2015.
 */
public class FoodItem {

    private static final String TAG = "Fridge-Log";

    public enum Place {
        FRIDGE, FREEZER
    }

    public final static String TITLE = "title";
    public final static String QUANTITY = "quantity";
    public final static String PLACE = "place";
    public final static String DATE = "date";
    private boolean CHECKED = false;

    public final static SimpleDateFormat FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.US);

    private String mTitle = new String();
    private Integer mQuantity = 1;
    private Place mPlace = Place.FRIDGE;
    private Date mDate = new Date();
    private Integer mDateDiff = 0;

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
        mDateDiff = intent.getIntExtra(FoodItem.DATE,1);
    }

    public String getTitle() {return mTitle;}

    public Integer getQuantity() {return mQuantity;}

    public Place getPlace() {return mPlace;}

    public void setPlace(Place mPlace) {this.mPlace = mPlace;}

    public void setDate(Date mDate) {this.mDate = mDate;}

    //Take a set of String data values and
    // package them for transport in an Intent

    public static void packageIntent(Intent intent, String title,
           Integer quantity, Place place, Integer date) {

        intent.putExtra(FoodItem.TITLE, title);
        intent.putExtra(FoodItem.QUANTITY, quantity);
        intent.putExtra(FoodItem.PLACE, place.toString());
        intent.putExtra(FoodItem.DATE, date);
    }

    public int getDateDiff() {
        return mDateDiff;
    }

    public void setChecked(boolean Checked){
        this.CHECKED = Checked;
     }

    public boolean getChecked(){
        return this.CHECKED;
    }
}
