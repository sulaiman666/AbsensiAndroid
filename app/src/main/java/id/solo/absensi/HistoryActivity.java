package id.solo.absensi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import id.solo.absensi.adapter.HistoryAdapter;
import id.solo.absensi.entity.Absensi;
import id.solo.absensi.services.AbsenAPI;
import id.solo.absensi.services.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView rvHistory;
    TextView tvUsername;
    HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        tvUsername = findViewById(R.id.tv_username);
        rvHistory = findViewById(R.id.rv_history);

        tvUsername.setText(getIntent().getStringExtra("username"));

        AbsenAPI absenAPI = ApiClient.getRetrofit().create(AbsenAPI.class);

        Call<ArrayList<Absensi>> call = absenAPI.getAbsenByUsername(getIntent().getStringExtra("username"));

        call.enqueue(new Callback<ArrayList<Absensi>>() {
            @Override
            public void onResponse(Call<ArrayList<Absensi>> call, Response<ArrayList<Absensi>> response) {
                historyAdapter = new HistoryAdapter(HistoryActivity.this, response.body());

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                        HistoryActivity.this, RecyclerView.VERTICAL, false);

                rvHistory.setLayoutManager(layoutManager);
                rvHistory.setAdapter(historyAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Absensi>> call, Throwable t) {

            }
        });

    }
}