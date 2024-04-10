//package com.google.BTL_Quanlychitieu;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Toast;
//
//import com.google.BTL_Quanlychitieu.Dialog.forgotPasswordDialog;
//import com.google.BTL_Quanlychitieu.Entity.user;
//import com.google.BTL_Quanlychitieu.Other.MyApplication;
//import com.google.BTL_Quanlychitieu.databinding.ActivitySigninBinding;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import java.io.Serializable;
//
//public class Signin extends AppCompatActivity {
//
//    private ActivitySigninBinding binding;
//    FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivitySigninBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        Intent it = getIntent();
//        Bundle bundle = it.getExtras();
//
//        if (bundle != null)
//        {
//            binding.edUsername.setText(it.getExtras().getString("acc"));
//            binding.edPass.setText(it.getExtras().getString("pass"));
//        }
//        binding.btnDangnhap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dangnhap(binding.edUsername.getText().toString(), binding.edPass.getText().toString());
//            }
//        });
//
//        //button chuyển từ signin -> signup
//        binding.btnNotacc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it = new Intent(Signin.this, SignUp.class);
//                startActivity(it);
//                finish();
//            }
//        });
//
//        binding.btnForgotpw.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                forgotPasswordDialog dialog = new forgotPasswordDialog(Signin.this,
//                        "",
//                        binding.edUsername.getText().toString(),
//                        forgotPasswordDialog.TYPE_EMAIL);
//                dialog.show();
//            }
//        });
//    }
//
//
//    public void dangnhap(String username, String pass)
//    {
//        db.collection("user")
//                .whereEqualTo("username",username)
//                .whereEqualTo("pass", pass)
//                .get().addOnCompleteListener(task -> {
//                    if (task.isSuccessful() && task.getResult() != null && task.getResult().size() == 1)
//                    {
//                        Toast.makeText(Signin.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                        Intent it = new Intent(Signin.this, MainActivity.class);
//                        QueryDocumentSnapshot tmp = (QueryDocumentSnapshot) task.getResult().getDocuments().get(0);
//                        user tmpuser = new user(
//                                // lấy dữ liệu mà mình select đc cho vào thằng tmpuser.
//                                tmp.get("name").toString(),
//                                tmp.get("username").toString(),
//                                tmp.get("pass").toString(),
//                                tmp.get("avatar").toString());
//                        it.putExtra("dulieu", (Serializable) tmpuser);
//                        MyApplication.User = tmpuser;
//                        startActivity(it);
//                        finish();
//                    }
//                    else
//                        Toast.makeText(Signin.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
//                });
//    }
//}