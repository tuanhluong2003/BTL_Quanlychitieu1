package com.example.navication_bar.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tableloaichi")
public class Loaichi {
    @PrimaryKey(autoGenerate = true)
    public int idloaichi;
    public String Tenloaichi;
    public boolean isDelete;
    public Loaichi() {
        this.isDelete = false;
    }
}