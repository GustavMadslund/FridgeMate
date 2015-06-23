package com.example.gustavmadslund.fridgemate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikkel on 19-06-2015.
 */
public class FoodItemAdapter extends BaseAdapter{

    private List<FoodItem> mItems = new ArrayList<FoodItem>();
    private final Context mContext;
    private int checkedBoxes = 0;
    private final View mView;
    private final List<FoodItem> mCheckedItems = new ArrayList<FoodItem>();

    private static final String TAG = "Fridge-Log";
    private SharedPreferences sharedPreferences;
    private Gson gson;
    Type type = new TypeToken<List<FoodItem>>(){}.getType();

    public FoodItemAdapter(Context context, View view) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        gson = new Gson();
        mContext = context;
        mView = view;

        String mString = sharedPreferences.getString("Fridge","[]");
        mItems = gson.fromJson(mString,type);
    }

    //Add a FoodItem to the adapter
    //Notify observers that the data set has changed

    public void add(FoodItem item) {

        mItems.add(item);
        notifyDataSetChanged();

        sharedPreferences.edit().putString("Fridge",gson.toJson(mItems, type)).commit();
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
        final FoodItem mFoodItem = (FoodItem) getItem(position);

        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout itemLayout = (RelativeLayout) layoutInflater.inflate(R.layout.food_item, null);

        // Fill in specific ToDoItem data
        // Remember that the data that goes in this View
        // corresponds to the user interface elements defined
        // in the layout file

        final TextView titleView = (TextView) itemLayout.findViewById(R.id.title);
        titleView.setText(mFoodItem.getTitle());


        final CheckBox selectedView = (CheckBox) itemLayout.findViewById(R.id.checkBox);

        selectedView.setChecked(mFoodItem.getChecked());


        // Listener is called when user toggles the selected checkbox

        selectedView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    checkedBoxes++;

                    mCheckedItems.add(mFoodItem);

                    mFoodItem.setChecked(true);

                    TextView mTextView = (TextView) mView.findViewById(R.id.items_selected);
                    mTextView.setText(checkedBoxes + " items selected");
                    } else {
                    checkedBoxes--;

                    mCheckedItems.remove(mFoodItem);

                    mFoodItem.setChecked(false);

                    TextView mTextView = (TextView) mView.findViewById(R.id.items_selected);
                    mTextView.setText(checkedBoxes + " items selected");
                    }

            }
        });
        ImageButton editButton = (ImageButton) itemLayout.findViewById(R.id.editItem);

        // Listener is called when user clicks the ImageButton

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditItemActivity.class);
                mContext.startActivity(intent);

            }
        });

        final TextView daysView = (TextView) itemLayout.findViewById(R.id.daysLeft);
        daysView.setText(String.valueOf((int) mFoodItem.getDateDiff()) + "days left");

        final ProgressBar progressBar = (ProgressBar) itemLayout.findViewById(R.id.progressBar);
        progressBar.setProgress(progressBar.getMax() - (int) mFoodItem.getDateDiff());

        // Return the View you just created
        return itemLayout;
    }

    public List<FoodItem> getmItems(){
        return mItems;
    }

    public void setmItems(List<FoodItem> mItems) {
        this.mItems.addAll(mItems);
        notifyDataSetChanged();
    }

    public ArrayList<FoodItem> getItemList() {return (ArrayList<FoodItem>) mItems;}

    public ArrayList<FoodItem> getCheckedItemList(){return (ArrayList<FoodItem>) mCheckedItems;}

    public int getCheckedBoxes(){return checkedBoxes;}

    public void setCheckedBoxes(int checkedBoxes){
        this.checkedBoxes = checkedBoxes;
    }

    public View getView(){return mView;}


}
