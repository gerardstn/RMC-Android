package avdinformatica.group1.rentmycar.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import avdinformatica.group1.rentmycar.R;
import avdinformatica.group1.rentmycar.models.RegisterResponse;
import avdinformatica.group1.rentmycar.models.UserResponse;
import avdinformatica.group1.rentmycar.network.Network;
import avdinformatica.group1.rentmycar.services.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private EditText etRegisterEmail, etRegisterPassword;
    private Button btnRegister;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        initializeListeners(view);
        onClickListeners();

        return view;
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

    private void initializeListeners(View view) {
        etRegisterEmail = view.findViewById(R.id.et_reg_email);
        etRegisterPassword = view.findViewById(R.id.et_reg_password);
        btnRegister = view.findViewById(R.id.btn_register);
    }
}