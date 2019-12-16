package com.stiki.mangab.adapter;

import android.app.AlertDialog;
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
        holder.tvNrp.setText(listMhs.get(position).nrp);
        holder.tvName.setText(listMhs.get(position).nama);
        holder.tvStatus.setText("( Alpa )");

        holder.btnIzin.setOnClickListener(v -> {
            AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create();
            alertDialog.setMessage("Change attendance to \"Izin\"?");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                    (dialog, which) -> {
                        listMhs.get(position).statusAbsen = 2;
                        holder.tvStatus.setText("( Izin )");
                        notifyDataSetChanged();
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                    (dialog, which) -> dialog.dismiss());
            alertDialog.show();
        });

        holder.btnSakit.setOnClickListener(v -> {
            AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create();
            alertDialog.setMessage("Change attendance to \"Sakit\"?");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                    (dialog, which) -> {
                        listMhs.get(position).statusAbsen = 3;
                        holder.tvStatus.setText("( Sakit )");
                        notifyDataSetChanged();
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                    (dialog, which) -> dialog.dismiss());
            alertDialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return listMhs.size();
    }

    class RekapAbsensiVH extends RecyclerView.ViewHolder{
        TextView tvNrp, tvName, tvStatus;
        Button btnIzin, btnSakit;
        RekapAbsensiVH(@NonNull View itemView) {
            super(itemView);
            tvNrp = itemView.findViewById(R.id.tvNRP);
            tvName = itemView.findViewById(R.id.tvName);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            btnIzin = itemView.findViewById(R.id.btnIzin);
            btnSakit = itemView.findViewById(R.id.btnSakit);
        }
    }
}
