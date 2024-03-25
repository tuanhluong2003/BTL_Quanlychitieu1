package com.example.navication_bar.ui.Thu.Loaikhoanthu;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.navication_bar.R;

public class Loaikhoanthu extends Fragment {

    private LoaikhoanthuViewModel mViewModel;

    public static Loaikhoanthu newInstance() {
        return new Loaikhoanthu();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loaikhoanthu, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoaikhoanthuViewModel.class);
        // TODO: Use the ViewModel
    }

}