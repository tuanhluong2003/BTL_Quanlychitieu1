package com.google.BTL_Quanlychitieu.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.google.BTL_Quanlychitieu.Dao.LoaiThuDao;
import com.google.BTL_Quanlychitieu.Entity.Loaithu;
import com.google.BTL_Quanlychitieu.RoomDTB.App_DTB_Thu;

import java.util.List;

public class RepositoryLoaiThu {
    private LoaiThuDao mloaiThuDao;
    private LiveData<List<Loaithu>> mAllLoaiThu;

    public RepositoryLoaiThu(Application application) {
        this.mloaiThuDao = App_DTB_Thu.getDatabase(application).loaiThuDao();
        mAllLoaiThu= mloaiThuDao.findAll(); //lấy về ds loại thu
    }
    public LiveData<List<Loaithu>> getAllLoaiThu(){
        return mAllLoaiThu;
    }


    public void insert(Loaithu loaiThu){
        new InsertAsyncTask(mloaiThuDao).execute(loaiThu);
    }
    public void delete(Loaithu loaiThu){
        new DeleteAsyncTask(mloaiThuDao).execute(loaiThu);

    }
    public void update(Loaithu loaiThu){
        new UpdateAsyncTask(mloaiThuDao).execute(loaiThu);
    }
    class UpdateAsyncTask extends AsyncTask<Loaithu, Void, Void>{
        private LoaiThuDao mloaiThuDao;
        public UpdateAsyncTask(LoaiThuDao loaiThuDao){
            this.mloaiThuDao=loaiThuDao;
        }
        @Override
        protected Void doInBackground(Loaithu... loaiThus) {
            mloaiThuDao.update(loaiThus[0]);
            return null;
        }
    }
    class InsertAsyncTask extends AsyncTask<Loaithu, Void, Void>{
        private LoaiThuDao mloaiThuDao;
        public InsertAsyncTask(LoaiThuDao loaiThuDao){
            this.mloaiThuDao=loaiThuDao;
        }
        @Override
        protected Void doInBackground(Loaithu... loaiThus) {
            mloaiThuDao.insert(loaiThus[0]);
            return null;
        }
    }
    class DeleteAsyncTask extends AsyncTask<Loaithu, Void, Void>{
        private LoaiThuDao mloaiThuDao;
        public DeleteAsyncTask(LoaiThuDao loaiThuDao){
            this.mloaiThuDao=loaiThuDao;
        }
        @Override
        protected Void doInBackground(Loaithu... loaiThus) {
            mloaiThuDao.delete(loaiThus[0]);
            return null;
        }
    }
}
