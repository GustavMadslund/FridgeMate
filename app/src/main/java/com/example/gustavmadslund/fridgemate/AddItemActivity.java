package com.example.gustavmadslund.fridgemate;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gustavmadslund.fridgemate.FoodItem.Place;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class AddItemActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener {
    // 7 days in milliseconds - 7 * 24 * 60 * 60 * 1000
    private static final int SEVEN_DAYS = 604800000;

    private static final String TAG = "FridgeFragment-Log";
    private static String dateString;
    private static TextView dateView;

    private Date mDate;
    private RadioGroup mPlaceRadioGroup;
    private EditText mTitleText;
    private EditText mQuantity;
    private ImageView mImageView;

    private AlarmManager mAlarmManager;
    private Intent mAlarmIntent;
    private PendingIntent mAlarmPendingIntent;
    private int mYear;
    private int mMonthOfYear;
    private int mDayOfMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        mTitleText = (EditText) findViewById(R.id.title);
        mQuantity = (EditText) findViewById(R.id.quantityItem);
        mPlaceRadioGroup = (RadioGroup) findViewById(R.id.radioGroupPlace);
        mImageView = (ImageView) findViewById(R.id.imageView);
        dateView = (TextView) findViewById(R.id.textView);

        //Set hint text
        mTitleText.setHint("Name");
        mQuantity.setHint("Quantity");
        mQuantity.setText("1");
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);



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

        // Gets the AlarmManager to handle alarms
        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

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
            case R.id.action_add: {
                if(getToDoTitle().isEmpty())
                    Toast.makeText(getApplicationContext(),"Name is empty",Toast.LENGTH_SHORT).show();
                else{
                    submitItem();
                    return true;
                }
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
        Integer dateDifference = getDateDiff(mDate);
        Intent data = new Intent();
        FoodItem.packageIntent(data, titleString, quantity, place, dateDifference);
        setAlarm(mYear, mMonthOfYear, mDayOfMonth);

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


       Log.v("Notification", "Time set for String");
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

    private String getPlaceToString(){
        switch (mPlaceRadioGroup.getCheckedRadioButtonId()) {
            case R.id.radioButtonFreezer: {
                return "freezer";
            }
            default: {
                return "fridge";
            }
        }
    }

    public String getToDoTitle() {
        return mTitleText.getText().toString();
    }

    private Integer getQuantity() {
        return Integer.parseInt(mQuantity.getText().toString());
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear,
                          int dayOfMonth) {
        setDateString(year, monthOfYear, dayOfMonth);

        mYear = year;
        mMonthOfYear = monthOfYear;
        mDayOfMonth = dayOfMonth;

        dateView.setText(dateString);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, monthOfYear);
        cal.set(Calendar.DATE, dayOfMonth);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        mDate = cal.getTime();

        Log.v("Notification", "Data set");
        // Passes the entered date-options to method for alarm-creation


    }

    public void setAlarm(int year, int monthOfYear, int dayOfMonth){
        // Intent to be broadcasted as a notification
        mAlarmIntent = new Intent(AddItemActivity.this, AlarmNotification.class);

        // Puts the user-defined item-name and place with the intent to show with notification later on
        mAlarmIntent.putExtra("NAME_OF_ITEM",  getToDoTitle().toString());
        mAlarmIntent.putExtra("PLACE_OF_ITEM",  getPlaceToString());

        // PendingIntent that holds the above intent
        mAlarmPendingIntent = PendingIntent.getBroadcast(AddItemActivity.this, 0, mAlarmIntent, 0);

        Log.v("Notification","Intent created");

        // Creates Calendar-object to convert date-format to time in milliseconds
        Calendar mAlarmDate = Calendar.getInstance();
        mAlarmDate.setTimeInMillis(System.currentTimeMillis());
        mAlarmDate.set(year, monthOfYear, dayOfMonth, 12, 00, 0);

        // Sets the alarm
        mAlarmManager.set(AlarmManager.RTC_WAKEUP, mAlarmDate.getTimeInMillis(), mAlarmPendingIntent);
        //mAlarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 3*1000L, mAlarmPendingIntent);
        Log.v("Notification","Alarm set successfully");

    }

//////////////////////////////////NEW CLASS//////////////////////////////////////////////
    public static class DatePickerFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
    
            // Use the current date as the default date in the picker
    
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
    
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), (AddItemActivity) getActivity(), year, month, day);
        }
    }

//////////////////////////////// END NEW CLASS///////////////////////////////////////////

    private void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public int getDateDiff(Date futureDate) {
        Log.i(TAG, futureDate.toString());
        Log.i(TAG, new Date().toString());
        long diffInMillies = futureDate.getTime() - new Date().getTime();
        return (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
    
