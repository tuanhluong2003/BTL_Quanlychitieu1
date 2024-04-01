package com.google.BTL_Quanlychitieu.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.google.BTL_Quanlychitieu.Dao.ThuDao;
import com.google.BTL_Quanlychitieu.Entity.ThongKeLoaiThu;
import com.google.BTL_Quanlychitieu.Entity.Thu;
import com.google.BTL_Quanlychitieu.RoomDTB.App_DTB_Thu;

import java.util.Calendar;
import java.util.List;

public class RepositoryThu {
    private ThuDao mThuDao;
    private LiveData<List<Thu>> mAllThu;

    public RepositoryThu(Application application) {
        this.mThuDao = App_DTB_Thu.getDatabase(application).thuDao();
        int thang = Calendar.getInstance().get(Calendar.MONTH)+1;
        int nam = Calendar.getInstance().get(Calendar.YEAR);
        mAllThu= mThuDao.findAll((thang < 10 ? nam+ "-0"+thang+"-%" : nam+"-"+thang+"-%"));
    }
    public void Loaddata(int thang, int nam)
    {
        mAllThu= mThuDao.findAll((thang < 10 ? nam+ "-0"+thang+"-%" : nam+"-"+thang+"-%"));
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
        new InsertAsyncTask(mThuDao, thu).start();

    }
    public void delete(Thu thu){
        new DeleteAsyncTask(mThuDao, thu).start();
    }
    public void update(Thu thu){
        new UpdateAsyncTask(mThuDao, thu).start();
    }
    class UpdateAsyncTask extends Thread{
        private ThuDao mThuDao;
        private Thu thu;
        public UpdateAsyncTask(ThuDao thuDao, Thu... thus){
            this.mThuDao=thuDao;
            this.thu = thus[0];

        }
        @Override
        public void run() {
            mThuDao.update(thu);
        }
    }
     class InsertAsyncTask extends Thread{
        private ThuDao mThuDao;
        private Thu thu;
        public InsertAsyncTask(ThuDao thuDao, Thu ...thus){
            this.mThuDao=thuDao;
            this.thu = thus[0];
        }
        @Override
        public void run() {
            mThuDao.insert(thu);
        }
    }
    class DeleteAsyncTask extends Thread{
        private ThuDao mThuDao;
        private Thu thu;
        public DeleteAsyncTask(ThuDao thuDao, Thu ...thus){
            this.mThuDao=thuDao;
            this.thu = thus[0];
        }
        @Override
        public void run() {
            mThuDao.delete(thu);
        }
    }

}
