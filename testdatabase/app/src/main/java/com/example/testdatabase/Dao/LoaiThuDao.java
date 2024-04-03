package com.example.testdatabase.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.testdatabase.Entity.Loaithu;

import java.util.List;
@Dao
public interface LoaiThuDao {
    @Query("Select * from tableloaithu a where a.isDelete = 0")
    LiveData<List<Loaithu>> findAll();

    @Insert
    void insert(Loaithu loaiThu);

    @Update
    void update (Loaithu loaiThu);

    @Delete
    void delete(Loaithu loaiThu);
}
