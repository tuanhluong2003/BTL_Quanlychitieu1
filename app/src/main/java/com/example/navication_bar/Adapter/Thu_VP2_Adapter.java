package com.example.navication_bar.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.navication_bar.ui.Thu.Khoanthu.Khoanthu;
import com.example.navication_bar.ui.Thu.Loaikhoanthu.Loaikhoanthu;

public class Thu_VP2_Adapter extends FragmentStateAdapter {
    public Thu_VP2_Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        if(position ==0){
            fragment = Khoanthu.newInstance();
        }
        else {
            fragment = Loaikhoanthu.newInstance();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
