package com.example.gustavmadslund.fridgemate;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikkel on 19-06-2015.
 */
public class FoodItemAdapter extends BaseAdapter {

    private final List<FoodItem> mItems = new ArrayList<FoodItem>();
    private final Context mContext;
    private int checkedBoxes = 0;
    private final View mView;


    private static final String TAG = "Fridge-Log";

    public FoodItemAdapter(Context context, View view) {
        mContext = context;
        mView = view;

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
    public View getView(int position, View convertView, final ViewGroup parent) {
        final FoodItem foodItem = (FoodItem) getItem(position);

        final LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout itemLayout = (RelativeLayout) layoutInflater.inflate(R.layout.food_item, null);

        // Fill in specific ToDoItem data
        // Remember that the data that goes in this View
        // corresponds to the user interface elements defined
        // in the layout file

        final TextView titleView = (TextView) itemLayout.findViewById(R.id.title);
        //titleView.setText(foodItem.getTitle());


        final CheckBox selectedView = (CheckBox) itemLayout.findViewById(R.id.checkBox);

        // Listener is called when user toggles the selected checkbox

        selectedView.setChecked(foodItem.getChecked());

        selectedView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked){
                    checkedBoxes++;

                    foodItem.setChecked(true);

                    TextView mTextView = (TextView) mView.findViewById(R.id.items_selected);
                    mTextView.setText(checkedBoxes + " items selected");
                }
                else {
                    checkedBoxes--;

                    foodItem.setChecked(false);

                    TextView mTextView = (TextView) mView.findViewById(R.id.items_selected);
                    mTextView.setText(checkedBoxes + " items selected");
                }

            }
        });



        final TextView daysView = (TextView) itemLayout.findViewById(R.id.daysLeft);
        daysView.setText(String.valueOf(foodItem.getDateDiff()) + "days left");

        final ProgressBar progressBar = (ProgressBar) itemLayout.findViewById(R.id.progressBar);
        progressBar.setProgress(progressBar.getMax()- (int)(long) foodItem.getDateDiff());

        // Return the View you just created
        return itemLayout;
    }
}
