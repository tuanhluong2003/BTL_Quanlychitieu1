package com.google.BTL_Quanlychitieu.RoomDTB;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.google.BTL_Quanlychitieu.Dao.ThuDao;
import com.google.BTL_Quanlychitieu.Dao.LoaiThuDao;
import com.google.BTL_Quanlychitieu.Entity.Loaithu;
import com.google.BTL_Quanlychitieu.Entity.Thu;

@Database(entities = {Loaithu.class, Thu.class}, version = 1)
public abstract class App_DTB_Thu extends RoomDatabase {
    public abstract LoaiThuDao loaiThuDao();
    public abstract ThuDao thuDao();

    public static App_DTB_Thu INSTANCE;

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateData(INSTANCE).execute();
        }
    };

    public  static App_DTB_Thu getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (App_DTB_Thu.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                App_DTB_Thu.class, "thu_db")
                        .fallbackToDestructiveMigration()
                        .addCallback(callback)  //đổ dl khi bđ chạy
                        .build();
            }
        }
        return  INSTANCE;
    }
    public static class  PopulateData extends AsyncTask<Void, Void,Void> {
        private LoaiThuDao loaiThuDao;

        public PopulateData(App_DTB_Thu db) {
            loaiThuDao = db.loaiThuDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String[] cacloaithu = new String[]{"Luong", "Thuong", "Co phieu"};
            for (String it : cacloaithu){
                Loaithu lt = new Loaithu();
                lt.Tenloaithu = it;
                loaiThuDao.insert(lt);
            }
            return null;
        }
    }
}
