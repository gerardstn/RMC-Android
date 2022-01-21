package avdinformatica.group1.rentmycar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import avdinformatica.group1.rentmycar.R;
import avdinformatica.group1.rentmycar.services.ApiService;
import avdinformatica.group1.rentmycar.network.Network;
import avdinformatica.group1.rentmycar.models.CarResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuccessfulLoginActivity extends AppCompatActivity {

    TextView tvEmail;
    Button btnRentalCars;
    Button btnRegisterYourCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_login);
        String email = getIntent().getStringExtra("email");
        tvEmail = findViewById(R.id.tv_Email);
        tvEmail.setText(getString(R.string.hello) + "  " + email);
        initializeListeners();
        onClickListeners();
    }

    private void onClickListeners() {
        btnRegisterYourCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuccessfulLoginActivity.this, CarRegistrationActivity.class);
                startActivity(intent);
            }
        });

        btnRentalCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    CarResponse carResponse = new CarResponse(true);

                    ApiService apiService = Network.getInstance().create(ApiService.class);
                    apiService.getAvailableCars(carResponse).enqueue(new Callback<List<CarResponse>>() {
                        @Override
                        public void onResponse(Call<List<CarResponse>> call, Response<List<CarResponse>> response) {

                            if (response.body() != null) {
                                    Log.d("responseFromRetrofit", response.body().toString());

                                Toast.makeText(SuccessfulLoginActivity.this, "Cars loading", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SuccessfulLoginActivity.this, RenteeActivity.class);
                                intent.putExtra("carList", (Serializable) response.body());
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<CarResponse>> call, Throwable t) {
                            Toast.makeText(SuccessfulLoginActivity.this, "Unable to retrieve available cars", Toast.LENGTH_SHORT).show();
                        }
                    });
            }
        });
    }
    private void initializeListeners() {
        btnRentalCars = findViewById(R.id.btn_RentalCars);
        btnRegisterYourCar = findViewById(R.id.btn_register_your_car);
    }
}




