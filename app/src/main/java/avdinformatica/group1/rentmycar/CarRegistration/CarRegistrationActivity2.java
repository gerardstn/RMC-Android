package avdinformatica.group1.rentmycar.CarRegistration;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
<<<<<<< Updated upstream:app/src/main/java/avdinformatica/group1/rentmycar/CarRegistration/CarRegistrationActivity2.java
import android.widget.TextView;
=======
import android.widget.RadioButton;
>>>>>>> Stashed changes:app/src/main/java/avdinformatica/group1/rentmycar/activities/CarRegistrationActivity.java
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import avdinformatica.group1.rentmycar.R;
import avdinformatica.group1.rentmycar.remote.ApiService;
import avdinformatica.group1.rentmycar.remote.Network;
import avdinformatica.group1.rentmycar.responseClasses.ResponseClass;
import avdinformatica.group1.rentmycar.responseClasses.ResponseRegisterClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarRegistrationActivity2 extends AppCompatActivity {

    EditText brand;
    EditText model;
    EditText licensePlate;
    RadioButton engineType;
    EditText registerLocation;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< Updated upstream:app/src/main/java/avdinformatica/group1/rentmycar/CarRegistration/CarRegistrationActivity2.java
        setContentView(R.layout.car_registration);

        final EditText brand = (EditText) findViewById(R.id.editText1);
        final EditText model = (EditText) findViewById(R.id.editText2);
        final EditText year = (EditText) findViewById(R.id.editText3);
        final EditText engineType = (EditText) findViewById(R.id.editText4);
        final EditText mileage = (EditText) findViewById(R.id.editText5);
=======
        setContentView(R.layout.activity_car_registration);

        brand = (EditText) findViewById(R.id.et_car_register_brand);
        model = (EditText) findViewById(R.id.et_car_register_model);
        licensePlate = (EditText) findViewById(R.id.et_car_register_licence_plate);
        engineType = (RadioButton) findViewById(R.id.rb_car_register_bev);
        engineType = (RadioButton) findViewById(R.id.rb_car_register_ice);
        engineType = (RadioButton) findViewById(R.id.rb_car_register_fcev);
        registerLocation = (EditText) findViewById(R.id.et_car_register_location);
        register = (Button) findViewById(R.id.btn_car_register);
>>>>>>> Stashed changes:app/src/main/java/avdinformatica/group1/rentmycar/activities/CarRegistrationActivity.java

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();
            }
        });
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered() {
        if (isEmpty(brand)) {
            Toast t = Toast.makeText(this, "You must enter the car's brand to register!", Toast.LENGTH_SHORT);
            t.show();
        }

        if (isEmpty(model)) {
            model.setError("Car model is required!");
        }

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
