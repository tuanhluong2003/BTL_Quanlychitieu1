package com.google.BTL_Quanlychitieu.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.google.BTL_Quanlychitieu.Dao.LoaiChiDao;
import com.google.BTL_Quanlychitieu.Entity.Loaichi;
import com.google.BTL_Quanlychitieu.RoomDTB.AppDTB_Chi;

import java.util.List;

public class RepositoryLoaiChi {
    private LoaiChiDao mLoaiChiDao;
    private LiveData<List<Loaichi>> mAllLoaiChi;

    public RepositoryLoaiChi(Application application) {
        this.mLoaiChiDao = AppDTB_Chi.getDatabase(application).loaiChiDao();
        mAllLoaiChi= mLoaiChiDao.findAll();
    }
    public LiveData<List<Loaichi>> getAllLoaiChi(){
        return mAllLoaiChi;
    }


    public void insert(Loaichi loaiChi){
        new InsertAsyncTask(mLoaiChiDao).execute(loaiChi);

    }
    public void delete(Loaichi loaiChi){
        new DeleteAsyncTask(mLoaiChiDao).execute(loaiChi);
    }
    public void update(Loaichi loaiChi){
        new UpdateAsyncTask(mLoaiChiDao).execute(loaiChi);
    }
    class UpdateAsyncTask extends AsyncTask<Loaichi, Void, Void> {
        private LoaiChiDao mLoaiChiDao;
        public UpdateAsyncTask(LoaiChiDao loaiChiDao){
            this.mLoaiChiDao=loaiChiDao;
        }
        @Override
        protected Void doInBackground(Loaichi ... loaiChis) {
            mLoaiChiDao.update(loaiChis[0]);
            return null;
        }
    }
    class InsertAsyncTask extends AsyncTask<Loaichi, Void, Void>{
        private LoaiChiDao mLoaiChiDao;
        public InsertAsyncTask(LoaiChiDao loaiChiDao){
            this.mLoaiChiDao=loaiChiDao;
        }
        @Override
        protected Void doInBackground(Loaichi... loaiChis) {
            mLoaiChiDao.insert(loaiChis[0]);
            return null;
        }
    }
    class DeleteAsyncTask extends AsyncTask<Loaichi, Void, Void>{
        private LoaiChiDao mLoaiChiDao;
        public DeleteAsyncTask(LoaiChiDao loaiChiDao){
            this.mLoaiChiDao=loaiChiDao;
        }
        @Override
        protected Void doInBackground(Loaichi... loaiChis) {
            mLoaiChiDao.delete(loaiChis[0]);
            return null;
        }
    }
}
