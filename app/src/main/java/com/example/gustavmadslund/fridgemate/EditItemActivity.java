package com.example.gustavmadslund.fridgemate;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.gustavmadslund.fridgemate.FoodItem.Place;

import java.util.Date;


public class EditItemActivity extends AppCompatActivity {

    private static final String TAG = "FridgeFragment-Log";

    private TextView dateView;
    private TextView mTitleText;
    private TextView mQuantity;
    private RadioGroup mPlaceRadioGroup;
    private FoodItem mFoodItem;
    private int index;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        mTitleText = (TextView) findViewById(R.id.title);
        mQuantity = (TextView) findViewById(R.id.quantityItem);
        mPlaceRadioGroup = (RadioGroup) findViewById(R.id.radioGroupPlace);
        dateView = (TextView) findViewById(R.id.textDate);
        mImageView = (ImageView) findViewById(R.id.imageView);
        Intent intent = getIntent();
        mFoodItem = new FoodItem(intent);
        index = intent.getIntExtra("Index", 0);


        mTitleText.setText(getItemTitle());
        mQuantity.setText(getQuantity().toString());
        dateView.setText(getDate() + " days");
        setPlace();
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);

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
        Place place = setPlace();
        String name = setName();
        Intent data = new Intent();
        data.putExtra(FoodItem.PLACE,place);
        data.putExtra(FoodItem.TITLE, name);
        data.putExtra("Index", index);

        setResult(RESULT_OK, data);
        finish();
    }

    public void deleteItem() {
        // TODO - Finish delete item
        Intent data = new Intent();

        setResult(RESULT_OK, data);
        finish();
    }

    private Place setPlace() {
        switch (mPlaceRadioGroup.getCheckedRadioButtonId()) {
            case R.id.radioButtonFreezer:{
                return FoodItem.Place.FREEZER;
            }
            default: {
                return FoodItem.Place.FRIDGE;
            }
        }
    }

    private String setName() {
        return String.valueOf(mTitleText.getText());
    }

    private Integer getDate() {return (int) mFoodItem.getDateDiff();}

    private Place getPlace() {return mFoodItem.getPlace();}

    private Integer getQuantity() {return mFoodItem.getQuantity();}

    private String getItemTitle() {return mFoodItem.getTitle();}



}
