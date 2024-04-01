package com.google.BTL_Quanlychitieu.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.google.BTL_Quanlychitieu.Dao.ChiDao;
import com.google.BTL_Quanlychitieu.Entity.Chi;
import com.google.BTL_Quanlychitieu.Entity.ThongKeLoaiChi;
import com.google.BTL_Quanlychitieu.RoomDTB.AppDTB_Chi;

import java.util.Calendar;
import java.util.List;

public class RepositoryChi {
    private ChiDao mChiDao;
    private LiveData<List<Chi>> mAllChi;

    private LiveData<Float> tongchi;

    public RepositoryChi(Application application) {
        this.mChiDao = AppDTB_Chi.getDatabase(application).chiDao();
        int thang = Calendar.getInstance().get(Calendar.MONTH)+1;
        int nam = Calendar.getInstance().get(Calendar.YEAR);
        mAllChi= mChiDao.findAll((thang < 10 ? nam+ "-0"+thang+"-%" : nam+"-"+thang+"-%")); //lấy về ds loại chi theo thanh hien tai
        tongchi = mChiDao.sumTongChi((thang < 10 ? nam+ "-0"+thang+"-%" : nam+"-"+thang+"-%"));
    }



    public void Loaddata(int thang, int nam)
    {
        mAllChi= mChiDao.findAll((thang < 10 ? nam+ "-0"+thang+"-%" : nam+"-"+thang+"-%"));
    }
    public LiveData<List<Chi>> getAllchi(){
        return mAllChi;
    }

    public LiveData<Float> getTongchi(){
        return tongchi;
    }

    public LiveData<Float> sumTongChi(){
        return mChiDao.sumTongChi();
    }

    public LiveData<List<ThongKeLoaiChi>> sumByLoaiChi(){
        return mChiDao.sumByLoaiChi();
    }

    public void insert(Chi chi){
        new InsertThread(mChiDao, chi).start();

    }
    public void delete(Chi chi){
        new DeleteThread(mChiDao,chi).start();

    }

    public void update(Chi chi){
        new UpdateThread(mChiDao, chi).start();
    }
    class UpdateThread extends Thread {
        private ChiDao mChiDao;
        private Chi chi;
        public UpdateThread(ChiDao chiDao, Chi ...chis){
            this.mChiDao=chiDao;
            this.chi = chis[0];
        }
        @Override
        public void run() {
            mChiDao.update(this.chi);
        }
    }
    class InsertThread extends Thread{
        private ChiDao mChiDao;
        private Chi chi;
        public InsertThread(ChiDao chiDao, Chi ...chis){
            this.mChiDao=chiDao;
            chi = chis[0];
        }
        @Override
        public void run() {
            android.util.Log.d("them","thanhcong");
            mChiDao.insert(chi);
        }
    }
    class DeleteThread extends Thread{
        private ChiDao mChiDao;
        private Chi chi;
        public DeleteThread(ChiDao chiDao, Chi ...chi){
            this.mChiDao=chiDao;
            this.chi = chi[0];
        }
        @Override
        public void run() {
            mChiDao.delete(chi);
        }
    }
}