package com.smartlens.fragments;

import static android.Manifest.permission.CAMERA;
import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.smartlens.ImageSearchResultActivity;
import com.smartlens.R;

public class ImageSearchFragment extends Fragment {

    private static final byte REQUEST_IMAGE_CAPTURE = 10;
    private ImageView clickedImg;
    private MaterialButton clickBtn, getResultsBtn;
    private TextView actionBarTv;
    private Bitmap bitmap;
    final Handler handler = new Handler();

    public ImageSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image_search, container, false);

        clickedImg = view.findViewById(R.id.placeholder_image);
        clickBtn = view.findViewById(R.id.photo_btn);
        getResultsBtn = view.findViewById(R.id.fetch_resultsbtn);
        actionBarTv = view.findViewById(R.id.activity_name);
        actionBarTv.setText("Image Search");


        int[] placeHolderImages = {R.drawable.img_search, R.drawable.add_img, R.drawable.img_search2
                , R.drawable.img_search3, R.drawable.img_search4};
        Runnable runnable = new Runnable() {
            int i = 0;

            @Override
            public void run() {
                clickedImg.setImageResource(placeHolderImages[i]);
                i++;
                if (i >= placeHolderImages.length) {
                    i = 0;
                }
                handler.postDelayed(this, 3000);
            }
        };
        handler.postDelayed(runnable, 0);

        getResultsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultsIntent = new Intent(getContext(), ImageSearchResultActivity.class);
                //this is required in next activity don't forget
                resultsIntent.putExtra("BitmapImage", bitmap);

                startActivity(resultsIntent);
            }
        });

        //ClickPic Btn
        clickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermissions()) {
                    clickImage();
                } else {
                    requestPermissions();
                }

            }
        });


        return view;
    }

    private boolean checkPermissions() {
        int cameraPermission = ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), CAMERA);
        return cameraPermission == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        int PERMISSION_CODE = 200;
        requestPermissions(new String[]{
                CAMERA
        }, PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            boolean cameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            if (cameraPermission) {
                Toast.makeText(getActivity(), "Permission Granted!", Toast.LENGTH_SHORT).show();
                clickImage();
            } else {
                Toast.makeText(getActivity(), "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void clickImage() {
        Intent takeImageFromObjSearch = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takeImageFromObjSearch.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takeImageFromObjSearch, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            handler.removeCallbacksAndMessages(null);
            clickedImg.setImageBitmap(bitmap);
            getResultsBtn.setVisibility(View.VISIBLE);
        }
    }
}