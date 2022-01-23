package avdinformatica.group1.rentmycar.ui;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import avdinformatica.group1.rentmycar.R;
import avdinformatica.group1.rentmycar.database.AppDatabase;
import avdinformatica.group1.rentmycar.database.AppExecutors;
import avdinformatica.group1.rentmycar.models.User;
import avdinformatica.group1.rentmycar.utils.Helper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    User user;
    String sessionId;
    TextView tvEmail;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String sessionId) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("sessionId", sessionId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sessionId = getArguments().getString("sessionId");
        }

        AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplicationContext());
        user = appDatabase.userDao().getUser(sessionId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tvEmail = view.findViewById(R.id.tv_Email);
        tvEmail.setText(user.getEmail());
        return view;
    }

}