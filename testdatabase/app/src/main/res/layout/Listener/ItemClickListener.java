package com.example.testdatabase.Listener;

import com.example.testdatabase.Entity.Chi;
import com.example.testdatabase.Entity.ChiDuKien;
import com.example.testdatabase.Entity.Loaichi;
import com.example.testdatabase.Entity.Loaithu;
import com.example.testdatabase.Entity.Thu;

public interface ItemClickListener{
    void onItemClick(Thu position);

    void onItemClick(Chi position);

    void onItemClick(Loaichi position);

    void onItemClick(Loaithu position);
    void onItemClick(ChiDuKien position);
}
