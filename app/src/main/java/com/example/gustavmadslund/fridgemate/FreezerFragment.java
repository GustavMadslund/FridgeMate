package com.example.gustavmadslund.fridgemate;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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

        mAdapter = new FoodItemAdapter(getActivity(), freezerView, "Freezer");

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
                if (mAdapter.getCheckedItemList().size() == 0) {
                    Toast.makeText(getActivity(), "No items selected", Toast.LENGTH_SHORT).show();
                } else {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Delete items")
                            .setMessage("Delete " + mAdapter.getCheckedItemList().size() + " item(s) from freezer?")
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



        return freezerView;

    }
}
