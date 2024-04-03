package com.example.testdatabase.RoomDTB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.testdatabase.Dao.ChiDuKienDao;
import com.example.testdatabase.Entity.ChiDuKien;

@Database(entities = {ChiDuKien.class}, version = 1)
public abstract class AppDTB_ChiDuKien extends RoomDatabase {
    public  abstract ChiDuKienDao chiDuKienDao();

    public static AppDTB_ChiDuKien INSTANCE;


    public  static AppDTB_ChiDuKien getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (AppDTB_ChiDuKien.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                AppDTB_ChiDuKien.class, "dlchidukien_db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return  INSTANCE;
    }
}
