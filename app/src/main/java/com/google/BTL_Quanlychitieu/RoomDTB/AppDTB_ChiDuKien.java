package com.google.BTL_Quanlychitieu.RoomDTB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.google.BTL_Quanlychitieu.Dao.ChiDuKienDao;
import com.google.BTL_Quanlychitieu.Dao.LoaiChiDao;
import com.google.BTL_Quanlychitieu.Entity.Chi;
import com.google.BTL_Quanlychitieu.Entity.ChiDuKien;
import com.google.BTL_Quanlychitieu.Entity.Loaichi;

@Database(entities = {ChiDuKien.class}, version = 1)
public abstract class AppDTB_ChiDuKien extends RoomDatabase {
    public  abstract ChiDuKienDao chiDuKienDao();

    public static AppDTB_ChiDuKien INSTANCE;


    public  static AppDTB_ChiDuKien getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (AppDTB_ChiDuKien.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                AppDTB_ChiDuKien.class, "dlchidukienn_db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return  INSTANCE;
    }
}
