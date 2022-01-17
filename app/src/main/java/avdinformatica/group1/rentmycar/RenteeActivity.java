package avdinformatica.group1.rentmycar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import avdinformatica.group1.rentmycar.remote.ApiService;
import avdinformatica.group1.rentmycar.remote.Network;
import avdinformatica.group1.rentmycar.responseClasses.ResponseClass;
import avdinformatica.group1.rentmycar.responseClasses.ResponseRegisterClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RenteeActivity extends AppCompatActivity implements AvailableCarRecyclerViewAdapter.ItemClickListener {

    AvailableCarRecyclerViewAdapter adapter;

    TextView tvCarBrand;
    TextView tvCarModel;
    TextView tvCarDistance;
    TextView tvCarPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentee_carlist);

        ArrayList<String> carDetails = new ArrayList<>();
        carDetails.add("Horse");
        carDetails.add("Cow");
        carDetails.add("Camel");
        carDetails.add("Sheep");
        carDetails.add("Goat");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rv_available_cars);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AvailableCarRecyclerViewAdapter(this, carDetails);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);






        String brand = getIntent().getStringExtra("brand");
        String model = getIntent().getStringExtra("model");
        String distance = getIntent().getStringExtra("distance");
        int price = getIntent().getIntExtra("price", 99);
        tvCarBrand = findViewById(R.id.tv_car_brand);
        tvCarModel = findViewById(R.id.tv_car_model);
        tvCarDistance = findViewById(R.id.tv_car_distance);
        tvCarPrice = findViewById(R.id.tv_car_price);

        tvCarBrand.setText(brand);
        tvCarModel.setText(model);
        tvCarDistance.setText(distance);
        tvCarPrice.setText(""+price);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

}