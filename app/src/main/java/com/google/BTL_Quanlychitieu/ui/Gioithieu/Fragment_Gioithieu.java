package com.google.BTL_Quanlychitieu.ui.Gioithieu;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.BTL_Quanlychitieu.Other.Customdate;
import com.google.BTL_Quanlychitieu.R;

import java.time.LocalDate;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Gioithieu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Gioithieu extends Fragment {
    private static final String ARG_PARAM1 = "parm1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    TextView date, gio, phut;



    public Fragment_Gioithieu() {
        // Required empty public constructor
    }
    public static Fragment_Gioithieu newInstance(String param1, String param2) {
        Fragment_Gioithieu fragment = new Fragment_Gioithieu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        date = view.findViewById(R.id.ngay);
        gio = view.findViewById(R.id.gio);
        phut = view.findViewById(R.id.phut);
        Calendar calendar = Calendar.getInstance();
        int ngayy = calendar.get(Calendar.DAY_OF_MONTH);
        int thangg = calendar.get(Calendar.MONTH) + 1;
        int nam = calendar.get(Calendar.YEAR);
        LocalDate date1 = Customdate.getLocaldate(nam, thangg, ngayy);
        date.setText(Customdate.ConvertDate(date1));
        gio.setText(String.valueOf(calendar.get(Calendar.HOUR)));
        phut.setText(String.valueOf(calendar.get(Calendar.MINUTE)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment__gioithieu, container, false);
    }
}