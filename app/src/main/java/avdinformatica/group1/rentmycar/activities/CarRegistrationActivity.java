package avdinformatica.group1.rentmycar.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import avdinformatica.group1.rentmycar.R;

public class CarRegistrationActivity extends AppCompatActivity {

    EditText brand;
    EditText model;
    RadioButton engineType;
    EditText licensePlate;
    EditText registerLocation;
    Button btn_car_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_registration);

        brand = (EditText) findViewById(R.id.et_car_register_brand);
        model = (EditText) findViewById(R.id.et_car_register_model);
        licensePlate = (EditText) findViewById(R.id.et_car_register_licence_plate);
        engineType = (RadioButton) findViewById(R.id.rb_car_register_bev);
        engineType = (RadioButton) findViewById(R.id.rb_car_register_ice);
        engineType = (RadioButton) findViewById(R.id.rb_car_register_fcev);
        registerLocation = (EditText) findViewById(R.id.et_car_register_location);
        btn_car_register = (Button) findViewById(R.id.btn_car_register);

        btn_car_register.setOnClickListener(new View.OnClickListener() {
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
