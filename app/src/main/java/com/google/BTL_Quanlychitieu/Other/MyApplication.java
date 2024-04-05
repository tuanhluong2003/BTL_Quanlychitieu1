package com.google.BTL_Quanlychitieu.Other;

import android.app.Application;

import com.google.BTL_Quanlychitieu.Entity.user;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.logging.Handler;

public class MyApplication extends Application {

    public static user User = null;
    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManager.init(getApplicationContext());
        CustomPicture.init(getApplicationContext());
        android.util.Log.d("thongbao","vao roi ne");
        String tmpuser = DataLocalManager.get_user();
        if (tmpuser != null)
            if (tmpuser != null){
                Gson gson = new Gson();
                Type objtype = new TypeToken<user>(){}.getType();
                User = gson.fromJson(tmpuser, objtype);
            }
    }
}
