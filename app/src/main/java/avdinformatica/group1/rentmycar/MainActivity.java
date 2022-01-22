package avdinformatica.group1.rentmycar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import avdinformatica.group1.rentmycar.utils.PermissionHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PermissionHandler.permissionCheck(MainActivity.this);
        setContentView(R.layout.activity_main);
    }
}