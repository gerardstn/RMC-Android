package avdinformatica.group1.rentmycar.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import avdinformatica.group1.rentmycar.R;

public class CarRegistrationActivity extends AppCompatActivity {

    EditText brand;
    EditText model;
    EditText year;
    EditText engineType;
    EditText mileage;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_registration);

//        brand = (EditText) findViewById(R.id.editText1);
//        model = (EditText) findViewById(R.id.editText2);
//        year = (EditText) findViewById(R.id.editText3);
//        engineType = (EditText) findViewById(R.id.editText4);
//        mileage = (EditText) findViewById(R.id.editText5);
        register = (Button) findViewById(R.id.btn_car_register);

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

        if (isEmpty(year) == false) {
            year.setError("Your car's year of construction is required!");
        }

        if (isEmpty(engineType) == false) {
            engineType.setError("Your car's engine type is required!");
        }

        if (isEmpty(mileage) == false) {
            mileage.setError("Your car's average mileage is required!");
        }


    }
}
