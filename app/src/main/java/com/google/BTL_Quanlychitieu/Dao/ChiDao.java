package com.google.BTL_Quanlychitieu.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.BTL_Quanlychitieu.Entity.Chi;
import com.google.BTL_Quanlychitieu.Entity.ThongKeLoaiChi;

import java.util.List;

@Dao
// phương thức để thực hiện các thao tác / bảng loại chi
public interface ChiDao {
    @Query("Select * from tablechi")
    LiveData<List<Chi>> findAll();

    @Query("Select sum(sotien) from tablechi")
    LiveData<Float> sumTongChi();

    @Query("Select b.idloaichi,b.Tenloaichi,sum(a.sotien) as tong from tablechi a INNER JOIN tableloaichi b on a.idloaichi = b.idloaichi"+
            " GROUP BY  b.Tenloaichi")
    LiveData<List<ThongKeLoaiChi>> sumByLoaiChi();

    @Insert
    void insert(Chi chi);

    @Update
    void update (Chi chi);

    @Delete
    void delete(Chi chi);
}