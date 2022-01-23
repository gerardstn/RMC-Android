package avdinformatica.group1.rentmycar.ui;

import static android.app.Activity.RESULT_CANCELED;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import avdinformatica.group1.rentmycar.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CameraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CameraFragment extends Fragment {

    public static final String EXTRA_INFO = "default";
    private static final int RESULT_OK = 1;
    Button btnCapture;
    ImageView imgCapture;
    private static final int Image_Capture_Code = 1;


    public CameraFragment() {
        // Required empty public constructor
    }

    public static CameraFragment newInstance() {
        CameraFragment fragment = new CameraFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera, container, false);

        btnCapture = view.findViewById(R.id.btn_take_picture);
        imgCapture = view.findViewById(R.id.iv_taken_picture);

        setOnClickListeners();

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == Image_Capture_Code) {
            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                imgCapture.setImageBitmap(bp);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
            }
        } 
    }

    private void setOnClickListeners()
    {
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cIntent, Image_Capture_Code);
            }
        });
    }
}