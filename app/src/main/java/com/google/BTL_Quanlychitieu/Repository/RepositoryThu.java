package com.google.BTL_Quanlychitieu.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.google.BTL_Quanlychitieu.Dao.ThuDao;
import com.google.BTL_Quanlychitieu.Entity.ThongKeLoaiThu;
import com.google.BTL_Quanlychitieu.Entity.Thu;
import com.google.BTL_Quanlychitieu.RoomDTB.App_DTB_Thu;

import java.util.List;

public class RepositoryThu {
    private ThuDao mThuDao;
    private LiveData<List<Thu>> mAllThu;

    public RepositoryThu(Application application) {
        this.mThuDao = App_DTB_Thu.getDatabase(application).thuDao();
        mAllThu= mThuDao.findAll(); //lấy về ds loại thu
    }
    public LiveData<List<Thu>> getAllthu(){
        return mAllThu;
    }

    public LiveData<Float> sumTongThu(){
        return mThuDao.sumTongThu();
    }

    public LiveData<List<ThongKeLoaiThu>> sumByLoaiThu(){
        return mThuDao.sumByLoaiThu();
    }

    public void insert(Thu thu){
        new InsertAsyncTask(mThuDao).execute(thu);

    }
    public void delete(Thu thu){
        new DeleteAsyncTask(mThuDao).execute(thu);

    }
//    public void xoadulieu(Thu thu){
//        mThuDao.xoadulieu();
//    }
    public void update(Thu thu){
        new UpdateAsyncTask(mThuDao).execute(thu);
    }
    class UpdateAsyncTask extends AsyncTask<Thu, Void, Void>{
        private ThuDao mThuDao;
        public UpdateAsyncTask(ThuDao thuDao){
            this.mThuDao=thuDao;
        }
        @Override
        protected Void doInBackground(Thu... thus) {
            mThuDao.update(thus[0]);
            return null;
        }
    }
     class InsertAsyncTask extends AsyncTask<Thu, Void, Void>{
        private ThuDao mThuDao;
        public InsertAsyncTask(ThuDao thuDao){
            this.mThuDao=thuDao;
        }
        @Override
        protected Void doInBackground(Thu... thus) {
            mThuDao.insert(thus[0]);
            return null;
        }
    }
    class DeleteAsyncTask extends AsyncTask<Thu, Void, Void>{
        private ThuDao mThuDao;
        public DeleteAsyncTask(ThuDao thuDao){
            this.mThuDao=thuDao;
        }
        @Override
        protected Void doInBackground(Thu... thus) {
            mThuDao.delete(thus[0]);
            return null;
        }
    }

}
