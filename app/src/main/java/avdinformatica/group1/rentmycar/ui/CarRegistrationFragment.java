package avdinformatica.group1.rentmycar.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import avdinformatica.group1.rentmycar.R;
import avdinformatica.group1.rentmycar.database.AppDatabase;
import avdinformatica.group1.rentmycar.models.RegisterCarResponse;
import avdinformatica.group1.rentmycar.models.User;
import avdinformatica.group1.rentmycar.network.Network;
import avdinformatica.group1.rentmycar.services.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarRegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarRegistrationFragment extends Fragment {

    private static final String SESSION_ID = "sessionId";
    private String mSessionId;

    EditText etCarRegisterBrand, etCarRegisterModel, etCarRegisterLicensePlate, etCarRegisterLocation;
    User user;
    RadioButton engineType;
    Button btnCarRegister;


    public CarRegistrationFragment() {
        // Required empty public constructor
    }

    public static CarRegistrationFragment newInstance(String sessionId) {
        CarRegistrationFragment fragment = new CarRegistrationFragment();
        Bundle args = new Bundle();
        args.putString(SESSION_ID, sessionId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppDatabase appDatabase = AppDatabase.getInstance(requireActivity().getApplicationContext());

        if (getArguments() != null) {
            mSessionId = getArguments().getString(SESSION_ID);
            user = appDatabase.userDao().getUser(mSessionId);
        }
    }

    private void onClickListeners() {
        Bundle bundle = new Bundle();
        bundle.putString(SESSION_ID, mSessionId);

        btnCarRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegisterCarResponse registerCarResponse = new RegisterCarResponse(
                        etCarRegisterBrand.getText().toString(),
                        etCarRegisterModel.getText().toString(),
                        etCarRegisterLocation.getText().toString(),
                        engineType.getText().toString(),
                        etCarRegisterLicensePlate.getText().toString(),
                        user.getClientId(), true);

                ApiService apiService = Network.getInstance().create(ApiService.class);
                apiService.addCar(registerCarResponse).enqueue(new Callback<RegisterCarResponse>() {
                    @Override
                    @EverythingIsNonNull
                    public void onResponse(Call<RegisterCarResponse> call, Response<RegisterCarResponse> response) {
                        if (response.body() != null) {
                            Toast.makeText(requireActivity().getApplicationContext(), "Car Registered successful", Toast.LENGTH_SHORT).show();

                            Navigation.findNavController(v).navigate(
                                    R.id.action_fragment_car_registration_to_fragment_thanks_for_submitting, bundle);

                        } else {
                            Toast.makeText(requireActivity().getApplicationContext(), "something went wrong! please try again", Toast.LENGTH_SHORT).show();
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
