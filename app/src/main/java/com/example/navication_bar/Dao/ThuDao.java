package com.example.navication_bar.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.navication_bar.Entity.ThongKeLoaiThu;
import com.example.navication_bar.Entity.Thu;

import java.util.List;

@Dao
// phương thức để thực hiện các thao tác / bảng loại thu
public interface ThuDao {
    @Query("Select * from tablethu")
    LiveData<List<Thu>> findAll();

    @Query("Select sum(sotien) from tablethu")
    LiveData<Float> sumTongThu();

    @Query("Select b.idloaithu,b.Tenloaithu,sum(a.sotien) as tong from tablethu a INNER JOIN tableloaithu b on a.idloaithu = b.idloaithu"+
            " GROUP BY  b.idloaithu")
    LiveData<List<ThongKeLoaiThu>> sumByLoaiThu();

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