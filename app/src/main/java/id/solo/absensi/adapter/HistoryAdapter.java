package id.solo.absensi.adapter;

import android.annotation.SuppressLint;
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.AbsenViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.itemRow.setBackgroundColor(ResourcesCompat.getColor(context.getResources(),
                    R.color.gray, null));
        }

        if (dataAbsen.get(position).getTanggalMasuk() != null)
            holder.tvTanggal.setText(getTanggal(dataAbsen.get(position).getTanggalMasuk()));
        else holder.tvTanggal.setText(getTanggal(dataAbsen.get(position).getTanggalKeluar()));

        holder.tvMasuk.setText(getJam(String.valueOf(dataAbsen.get(position).getTanggalMasuk())));
        holder.tvPulang.setText(getJam(String.valueOf(dataAbsen.get(position).getTanggalKeluar())));

        if (dataAbsen.get(position).getTanggalKeluar() != null
                && dataAbsen.get(position).getTanggalMasuk() != null
                && getLembur(getJam(dataAbsen.get(position).getTanggalMasuk()),
                getJam(dataAbsen.get(position).getTanggalKeluar())) > 0)
            holder.tvLembur.setText(String.valueOf(getLembur(getJam(dataAbsen.get(position).getTanggalMasuk()),
                    getJam(dataAbsen.get(position).getTanggalKeluar()))));
        else holder.tvLembur.setText(R.string.tanda);
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

    private String getTanggal(String tanggal) {
        String[] split = tanggal.split(" ");
        return split[0];
    }

    private String getJam(String tanggal) {
        if (tanggal != "null") {
            String[] split = tanggal.split(" ");
            return split[1];
        }
        else return "0";
    }

    private int getLembur(String jamMasuk, String jamKeluar) {
        String[] splitJamMasuk = jamMasuk.split(":");
        String[] splitJamKeluar = jamKeluar.split(":");

        int menitMasuk = Integer.parseInt(String.valueOf(splitJamMasuk[0])) * 60
                + Integer.parseInt(String.valueOf(splitJamMasuk[1]));
        int menitPulang = Integer.parseInt(String.valueOf(splitJamKeluar[0])) * 60
                + Integer.parseInt(String.valueOf(splitJamKeluar[1]));

        int hasil = (menitPulang - menitMasuk) / 60 - 10;

        return hasil;
    }


}
