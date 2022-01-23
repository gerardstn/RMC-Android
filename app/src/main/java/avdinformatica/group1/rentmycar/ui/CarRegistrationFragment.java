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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarRegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarRegistrationFragment extends Fragment {

    EditText brand, model, licensePlate, registerLocation;
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
        return view;
    }


    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered() {
        if (isEmpty(brand)) {
            Toast t = Toast.makeText(getActivity().getApplicationContext(), "You must enter the car's brand to register!", Toast.LENGTH_SHORT);
            t.show();
        }

        if (isEmpty(model) == false) {
            model.setError("Your car's year of construction is required!");

            if (isEmpty(licensePlate) == false) {
                licensePlate.setError("Your car's licenseplate is required!");
            }

            if (!engineType.isChecked()) {
                engineType.setError("Your car's engine type is required!");
            }

            if (isEmpty(registerLocation) == false) {
                registerLocation.setError("Your car's average mileage is required!");
            }

        }


    }
}