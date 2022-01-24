package avdinformatica.group1.rentmycar.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Calendar;
import java.util.Date;

import avdinformatica.group1.rentmycar.R;
import avdinformatica.group1.rentmycar.database.AppDatabase;
import avdinformatica.group1.rentmycar.models.Car;


public class ForRentDetailFragment extends Fragment {


    private static final String ARG_PARAM1 = "carId";

    private Long mCarId;
    Car car;
    TextView tvCarDetailsBrand, tvCarDetailsModel, tvCarDetailsDistance, tvCarDetailsPrice, tvCarDetailsType, tvCarDetailsFuel, tvCarDetailsUsage;

    EditText etCarReserveStartDate, etCarReserveEndDate;
    Date currentTime = Calendar.getInstance().getTime();


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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_for_rent_detail, container, false);

//        etCarReserveStartDate = view.findViewById(R.id.et_car_reserve_start_date);
//        etCarReserveEndDate = view.findViewById(R.id.et_car_reserve_end_date);

        tvCarDetailsBrand = view.findViewById(R.id.tv_car_details_brand);
        tvCarDetailsModel = view.findViewById(R.id.tv_car_details_model);
        tvCarDetailsDistance = view.findViewById(R.id.tv_car_details_distance);
        tvCarDetailsPrice = view.findViewById(R.id.tv_car_details_price_value);
        tvCarDetailsType = view.findViewById(R.id.tv_car_details_type);
        tvCarDetailsFuel = view.findViewById(R.id.tv_car_details_type_fuel);
        tvCarDetailsUsage = view.findViewById(R.id.tv_car_details_usage);

        tvCarDetailsBrand.setText(car.getBrand());
        tvCarDetailsModel.setText(car.getModel());
        tvCarDetailsDistance.setText(car.getPickupLocationCoordinates());
        tvCarDetailsPrice.setText(car.getPrice());
        tvCarDetailsType.setText(car.getCarType());
        tvCarDetailsFuel.setText(car.getFuelType());
        tvCarDetailsUsage.setText("" + car.getFuelUsage());

        return view;
    }


}
