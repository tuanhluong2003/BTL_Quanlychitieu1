package com.google.BTL_Quanlychitieu.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.BTL_Quanlychitieu.Other.DataLocalManager;
import com.google.BTL_Quanlychitieu.Other.MyApplication;
import com.google.BTL_Quanlychitieu.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordDialog_second {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Context context;
    String email;


    public ChangePasswordDialog_second(Context context, String email)
    {
        this.context = context;
        this.email = email;
    }

    public void show(){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_change_password_second);
        Window window = dialog.getWindow();
        if (window == null) return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttr = window.getAttributes();
        windowAttr.gravity = Gravity.CENTER;
        window.setAttributes(windowAttr);
        dialog.setCancelable(true);

        Button btn_thoat, btn_continue;
        EditText ed_email, ed_newpass, ed_confirmpass, ed_oldpass;
        TextView tv_title;

        btn_thoat = dialog.findViewById(R.id.btn_thoat);
        btn_continue = dialog.findViewById(R.id.btn_continue);
        ed_email = dialog.findViewById(R.id.ed_email);
        ed_newpass = dialog.findViewById(R.id.ed_newpass);
        ed_confirmpass = dialog.findViewById(R.id.ed_cfpass);
        ed_oldpass = dialog.findViewById(R.id.ed_oldpass);

        ed_email.setText(email);

        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_continue.setOnClickListener(new View.OnClickListener() {
            boolean isEmpty(EditText str)
            {
                return TextUtils.isEmpty(str.getText().toString());
            }
            @Override
            public void onClick(View v) {
                if (isEmpty(ed_oldpass))
                    ed_oldpass.setError("Vui lòng nhập mật khẩu cũ");
                else
                    if (isEmpty(ed_newpass))
                        ed_newpass.setError("Vui lòng nhập mật khẩu mới");
                    else
                        if (!ed_newpass.getText().toString().equals(ed_confirmpass.getText().toString()))
                            ed_confirmpass.setError("Mật khẩu không khớp");
                        else
                        {
                            Map<String, Object> passchange = new HashMap<>();
                            passchange.put("pass",ed_newpass.getText().toString());
                            db.collection("user")
                                    .whereEqualTo("username",email)
                                     .whereEqualTo("pass",ed_oldpass.getText().toString()).
                                    get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if(task.isSuccessful() && !task.getResult().isEmpty()){
                                                DocumentSnapshot documentSnapshot= task.getResult().getDocuments().get(0);
                                                String id = documentSnapshot.getId();
                                                db.collection("user").document(id).update(passchange).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toast.makeText(context, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                                        // doi du lieu luu trong sharepreference
                                                        MyApplication.User.Pass = ed_newpass.getText().toString();
                                                        Gson gson = new Gson();
                                                        DataLocalManager.update_user(gson.toJson(MyApplication.User));
                                                        dialog.dismiss();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(context, "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                                                        dialog.dismiss();
                                                    }
                                                });
                                            } else {
                                                ed_oldpass.setError("Nhập đúng mật khẩu cũ");
                                            }
                                        }
                                    });
                        }
            }
        });
        dialog.show();
    }
}

