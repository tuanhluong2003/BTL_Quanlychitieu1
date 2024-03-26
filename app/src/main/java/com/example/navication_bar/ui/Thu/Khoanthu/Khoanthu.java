package com.example.navication_bar.ui.Thu.Khoanthu;

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

import com.example.navication_bar.Adapter.Thu_R_Adapter;
import com.example.navication_bar.Adapter.Thu_R_Adapter;
import com.example.navication_bar.Dialog.AlertDialogg;
import com.example.navication_bar.Dialog.khoanthuDialog;
import com.example.navication_bar.Entity.Chi;
import com.example.navication_bar.Entity.Loaichi;
import com.example.navication_bar.Entity.Thu;
import com.example.navication_bar.Entity.Loaithu;
import com.example.navication_bar.Entity.Loaithu;
import com.example.navication_bar.Entity.Thu;
import com.example.navication_bar.Listener.DialogListener;
import com.example.navication_bar.Listener.ItemClickListener;
import com.example.navication_bar.R;
import com.example.navication_bar.ui.Thu.Khoanthu.KhoanthuViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Khoanthu extends Fragment {

    private KhoanthuViewModel mViewModel;

    com.example.navication_bar.ui.Thu.Khoanthu.Khoanthu current;

    public KhoanthuViewModel getViewmodel() {
        return mViewModel;
    }

    public static com.example.navication_bar.ui.Thu.Khoanthu.Khoanthu newInstance() {
        return new com.example.navication_bar.ui.Thu.Khoanthu.Khoanthu();
    }

    RecyclerView rv_khoanthu;
    Thu_R_Adapter mAdapter;
    FloatingActionButton fab;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_khoanthu = view.findViewById(R.id.rv_khoanthu);
        fab = view.findViewById(R.id.fabkhoanthu);
        current = this;
        mAdapter = new Thu_R_Adapter(getActivity());
        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(Chi position) {

            }

            @Override
            public void onItemClick(Thu position) {
                new khoanthuDialog(current, "seen", position).show();
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
                new khoanthuDialog(current, "edit", position).show();
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
        });


        rv_khoanthu.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_khoanthu.setAdapter(mAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new khoanthuDialog(current, "insert", null).show();
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
                        AlertDialogg dialogg = new AlertDialogg(current.getContext(),"Question","Bạn có chắc chắn muốn xóa khoản thu này?", R.drawable.ic_launcher_foreground);
                        dialogg.setDialogListener(new DialogListener() {
                            @Override
                            public void dialogPositive() {
                                int position = viewHolder.getLayoutPosition();
                                Thu kt = mAdapter.getItem(position);
                                Toast.makeText(getActivity(),"Khoản thu đã được xóa",Toast.LENGTH_SHORT).show();
                                mViewModel.delete(kt);
                            }
                        });
                        dialogg.show();
                        mAdapter.notifyDataSetChanged();
                    }
                }
        );
        helper.attachToRecyclerView((rv_khoanthu));

    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_khoanthu, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(KhoanthuViewModel.class);
        mViewModel.getAllThu().observe(getActivity(), new Observer<List<Thu>>() {
            @Override
            public void onChanged(List<Thu> thus) {
                mAdapter.setList(thus);
            }
        });
    }
}