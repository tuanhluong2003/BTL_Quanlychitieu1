package com.example.navication_bar.Entity;

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
    public long Time;
}
