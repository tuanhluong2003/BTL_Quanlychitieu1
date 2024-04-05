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
import android.widget.Spinner;
import android.widget.Toast;
import androidx.lifecycle.Observer;
import com.google.BTL_Quanlychitieu.Adapter.Loaithu_spinner_adapter;
import com.google.BTL_Quanlychitieu.Entity.Thu;
import com.google.BTL_Quanlychitieu.Entity.Loaithu;
import com.google.BTL_Quanlychitieu.Other.Customdate;
import com.google.BTL_Quanlychitieu.Other.MyApplication;
import com.google.BTL_Quanlychitieu.R;
import com.google.BTL_Quanlychitieu.ui.Thu.Khoanthu.Khoanthu;
import java.util.List;

public class khoanthuDialog {
    Thu thu;
    Khoanthu fragmentkhoanthu;
    String type;

    public khoanthuDialog(Khoanthu khoanthu, String type,  Thu thu)
    {
        this.fragmentkhoanthu = khoanthu;
        this.thu = thu;
        this.type = type;
    }

    public void show(){
        final Dialog dialog = new Dialog(fragmentkhoanthu.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_khoanthu);

        Window window = dialog.getWindow();
        if (window == null) return;

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttr = window.getAttributes();
        windowAttr.gravity = Gravity.CENTER;
        window.setAttributes(windowAttr);
        dialog.setCancelable(true);

        Button btn_thoat, btn_luu;
        Spinner spinner;
        EditText ed_idthu, ed_Sotien, ed_tenkhoanthu, ed_ghichu;

        btn_thoat = dialog.findViewById(R.id.btn_thoat);
        btn_luu = dialog.findViewById(R.id.btn_luu);
        spinner = dialog.findViewById(R.id.spinerkhoanthu);
        Loaithu_spinner_adapter adapter = new Loaithu_spinner_adapter(fragmentkhoanthu.getContext());
        ed_idthu  = dialog.findViewById(R.id.ed_idthu);
        ed_Sotien = dialog.findViewById(R.id.ed_sotienthu);
        ed_tenkhoanthu = dialog.findViewById(R.id.ed_tenkhoanthu);
        ed_ghichu = dialog.findViewById(R.id.ghichuthu);
        fragmentkhoanthu.getViewmodel().getAllLoaiThu().observe(fragmentkhoanthu.getActivity(), new Observer<List<Loaithu>>() {
            @Override
            public void onChanged(List<Loaithu> loaithus) {
                if (loaithus != null && loaithus.size()!=0)
                {
                    adapter.setList(loaithus);
                } else
                {
                    Loaithu tmp = new Loaithu();
                    tmp.user = MyApplication.User.username;
                    tmp.Tenloaithu = "Loại thu KXD";
                    fragmentkhoanthu.getViewmodel().insert(tmp);
                }

            }
        });
        spinner.setAdapter(adapter);

        if (type.equals("seen"))
        {
            btn_luu.setVisibility(View.INVISIBLE);
            ed_idthu.setText(String.valueOf(thu.idthu));
            ed_tenkhoanthu.setText(thu.ten);
            ed_Sotien.setText(String.valueOf(thu.sotien));
            ed_ghichu.setText(thu.ghichu);
            spinner.setSelection(adapter.findposbyid(thu.idloaithu));
        }

        if (type.equals("edit"))
        {
            ed_idthu.setText(String.valueOf(thu.idthu));
            ed_tenkhoanthu.setText(thu.ten);
            ed_Sotien.setText(String.valueOf(thu.sotien));
            ed_ghichu.setText(thu.ghichu);
            spinner.setSelection(adapter.findposbyid(thu.idloaithu));
        }


        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("edit")) {
                    thu.ten = ed_tenkhoanthu.getText().toString();
                    thu.sotien = Float.parseFloat(ed_Sotien.getText().toString());
                    thu.ghichu = ed_ghichu.getText().toString();
                    thu.idloaithu = adapter.getItem(spinner.getSelectedItemPosition()).idloaithu;
                    fragmentkhoanthu.getViewmodel().update(thu);
                    Toast.makeText(fragmentkhoanthu.getContext(), "Update thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Thu tmp = new Thu();
                    tmp.ten = ed_tenkhoanthu.getText().toString();
                    tmp.sotien = Float.parseFloat(ed_Sotien.getText().toString());
                    tmp.ghichu = ed_ghichu.getText().toString();
                    tmp.date = Customdate.getLocaldatenow().toString();
                    tmp.time = Customdate.getLocaltimenow().toString();
                    tmp.idloaithu = adapter.getItem(spinner.getSelectedItemPosition()).idloaithu;
                    tmp.user = MyApplication.User.username;
                    fragmentkhoanthu.getViewmodel().insert(tmp);
                    Toast.makeText(fragmentkhoanthu.getContext(), "Insert thành công", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}

