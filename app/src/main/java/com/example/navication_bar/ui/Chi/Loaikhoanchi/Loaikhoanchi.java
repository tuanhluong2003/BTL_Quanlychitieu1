package com.example.navication_bar.ui.Chi.Loaikhoanchi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navication_bar.Adapter.Loaichi_R_Adapter;
import com.example.navication_bar.Dialog.AlertDialogg;
import com.example.navication_bar.Dialog.loaikhoanchiDialog;
import com.example.navication_bar.Entity.Chi;
import com.example.navication_bar.Entity.Loaichi;
import com.example.navication_bar.Entity.Loaithu;
import com.example.navication_bar.Entity.Thu;
import com.example.navication_bar.Listener.DialogListener;
import com.example.navication_bar.Listener.ItemClickListener;
import com.example.navication_bar.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Loaikhoanchi extends Fragment {

    private RecyclerView mRv;
    private Loaichi_R_Adapter mAdapter;


    FloatingActionButton fab;

    private LoaikhoanchiViewModel mViewModel;

    public static Loaikhoanchi newInstance() {
        return new Loaikhoanchi();
    }

    public LoaikhoanchiViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loaikhoanchi, container, false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.rv_Loaikhoanchi);
        fab = view.findViewById(R.id.fabloaikhoanchi);
        mAdapter = new Loaichi_R_Adapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
        final Loaikhoanchi currentFragment = this;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new loaikhoanchiDialog(currentFragment, "insert", null).show();
            }
        });

        mAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(Thu position) {
            }

            @Override
            public void onItemClick(Chi position) {

            }

            @Override
            public void onItemClick(Loaichi position) {
                new loaikhoanchiDialog(currentFragment, "edit", position).show();
            }

            @Override
            public void onItemClick(Loaithu position) {

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
                        AlertDialogg dialogg = new AlertDialogg(currentFragment.getContext(),"Question","Bạn có chắc chắn muốn xóa loại khoản chi này?", R.drawable.ic_launcher_foreground);
                        dialogg.setDialogListener(new DialogListener() {
                            @Override
                            public void dialogPositive() {
                                int position = viewHolder.getLayoutPosition();
                                Loaichi lt = mAdapter.getItem(position);
                                lt.isDelete = true;
                                Toast.makeText(getActivity(),"Loại chi đã được xóa",Toast.LENGTH_SHORT).show();
                                mViewModel.update(lt);
                            }
                        });
                        dialogg.show();
                        mAdapter.notifyDataSetChanged();
                    }
                }
        );
        helper.attachToRecyclerView((mRv));
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoaikhoanchiViewModel.class);
        mViewModel.getAllLoaiChi().observe(getActivity(), new Observer<List<Loaichi>>() {
            @Override
            public void onChanged(List<Loaichi> loaiChis) {
                mAdapter.setList(loaiChis);
            }
        });
    }


}