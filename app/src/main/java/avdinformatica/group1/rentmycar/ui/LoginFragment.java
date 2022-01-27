package avdinformatica.group1.rentmycar.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import avdinformatica.group1.rentmycar.R;
import avdinformatica.group1.rentmycar.database.AppDatabase;
import avdinformatica.group1.rentmycar.database.AppExecutors;
import avdinformatica.group1.rentmycar.models.RegisterResponse;
import avdinformatica.group1.rentmycar.models.User;
import avdinformatica.group1.rentmycar.models.UserResponse;
import avdinformatica.group1.rentmycar.network.Network;
import avdinformatica.group1.rentmycar.services.ApiService;
import avdinformatica.group1.rentmycar.utils.Helper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    EditText etEmail, etPassword;
    TextView tvRegister;
    Button btnLogin;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        etEmail = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);
        tvRegister = view.findViewById(R.id.tv_register);
        btnLogin = view.findViewById(R.id.btn_login);

        setOnClickListeners();

        return view;
    }

    private void setOnClickListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateUsername() && validatePassword()) {
                    RegisterResponse registerResponse = new RegisterResponse(etEmail.getText().toString(), etPassword.getText().toString());

                    ApiService apiService = Network.getInstance().create(ApiService.class);
                    apiService.getUser(registerResponse).enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            String sessionId = Helper.generateRandomSessionString();
                            if (response.body() != null) {

                                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                    @Override
                                    public void run() {

                                        AppDatabase appDatabase = AppDatabase.getInstance(requireActivity().getApplicationContext());
                                        User user = new User(response.body().getEmail(),
                                                response.body().getName(),
                                                response.body().getSurname(),
                                                sessionId,
                                                response.body().getId() );
                                        appDatabase.userDao().insertUser(user);

                                    }
                                });

                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                Toast.makeText(requireActivity().getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();

                                Bundle bundle = new Bundle();
                                bundle.putString("sessionId", sessionId);

                                Navigation.findNavController(view).navigate(R.id.action_login_to_home, bundle);
                            }
                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {
                            Toast.makeText(requireActivity().getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_login_to_register);
            }
        });
    }

    private boolean validateUsername() {
        if (TextUtils.isEmpty(etEmail.getText().toString())) {
            etEmail.setError("username cannot be empty");
            etEmail.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validatePassword() {
        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError("password cannot be empty");
            etPassword.requestFocus();
            return false;
        }
        return true;
    }

}