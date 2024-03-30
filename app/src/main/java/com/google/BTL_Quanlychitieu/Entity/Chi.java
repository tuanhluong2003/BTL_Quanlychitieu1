package com.google.BTL_Quanlychitieu.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tablechi")
public class Chi {
    @PrimaryKey(autoGenerate = true)
    public int idchi;
    public int idloaichi;
    public String ten;
    public float sotien;
    public String ghichu;
    public String date;

    public String time;
}
