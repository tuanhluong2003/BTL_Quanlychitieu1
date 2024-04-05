package com.google.BTL_Quanlychitieu.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tableloaithu")
public class Loaithu {
    @PrimaryKey(autoGenerate = true)
    public int idloaithu;
    public String Tenloaithu;

    public int isDelete;

    public String user;
    public Loaithu()
    {
        isDelete = 0;
    }
}
