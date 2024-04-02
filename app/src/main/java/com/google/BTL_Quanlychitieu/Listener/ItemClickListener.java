package com.google.BTL_Quanlychitieu.Listener;

import com.google.BTL_Quanlychitieu.Entity.Chi;
import com.google.BTL_Quanlychitieu.Entity.ChiDuKien;
import com.google.BTL_Quanlychitieu.Entity.Loaichi;
import com.google.BTL_Quanlychitieu.Entity.Loaithu;
import com.google.BTL_Quanlychitieu.Entity.Thu;

public interface ItemClickListener{
    void onItemClick(Thu position);

    void onItemClick(Chi position);

    void onItemClick(Loaichi position);

    void onItemClick(Loaithu position);
    void onItemClick(ChiDuKien position);
}
