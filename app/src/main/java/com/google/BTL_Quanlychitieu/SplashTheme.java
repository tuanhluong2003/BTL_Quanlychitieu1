package com.google.BTL_Quanlychitieu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.BTL_Quanlychitieu.BroadcardReciver.InternetBroadcastReciver;
import com.google.BTL_Quanlychitieu.Entity.user;
import com.google.BTL_Quanlychitieu.Other.DataLocalManager;
import com.google.BTL_Quanlychitieu.Other.MyApplication;
import com.google.common.reflect.TypeToken;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.gson.Gson;

import java.io.Serializable;
import java.lang.reflect.Type;

public class SplashTheme  extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       Handler handler = new Handler();
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               if (MyApplication.User != null)
               {
                       dangnhap(MyApplication.User.username, MyApplication.User.Pass);
               } else {
                   Intent it = new Intent(SplashTheme.this, Signin.class);
                   startActivity(it);
                   finish();
               }
           }
       },2000);
    }

    InternetBroadcastReciver br = new InternetBroadcastReciver();
    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(br, intentFilter);
        IntentFilter filter = new IntentFilter("anhtu.action_internet");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(br, filter, null, null, Context.RECEIVER_EXPORTED);
        }
    }

    public void dangnhap(String username, String pass)
    {
        db.collection("user")
                .whereEqualTo("username",username)
                .whereEqualTo("pass", pass)
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null && task.getResult().size() == 1)
                    {
                        Toast.makeText(SplashTheme.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(SplashTheme.this, MainActivity.class);
                        QueryDocumentSnapshot tmp = (QueryDocumentSnapshot) task.getResult().getDocuments().get(0);
                        startActivity(it);
                        finish();
                    }
                    else
                    {
                        Intent it = new Intent(SplashTheme.this, Signin.class);
                        startActivity(it);
                        finish();
                    }
                });
    }
}
