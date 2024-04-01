package com.google.BTL_Quanlychitieu.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.google.BTL_Quanlychitieu.Dao.ChiDao;
import com.google.BTL_Quanlychitieu.Dao.ThuDao;
import com.google.BTL_Quanlychitieu.Entity.Chi;
import com.google.BTL_Quanlychitieu.Entity.ThongKeLoaiChi;
import com.google.BTL_Quanlychitieu.RoomDTB.AppDTB_Chi;
import com.google.BTL_Quanlychitieu.RoomDTB.App_DTB_Thu;

import java.util.Calendar;
import java.util.List;

public class RepositoryHome {

    private ChiDao mChiDao;

    private ThuDao mThuDao;
 //   private LiveData<List<Chi>> mAllChi;
    private LiveData<Float> tongchi;

    private LiveData<Float> tongthu;

    public RepositoryHome(Application application) {
        this.mChiDao = AppDTB_Chi.getDatabase(application).chiDao();
        this.mThuDao = App_DTB_Thu.getDatabase(application).thuDao();
        int thang = Calendar.getInstance().get(Calendar.MONTH)+1;
        int nam = Calendar.getInstance().get(Calendar.YEAR);
      //  mAllChi= mChiDao.findAll((thang < 10 ? nam+ "-0"+thang+"-%" : nam+"-"+thang+"-%")); //lấy về ds loại chi theo thanh hien tai
        tongchi = mChiDao.sumTongChi((thang < 10 ? nam+ "-0"+thang+"-%" : nam+"-"+thang+"-%"));
        tongthu = mThuDao.sumTongThu((thang < 10 ? nam+ "-0"+thang+"-%" : nam+"-"+thang+"-%"));
    }

//    public LiveData<List<Chi>> getAllchi(){
//        return mAllChi;
//    }

    public LiveData<Float> getTongchi(){
        return tongchi;
    }

    public LiveData<Float> getTongthu(){
        return tongthu;
    }

//    public LiveData<List<ThongKeLoaiChi>> sumByLoaiChi(){
//        return mChiDao.sumByLoaiChi();
//    }


}
