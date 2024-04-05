package com.google.BTL_Quanlychitieu.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "tabledukienchi")
public class ChiDuKien {
    @PrimaryKey(autoGenerate = true)
    public int iddukien;

    public int idloaichi;

    public String ten;

    public float sotien;
    public String ghichu;

    public String date;

    public String time;
    public String user;
}
