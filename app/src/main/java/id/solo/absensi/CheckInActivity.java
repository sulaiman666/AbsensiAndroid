package id.solo.absensi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import id.solo.absensi.entity.Absensi;
import id.solo.absensi.services.AbsenAPI;
import id.solo.absensi.services.ApiClient;
import in.mayanknagwanshi.imagepicker.ImageSelectActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckInActivity extends AppCompatActivity {
    Button btnFoto, btnSubmitFoto;
    TextView tvGPS, tvSuccess;
    ImageView imgLogin;
    String mediaPath = "", textUsername;
    String locationText = "0";
    FusedLocationProviderClient mFusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        varInit();

        getLocation();

        textUsername = getIntent().getStringExtra("username");

        btnFoto.setOnClickListener(v -> {
            Intent gambar = new Intent(CheckInActivity.this, ImageSelectActivity.class);
            gambar.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);
            gambar.putExtra(ImageSelectActivity.FLAG_CAMERA, true);
            gambar.putExtra(ImageSelectActivity.FLAG_GALLERY, true);
            startActivityForResult(gambar, 1);
        });

        btnSubmitFoto.setOnClickListener(v -> {
            if (mediaPath.isEmpty()) {
                Toast.makeText(CheckInActivity.this, "Ambil foto terlebih dahulu", Toast.LENGTH_LONG).show();
            } else uploadAbsen();
        });
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            mediaPath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            //imgLogin.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
            imgLogin.setImageDrawable(getResources().getDrawable(R.drawable.checklist));
            tvSuccess.setText("Login Berhasil");
            tvSuccess.setVisibility(View.VISIBLE);
            tvGPS.setText("Geotag: " + locationText);
            tvGPS.setVisibility(View.VISIBLE);
        } else {
            //coba lg
            imgLogin.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_warning_24));
            tvSuccess.setText("Foto belum masuk, coba lagi");
            tvSuccess.setVisibility(View.VISIBLE);
        }
    }

    private void uploadAbsen() {
        Absensi absen = new Absensi();

        absen.setUsername(textUsername);
        absen.setTanggalMasuk(getTanggal());
        absen.setGPS(locationText);

        Gson gson = new Gson();
        String json = gson.toJson(absen);

        AbsenAPI absenAPI = ApiClient
                .getRetrofit().create(AbsenAPI.class);

        File file = new File(mediaPath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file",
                file.getName(), requestBody);
        RequestBody data = RequestBody.create(MediaType.parse("text/plain"), json);

        Call<ResponseBody> call = absenAPI.checkInAbsen(fileToUpload, data);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Toast.makeText(CheckInActivity.this, (getJam() / 100) + ":" + String.valueOf(getJam() % 100) + " " + response.body().string(), Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private int getJam() {
        Calendar calendar = Calendar.getInstance();
        int hour24hrs = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        return (hour24hrs * 100) + minutes;
    }

    @SuppressLint("MissingPermission")
    private String getLocation() {
        mFusedLocationProviderClient = LocationServices
                .getFusedLocationProviderClient(CheckInActivity.this);

        mFusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(CheckInActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            locationText = location.getLatitude() + ", " +
                                    location.getLongitude();
                        } else locationText = "null";
                    }
                });

        return locationText;
    }

    private String getTanggal() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter =
                new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date date = new Date();

        return formatter.format(date);
    }

    private void varInit() {
        btnFoto = findViewById(R.id.btn_foto);
        btnSubmitFoto = findViewById(R.id.btn_submit_foto);
        imgLogin = findViewById(R.id.img_login);
        tvGPS = findViewById(R.id.tv_gps);
        tvSuccess = findViewById(R.id.tv_success);
    }
}