package avdinformatica.group1.rentmycar.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import avdinformatica.group1.rentmycar.R;
import avdinformatica.group1.rentmycar.models.RegisterCarResponse;
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

    EditText et_car_register_brand, et_car_register_model, et_car_register_license_plate, et_car_register_location;
    RadioButton engineType;
    Button btn_car_register;


    public CarRegistrationFragment() {
        // Required empty public constructor
    }

    public static CarRegistrationFragment newInstance(String param1, String param2) {
        CarRegistrationFragment fragment = new CarRegistrationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_car_registration, container, false);
        et_car_register_brand = (EditText) view.findViewById(R.id.et_car_register_brand);
        et_car_register_model = (EditText) view.findViewById(R.id.et_car_register_model);
        et_car_register_license_plate = (EditText) view.findViewById(R.id.et_car_register_licence_plate);
        engineType = (RadioButton) view.findViewById(R.id.rb_car_register_bev);
        engineType = (RadioButton) view.findViewById(R.id.rb_car_register_ice);
        engineType = (RadioButton) view.findViewById(R.id.rb_car_register_fcev);
        et_car_register_location = (EditText) view.findViewById(R.id.et_car_register_location);
        btn_car_register = (Button) view.findViewById(R.id.btn_car_register);

        btn_car_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();
            }
        });
        return view;
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered() {
        if (isEmpty(et_car_register_brand)) {
            Toast t = Toast.makeText(getActivity().getApplicationContext(), "You must enter the car's brand to register!", Toast.LENGTH_SHORT);
            t.show();
        }

        if (isEmpty(et_car_register_model) == false) {
            et_car_register_model.setError("Your car's year of construction is required!");
        } else if (isEmpty(et_car_register_license_plate) == false) {
            et_car_register_license_plate.setError("Your car's licenseplate is required!");
        } else if (!engineType.isChecked()) {
            engineType.setError("Your car's engine type is required!");
        } else if (isEmpty(et_car_register_location) == false) {
            et_car_register_location.setError("Your car's location is required!");
        } else {
            RegisterCarResponse registerCarResponse = new RegisterCarResponse(
                    et_car_register_brand.getText().toString(),
                    et_car_register_model.getText().toString(),
                    et_car_register_license_plate.getText().toString(),
                    engineType.getText().toString(),
                    et_car_register_location.getText().toString()
            );

            ApiService apiService = Network.getInstance().create(ApiService.class);
            apiService.addCar(registerCarResponse).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.body() != null) {
                        Toast.makeText(getActivity().getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                        getParentFragmentManager().popBackStack();
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "something went wrong! please try again", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                }

            });
        }
    }
}
