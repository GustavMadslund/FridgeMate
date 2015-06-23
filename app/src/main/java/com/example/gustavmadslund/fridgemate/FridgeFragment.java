package com.example.gustavmadslund.fridgemate;


import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GustavMadslund on 19/06/15.
 */
public class FridgeFragment extends ListFragment {

    FoodItemAdapter mAdapter;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //TODO - change result code to proper resultcode
        if (requestCode == 1 && resultCode == resultCode){
            FoodItem foodItem = new FoodItem(data);
            mAdapter.add(foodItem);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fridgeView = inflater.inflate(R.layout.fragment_layout, container, false);
        super.onCreate(savedInstanceState);

        mAdapter = new FoodItemAdapter(getActivity(), fridgeView);
        this.setListAdapter(mAdapter);
        
        Button addButton = (Button) fridgeView.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(getActivity(),"Add-button pressed in fridge", Toast.LENGTH_SHORT).show();*/
                Intent addIntent = new Intent(getActivity(), AddItemActivity.class);
                startActivityForResult(addIntent,1);
            }
        });

        Button deleteButton = (Button) fridgeView.findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapter.getCheckedItemList().size() == 0) {
                    Toast.makeText(getActivity(), "No items selected", Toast.LENGTH_SHORT).show();
                } else {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Delete items")
                            .setMessage("Delete " + mAdapter.getCheckedItemList().size() + " items?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    for (FoodItem foodItem : mAdapter.getCheckedItemList()) {
                                        mAdapter.getItemList().remove(foodItem);
                                        mAdapter.notifyDataSetChanged();


                                    }
                                    mAdapter.setCheckedBoxes(mAdapter.getCheckedBoxes() - mAdapter.getCheckedItemList().size());

                                    TextView mTextView = (TextView) mAdapter.getView().findViewById(R.id.items_selected);
                                    mTextView.setText(mAdapter.getCheckedBoxes() + " items selected");

                                    mAdapter.getCheckedItemList().clear();

                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                }
            }
            });

        return fridgeView;

    }

    public FoodItemAdapter getFoodItemAdapter() {return mAdapter;}

}


