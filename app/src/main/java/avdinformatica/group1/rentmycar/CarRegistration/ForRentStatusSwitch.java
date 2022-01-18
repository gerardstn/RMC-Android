package avdinformatica.group1.rentmycar.CarRegistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import avdinformatica.group1.rentmycar.R;


public class ForRentStatusSwitch extends AppCompatActivity {

    Button btn = (Button) findViewById(R.id.open_activity_button);

btn.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
        startActivity(new Intent(CarOverview.this, ConfirmForRentStatusSwitch.class));
    }
    });
}
}