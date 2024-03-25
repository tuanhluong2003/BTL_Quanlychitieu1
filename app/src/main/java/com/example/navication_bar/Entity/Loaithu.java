package com.example.navication_bar.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tableloaithu")
public class Loaithu {
    @PrimaryKey(autoGenerate = true)
    public int idloaithu;
    public String Tenloaithu;

    public boolean isDelete;
    public Loaithu()
    {
        isDelete = false;
    }
}
