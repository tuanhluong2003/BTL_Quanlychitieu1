package com.example.navication_bar.ui.Thu.Loaikhoanthu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.navication_bar.Entity.Loaithu;
import com.example.navication_bar.Repository.RepositoryLoaiThu;

import java.util.List;

public class LoaikhoanthuViewModel extends AndroidViewModel {
    private RepositoryLoaiThu mrepositoryLoaiThu;
    private LiveData<List<Loaithu>> mAllLoaiThu;

    public LoaikhoanthuViewModel(@NonNull Application application) {
        super(application);
        mrepositoryLoaiThu= new RepositoryLoaiThu(application);
        mAllLoaiThu = mrepositoryLoaiThu.getAllLoaiThu(); //trả về ds loại thu có trong csdl
    }
    public LiveData<List<Loaithu>> getAllLoaiThu(){
        return mAllLoaiThu;

    }
    public void insert(Loaithu loaiThu){
        mrepositoryLoaiThu.insert(loaiThu);
        //chèn loại thu vào csdl

    }

    public void delete(Loaithu loaiThu){
        mrepositoryLoaiThu.delete(loaiThu);
    }
    public void update(Loaithu loaiThu){
        mrepositoryLoaiThu.update(loaiThu);
    }
}