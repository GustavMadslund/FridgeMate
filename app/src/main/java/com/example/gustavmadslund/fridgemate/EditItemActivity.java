package com.example.gustavmadslund.fridgemate;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.gustavmadslund.fridgemate.FoodItem.Place;


public class EditItemActivity extends AppCompatActivity {

    private static final String TAG = "Fridge-Log";

    private TextView dateView;
    private TextView mTitleText;
    private TextView mQuantity;
    private RadioGroup mPlaceRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        mTitleText = (TextView) findViewById(R.id.title);
        mQuantity = (TextView) findViewById(R.id.quantityItem);
        mPlaceRadioGroup = (RadioGroup) findViewById(R.id.radioGroupPlace);
        dateView = (TextView) findViewById(R.id.textDate);
        
        mTitleText.setText("DummyText");
        mQuantity.setText("999");
        dateView.setText("X days");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_edit: {
                editItem();
                return true;
            }
            case R.id.action_delete: {
                deleteItem();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    private void editItem() {
        // TODO - Finish edit item
        Place place = getPlace();
        Intent data = new Intent();
        data.putExtra(FoodItem.PLACE,place);

        setResult(RESULT_OK, data);
        finish();
    }

    public void deleteItem() {
        // TODO - Finish delete item
        Intent data = new Intent();

        setResult(RESULT_OK, data);
        finish();
    }

    private Place getPlace() {
        switch (mPlaceRadioGroup.getCheckedRadioButtonId()) {
            case R.id.radioButtonFreezer:{
                return FoodItem.Place.FREEZER;
            }
            default: {
                return FoodItem.Place.FRIDGE;
            }
        }
    }


}
