package com.example.navication_bar.ui.Thu.Loaikhoanthu;

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
import android.widget.Toast;

import com.example.navication_bar.Adapter.Loaithu_R_Adapter;
import com.example.navication_bar.Dialog.AlertDialogg;
import com.example.navication_bar.Dialog.loaikhoanthuDialog;
import com.example.navication_bar.Entity.Chi;
import com.example.navication_bar.Entity.Loaichi;
import com.example.navication_bar.Entity.Thu;
import com.example.navication_bar.Entity.Loaithu;
import com.example.navication_bar.Entity.Loaithu;
import com.example.navication_bar.Entity.Thu;
import com.example.navication_bar.Listener.DialogListener;
import com.example.navication_bar.Listener.ItemClickListener;
import com.example.navication_bar.R;
import com.example.navication_bar.ui.Thu.Loaikhoanthu.LoaikhoanthuViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Loaikhoanthu extends Fragment {

    private RecyclerView mRv;
    private Loaithu_R_Adapter mAdapter;


    FloatingActionButton fab;

    private LoaikhoanthuViewModel mViewModel;

    public static com.example.navication_bar.ui.Thu.Loaikhoanthu.Loaikhoanthu newInstance() {
        return new com.example.navication_bar.ui.Thu.Loaikhoanthu.Loaikhoanthu();
    }

    public LoaikhoanthuViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loaikhoanthu, container, false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.rv_Loaikhoanthu);
        fab = view.findViewById(R.id.fabloaikhoanthu);
        mAdapter = new Loaithu_R_Adapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
        final com.example.navication_bar.ui.Thu.Loaikhoanthu.Loaikhoanthu currentFragment = this;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new loaikhoanthuDialog(currentFragment, "insert", null).show();
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
            public void onItemClick(Loaithu position) {
                new loaikhoanthuDialog(currentFragment, "edit", position).show();
            }

            @Override
            public void onItemClick(Loaichi position) {

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
                        AlertDialogg dialogg = new AlertDialogg(currentFragment.getContext(),"Question","Bạn có chắc chắn muốn xóa loại thu này?", R.drawable.ic_launcher_foreground);
                        dialogg.setDialogListener(new DialogListener() {
                            @Override
                            public void dialogPositive() {
                                int position = viewHolder.getLayoutPosition();
                                Loaithu lt = mAdapter.getItem(position);
                                lt.isDelete = true;
                                Toast.makeText(getActivity(),"Loại thu đã được xóa",Toast.LENGTH_SHORT).show();
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
        mViewModel = new ViewModelProvider(this).get(LoaikhoanthuViewModel.class);
        mViewModel.getAllLoaiThu().observe(getActivity(), new Observer<List<Loaithu>>() {
            @Override
            public void onChanged(List<Loaithu> loaiThus) {
                mAdapter.setList(loaiThus);
            }
        });
    }


}