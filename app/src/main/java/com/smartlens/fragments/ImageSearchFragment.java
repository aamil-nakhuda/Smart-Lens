package com.smartlens.fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import com.smartlens.ImageSearchResultActivity;
import com.smartlens.R;

public class ImageSearchFragment extends Fragment {

    private static final byte REQUEST_IMAGE_CAPTURE = 10;
    private ImageView clickedImg;
    private Button clickBtn, getResultsBtn;
    private ProgressBar progressBar;
    private Bitmap bitmap;

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
        progressBar = view.findViewById(R.id.progress_bar1);

        getResultsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultsIntent = new Intent(getContext(), ImageSearchResultActivity.class);
                resultsIntent.putExtra("BitmapImage", bitmap);
                startActivity(resultsIntent);
            }
        });

        //ClickPic Btn
        clickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takeImageFromObjSearch = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takeImageFromObjSearch.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(takeImageFromObjSearch, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            clickedImg.setImageBitmap(bitmap);
            getResultsBtn.setVisibility(View.VISIBLE);
        }
    }
}