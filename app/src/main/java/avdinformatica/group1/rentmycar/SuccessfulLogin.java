package avdinformatica.group1.rentmycar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import avdinformatica.group1.rentmycar.remote.ApiService;
import avdinformatica.group1.rentmycar.remote.Network;
import avdinformatica.group1.rentmycar.responseClasses.ResponseCarClass;
import avdinformatica.group1.rentmycar.responseClasses.ResponseClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuccessfulLogin extends AppCompatActivity {

    TextView tvEmail;
    Button btnRentalCars;

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
        btnRentalCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    ResponseCarClass responseCarClass = new ResponseCarClass(true);

                    ApiService apiService = Network.getInstance().create(ApiService.class);
                    apiService.getAvailableCars(responseCarClass).enqueue(new Callback<ResponseClass>() {
                        @Override
                        public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {

                            if (response.body() != null) {
                                Toast.makeText(SuccessfulLogin.this, "Cars loading", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SuccessfulLogin.this, RenteeActivity.class);
                                intent.putExtra("brand", response.body().getBrand());
                                intent.putExtra("model", response.body().getModel());
                                intent.putExtra("distance", response.body().getDistance());
                                intent.putExtra("price", response.body().getPrice());
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseClass> call, Throwable t) {
                            Toast.makeText(SuccessfulLogin.this, "Unable to retrieve available cars", Toast.LENGTH_SHORT).show();
                        }
                    });
            }
        });
    }
    private void initializeListeners() {
        btnRentalCars = findViewById(R.id.btn_RentalCars);
    }
}




