package com.google.BTL_Quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.BTL_Quanlychitieu.Other.CustomPicture;
import com.google.BTL_Quanlychitieu.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    private ActivitySignUpBinding biding;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        biding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(biding.getRoot());
        firestore = FirebaseFirestore.getInstance();
        CollectionReference reference = firestore.collection("user");

        biding.btnLinklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SignUp.this, Signin.class);
                startActivity(it);
            }
        });

        biding.btnDangkyy.setOnClickListener(new View.OnClickListener() {

            boolean isEmpty(EditText str)
            {
                return TextUtils.isEmpty(str.getText().toString());
            }

            boolean isEmail(EditText str)
            {
                return (!isEmpty(str) && Patterns.EMAIL_ADDRESS.matcher(str.getText().toString()).matches());
            }
            void signup()
            {
                Map<String, String> mymap = new HashMap<String , String>();
                mymap.put("name",biding.edName.getText().toString());
                mymap.put("username", biding.edUsernamesignup.getText().toString());
                mymap.put("pass", biding.edPassSignup.getText().toString());
                mymap.put("avatar", "ss_"+(int)(Math.random()*10-1));
                reference.add(mymap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(SignUp.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(SignUp.this, Signin.class);
                        it.putExtra("acc",biding.edUsernamesignup.getText().toString());
                        it.putExtra("pass",biding.edPassSignup.getText().toString());
                        startActivity(it);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUp.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            void checksignup()
            {
                reference.whereEqualTo("username",biding.edUsernamesignup.getText().toString())
                        .get().addOnCompleteListener(task -> {
                            if (task.isSuccessful() && task.getResult() != null && task.getResult().size() > 0)
                                Toast.makeText(SignUp.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                            else signup();
                        });
                //dung thi cho signup();
            }
            void checkdata()
            {
                if (isEmpty(biding.edName))
                {
                    biding.edName.setError("Nhập tên đi");
                } else
                if (isEmpty(biding.edUsernamesignup))
                {
                    biding.edUsernamesignup.setError("Nhập tên tài khoản đi");
                } else
                if (!isEmail(biding.edUsernamesignup))
                {
                    biding.edUsernamesignup.setError("Nhập đúng định dạng email đi");
                } else
                if (isEmpty(biding.edPassSignup))
                {
                    biding.edPassSignup.setError("Nhập mật khẩu đi");
                } else
                if (isEmpty(biding.edCfpass))
                {
                    biding.edCfpass.setError("Nhập lại mật khẩu đi");
                } else
                if (!biding.edPassSignup.getText().toString().equals(biding.edCfpass.getText().toString()))
                {
                    Toast.makeText(SignUp.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                } else checksignup();

            }
            @Override
            public void onClick(View v) {
                checkdata();
            }
        });
    }
}