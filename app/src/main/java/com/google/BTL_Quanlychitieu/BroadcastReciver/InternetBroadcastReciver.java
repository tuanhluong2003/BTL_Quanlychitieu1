package com.google.BTL_Quanlychitieu.BroadcardReciver;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class InternetBroadcastReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE") || intent.getAction().equals("anhtu.action_internet"));
        {
            if (isNetWorkAvailable(context))
                Toast.makeText(context, "Internet Connected", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context, "Internet Disconected", Toast.LENGTH_SHORT).show();
        }
    }
    @SuppressLint("ServiceCast")
    private boolean isNetWorkAvailable(@NonNull Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) return false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            Network network = connectivityManager.getActiveNetwork();
            if (network == null)
                return false;

            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
            return capabilities!=null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)|| capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
        }
        else
        {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable())
                return true;
            else return false;
        }
    }
}
