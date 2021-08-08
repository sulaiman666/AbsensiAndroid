package id.solo.absensi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.solo.absensi.R;
import id.solo.absensi.entity.Absensi;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.AbsenViewHolder> {
    private final ArrayList<Absensi> dataAbsen;
    private final Context context;

    public HistoryAdapter(Context context, ArrayList<Absensi> dataAbsen) {
        this.dataAbsen = dataAbsen;
        this.context = context;
    }

    @NonNull
    @Override
    public HistoryAdapter.AbsenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view =layoutInflater.inflate(R.layout.item_history, parent, false);
        return new AbsenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.AbsenViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.itemRow.setBackgroundColor(ResourcesCompat.getColor(context.getResources(),
                    R.color.gray, null));
        }
        holder.tvTanggal.setText(dataAbsen.get(position).getTanggalMasuk());
        holder.tvMasuk.setText(getJam(dataAbsen.get(position).getJamMasuk()));
        holder.tvPulang.setText(getJam(dataAbsen.get(position).getJamKeluar()));
        holder.tvLembur.setText(Integer.toString(getLembur(dataAbsen.get(position).getJamMasuk(), dataAbsen.get(position).getJamKeluar())));
    }

    @Override
    public int getItemCount() {
        return (dataAbsen != null) ? dataAbsen.size() : 0;
    }

    public class AbsenViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTanggal, tvMasuk, tvPulang, tvLembur;
        public TableRow itemRow;
        public AbsenViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
            tvMasuk = itemView.findViewById(R.id.tv_masuk);
            tvPulang = itemView.findViewById(R.id.tv_pulang);
            tvLembur = itemView.findViewById(R.id.tv_lembur);
            itemRow = itemView.findViewById(R.id.item_row);
        }
    }

    private int getLembur(int jamMasuk, int jamKeluar) {
        int menitMasuk = (jamMasuk / 100) * 60 + (jamMasuk % 100);
        int menitPulang = (jamKeluar / 100) * 60 + (jamKeluar % 100);

        return (menitPulang - menitMasuk) / 60;
    }

    private String getJam(int jam) {
        int hours = jam / 100;
        int minute = jam % 100;
        if (hours < 10 && minute < 10) return "0" + hours + ":" + "0" + minute;
        else if (hours < 10) return "0" + hours + ":" + minute;
        else if (minute < 10) return hours + ":" + minute;
        else return hours + ":" + minute;
    }
}
