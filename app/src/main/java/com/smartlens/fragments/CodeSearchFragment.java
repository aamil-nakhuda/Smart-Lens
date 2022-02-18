package com.smartlens.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.vision.CameraSource;
import com.smartlens.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class CodeSearchFragment extends Fragment {

    private SurfaceView surfaceView;

    private TextView textView;

    private CameraSource cameraSource;

    private static final int PERMISSION = 100;


    public CodeSearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_code_search, container, false);


//        surfaceView = root.findViewById(R.id.comera);
//        textView = root.findViewById(R.id.text);

//        startCameraSource();
        return root;
    }
}

//    private void startCameraSource() {
//
//        final TextRecognizer textRecognizer =
//                new TextRecognizer.Builder(getContext()).build();
//
//        if (!textRecognizer.isOperational()) {
//
//            Log.e("CodeSearchFragment", "Dependencies not loaded yet");
//
//        } else {
//
//            cameraSource = new CameraSource.Builder(getActivity().getApplicationContext(), textRecognizer)
//            setFacing(CameraSource.CAMERA FACING BACK).setRequested PreviewSize(1280, 1:1024) .
//            setAutofocusEnabled(true).setRequestedfps(2.0f).build();
//            return root;
//        }
//    }