package com.google.BTL_Quanlychitieu.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.BTL_Quanlychitieu.Entity.ThongKeLoaiThu;
import com.google.BTL_Quanlychitieu.Entity.ThongKeTheoNgay;
import com.google.BTL_Quanlychitieu.Entity.Thu;

import java.util.List;

@Dao
// phương thức để thực hiện các thao tác / bảng loại thu
public interface ThuDao {
    @Query("Select * from tablethu")
    LiveData<List<Thu>> findAll();

    @Query("Select * from tablethu where tablethu.date LIKE :strlike")
    LiveData<List<Thu>> findAll(String strlike);

    @Query("Select sum(sotien) from tablethu")
    LiveData<Float> sumTongThu();

    @Query("Select sum(sotien) from tablethu where tablethu.date LIKE :strlike")
    LiveData<Float> sumTongThu(String strlike);

    @Query("Select b.idloaithu,b.Tenloaithu,sum(a.sotien) as tong from tablethu a INNER JOIN tableloaithu b on a.idloaithu = b.idloaithu"+
            " where date like :strlike GROUP BY  b.idloaithu")
    LiveData<List<ThongKeLoaiThu>> sumByLoaiThu(String strlike);

    @Query("Select a.date,sum(a.sotien) as tong from tablethu a Where a.date like :strlike GROUP BY  a.date")
    LiveData<List<ThongKeTheoNgay>> sumByNgay(String strlike);

    @Insert
    void insert(Thu thu);

    @Update
    void update (Thu thu);

    @Delete
    void delete(Thu thu);

    @Query("Delete from tablethu")
    static void xoadulieu() {
    }

}