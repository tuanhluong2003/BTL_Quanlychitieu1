package com.example.testdatabase.Other;

import android.content.Context;
import android.content.SharedPreferences;

public class MysharedPreferences {
    public static final String MY_SHARE_PREFERENCES = "MY_SHARE_PREFERENCES";
    private Context mContext;
    public MysharedPreferences(Context mContext) {
        this.mContext = mContext;
    }

    public void pushintvalue(String key, int value)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARE_PREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key,value);

        editor.apply();
    }

    public int getIntvalue(String key)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARE_PREFERENCES,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key,10000000);
    }
}
