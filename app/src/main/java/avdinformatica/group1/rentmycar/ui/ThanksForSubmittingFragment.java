package avdinformatica.group1.rentmycar.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import avdinformatica.group1.rentmycar.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThanksForSubmittingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThanksForSubmittingFragment extends Fragment {

    private static final String SESSION_ID = "sessionId";

    private String mSessionId;
    private Button btnBackHome;

    public ThanksForSubmittingFragment() {}

    public static ThanksForSubmittingFragment newInstance(String sessionId) {
        ThanksForSubmittingFragment fragment = new ThanksForSubmittingFragment();
        Bundle args = new Bundle();
        args.putString(SESSION_ID, sessionId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSessionId = getArguments().getString(SESSION_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thanks_for_submitting, container, false);

        btnBackHome = view.findViewById(R.id.btn_back_home);
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("sessionId", mSessionId);

                Navigation.findNavController(view)
                        .navigate(R.id.action_fragment_thanks_for_submission_to_fragment_home, bundle);
            }
        });

        return view;
    }
}