package com.google.BTL_Quanlychitieu.ui.home;

import static java.lang.Math.max;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.BTL_Quanlychitieu.Other.CustomNumber;
import com.google.BTL_Quanlychitieu.Other.DataLocalManager;
import com.google.BTL_Quanlychitieu.databinding.FragmentHomeBinding;
import com.google.BTL_Quanlychitieu.ui.Chi.Khoanchi.Khoanchi;
import com.google.BTL_Quanlychitieu.ui.Chi.Khoanchi.KhoanchiViewModel;

import java.util.Calendar;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    HomeFragment current;

    HomeViewModel mViewModel;

    TextView tvtongchi;
    TextView tvtongthu;

    TextView tvtongngansach;

    TextView tv_ngansachdu;
    ProgressBar progressBar;

    int tongthu;
    int tongchi;

    public HomeViewModel getViewmodel() {
        return mViewModel;
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        current = this;
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvtongchi = binding.tbTongchi;
        tvtongthu = binding.tbTongthu;
        tvtongngansach = binding.tvTongngansach;
        tv_ngansachdu = binding.tvNgansachdu;
        progressBar = binding.progressbar;
        binding.tbNgaythang.setText("Tháng "+ (Calendar.getInstance().get(Calendar.MONTH)+1) +" năm " + Calendar.getInstance().get(Calendar.YEAR));
        mViewModel.getTongchi().observe(getActivity(), new Observer<Float>() {
            @Override
            public void onChanged(Float aFloat) {
                if (aFloat != null)
                    tongchi = aFloat.intValue();
                else tongchi = 0;
                tvtongchi.setText( "- " + CustomNumber.formatNumber(tongchi) + " Đồng");
                Loadprogressbar();
            }
        });

        mViewModel.getTongthu().observe(getActivity(), new Observer<Float>() {
            @Override
            public void onChanged(Float aFloat) {
                if (aFloat != null)
                    tongthu = aFloat.intValue();
                else tongthu = 0;
                tvtongthu.setText( "+ " + CustomNumber.formatNumber(tongthu) + " Đồng");
                Loadprogressbar();
            }
        });

        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loadprogressbar();
            }
        });
    }

    public void Loadprogressbar()
    {
        int tonngansach = DataLocalManager.get_mucchitieu();
        progressBar.setMax(tonngansach);
        tvtongngansach.setText("Ngân sách: " + CustomNumber.formatNumber(tonngansach) +" Đồng");
        progressBar.setProgress(max(0,tongchi-tongthu));
        tv_ngansachdu.setText("Ngân sách còn dư: " + CustomNumber.formatNumber(tonngansach-tongchi+tongthu)+ " Đồng");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}