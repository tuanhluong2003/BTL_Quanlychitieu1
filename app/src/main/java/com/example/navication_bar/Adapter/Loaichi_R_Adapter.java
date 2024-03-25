package com.example.navication_bar.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navication_bar.Entity.Loaichi;
import com.example.navication_bar.Listener.ItemClickListener;
import com.example.navication_bar.R;

import java.util.List;

public class Loaichi_R_Adapter extends RecyclerView.Adapter<Loaichi_R_Adapter.LoaiChiViewHolder> {
    private LayoutInflater mlayoutInflater;
    private List<Loaichi> mList;
    public static ItemClickListener itemClickListener;
    public Loaichi_R_Adapter(Context context) {
        mlayoutInflater= LayoutInflater.from(context);
    }

    public void setOnItemClickListener(ItemClickListener itemEditClickListener) {
        Loaichi_R_Adapter.itemClickListener = itemEditClickListener;
    }

    @NonNull
    @Override
    public LoaiChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mlayoutInflater.inflate(R.layout.item_loaikhoanchi, parent, false);
        return new LoaiChiViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull LoaiChiViewHolder holder, int position) {
        if (mList !=null){
            holder.textviewName.setText(mList.get(position).Tenloaichi);
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(mList.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mList==null)
            return 0;
        return mList.size();
    }
    public Loaichi getItem(int position){
        if(mList==null || position>= mList.size()){
            return null;
        }
        return mList.get(position);
    }
    public void setList(List<Loaichi> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class LoaiChiViewHolder extends RecyclerView.ViewHolder{
        public TextView textviewName;
        public CardView cardView;
        public LoaiChiViewHolder(@NonNull View itemView) {
            super(itemView);
            textviewName = itemView.findViewById(R.id.tv_loaikhoanchi);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
