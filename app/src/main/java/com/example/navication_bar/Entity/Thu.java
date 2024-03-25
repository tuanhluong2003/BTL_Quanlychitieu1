package com.example.navication_bar.Entity;

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