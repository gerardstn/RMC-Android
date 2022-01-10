package avdinformatica.group1.rentmycar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;



public class SuccessfulLogin extends AppCompatActivity {

    TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_login);
        String email = getIntent().getStringExtra("email");
        tvEmail = findViewById(R.id.tvEmail);
        tvEmail.setText(getString(R.string.hello) + "  " + email);
    }
}