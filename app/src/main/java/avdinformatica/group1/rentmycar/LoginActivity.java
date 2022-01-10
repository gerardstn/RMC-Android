package avdinformatica.group1.rentmycar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import avdinformatica.group1.rentmycar.remote.ApiService;
import avdinformatica.group1.rentmycar.remote.Network;
import avdinformatica.group1.rentmycar.responseClasses.ResponseClass;
import avdinformatica.group1.rentmycar.responseClasses.ResponseRegisterClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    EditText etEmail, etPassword;
    TextView tvRegister;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeListeners();
        onClickListeners();
    }

    private void onClickListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateUsername() && validatePassword()) {
                    ResponseRegisterClass responseRegisterClass = new ResponseRegisterClass(etEmail.getText().toString(), etPassword.getText().toString());

                    ApiService apiService = Network.getInstance().create(ApiService.class);
                    apiService.getUser(responseRegisterClass).enqueue(new Callback<ResponseClass>() {
                        @Override
                        public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                            if (response.body() != null) {
                                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, SuccessfulLogin.class);
                                intent.putExtra("email", response.body().getEmail());
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseClass> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }

    private boolean validateUsername() {
        if (TextUtils.isEmpty(etEmail.getText().toString())) {
            etEmail.setError("username cannot be empty");
            etEmail.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validatePassword() {
        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError("password cannot be empty");
            etPassword.requestFocus();
            return false;
        }
        return true;
    }

    private void initializeListeners() {
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        tvRegister = findViewById(R.id.tv_register);
        btnLogin = findViewById(R.id.btn_login);
    }
}