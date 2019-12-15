package com.stiki.mangab.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.stiki.mangab.R;
import com.stiki.mangab.api.response.DetailAbsenResponse;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RekapAbsensiAdapter extends RecyclerView.Adapter<RekapAbsensiAdapter.RekapAbsensiVH> {
    private List<DetailAbsenResponse.MhsData> listMhs;

    public RekapAbsensiAdapter(List<DetailAbsenResponse.MhsData> listMhs){
        this.listMhs = listMhs;
    }

    @NonNull
    @Override
    public RekapAbsensiVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RekapAbsensiVH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_rekap, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RekapAbsensiVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listMhs.size();
    }

    class RekapAbsensiVH extends RecyclerView.ViewHolder{
        public TextView tvNrp, tvName;
        public Button btnIzin, btnSakit;
        public RekapAbsensiVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
