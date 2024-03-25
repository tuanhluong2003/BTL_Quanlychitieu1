package com.example.navication_bar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.navication_bar.Entity.Loaithu;
import com.example.navication_bar.R;

import java.util.List;

public class Loaithu_spinner_adapter extends BaseAdapter {
    private List<Loaithu> mList;
    private LayoutInflater mLayoutInflater;

    public Loaithu_spinner_adapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setList(List<Loaithu> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(mList ==null)
            return 0;
        return mList.size();
    }

    @Override
    public Loaithu getItem(int i) {
        if(mList==null)
            return null;
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        KhoanThuViewHolder holder;
        if(view == null){
            view = mLayoutInflater.inflate(R.layout.spinner_thu_item, null, false);
            holder = new KhoanThuViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (KhoanThuViewHolder) view.getTag();
        }
        holder.tvName.setText(mList.get(i).Tenloaithu);
        return view;
    }
    public static class KhoanThuViewHolder{
        public TextView tvName;
        public KhoanThuViewHolder(View view){
            tvName = view.findViewById(R.id.tvName);
        }
    }
}