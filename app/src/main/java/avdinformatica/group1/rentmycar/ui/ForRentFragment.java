package avdinformatica.group1.rentmycar.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

import avdinformatica.group1.rentmycar.R;
import avdinformatica.group1.rentmycar.adapters.AvailableCarRecyclerViewAdapter;
import avdinformatica.group1.rentmycar.database.AppDatabase;
import avdinformatica.group1.rentmycar.database.AppExecutors;
import avdinformatica.group1.rentmycar.models.Car;
import avdinformatica.group1.rentmycar.models.CarResponse;

public class ForRentFragment extends Fragment implements AvailableCarRecyclerViewAdapter.ItemClickListener {

    private static final String SESSION_ID = "sessionId";
    private String mSessionId;

    AvailableCarRecyclerViewAdapter adapter;
    TextView tvCarBrand, tvCarModel, tvCarDistance, tvCarPrice;
    ArrayList<CarResponse> carList;

    public ForRentFragment() {
        // Required empty public constructor
    }

    public static ForRentFragment newInstance(Serializable carList, String sessionId) {
        ForRentFragment fragment = new ForRentFragment();
        Bundle args = new Bundle();
        args.putSerializable("carList", carList);
        args.putString(SESSION_ID, sessionId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSessionId = getArguments().getString(SESSION_ID);
            Log.d("user", "onCreate: mSessionId = " + mSessionId);
            carList = (ArrayList<CarResponse>) getArguments().getSerializable("carList");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_for_rent, container, false);

        ArrayList<CarResponse> carDetails = new ArrayList<CarResponse>(carList);

        AppExecutors.getInstance().diskIO().execute(new CarImporter(carList, requireActivity().getApplicationContext()));


        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.rv_available_cars);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        adapter = new AvailableCarRecyclerViewAdapter(getActivity().getApplicationContext(), carDetails);
        adapter.setClickListener(this::onItemClick);
        recyclerView.setAdapter(adapter);

        tvCarBrand = view.findViewById(R.id.tv_car_brand);
        tvCarModel = view.findViewById(R.id.tv_car_model);
        tvCarDistance = view.findViewById(R.id.tv_car_distance);
        tvCarPrice = view.findViewById(R.id.tv_car_price_value);

        return view;
    }

    @Override
    public void onItemClick(View view, int position) {

        Bundle bundle = new Bundle();
        bundle.putString(SESSION_ID, mSessionId);
        bundle.putLong("carId", adapter.getItem(position).getCarId());

        Navigation.findNavController(view).navigate(R.id.action_fragment_for_rent_to_fragment_for_rent_detail, bundle);

        Toast.makeText(requireActivity().getApplicationContext(), "You clicked " + adapter.getItem(position).getCarId() + " on row number " + position, Toast.LENGTH_SHORT).show();
    }


    class CarImporter implements Runnable {

        private final ArrayList<CarResponse> carList;
        private final AppDatabase appDatabase;

        public CarImporter(ArrayList<CarResponse> carList, Context applicationContext) {
            this.appDatabase = AppDatabase.getInstance(applicationContext);
            this.carList = carList;
        }

        @Override
        public void run() {
            for (int i = 0; i < carList.size(); i++) {
                Car car = carList.get(i).toCar();

                if (appDatabase.carDao().getCar(car.getCarId()) != null) {
                    appDatabase.carDao().updateCar(car);
                } else {
                    appDatabase.carDao().insertCar(car);
                }
            }
        }
    }


}