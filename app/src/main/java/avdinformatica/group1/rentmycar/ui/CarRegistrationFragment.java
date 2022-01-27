package avdinformatica.group1.rentmycar.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    RadioButton rbCarEngineTypeBev, rbCarEngineTypeIce, rbCarEngineTypeFcev;
    RadioGroup rgCarEngineType;
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
                if (validateForm()) {

                    RadioButton rb = null;

                    if (rbCarEngineTypeBev.isChecked()){
                        rb = rbCarEngineTypeBev;
                    }else if (rbCarEngineTypeIce.isChecked()){
                        rb = rbCarEngineTypeIce;
                    }else if (rbCarEngineTypeFcev.isChecked()){
                        rb = rbCarEngineTypeFcev;
                    }

                    assert rb != null;

                    RegisterCarResponse registerCarResponse = new RegisterCarResponse(
                            etCarRegisterBrand.getText().toString(),
                            etCarRegisterModel.getText().toString(),
                            etCarRegisterLocation.getText().toString(),
                            rb.getText().toString(),
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
        rgCarEngineType = view.findViewById(R.id.rg_car_engine_type);
        rbCarEngineTypeBev = view.findViewById(R.id.rb_car_register_bev);
        rbCarEngineTypeIce = view.findViewById(R.id.rb_car_register_ice);
        rbCarEngineTypeFcev = view.findViewById(R.id.rb_car_register_fcev);
        etCarRegisterLocation = view.findViewById(R.id.et_car_register_location);
        btnCarRegister = view.findViewById(R.id.btn_car_register);
    }

    private boolean validateForm() {
        return validateCarRegisterBrand()
                && validateCarRegisterModel()
                && validateCarRegisterLicensePlate()
                && validateCarRegisterLocation()
                && validateCarRegisterEngineType();
    }

    private boolean validateCarRegisterBrand() {
        if (TextUtils.isEmpty(etCarRegisterBrand.getText().toString())) {
            etCarRegisterBrand.setError("Merk mag niet leeg zijn");
            etCarRegisterBrand.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validateCarRegisterModel() {
        if (TextUtils.isEmpty(etCarRegisterModel.getText().toString())) {
            etCarRegisterModel.setError("Model mag niet leeg zijn");
            etCarRegisterModel.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validateCarRegisterLicensePlate() {
        if (TextUtils.isEmpty(etCarRegisterLicensePlate.getText().toString())) {
            etCarRegisterLicensePlate.setError("Kenteken mag niet leeg zijn");
            etCarRegisterLicensePlate.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validateCarRegisterLocation() {

        if (TextUtils.isEmpty(etCarRegisterLocation.getText().toString())) {
            etCarRegisterLocation.setError("Locatie mag niet leeg zijn");
            etCarRegisterLocation.requestFocus();
            return false;
        }

        String regexp = "^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$";

        if (!etCarRegisterLocation.getText().toString().matches(regexp)) {
            etCarRegisterLocation.setError("Locatie waarde zijn geen geldige coordinaten");
            etCarRegisterLocation.requestFocus();
            return false;
        }

        return true;
    }

    private boolean validateCarRegisterEngineType(){
        if (rgCarEngineType.getCheckedRadioButtonId() == -1){
            Toast.makeText(requireActivity().getApplicationContext(), "Selecteer een auto type", Toast.LENGTH_LONG).show();
            rgCarEngineType.requestFocus();
            return false;
        }
//
//        if(!rbCarEngineTypeBev.isSelected() && !rbCarEngineTypeFcev.isSelected() && !rbCarEngineTypeIce.isSelected()){
//            Toast.makeText(requireActivity().getApplicationContext(), "Selecteer een auto type", Toast.LENGTH_LONG).show();
//            return false;
//        }

        Log.d("Engine Type", "validateCarRegisterEngineType: " + rgCarEngineType.getCheckedRadioButtonId());

        return true;
    }
}
