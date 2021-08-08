package id.solo.absensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.Objects;

import id.solo.absensi.entity.Status;
import id.solo.absensi.entity.User;
import id.solo.absensi.services.ApiClient;
import id.solo.absensi.services.UserAPI;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edtUsername, edtPassword;
    String username, password;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        varInit();

        btnLogin.setOnClickListener(v -> {
            loginUser();
        });
    }

    private void loginUser() {
        username = edtUsername.getText().toString();
        password = edtPassword.getText().toString();


        if (username.isEmpty()) {
            edtUsername.setError("Username is required");
            edtUsername.requestFocus();
            return;
        } else if (password.isEmpty()) {
            edtPassword.setError("Password is required");
            edtPassword.requestFocus();
            return;
        }

        UserAPI userAPI = ApiClient
                .getRetrofit().create(UserAPI.class);

        Call<ResponseBody> call = userAPI.loginUser(new User(username, password));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    s = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

//                if (s.equals(username)) {
                if (s.equals("SUCCESS")){
                    Toast.makeText(LoginActivity.this, "User logged in!", Toast.LENGTH_LONG).show();
                    Intent mainActivityStart = new Intent(LoginActivity.this, MainActivity.class);
                    mainActivityStart.putExtra("username", username);
                    startActivity(mainActivityStart);
                } else {
                    Toast.makeText(LoginActivity.this, "Incorrect Credentials! Try again!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void varInit() {
        btnLogin = findViewById(R.id.btn_login);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
    }
}