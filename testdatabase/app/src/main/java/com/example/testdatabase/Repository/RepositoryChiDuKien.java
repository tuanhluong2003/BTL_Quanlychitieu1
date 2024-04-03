package com.example.testdatabase.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.testdatabase.Dao.ChiDuKienDao;
import com.example.testdatabase.Entity.ChiDuKien;
import com.example.testdatabase.RoomDTB.AppDTB_ChiDuKien;

import java.util.Calendar;
import java.util.List;

public class RepositoryChiDuKien {
    private ChiDuKienDao mChiDuKienDao;
    private LiveData<List<ChiDuKien>> mAllChiDuKien;

    private LiveData<Float> tongchidukien;

    public RepositoryChiDuKien(Application application) {
        this.mChiDuKienDao = AppDTB_ChiDuKien.getDatabase(application).chiDuKienDao();
        int thang = Calendar.getInstance().get(Calendar.MONTH)+1;
        int nam = Calendar.getInstance().get(Calendar.YEAR);
        mAllChiDuKien= mChiDuKienDao.findAll((thang < 10 ? nam+ "-0"+thang+"-%" : nam+"-"+thang+"-%"));
        tongchidukien = mChiDuKienDao.sumTongChiDuKien((thang < 10 ? nam+ "-0"+thang+"-%" : nam+"-"+thang+"-%"));
    }
    
    public LiveData<List<ChiDuKien>> getmAllChiDuKien(){
        return mAllChiDuKien;
    }

    public LiveData<Float> getTongchidukien(){
        return tongchidukien;
    }
    
    public void insert(ChiDuKien chi){
        new InsertThread(mChiDuKienDao, chi).start();

    }
    public void delete(ChiDuKien chi){
        new DeleteThread(mChiDuKienDao,chi).start();

    }

    public void update(ChiDuKien chi){
        new UpdateThread(mChiDuKienDao, chi).start();
    }
    class UpdateThread extends Thread {
        private ChiDuKienDao mChiDuKienDao;
        private ChiDuKien chi;
        public UpdateThread(ChiDuKienDao chiDuKienDao, ChiDuKien ...chis){
            this.mChiDuKienDao = chiDuKienDao;
            this.chi = chis[0];
        }
        @Override
        public void run() {
            mChiDuKienDao.update(this.chi);
        }
    }
    class InsertThread extends Thread{
        private ChiDuKienDao mChiDuKienDao;
        private ChiDuKien chi;
        public InsertThread(ChiDuKienDao chiDuKienDao, ChiDuKien ...chis){
            this.mChiDuKienDao=chiDuKienDao;
            chi = chis[0];
        }
        @Override
        public void run() {
            mChiDuKienDao.insert(chi);
        }
    }
    class DeleteThread extends Thread{
        private ChiDuKienDao mChiDuKienDao;
        private ChiDuKien chi;
        public DeleteThread(ChiDuKienDao chiDuKienDao, ChiDuKien ...chi){
            this.mChiDuKienDao=chiDuKienDao;
            this.chi = chi[0];
        }
        @Override
        public void run() {
            mChiDuKienDao.delete(chi);
        }
    }
}