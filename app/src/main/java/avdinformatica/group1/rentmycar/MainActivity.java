package avdinformatica.group1.rentmycar;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;

import avdinformatica.group1.rentmycar.services.GPSService;
import avdinformatica.group1.rentmycar.utils.PermissionHandler;

public class MainActivity extends AppCompatActivity {

    public AppBarConfiguration appBarConfiguration;
    public NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionHandler.permissionCheck(MainActivity.this);
        setContentView(R.layout.activity_main);

        GPSService mGPS = new GPSService(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.rmclogo);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //Title bar back press triggers onBackPressed()
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
//        // Create the object of
//        // AlertDialog Builder class
//        AlertDialog.Builder builder
//                = new AlertDialog
//                .Builder(MainActivity.this);
//
//        // Set the message show for the Alert time
//        builder.setMessage("Do you want to exit ?");
//
//        // Set Alert Title
//        builder.setTitle("Alert !");
//
//        // Set Cancelable false
//        // for when the user clicks on the outside
//        // the Dialog Box then it will remain show
//        builder.setCancelable(false);
//
//        // Set the positive button with yes name
//        // OnClickListener method is use of
//        // DialogInterface interface.
//
//        builder
//                .setPositiveButton(
//                        "Yes",
//                        new DialogInterface
//                                .OnClickListener() {
//
//                            @Override
//                            public void onClick(DialogInterface dialog,
//                                                int which) {
//
//                                // When the user click yes button
//                                // then app will close
//                                finish();
//                            }
//                        });
//
//        // Set the Negative button with No name
//        // OnClickListener method is use
//        // of DialogInterface interface.
//        builder
//                .setNegativeButton(
//                        "No",
//                        new DialogInterface
//                                .OnClickListener() {
//
//                            @Override
//                            public void onClick(DialogInterface dialog,
//                                                int which) {
//
//                                // If user click no
//                                // then dialog box is canceled.
//                                dialog.cancel();
//                            }
//                        });
//
//        // Create the Alert dialog
//        AlertDialog alertDialog = builder.create();
//
//        // Show the Alert Dialog box
//        alertDialog.show();

    }
}