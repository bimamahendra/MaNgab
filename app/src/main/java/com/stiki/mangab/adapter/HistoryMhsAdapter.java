package com.stiki.mangab.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stiki.mangab.R;
import com.stiki.mangab.api.response.HistoryAbsensiMhsResponse;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryMhsAdapter extends RecyclerView.Adapter<HistoryMhsAdapter.HistoryAdapterVH> {

    private List<HistoryAbsensiMhsResponse.HistoryAbsensiData> list;

    public HistoryMhsAdapter(List<HistoryAbsensiMhsResponse.HistoryAbsensiData> list){
        this.list = list;
    }

    @NonNull
    @Override
    public HistoryAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryAdapterVH(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.row_history_mhs, parent,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapterVH holder, int position) {
        holder.tvMatkul.setText(list.get(position).namaMatkul + " | " + list.get(position).kelasMatkul);
        holder.tvAbsen.setText(list.get(position).jadwalAbsen);
        holder.tvNamaDosen.setText(list.get(position).namaDosen);
        if(list.get(position).statusAbsen == 0){
            holder.tvStatusKehadiran.setText("Alpa");
        }else if(list.get(position).statusAbsen == 1){
            holder.tvStatusKehadiran.setText("Hadir");
        }else if(list.get(position).statusAbsen == 2){
            holder.tvStatusKehadiran.setText("Izin");
        }else if(list.get(position).statusAbsen == 3){
            holder.tvStatusKehadiran.setText("Sakit");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HistoryAdapterVH extends RecyclerView.ViewHolder{
        public TextView tvMatkul, tvAbsen, tvNamaDosen, tvStatusKehadiran;
        public HistoryAdapterVH(@NonNull View itemView) {
            super(itemView);
            tvMatkul = itemView.findViewById(R.id.tvMatkul);
            tvAbsen = itemView.findViewById(R.id.tvAbsen);
            tvNamaDosen = itemView.findViewById(R.id.tvNamaDosen);
            tvStatusKehadiran = itemView.findViewById(R.id.tvStatusKehadiran);
        }
    }
}
