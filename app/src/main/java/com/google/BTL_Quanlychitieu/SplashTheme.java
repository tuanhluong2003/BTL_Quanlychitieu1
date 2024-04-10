//package com.google.BTL_Quanlychitieu;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.BTL_Quanlychitieu.BroadcastReciver.AlarmReceiver;
//import com.google.BTL_Quanlychitieu.BroadcastReciver.InternetBroadcastReciver;
//import com.google.BTL_Quanlychitieu.Other.MyApplication;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//
//public class SplashTheme  extends AppCompatActivity {
//
//    FirebaseFirestore db = FirebaseFirestore.getInstance();
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        new Handler().postDelayed(new Runnable() { // tạo ra 1 luồng chạy riêng
//            @Override
//            public void run() {
//                if (MyApplication.User != null)
//                {
//                    dangnhap(MyApplication.User.username, MyApplication.User.Pass);
//                } else {
//                    Intent it = new Intent(SplashTheme.this, Signin.class);
//                    startActivity(it);
//                    finish();
//                }
//            }
//        },2000);
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//    }
//
//    public void dangnhap(String username, String pass)
//    {
//        db.collection("user")
//                .whereEqualTo("username",username)
//                .whereEqualTo("pass", pass)
//                .get().addOnCompleteListener(task -> {
//                    if (task.isSuccessful() && task.getResult() != null && task.getResult().size() == 1)
//                    {
//                        Toast.makeText(SplashTheme.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                        Intent it = new Intent(SplashTheme.this, MainActivity.class);
//                        // QueryDocumentSnapshot tmp = (QueryDocumentSnapshot) task.getResult().getDocuments().get(0);
//                        startActivity(it);
//                        finish();
//                    }
//                    else
//                    {
//                        Intent it = new Intent(SplashTheme.this, Signin.class);
//                        startActivity(it);
//                        finish();
//                    }
//                });
//    }
//}
