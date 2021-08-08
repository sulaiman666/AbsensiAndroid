package id.solo.absensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView username;
    String textUsername;
    Button btnCheckIn, btnCheckOut, btnHistoryAbsen, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        varInit();

        textUsername = getIntent().getStringExtra("username");

        username.setText(textUsername);

        btnCheckIn.setOnClickListener(v -> {
            Intent checkIn = new Intent(MainActivity.this, CheckInActivity.class);
            checkIn.putExtra("username", textUsername);
            startActivity(checkIn);
        });

        btnCheckOut.setOnClickListener(v -> {
            Intent checkOut = new Intent(MainActivity.this, CheckOutActivity.class);
            checkOut.putExtra("username", textUsername);
            startActivity(checkOut);
        });

        btnHistoryAbsen.setOnClickListener(v -> {
            Intent historyAbsen = new Intent(MainActivity.this, HistoryActivity.class);
            historyAbsen.putExtra("username", textUsername);
            startActivity(historyAbsen);
        });

        btnLogout.setOnClickListener(v -> {
            username = null;
            finish();
        });
    }

    private void varInit() {
        username = findViewById(R.id.username);
        btnCheckIn = findViewById(R.id.btn_checkin);
        btnCheckOut = findViewById(R.id.btn_checkout);
        btnHistoryAbsen = findViewById(R.id.btn_history_absen);
        btnLogout = findViewById(R.id.btn_logout);
    }
}