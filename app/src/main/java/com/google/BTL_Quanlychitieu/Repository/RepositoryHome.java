package com.google.BTL_Quanlychitieu.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.google.BTL_Quanlychitieu.Dao.ChiDao;
import com.google.BTL_Quanlychitieu.Dao.LoaiChiDao;
import com.google.BTL_Quanlychitieu.Dao.ThuDao;
import com.google.BTL_Quanlychitieu.Entity.Chi;
import com.google.BTL_Quanlychitieu.Entity.Loaichi;
import com.google.BTL_Quanlychitieu.Entity.ThongKeLoaiChi;
import com.google.BTL_Quanlychitieu.Entity.Thu;
import com.google.BTL_Quanlychitieu.RoomDTB.AppDTB_Chi;
import com.google.BTL_Quanlychitieu.RoomDTB.App_DTB_Thu;

import java.util.Calendar;
import java.util.List;

public class RepositoryHome {

    private ChiDao mChiDao;
    private ThuDao mThuDao;

    private LoaiChiDao mLoaiChiDao;
    private LiveData<List<Chi>> mAllChi;
    private LiveData<List<Thu>> mAllThu;
    private LiveData<Float> tongchi;

    private LiveData<Float> tongthu;

    private LiveData<List<Loaichi>> allLoaichi;

    public RepositoryHome(Application application) {
        this.mChiDao = AppDTB_Chi.getDatabase(application).chiDao();
        this.mThuDao = App_DTB_Thu.getDatabase(application).thuDao();
        this.mLoaiChiDao = AppDTB_Chi.getDatabase(application).loaiChiDao();
        int thang = Calendar.getInstance().get(Calendar.MONTH)+1;
        int nam = Calendar.getInstance().get(Calendar.YEAR);
        mAllChi= mChiDao.findAll((thang < 10 ? nam+ "-0"+thang+"-%" : nam+"-"+thang+"-%")); //lấy về ds loại chi theo thanh hien tai
        mAllThu= mThuDao.findAll((thang < 10 ? nam+ "-0"+thang+"-%" : nam+"-"+thang+"-%"));
        tongchi = mChiDao.sumTongChi((thang < 10 ? nam+ "-0"+thang+"-%" : nam+"-"+thang+"-%"));
        tongthu = mThuDao.sumTongThu((thang < 10 ? nam+ "-0"+thang+"-%" : nam+"-"+thang+"-%"));
        allLoaichi = mLoaiChiDao.findAll();
    }


    public void insert(Chi chi){
        new InsertThread(mChiDao, chi).start();
    }
    public LiveData<List<Loaichi>> getAllLoaichi()
    {
        return allLoaichi;
    }
    public LiveData<List<Chi>> getAllchi(){
        return mAllChi;
    }

    public LiveData<List<Thu>> getAllthu(){
        return mAllThu;
    }

    public LiveData<Float> getTongchi(){
        return tongchi;
    }

    public LiveData<Float> getTongthu(){
        return tongthu;
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

}
