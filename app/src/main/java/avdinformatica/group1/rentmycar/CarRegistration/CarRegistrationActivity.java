package avdinformatica.group1.rentmycar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import avdinformatica.group1.rentmycar.remote.ApiService;
import avdinformatica.group1.rentmycar.remote.Network;
import avdinformatica.group1.rentmycar.responseClasses.ResponseClass;
import avdinformatica.group1.rentmycar.responseClasses.ResponseRegisterClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarRegistrationActivity extends AppCompatActivity {

    EditText etbrand, etmodel, etyear, etengineType, etmileage;
    TextView tvRegisterCar;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_registration);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btnSubmit = (Button) findViewById(R.id.button_submit);
        Button btnSrc = (Button) findViewById(R.id.buttonSrc);
        final EditText brand = (EditText) findViewById(R.id.editText1);
        final EditText model = (EditText) findViewById(R.id.editText2);
        final EditText year = (EditText) findViewById(R.id.editText3);
        final EditText engineType = (EditText) findViewById(R.id.editText4);
        final EditText mileage = (EditText) findViewById(R.id.editText5);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sender = new Intent(Form.this, com.chalkstreet.learnandroid.main.Display.class);
                Bundle b1 = new Bundle();
                b1.putString("brand", brand.getText().toString());
                b1.putString("model", model.getText().toString());
                b1.putString("year", year.getText().toString());
                b1.putString("engineType", year.getText().toString());
                b1.putString("mileage", year.getText().toString());
                sender.putExtras(b1);
                startActivity(sender);
                Form.this.finish();
            }
        });

        btnSrc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(Form.this, MainSource.class);
                startActivity(j);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            Form.this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);


    }
}

