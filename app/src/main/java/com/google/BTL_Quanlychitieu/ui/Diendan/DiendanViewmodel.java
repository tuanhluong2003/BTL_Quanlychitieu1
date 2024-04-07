package com.google.BTL_Quanlychitieu.ui.Diendan;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.BTL_Quanlychitieu.Entity.Message;

import java.util.ArrayList;
import java.util.List;

public class DiendanViewmodel extends AndroidViewModel {
    private List<Message> mylist;
    private MutableLiveData<List<Message>> myListLiveData = new MutableLiveData<>();
    public DiendanViewmodel(@NonNull Application application) {
        super(application);
        mylist = new ArrayList<>();
    }

    // Phương thức để lấy LiveData cho danh sách
    public LiveData<List<Message>> getMyListLiveData() {
        return myListLiveData;
    }

    public int  getsize()
    {
    if (mylist!= null)
        return mylist.size()-1;
    else return -1;
    }
    public void updateList(Message ms) {
        mylist.add(ms);
        myListLiveData.setValue(mylist);
    }

}
