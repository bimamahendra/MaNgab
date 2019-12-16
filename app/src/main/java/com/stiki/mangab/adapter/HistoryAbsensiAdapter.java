package com.stiki.mangab.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stiki.mangab.R;
import com.stiki.mangab.api.response.HistoryAbsensiResponse;

import java.util.List;

public class HistoryAbsensiAdapter extends RecyclerView.Adapter<HistoryAbsensiAdapter.HistoriAbsensiVH> {

    private List<HistoryAbsensiResponse.HistoryAbsensiData> dataHistory;

    public HistoryAbsensiAdapter(List<HistoryAbsensiResponse.HistoryAbsensiData> dataHistory) {
        this.dataHistory = dataHistory;
    }

    @NonNull
    @Override
    public HistoriAbsensiVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryAbsensiAdapter.HistoriAbsensiVH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_history, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoriAbsensiVH holder, int position) {
        holder.tvMataKuliahClass.setText(dataHistory.get(position).namaMatkul + " | " + dataHistory.get(position).kelasMatkul);
        holder.tvClassDateTime.setText(dataHistory.get(position).ruanganMatkul + ", " + dataHistory.get(position).jadwalKelas);
        holder.tvTopik.setText(dataHistory.get(position).topikMatkul);
    }

    @Override
    public int getItemCount() {
        return dataHistory.size();
    }

    public class HistoriAbsensiVH extends RecyclerView.ViewHolder {

        TextView tvMataKuliahClass, tvClassDateTime, tvTopik;

        public HistoriAbsensiVH(@NonNull View itemView) {
            super(itemView);
            tvMataKuliahClass = itemView.findViewById(R.id.tvMataKuliahClass);
            tvClassDateTime = itemView.findViewById(R.id.tvClassDateTime);
            tvTopik = itemView.findViewById(R.id.tvTopik);
        }

    }

}
