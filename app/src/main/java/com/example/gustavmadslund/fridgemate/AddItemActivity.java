package com.example.gustavmadslund.fridgemate;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.gustavmadslund.fridgemate.FoodItem.Place;

import java.util.Calendar;
import java.util.Date;


public class AddItemActivity extends Activity {
    // 7 days in milliseconds - 7 * 24 * 60 * 60 * 1000
    private static final int SEVEN_DAYS = 604800000;

    private static final String TAG = "Fridge-Log";

    private static String dateString;
    private static TextView dateView;

    private Date mDate;
    private RadioGroup mPlaceRadioGroup;
    private EditText mTitleText;
    private EditText mQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        mTitleText = (EditText) findViewById(R.id.title);
        mQuantity = (EditText) findViewById(R.id.quantityItem);
        mPlaceRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        dateView = (TextView) findViewById(R.id.textView);

        //Set the default date

        setDefaultDateTime();

        //OnClickListener for the Date Button, calls showDatePickerDialog()

        final Button datePickerButton = (Button) findViewById(R.id.button);
        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings: {
                return true;
            }
            case R.id.action_add: {
                submitItem();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    private void submitItem() {
        String titleString = getToDoTitle();
        Integer quantity = getQuantity();
        Place place = getPlace();
        Intent data = new Intent();
        FoodItem.packageIntent(data, titleString, quantity, place, dateString);

        setResult(RESULT_OK, data);
        finish();
    }

    private void setDefaultDateTime() {

        // Default is current time + 7 days
        mDate = new Date();
        mDate = new Date(mDate.getTime() + SEVEN_DAYS);

        Calendar c = Calendar.getInstance();
        c.setTime(mDate);

        setDateString(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));

        dateView.setText(dateString);
    }

    private static void setDateString(int year, int monthOfYear, int dayOfMonth) {

        // Increment monthOfYear for Calendar/Date -> Time Format setting
        monthOfYear++;
        String mon = "" + monthOfYear;
        String day = "" + dayOfMonth;

        if (monthOfYear < 10)
            mon = "0" + monthOfYear;
        if (dayOfMonth < 10)
            day = "0" + dayOfMonth;

        dateString = year + "-" + mon + "-" + day;
    }

    private Place getPlace() {
        switch (mPlaceRadioGroup.getCheckedRadioButtonId()) {
        case R.id.radioButtonFreezer:{
            return Place.FREEZER;
        }
        default: {
            return Place.FRIDGE;
        }
        }
    }

    private String getToDoTitle() {
        return mTitleText.getText().toString();
    }

    private Integer getQuantity() {
        return Integer.parseInt(mQuantity.getText().toString());
    }

    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the current date as the default date in the picker

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            setDateString(year, monthOfYear, dayOfMonth);

            dateView.setText(dateString);
        }

    }

    private void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }
}
