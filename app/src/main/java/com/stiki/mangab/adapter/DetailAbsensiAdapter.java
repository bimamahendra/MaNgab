package com.stiki.mangab.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stiki.mangab.R;
import com.stiki.mangab.api.response.DetailAbsenResponse;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DetailAbsensiAdapter extends RecyclerView.Adapter<DetailAbsensiAdapter.DetailAbsensiVH> {
    private List<DetailAbsenResponse.MhsData> dataMhs;

    public DetailAbsensiAdapter(List<DetailAbsenResponse.MhsData> data){
        this.dataMhs = data;
    }

    @NonNull
    @Override
    public DetailAbsensiVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DetailAbsensiVH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_students, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAbsensiVH holder, int position) {
        holder.tvName.setText(dataMhs.get(position).nama);
        holder.tvNrp.setText(dataMhs.get(position).nrp);
        holder.ivStatus.setVisibility(dataMhs.get(position).statusAbsen == 1 ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return dataMhs.size();
    }

    public void setNewData(List<DetailAbsenResponse.MhsData> data){
        this.dataMhs = data;
        notifyDataSetChanged();
    }

    class DetailAbsensiVH extends RecyclerView.ViewHolder{
        TextView tvName, tvNrp;
        ImageView ivStatus;

        DetailAbsensiVH(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvNrp = itemView.findViewById(R.id.tvNRP);
            ivStatus = itemView.findViewById(R.id.ivStatus);
        }
    }
}
