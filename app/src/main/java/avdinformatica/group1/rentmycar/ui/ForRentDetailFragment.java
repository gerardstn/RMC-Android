package avdinformatica.group1.rentmycar.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import avdinformatica.group1.rentmycar.R;
import avdinformatica.group1.rentmycar.database.AppDatabase;
import avdinformatica.group1.rentmycar.models.Car;
import avdinformatica.group1.rentmycar.models.RegisterResponse;
import avdinformatica.group1.rentmycar.models.ReservationResponse;
import avdinformatica.group1.rentmycar.models.User;
import avdinformatica.group1.rentmycar.models.UserResponse;
import avdinformatica.group1.rentmycar.network.Network;
import avdinformatica.group1.rentmycar.services.ApiService;
import avdinformatica.group1.rentmycar.services.GPSService;
import avdinformatica.group1.rentmycar.utils.Helper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@RequiresApi(api = Build.VERSION_CODES.O)
public class ForRentDetailFragment extends Fragment {


    private static final String ARG_PARAM1 = "carId";
    private GPSService mGPS;
    private Long mCarId;
    private Button btnReserveCar;
    String sessionId;
    User user;
    Car car;
    TextView tvCarDetailsBrand, tvCarDetailsModel, tvCarDetailsDistance, tvCarDetailsPrice, tvCarDetailsType, tvCarDetailsFuel, tvCarDetailsUsage;
    EditText etCarReserveStartDate, etCarReserveEndDate;
    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Amsterdam"));

    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH) + 1;
    int day = cal.get(Calendar.DAY_OF_MONTH);
    int hour = cal.get(Calendar.HOUR_OF_DAY);
    int minute = cal.get(Calendar.MINUTE);


    public ForRentDetailFragment() {
        // Required empty public constructor

    }


    public static ForRentDetailFragment newInstance(Long param1) {
        ForRentDetailFragment fragment = new ForRentDetailFragment();
        Bundle args = new Bundle();
        args.putLong("carId", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplicationContext());

        if (getArguments() != null) {
            mCarId = getArguments().getLong(ARG_PARAM1);
            car = appDatabase.carDao().getCar(mCarId);
            user = appDatabase.userDao().getUser(getArguments().getString("sessionId"));
        }


    }
    private void onClickListeners() {
        btnReserveCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    ReservationResponse reservationResponse = new ReservationResponse(car.getCarId(), 4l , etCarReserveStartDate.getText().toString(), etCarReserveEndDate.getText().toString());

                    ApiService apiService = Network.getInstance().create(ApiService.class);
                    apiService.addReservation(reservationResponse).enqueue(new Callback<ReservationResponse>() {
                        @Override
                        public void onResponse(Call<ReservationResponse> call, Response<ReservationResponse> response) {
                            if (response.body() != null) {
                                Toast.makeText(getActivity().getApplicationContext(), "Reservation successful", Toast.LENGTH_SHORT).show();
                                HomeFragment fragment = new HomeFragment();
                                Navigation.findNavController(v).navigate(R.id.fragment_thanks_for_submission);
                            } else {
                                Toast.makeText(getActivity().getApplicationContext(), "something went wrong! please try again", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ReservationResponse> call, Throwable t) {

                        }
                    });


            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mGPS = new GPSService(getActivity().getApplicationContext());
        View view = inflater.inflate(R.layout.fragment_for_rent_detail, container, false);

        etCarReserveStartDate = view.findViewById(R.id.et_car_reserve_start_date);
        etCarReserveEndDate = view.findViewById(R.id.et_car_reserve_end_date);
        etCarReserveStartDate.setText(year +"-0"+ month+"-"+ (day + 1)+" "+hour+":"+minute+":00");
        etCarReserveEndDate.setText(year +"-0"+ month+"-"+ (day + 2)+" "+hour+":"+minute+":00");

        tvCarDetailsBrand = view.findViewById(R.id.tv_car_details_brand);
        tvCarDetailsModel = view.findViewById(R.id.tv_car_details_model);
        tvCarDetailsDistance = view.findViewById(R.id.tv_car_details_distance);
        tvCarDetailsPrice = view.findViewById(R.id.tv_car_details_price_value);
        tvCarDetailsType = view.findViewById(R.id.tv_car_details_type);
        tvCarDetailsFuel = view.findViewById(R.id.tv_car_details_type_fuel);
        tvCarDetailsUsage = view.findViewById(R.id.tv_car_details_usage);
        tvCarDetailsBrand.setText(car.getBrand());
        tvCarDetailsModel.setText(car.getModel());
        tvCarDetailsDistance.setText(Double.toString(
                Helper.calculateDistance(
                        Helper.getLat(car.getPickupLocationCoordinates()),
                        Helper.getLong(car.getPickupLocationCoordinates()),
                        mGPS.getLatitude(),
                        mGPS.getLongitude()
                )));

        if (car.getCarType().equals("IceCar")) {
            tvCarDetailsType.setText("Brandstof motor");
            tvCarDetailsFuel.setText(car.getFuelType());
            tvCarDetailsUsage.setText(car.getFuelUsage() + "l op 100km");
        } else if (car.getCarType().equals("BevCar")) {
            tvCarDetailsType.setText("Elektrisch");
            tvCarDetailsFuel.setText(car.getFuelType());
            tvCarDetailsUsage.setText(car.getBatteryUsage() + "KWH op 100km");
        } else if (car.getCarType().equals("FcevCar")) {
            tvCarDetailsType.setText("Waterstof");
            tvCarDetailsFuel.setText(car.getFuelType());
            tvCarDetailsUsage.setText(car.getHydrogenUsage() + "kg op 100km");
        }
        tvCarDetailsPrice.setText(car.getPrice());
        initializeListeners(view);
        onClickListeners();
        return view;
    }
    private void initializeListeners(View view) {
        etCarReserveStartDate = view.findViewById(R.id.et_car_reserve_start_date);
        etCarReserveEndDate = view.findViewById(R.id.et_car_reserve_end_date);
        btnReserveCar = view.findViewById(R.id.btn_car_details_reserve);
    }

}
