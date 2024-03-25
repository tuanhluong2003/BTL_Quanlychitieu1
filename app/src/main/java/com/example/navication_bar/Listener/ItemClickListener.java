package com.example.navication_bar.Listener;

import com.example.navication_bar.Entity.Chi;
import com.example.navication_bar.Entity.Loaichi;
import com.example.navication_bar.Entity.Loaithu;
import com.example.navication_bar.Entity.Thu;

public interface ItemClickListener{
    void onItemClick(Thu position);

    void onItemClick(Chi position);

    void onItemClick(Loaichi position);

    void onItemClick(Loaithu position);
}
