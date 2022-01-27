package avdinformatica.group1.rentmycar.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import avdinformatica.group1.rentmycar.R;
import avdinformatica.group1.rentmycar.models.CarResponse;
import avdinformatica.group1.rentmycar.services.GPSService;
import avdinformatica.group1.rentmycar.utils.Helper;

public class AvailableCarRecyclerViewAdapter extends RecyclerView.Adapter<AvailableCarRecyclerViewAdapter.ViewHolder>{

    private final ArrayList<CarResponse> mData;
    private final LayoutInflater mInflater;
    private final GPSService mGPS;
    private ItemClickListener mClickListener;
    private Context mContext;


    // data is passed into the constructor
    public AvailableCarRecyclerViewAdapter(Context context, ArrayList<CarResponse> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mContext = context;
        this.mGPS = new GPSService(context);
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.singlecar_listview_layout, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CarResponse carModel = mData.get(position);
        holder.tvCarModel.setText(carModel.getModel());
        holder.tvCarBrand.setText(carModel.getBrand());
        holder.tvCarPrice.setText(carModel.getPrice());

        holder.tvCarDistance.setText(Double.toString(
                Helper.calculateDistance(
                    Helper.getLat(carModel.getDistance()),
                    Helper.getLong(carModel.getDistance()),
                        mGPS.getLatitude(),
                        mGPS.getLongitude()
                )));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvCarModel;
        TextView tvCarBrand;
        TextView tvCarDistance;
        TextView tvCarPrice;

        ViewHolder(View itemView) {
            super(itemView);
            tvCarModel = itemView.findViewById(R.id.tv_car_model);
            tvCarBrand = itemView.findViewById(R.id.tv_car_brand);
            tvCarPrice = itemView.findViewById(R.id.tv_car_price_value);
            itemView.setOnClickListener(this);

            tvCarDistance = itemView.findViewById(R.id.tv_car_distance);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public CarResponse getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}