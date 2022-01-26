package avdinformatica.group1.rentmycar.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import avdinformatica.group1.rentmycar.R;
import avdinformatica.group1.rentmycar.database.AppDatabase;
import avdinformatica.group1.rentmycar.models.Car;
import avdinformatica.group1.rentmycar.models.CarResponse;
import avdinformatica.group1.rentmycar.models.RegisterCarResponse;
import avdinformatica.group1.rentmycar.models.User;
import avdinformatica.group1.rentmycar.models.UserResponse;
import avdinformatica.group1.rentmycar.network.Network;
import avdinformatica.group1.rentmycar.services.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarRegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarRegistrationFragment extends Fragment {

    EditText etCarRegisterBrand, etCarRegisterModel, etCarRegisterLicensePlate, etCarRegisterLocation;
//    User user;
    RadioButton engineType;
    Button btnCarRegister;


    public CarRegistrationFragment() {
        // Required empty public constructor
    }

    public static CarRegistrationFragment newInstance() {
        CarRegistrationFragment fragment = new CarRegistrationFragment();
        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplicationContext());

        if (getArguments() != null) {
//            user = appDatabase.userDao().getUser(getArguments().getString("sessionId"));
        }
    }

    private void onClickListeners() {
        btnCarRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegisterCarResponse registerCarResponse = new RegisterCarResponse(etCarRegisterBrand.getText().toString(), etCarRegisterModel.getText().toString(), etCarRegisterLocation.getText().toString(), engineType.getText().toString(), etCarRegisterLicensePlate.getText().toString(), 4l);

                ApiService apiService = Network.getInstance().create(ApiService.class);
                apiService.addCar(registerCarResponse).enqueue(new Callback<RegisterCarResponse>() {
                    @Override
                    public void onResponse(Call<RegisterCarResponse> call, Response<RegisterCarResponse> response) {
                        if (response.body() != null) {
                            Toast.makeText(getActivity().getApplicationContext(), "Car Registered successful", Toast.LENGTH_SHORT).show();
                           // HomeFragment fragment = new HomeFragment();
                           //Navigation.findNavController(v).navigate(R.id.fragment_thanks_for_submission);
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), "something went wrong! please try again", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterCarResponse> call, Throwable t) {

                    }
                });
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_car_registration, container, false);

        initializeListeners(view);
        onClickListeners();
        return view;
    }

    private void initializeListeners(View view) {
        etCarRegisterBrand = view.findViewById(R.id.et_car_register_brand);
        etCarRegisterModel = view.findViewById(R.id.et_car_register_model);
        etCarRegisterLicensePlate = view.findViewById(R.id.et_car_register_licence_plate);
        engineType = view.findViewById(R.id.rb_car_register_bev);
        engineType = view.findViewById(R.id.rb_car_register_ice);
        engineType = view.findViewById(R.id.rb_car_register_fcev);
        etCarRegisterLocation = view.findViewById(R.id.et_car_register_location);
        btnCarRegister = view.findViewById(R.id.btn_car_register);
    }

}
