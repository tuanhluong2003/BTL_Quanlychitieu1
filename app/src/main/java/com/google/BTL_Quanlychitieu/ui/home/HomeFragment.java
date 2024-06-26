package com.google.BTL_Quanlychitieu.ui.home;

import static java.lang.Math.max;
import static java.lang.Math.sqrt;

import android.os.Bundle;
import android.util.Log;
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

import com.google.BTL_Quanlychitieu.Adapter.ChiHome_R_Adapter;
import com.google.BTL_Quanlychitieu.Adapter.Chidukien_R_Adapter;
import com.google.BTL_Quanlychitieu.Adapter.ThuHome_R_Adapter;
import com.google.BTL_Quanlychitieu.Dialog.AlertDialogg;
import com.google.BTL_Quanlychitieu.Dialog.khoanchidukienDialog;
import com.google.BTL_Quanlychitieu.Dialog.ngansachDialog;
import com.google.BTL_Quanlychitieu.Entity.Chi;
import com.google.BTL_Quanlychitieu.Entity.ChiDuKien;
import com.google.BTL_Quanlychitieu.Entity.Loaichi;
import com.google.BTL_Quanlychitieu.Entity.Loaithu;
import com.google.BTL_Quanlychitieu.Entity.Thu;
import com.google.BTL_Quanlychitieu.Listener.DialogListener;
import com.google.BTL_Quanlychitieu.Listener.ItemClickListener;
import com.google.BTL_Quanlychitieu.Other.CustomNumber;
import com.google.BTL_Quanlychitieu.Other.CustomPendingIntent;
import com.google.BTL_Quanlychitieu.Other.DataLocalManager;
import com.google.BTL_Quanlychitieu.R;
import com.google.BTL_Quanlychitieu.databinding.FragmentHomeBinding;
import com.google.BTL_Quanlychitieu.ui.Chi.Khoanchi.Khoanchi;
import com.google.BTL_Quanlychitieu.ui.Chi.Khoanchi.KhoanchiViewModel;

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
                tvtongchi.setText( "- " + CustomNumber.formatNumber(tongchi) + " VND");
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
                tvtongthu.setText( "+ " + CustomNumber.formatNumber(tongthu) + " VND");
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





        //để nhận sự kiện vuốt sang trái hoặc vuốt sang phải để xóa 1 item trong recyclerview
        ItemTouchHelper helper=new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getLayoutPosition();
                        ChiDuKien kt = adapterchidk.getItem(position);
                        AlertDialogg dialogg = new AlertDialogg(current.getContext(),"Question","Bạn có chắc chắn muốn xóa khoản chi Dự kiến "+kt.iddukien+"?", R.drawable.ic_launcher_foreground)
                                .setDialogListener(new DialogListener() {
                                    @Override
                                    public void dialogPositive() { // chỉ ra công việc thực hiện khi người dùng nhấn Yes
                                        CustomPendingIntent.removePendingIntent(getContext(),kt); // gọi đến cái AlarmService
                                        Toast.makeText(getActivity(),"Khoản chi DK "+kt.iddukien+" đã được xóa",Toast.LENGTH_SHORT).show();
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
        tvtongngansach.setText("Ngân sách: " + CustomNumber.formatNumber(tonngansach) +" VND");
        progressBar.setProgress(max(0,tongchi-tongthu));
        tv_ngansachdu.setText("Ngân sách còn dư: " + CustomNumber.formatNumber(tonngansach-tongchi+tongthu)+ " VND");
        tv_dukien.setText(CustomNumber.formatNumber(tonngansach - tongchi - tongchidk + tongthu) + " VND");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}