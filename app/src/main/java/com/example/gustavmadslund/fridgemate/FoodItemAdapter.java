package com.example.gustavmadslund.fridgemate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikkel on 19-06-2015.
 */
public class FoodItemAdapter extends BaseAdapter {

    private final List<FoodItem> mItems = new ArrayList<FoodItem>();
    private final Context mContext;

    private static final String TAG = "Fridge-Log";

    public FoodItemAdapter(Context context) {
        mContext = context;
    }

    //Add a FoodItem to the adapter
    //Notify observers that the data set has changed

    public void add(FoodItem item) {

        mItems.add(item);
        notifyDataSetChanged();
    }

    //Clears the list adapter of all items.

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    // Returns the number of FoodItems


    @Override
    public int getCount() {
        return mItems.size();
    }

    //Retrieve a FoodItem


    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO - Get the current ToDoItem



        // TODO - Inflate the View for this ToDoItem
        // from todo_item.xml


        // Fill in specific ToDoItem data
        // Remember that the data that goes in this View
        // corresponds to the user interface elements defined
        // in the layout file

        // TODO - Display Title in TextView



        // TODO - Set up Status CheckBox


        // TODO - Must also set up an OnCheckedChangeListener,
        // which is called when the user toggles the status checkbox



        // TODO - Display Priority in a TextView


        // TODO - Display Time and Date.
        // Hint - use ToDoItem.FORMAT.format(toDoItem.getDate()) to get date and
        // time String


        // Return the View you just created
        return null;
    }
}
