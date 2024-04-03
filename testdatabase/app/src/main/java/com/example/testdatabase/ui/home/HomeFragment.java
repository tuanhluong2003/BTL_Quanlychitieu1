package com.example.testdatabase.ui.home;

import static java.lang.Math.max;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testdatabase.Adapter.ChiHome_R_Adapter;
import com.example.testdatabase.Adapter.Chidukien_R_Adapter;
import com.example.testdatabase.Adapter.ThuHome_R_Adapter;
import com.example.testdatabase.Dialog.AlertDialogg;
import com.example.testdatabase.Dialog.khoanchidukienDialog;
import com.example.testdatabase.Dialog.ngansachDialog;
import com.example.testdatabase.Entity.Chi;
import com.example.testdatabase.Entity.ChiDuKien;
import com.example.testdatabase.Entity.Loaichi;
import com.example.testdatabase.Entity.Loaithu;
import com.example.testdatabase.Entity.Thu;
import com.example.testdatabase.Listener.DialogListener;
import com.example.testdatabase.Listener.ItemClickListener;
import com.example.testdatabase.Other.CustomNumber;
import com.example.testdatabase.Other.DataLocalManager;
import com.example.testdatabase.R;
import com.example.testdatabase.databinding.FragmentHomeBinding;

import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    HomeFragment current;

    HomeViewModel mViewModel = null;

    TextView tvtongchi;
    TextView tvtongthu;

    TextView tvtongngansach;

    TextView tv_ngansachdu;

    TextView tv_dukien;
    ProgressBar progressBar;

    RecyclerView rv_chi;

    RecyclerView rv_thu;

    RecyclerView rv_chidukien;

    Button btn_addukien;
    int tongthu;
    int tongchi;

    int tongchidk;


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
        rv_chi = binding.rvChi;
        rv_thu = binding.rvThu;
        rv_chidukien = binding.rvChidukien;
        btn_addukien = binding.btnAdd;
        tv_dukien = binding.tbDukien;
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

        tvtongngansach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ngansachDialog dialog = new ngansachDialog(current.getContext(), new DialogListener() {
                    @Override
                    public void dialogPositive() {
                        Loadprogressbar();
                    }
                });
                dialog.show();
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

        mViewModel.getTongchidukien().observe(getActivity(), new Observer<Float>() {
            @Override
            public void onChanged(Float aFloat) {
                if (aFloat != null)
                    tongchidk = aFloat.intValue();
                else tongchidk = 0;
                Loadprogressbar();
            }
        });

        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loadprogressbar();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(current.getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rv_chi.setLayoutManager(linearLayoutManager);
        ChiHome_R_Adapter adapterchi = new ChiHome_R_Adapter(getContext());
        rv_chi.setAdapter(adapterchi);
        mViewModel.getAllchi().observe(getActivity(), new Observer<List<Chi>>() {
            @Override
            public void onChanged(List<Chi> chis) {
                adapterchi.setList(chis);
            }
        });

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(current.getContext());
        linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);

        rv_thu.setLayoutManager(linearLayoutManager1);
        ThuHome_R_Adapter adapterthu = new ThuHome_R_Adapter(getContext());
        rv_thu.setAdapter(adapterthu);
        mViewModel.getAllthu().observe(getActivity(), new Observer<List<Thu>>() {
            @Override
            public void onChanged(List<Thu> thus) {
                adapterthu.setList(thus);
            }
        });


        btn_addukien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khoanchidukienDialog dialog = new khoanchidukienDialog(current, "insert", null);
                dialog.show();
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1);
        rv_chidukien.setLayoutManager(gridLayoutManager);
        Chidukien_R_Adapter adapterchidk = new Chidukien_R_Adapter(getContext());
        adapterchidk.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(Thu position) {

            }

            @Override
            public void onItemClick(Chi position) {

            }

            @Override
            public void onItemClick(Loaichi position) {

            }

            @Override
            public void onItemClick(Loaithu position) {

            }

            @Override
            public void onItemClick(ChiDuKien position)
            {
                khoanchidukienDialog dialog = new khoanchidukienDialog(current, "seen", position);
                dialog.show();
            }
        });

        adapterchidk.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(Thu position) {
            }

            @Override
            public void onItemClick(Chi position) {

            }

            @Override
            public void onItemClick(Loaichi position) {

            }

            @Override
            public void onItemClick(Loaithu position) {

            }
            @Override
            public void onItemClick(ChiDuKien position) {
                khoanchidukienDialog dialog = new khoanchidukienDialog(current, "edit", position);
                dialog.show();
            }
        });

        rv_chidukien.setAdapter(adapterchidk);
        mViewModel.getAllchidukien().observe(getActivity(), new Observer<List<ChiDuKien>>() {
            @Override
            public void onChanged(List<ChiDuKien> chiDuKiens) {
                adapterchidk.setList(chiDuKiens);
            }
        });

        ItemTouchHelper helper=new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        AlertDialogg dialogg = new AlertDialogg(current.getContext(),"Question","Bạn có chắc chắn muốn xóa khoản chi Dự kiến này?", R.drawable.ic_launcher_foreground)
                                .setDialogListener(new DialogListener() {
                                    @Override
                                    public void dialogPositive() {
                                        int position = viewHolder.getLayoutPosition();
                                        ChiDuKien kt = adapterchidk.getItem(position);
                                        Toast.makeText(getActivity(),"Khoản chi đã được xóa",Toast.LENGTH_SHORT).show();
                                        mViewModel.deletechidk(kt);
                                    }
                                });
                        dialogg.show();
                        adapterchidk.notifyDataSetChanged();
                    }
                }
        );
        helper.attachToRecyclerView((rv_chidukien));
    }

    public void Loadprogressbar()
    {
        int tonngansach = DataLocalManager.get_mucchitieu();
        progressBar.setMax(tonngansach);
        tvtongngansach.setText("Ngân sách: " + CustomNumber.formatNumber(tonngansach) +" Đồng");
        progressBar.setProgress(max(0,tongchi-tongthu));
        tv_ngansachdu.setText("Ngân sách còn dư: " + CustomNumber.formatNumber(tonngansach-tongchi+tongthu)+ " Đồng");
        tv_dukien.setText(CustomNumber.formatNumber(tonngansach - tongchi - tongchidk + tongthu) + " Đồng");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}