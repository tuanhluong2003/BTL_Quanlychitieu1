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

import com.google.BTL_Quanlychitieu.Adapter.Loaichi_spinner_adapter;
import com.google.BTL_Quanlychitieu.Entity.Chi;
import com.google.BTL_Quanlychitieu.Entity.Loaichi;
import com.google.BTL_Quanlychitieu.Other.Customdate;
import com.google.BTL_Quanlychitieu.ui.Chi.Khoanchi.Khoanchi;
import com.google.BTL_Quanlychitieu.R;

import java.time.LocalDate;
import java.util.List;

public class khoanchiDialog {
    Chi chi;

    Khoanchi fragmentkhoanchi;

    String type;

    public khoanchiDialog(Khoanchi khoanchi, String type,  Chi chi)
    {
        this.fragmentkhoanchi = khoanchi;
        this.chi = chi;
        this.type = type;
    }

    public void show(){
        final Dialog dialog = new Dialog(fragmentkhoanchi.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_khoanchi);

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
        EditText ed_idchi, ed_Sotien, ed_tenkhoanchi, ed_ghichu;

        btn_thoat = dialog.findViewById(R.id.btn_thoat);
        btn_luu = dialog.findViewById(R.id.btn_luu);
        spinner = dialog.findViewById(R.id.spinerkhoanchi);
        Loaichi_spinner_adapter adapter = new Loaichi_spinner_adapter(fragmentkhoanchi.getContext());
        ed_idchi  = dialog.findViewById(R.id.ed_idchi);
        ed_Sotien = dialog.findViewById(R.id.ed_sotienchi);
        ed_tenkhoanchi = dialog.findViewById(R.id.ed_tenkhoanchi);
        ed_ghichu = dialog.findViewById(R.id.ghichuchi);


        fragmentkhoanchi.getViewmodel().getAllLoaiChi().observe(fragmentkhoanchi.getActivity(), new Observer<List<Loaichi>>() {
            @Override
            public void onChanged(List<Loaichi> loaichis) {
                adapter.setList(loaichis);
            }
        });
        spinner.setAdapter(adapter);

        if (type.equals("seen"))
        {
            btn_luu.setVisibility(View.INVISIBLE);
            ed_idchi.setText(String.valueOf(chi.idchi));
            ed_tenkhoanchi.setText(chi.ten);
            ed_Sotien.setText(String.valueOf(chi.sotien));
            ed_ghichu.setText(chi.ghichu);
            spinner.setSelection(adapter.findposbyid(chi.idloaichi));
        }

        if (type.equals("edit"))
        {
            ed_idchi.setText(String.valueOf(chi.idchi));
            ed_tenkhoanchi.setText(chi.ten);
            ed_Sotien.setText(String.valueOf(chi.sotien));
            ed_ghichu.setText(chi.ghichu);
            spinner.setSelection(adapter.findposbyid(chi.idloaichi));
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
                    chi.ten = ed_tenkhoanchi.getText().toString();
                    chi.sotien = Float.parseFloat(ed_Sotien.getText().toString());
                    chi.ghichu = ed_ghichu.getText().toString();
                    chi.idloaichi = adapter.getItem(spinner.getSelectedItemPosition()).idloaichi;
                    fragmentkhoanchi.getViewmodel().update(chi);
                    Toast.makeText(fragmentkhoanchi.getContext(), "Update thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Chi tmp = new Chi();
                    tmp.ten = ed_tenkhoanchi.getText().toString();
                    tmp.sotien = Float.parseFloat(ed_Sotien.getText().toString());
                    tmp.ghichu = ed_ghichu.getText().toString();
                    tmp.date = Customdate.getLocaldatenow().toString();
                    tmp.time = Customdate.getLocaltimenow().toString();
                    tmp.idloaichi = adapter.getItem(spinner.getSelectedItemPosition()).idloaichi;
                    fragmentkhoanchi.getViewmodel().insert(tmp);
                    Toast.makeText(fragmentkhoanchi.getContext(), "Insert thành công", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}

