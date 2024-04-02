package com.google.BTL_Quanlychitieu.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.google.BTL_Quanlychitieu.Entity.Chi;
import com.google.BTL_Quanlychitieu.Entity.Loaichi;
import com.google.BTL_Quanlychitieu.Entity.Loaithu;
import com.google.BTL_Quanlychitieu.Entity.Thu;
import com.google.BTL_Quanlychitieu.Repository.RepositoryChi;
import com.google.BTL_Quanlychitieu.Repository.RepositoryHome;
import com.google.BTL_Quanlychitieu.Repository.RepositoryLoaiChi;
import com.google.BTL_Quanlychitieu.Repository.RepositoryLoaiThu;
import com.google.BTL_Quanlychitieu.Repository.RepositoryThu;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private LiveData<Float> tongchi;

    private LiveData<Float> tongthu;

    private LiveData<List<Chi>> allchi;

    private LiveData<List<Thu>> allthu;

    private RepositoryHome mrepositoryHome;


    public HomeViewModel(@NonNull Application application) {
        super(application);
        mrepositoryHome= new RepositoryHome(application);
        tongchi = mrepositoryHome.getTongchi();
        tongthu = mrepositoryHome.getTongthu();
        allchi = mrepositoryHome.getAllchi();
        allthu = mrepositoryHome.getAllthu();
    }

    public LiveData<List<Chi>> getAllchi()
    {
        return allchi;
    }

    public LiveData<List<Thu>> getAllthu()
    {
        return allthu;
    }

    public LiveData<Float> getTongchi()
    {
        return this.tongchi;
    }

    public LiveData<Float> getTongthu()
    {
        return this.tongthu;
    }

}