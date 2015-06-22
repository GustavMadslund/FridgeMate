package com.example.gustavmadslund.fridgemate;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Simon on 20-06-2015.
 */

public class AlarmNotification extends BroadcastReceiver {
    private static final int NOTIFICATION_ID = 1;

    private Intent mNotificationIntent;
    private PendingIntent mNotificationPendingIntent;

    private String item;
    private String place;

    @Override
    public void onReceive(Context context, Intent intent) {
///////////////////////////FIX TO CONCRETE ITEM WHEN ACTIVITY IS DONE/////////////////////////////
        Log.v("Notification", "Notification-request received");

        // Intent to redirect user from shown Notification to see the added item
        mNotificationIntent = new Intent(context, AddItemActivity.class);

        // Creates Pending-intent with above intent
        mNotificationPendingIntent = PendingIntent.getActivity(context, 0, mNotificationIntent, Intent.FLAG_ACTIVITY_NEW_TASK);



        Bundle extras = intent.getExtras();

        if (place == null ||place == ""){
            item = "item";
        } else {
            item = extras.getString("NAME_OF_ITEM");
        }

        place = extras.getString("PLACE_OF_ITEM", "inventory");


        Notification.Builder notificationBuild = new Notification.Builder(context)
                .setTicker("Notification from FridgeMate")
                .setContentIntent(mNotificationPendingIntent)
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setContentTitle("FridgeMate")
                .setContentText("A(n) " + item + " in your " + place + " expires today")
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true);


        Log.v("Notification", "Notification created");

        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        // Please note: NotificationManager.build() was first introduced in API 16,
        // so for API 11-15 .getNotification() is used
        mNotificationManager.notify(NOTIFICATION_ID, notificationBuild.getNotification());

        Log.v("Notification", "Notification sent");




    }
}
