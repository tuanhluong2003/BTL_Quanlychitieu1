package com.example.navication_bar.ui.Chi.Loaikhoanchi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.navication_bar.Entity.Loaichi;
import com.example.navication_bar.Repository.RepositoryLoaiChi;

import java.util.List;

public class LoaikhoanchiViewModel extends AndroidViewModel {

    private RepositoryLoaiChi mRepositoryLoaiChi;
    private LiveData<List<Loaichi>> mAllLoaiChi;

    public LoaikhoanchiViewModel(@NonNull Application application) {
        super(application);
        mRepositoryLoaiChi= new RepositoryLoaiChi(application);
        mAllLoaiChi = mRepositoryLoaiChi.getAllLoaiChi(); //trả về ds loại chi có trong csdl
    }
    public LiveData<List<Loaichi>> getAllLoaiChi(){
        return mAllLoaiChi;

    }
    public void insert(Loaichi loaiChi){
        mRepositoryLoaiChi.insert(loaiChi);
        //chèn loại chi vào csdl

    }
    public void delete(Loaichi loaiChi){
        mRepositoryLoaiChi.delete(loaiChi);
    }
    public void update(Loaichi loaiChi){
        mRepositoryLoaiChi.update(loaiChi);
    }
}