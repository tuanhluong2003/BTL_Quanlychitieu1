package com.example.testdatabase.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.testdatabase.Entity.Chi;
import com.example.testdatabase.Entity.ChiDuKien;
import com.example.testdatabase.Entity.Loaichi;
import com.example.testdatabase.Entity.Thu;
import com.example.testdatabase.Repository.RepositoryChiDuKien;
import com.example.testdatabase.Repository.RepositoryHome;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private LiveData<Float> tongchi;

    private LiveData<Float> tongthu;

    private LiveData<Float> tongchidukien;

    private LiveData<List<Chi>> allchi;

    private LiveData<List<Thu>> allthu;

    private LiveData<List<Loaichi>> allLoaichi;

    private LiveData<List<ChiDuKien>> allchidukien;

    private RepositoryHome mrepositoryHome;

    private RepositoryChiDuKien mrepositoryChiDuKien;


    public HomeViewModel(@NonNull Application application) {
        super(application);
        mrepositoryHome= new RepositoryHome(application);
        mrepositoryChiDuKien = new RepositoryChiDuKien(application);
        tongchi = mrepositoryHome.getTongchi();
        tongthu = mrepositoryHome.getTongthu();
        allchi = mrepositoryHome.getAllchi();
        allthu = mrepositoryHome.getAllthu();
        allchidukien = mrepositoryChiDuKien.getmAllChiDuKien();
        tongchidukien = mrepositoryChiDuKien.getTongchidukien();
        allLoaichi = mrepositoryHome.getAllLoaichi();
    }

    public LiveData<List<Loaichi>> getAllLoaichi()
    {
        return allLoaichi;
    }

    public LiveData<List<ChiDuKien>> getAllchidukien() {
        return allchidukien;
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

    public LiveData<Float> getTongchidukien()
    {
        return this.tongchidukien;
    }

    public LiveData<Float> getTongthu()
    {
        return this.tongthu;
    }

    public void insertchidk(ChiDuKien chi){
        mrepositoryChiDuKien.insert(chi);
    }

    public void insertchi(Chi chi){
        mrepositoryHome.insert(chi);
    }
    public void deletechidk(ChiDuKien chi){
        mrepositoryChiDuKien.delete(chi);
    }
    public void updatechidk(ChiDuKien chi){
        mrepositoryChiDuKien.update(chi);
    }
}