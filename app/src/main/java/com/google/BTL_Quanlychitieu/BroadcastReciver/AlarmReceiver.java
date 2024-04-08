package com.google.BTL_Quanlychitieu.BroadcastReciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.BTL_Quanlychitieu.Other.Notificationn;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == "Myaction")
        {
            //Log.d("thongbao","den gio roi");
            Notificationn.sendnotification(context, intent);
        }
    }
}
