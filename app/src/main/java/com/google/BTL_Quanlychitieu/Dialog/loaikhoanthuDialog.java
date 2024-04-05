package com.google.BTL_Quanlychitieu.Dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.BTL_Quanlychitieu.Entity.Loaithu;
import com.google.BTL_Quanlychitieu.Other.MyApplication;
import com.google.BTL_Quanlychitieu.R;
import com.google.BTL_Quanlychitieu.ui.Thu.Loaikhoanthu.Loaikhoanthu;

public class loaikhoanthuDialog {
    Loaithu loaithu;

    Loaikhoanthu fragmentloaikhoanthu;

    EditText ed_loaikhoanthu;


    String type;

    public loaikhoanthuDialog(Loaikhoanthu loaikhoanthu, String type, Loaithu loaithu)
    {
        this.fragmentloaikhoanthu = loaikhoanthu;
        this.loaithu = loaithu;
        this.type = type;
    }

    public void show(){
        final Dialog dialog = new Dialog(fragmentloaikhoanthu.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_loaithu);

        Window window = dialog.getWindow();
        if (window == null) return;

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttr = window.getAttributes();
        windowAttr.gravity = Gravity.CENTER;
        window.setAttributes(windowAttr);
        dialog.setCancelable(true);

        Button btn_thoat, btn_luu;
        btn_thoat = dialog.findViewById(R.id.btn_thoat);
        btn_luu = dialog.findViewById(R.id.btn_luu);
        ed_loaikhoanthu = dialog.findViewById(R.id.ed_tenloaikhoanthu);


        if (type.equals("edit"))
        {
            ed_loaikhoanthu.setText(loaithu.Tenloaithu);
        }


        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();;
            }
        });

        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("edit")) {
                    loaithu.Tenloaithu = ed_loaikhoanthu.getText().toString();
                    fragmentloaikhoanthu.getViewModel().update(loaithu);
                    Toast.makeText(fragmentloaikhoanthu.getContext(), "Update thành công", Toast.LENGTH_SHORT).show();

                } else {
                    Loaithu tmp = new Loaithu();
                    tmp.Tenloaithu = ed_loaikhoanthu.getText().toString();
                    tmp.user = MyApplication.User.username;
                    fragmentloaikhoanthu.getViewModel().insert(tmp);
                    Toast.makeText(fragmentloaikhoanthu.getContext(), "Insert thành công", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}

