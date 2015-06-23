package com.example.gustavmadslund.fridgemate;

import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by GustavMadslund on 21/06/15.
 */
public class FreezerFragment extends ListFragment{

    FoodItemAdapter mAdapter;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //TODO - change result code to proper resultcode
        if (requestCode == 2 && resultCode == resultCode){
            FoodItem foodItem = new FoodItem(data);
            mAdapter.add(foodItem);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View freezerView = inflater.inflate(R.layout.fragment_layout, container, false);

        mAdapter = new FoodItemAdapter(getActivity(), freezerView);

        this.setListAdapter(mAdapter);

        Button addButton = (Button) freezerView.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(getActivity(), AddItemActivity.class);
                startActivityForResult(addIntent, 2);
            }
        });

        Button deleteButton = (Button) freezerView.findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Delete-button pressed in freezer", Toast.LENGTH_SHORT).show();
            }
        });



        return freezerView;

    }
}
