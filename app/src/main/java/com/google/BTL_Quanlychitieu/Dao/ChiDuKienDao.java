package com.google.BTL_Quanlychitieu.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.google.BTL_Quanlychitieu.Entity.ChiDuKien;

import java.util.List;

@Dao
// phương thức để thực hiện các thao tác / bảng loại chi
public interface ChiDuKienDao {
    @Query("Select * from tabledukienchi")
    LiveData<List<ChiDuKien>> findAll();

    @Query("Select * from tabledukienchi where tabledukienchi.date like :str")
    LiveData<List<ChiDuKien>> findAll(String str);

    @Query("Select sum(sotien) from tabledukienchi")
    LiveData<Float> sumTongChiDuKien();

    @Query("Select sum(sotien) from tabledukienchi where tabledukienchi.date like :strlike")
    LiveData<Float> sumTongChiDuKien(String strlike);

    @Insert
    void insert(ChiDuKien chi);

    @Update
    void update (ChiDuKien chi);

    @Delete
    void delete(ChiDuKien chi);
}