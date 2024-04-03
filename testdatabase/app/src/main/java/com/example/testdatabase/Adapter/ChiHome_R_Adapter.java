package com.example.testdatabase.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testdatabase.Entity.Chi;
import com.example.testdatabase.Other.CustomNumber;
import com.example.testdatabase.Other.Customdate;
import com.example.testdatabase.R;

import java.util.List;

public class ChiHome_R_Adapter extends RecyclerView.Adapter<ChiHome_R_Adapter.ChiViewHolder>{
    private LayoutInflater mlayoutInflater;
    private List<Chi> mList;
    public ChiHome_R_Adapter(Context context) {
        mlayoutInflater= LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public ChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mlayoutInflater.inflate(R.layout.item_khoanthuchi_home, parent, false);
        return new ChiViewHolder(view);
    }
    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ChiViewHolder holder, int position) {
        if (mList !=null){
            Chi tmpchi = mList.get(mList.size()-position-1);
            holder.tv_makhoan.setText("Mã khoản: "+ String.valueOf(tmpchi.idchi));
            holder.tv_sotienkhoan.setText("-"+ CustomNumber.formatNumber((int)tmpchi.sotien)+ " VND");
            holder.Tv_tenkhoan.setText("Tên: " + tmpchi.ten);
            holder.tv_timekhoan.setText(Customdate.ConvertDate(tmpchi.date) + " \t " + tmpchi.time);
        }
        setAnimation(holder.itemView);
    }

    private void setAnimation(View view) {
        ScaleAnimation animation = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(300);
        view.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        if (mList==null)
            return 0;
        return mList.size();
    }
    public Chi getItem(int position){
        if(mList==null || position>= mList.size()){
            return null;
        }
        return mList.get(position);
    }
    public void setList(List<Chi> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class ChiViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_makhoan,tv_sotienkhoan, tv_timekhoan, Tv_tenkhoan;
        @SuppressLint("ResourceAsColor")
        public ChiViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_makhoan = itemView.findViewById(R.id.tv_makhoanthuchi);
            tv_sotienkhoan = itemView.findViewById(R.id.sotienthuchi);
            tv_timekhoan = itemView.findViewById(R.id.tv_thoigianthuchi);
            Tv_tenkhoan = itemView.findViewById(R.id.tv_tenkhoanthuchi);
        }
    }
}
