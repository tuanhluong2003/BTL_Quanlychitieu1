package com.example.testdatabase.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tableloaichi")
public class Loaichi {
    @PrimaryKey(autoGenerate = true)
    public int idloaichi;
    public String Tenloaichi;
    public int isDelete;
    public Loaichi() {
        this.isDelete = 0;
    }
}
