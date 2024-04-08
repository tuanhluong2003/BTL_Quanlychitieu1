package com.google.BTL_Quanlychitieu.ui.Diendan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.BTL_Quanlychitieu.Adapter.Message_R_Adapter;
import com.google.BTL_Quanlychitieu.Entity.Message;
import com.google.BTL_Quanlychitieu.Other.Customdate;
import com.google.BTL_Quanlychitieu.Other.MyApplication;
import com.google.BTL_Quanlychitieu.R;
import com.google.BTL_Quanlychitieu.ui.home.HomeViewModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DiendanFragment extends Fragment {


    DiendanViewmodel mViewModel;
    RecyclerView rv_message;

    ImageButton btn_send;

    EditText edit_content;

    public static DiendanFragment newInstance(String param1, String param2) {
        DiendanFragment fragment = new DiendanFragment();
        return fragment;
    }

    DatabaseReference mdata;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_diendan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel =  new ViewModelProvider(this).get(DiendanViewmodel.class);
        rv_message = view.findViewById(R.id.rv_chat);
        btn_send = view.findViewById(R.id.btn_send);
        edit_content = view.findViewById(R.id.ed_content);
        Message_R_Adapter adapter = new Message_R_Adapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_message.setLayoutManager(linearLayoutManager);
        mdata = FirebaseDatabase.getInstance().getReference();
        rv_message.setAdapter(adapter);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_content.getText() != null || edit_content.getText().toString()!="")
                {
                    sendMessage(edit_content.getText().toString());
                    edit_content.setText("");
                }
            }
        });

        mViewModel.getMyListLiveData().observe(getActivity(), new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                adapter.setdata(messages);

                view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect r = new Rect();
                        view.getWindowVisibleDisplayFrame(r);
                        int screenHeight =view.getRootView().getHeight();

                        // Tính toán chiều cao của bàn phím từ phía dưới của màn hình
                        int keypadHeight = screenHeight - r.bottom;

                        if (keypadHeight > screenHeight * 0.15) {
                            rv_message.scrollToPosition(mViewModel.getsize());
                        }
                    }
                });
            }
        });

        edit_content.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                rv_message.scrollToPosition(mViewModel.getsize());
                return false;
            }
        });

        mdata.child("Messages").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                 mViewModel.updateList(snapshot.getValue(Message.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rv_message.scrollToPosition(mViewModel.getsize());
            }
        }, 1500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mViewModel.getsize() <= 0)
                {
                    Intent it = new Intent("anhtu.action_internet");
                    getContext().sendBroadcast(it);
                }
            }
        }, 3000);
    }


    public void sendMessage(String tmp)
    {
        Message ms = new Message(MyApplication.User.username
                , MyApplication.User.avatar
                , Customdate.getLocaldatetimenow()
                , tmp);
        mdata.child("Messages").push().setValue(ms, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error != null)
                {
                    Toast.makeText(getContext(), "Gửi thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}