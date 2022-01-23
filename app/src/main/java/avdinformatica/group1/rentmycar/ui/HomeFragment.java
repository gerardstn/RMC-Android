package avdinformatica.group1.rentmycar.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import avdinformatica.group1.rentmycar.R;
import avdinformatica.group1.rentmycar.database.AppDatabase;
import avdinformatica.group1.rentmycar.database.AppExecutors;
import avdinformatica.group1.rentmycar.models.CarResponse;
import avdinformatica.group1.rentmycar.models.User;
import avdinformatica.group1.rentmycar.network.Network;
import avdinformatica.group1.rentmycar.services.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    User user;
    String sessionId;
    TextView tvEmail;
    Button btnRegisterYourCar, btnRentalCars;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String sessionId) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("sessionId", sessionId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sessionId = getArguments().getString("sessionId");
        }

        AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplicationContext());
        user = appDatabase.userDao().getUser(sessionId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tvEmail = view.findViewById(R.id.tv_Email);
        tvEmail.setText(user.getEmail());

        initializeListeners(view);
        onClickListeners();

        return view;
    }

    private void onClickListeners() {
        btnRegisterYourCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.action_home_to_car_registraion);
            }
        });

        btnRentalCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CarResponse carResponse = new CarResponse(true);

                ApiService apiService = Network.getInstance().create(ApiService.class);
                apiService.getAvailableCars(carResponse).enqueue(new Callback<List<CarResponse>>() {
                    @Override
                    public void onResponse(Call<List<CarResponse>> call, Response<List<CarResponse>> response) {

                        if (response.body() != null) {
                            Toast.makeText(getActivity().getApplicationContext(), "Cars loading", Toast.LENGTH_SHORT).show();

                            Bundle bundle = new Bundle();
                            bundle.putSerializable("carList", (Serializable) response.body());

                            Navigation.findNavController(view).navigate(R.id.action_home_to_for_rent, bundle);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CarResponse>> call, Throwable t) {
                        Toast.makeText(getActivity().getApplicationContext(), "Unable to retrieve available cars", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initializeListeners(View view) {
        btnRentalCars = view.findViewById(R.id.btn_RentalCars);
        btnRegisterYourCar = view.findViewById(R.id.btn_register_your_car);
    }

}