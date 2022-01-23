package avdinformatica.group1.rentmycar.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import avdinformatica.group1.rentmycar.R;
import avdinformatica.group1.rentmycar.adapters.AvailableCarRecyclerViewAdapter;
import avdinformatica.group1.rentmycar.models.CarResponse;

public class ForRentFragment extends Fragment implements AvailableCarRecyclerViewAdapter.ItemClickListener {

    AvailableCarRecyclerViewAdapter adapter;

    TextView tvCarBrand;
    TextView tvCarModel;
    TextView tvCarDistance;
    TextView tvCarPrice;

    ArrayList<CarResponse> carList;

    public ForRentFragment() {
        // Required empty public constructor
    }

    public static ForRentFragment newInstance(Serializable carList) {
        ForRentFragment fragment = new ForRentFragment();
        Bundle args = new Bundle();
        args.putSerializable("carList", carList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            carList = (ArrayList<CarResponse>) getArguments().getSerializable("carList");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_for_rent, container, false);

        ArrayList<CarResponse> carDetails = new ArrayList<CarResponse>();
        for (int i = 0; i < carList.size(); i++){
            carDetails.add(carList.get(i));
        }

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
        Toast.makeText(getActivity().getApplicationContext(), "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}