package com.example.gustavmadslund.fridgemate;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by GustavMadslund on 19/06/15.
 */
public class FridgeFragment extends ListFragment {

    FoodItemAdapter mAdapter;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == resultCode){
            FoodItem foodItem = new FoodItem(data);
            mAdapter.add(foodItem);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mAdapter = new FoodItemAdapter(getActivity());

        this.setListAdapter(mAdapter);

        View fridgeView = inflater.inflate(R.layout.fragment_layout, container, false);


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
                Toast.makeText(getActivity(),"Delete-button pressed in fridge", Toast.LENGTH_SHORT).show();
            }
        });

        return fridgeView;

    }

}


