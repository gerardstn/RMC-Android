package avdinformatica.group1.rentmycar.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import avdinformatica.group1.rentmycar.R;
import avdinformatica.group1.rentmycar.services.ApiService;
import avdinformatica.group1.rentmycar.network.Network;
import avdinformatica.group1.rentmycar.models.UserResponse;
import avdinformatica.group1.rentmycar.models.RegisterResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText etRegisterEmail, etRegisterPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeListeners();
        onClickListeners();

    }

    private void onClickListeners() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateUserName() && validatePassword()) {
                    RegisterResponse registerResponse = new RegisterResponse(etRegisterEmail.getText().toString(), etRegisterPassword.getText().toString());

                    ApiService apiService = Network.getInstance().create(ApiService.class);
                    apiService.addUser(registerResponse).enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            if (response.body() != null) {
                                Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(RegisterActivity.this, "something went wrong! please try again", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {

                        }
                    });
                }

            }
        });
    }

    private boolean validatePassword() {
        if (etRegisterPassword.getText().toString().length() < 6) {
            etRegisterPassword.setError("password must be atleast 6 characters");
            etRegisterPassword.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(etRegisterPassword.getText().toString())) {
            etRegisterPassword.setError("password cannot be empty");
            etRegisterPassword.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validateUserName() {
        if (TextUtils.isEmpty(etRegisterEmail.getText().toString())) {
            etRegisterEmail.setError("username cannot be empty");
            etRegisterEmail.requestFocus();
            return false;
        } else if (!etRegisterEmail.getText().toString().contains("@")) {
            etRegisterEmail.setError("email must contain @");
            etRegisterEmail.requestFocus();
            return false;
        }
        return true;
    }

    private void initializeListeners() {
        etRegisterEmail = findViewById(R.id.et_reg_email);
        etRegisterPassword = findViewById(R.id.et_reg_password);
        btnRegister = findViewById(R.id.btn_register);
    }
}