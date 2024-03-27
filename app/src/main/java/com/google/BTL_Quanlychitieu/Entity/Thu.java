package com.google.BTL_Quanlychitieu.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tablethu")
public class Thu {
    @PrimaryKey(autoGenerate = true)
    public int idthu;
    public int idloaithu;
    public String ten;
    public float sotien;
    public String ghichu;
    public long Time;
}