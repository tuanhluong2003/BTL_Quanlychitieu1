package com.example.navication_bar.ui.Chi.Khoanchi;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.navication_bar.Adapter.Chi_R_Adapter;
import com.example.navication_bar.Dialog.khoanchiDialog;
import com.example.navication_bar.Entity.Chi;
import com.example.navication_bar.Entity.Loaichi;
import com.example.navication_bar.Entity.Loaithu;
import com.example.navication_bar.Entity.Thu;
import com.example.navication_bar.Listener.ItemClickListener;
import com.example.navication_bar.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Khoanchi extends Fragment {

    private KhoanchiViewModel mViewModel;

    Khoanchi current;

    public KhoanchiViewModel getViewmodel() {
        return mViewModel;
    }

    public static Khoanchi newInstance() {
        return new Khoanchi();
    }

    RecyclerView rv_khoanchi;
    Chi_R_Adapter mAdapter;
    FloatingActionButton fab;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_khoanchi = view.findViewById(R.id.rv_khoanchi);
        fab = view.findViewById(R.id.fabkhoanchi);
        current = this;
        mAdapter = new Chi_R_Adapter(getActivity());
        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(Thu position) {

            }

            @Override
            public void onItemClick(Chi position) {
                new khoanchiDialog(current, "seen", position).show();
            }

            @Override
            public void onItemClick(Loaichi position) {

            }

            @Override
            public void onItemClick(Loaithu position) {

            }
        });

        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(Thu position) {

            }

            @Override
            public void onItemClick(Chi position) {
                new khoanchiDialog(current, "edit", position).show();
            }

            @Override
            public void onItemClick(Loaichi position) {

            }

            @Override
            public void onItemClick(Loaithu position) {

            }
        });


        rv_khoanchi.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_khoanchi.setAdapter(mAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new khoanchiDialog(current, "insert", null).show();
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
                        int position = viewHolder.getLayoutPosition();
                        Chi kt = mAdapter.getItem(position);
                        Toast.makeText(getActivity(),"Khoản chi đã được xóa",Toast.LENGTH_SHORT).show();
                        mViewModel.delete(kt);
                    }
                }
        );
        helper.attachToRecyclerView((rv_khoanchi));

    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_khoanchi, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(KhoanchiViewModel.class);
        mViewModel.getAllChi().observe(getActivity(), new Observer<List<Chi>>() {
            @Override
            public void onChanged(List<Chi> chis) {
                mAdapter.setList(chis);
            }
        });
    }

}