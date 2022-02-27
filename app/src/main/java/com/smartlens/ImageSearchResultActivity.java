package com.smartlens;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabeler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ImageSearchResultActivity extends AppCompatActivity {
    ArrayList<SearchRVModel> searchRVModelArrayList;
    SearchRVAdapter searchRVAdapter;
    ImageView backBtn, loadingIV;
    TextView activityName, loadingTV;
    private String imageURL, title, link;
    final Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_search_result);

        backBtn = findViewById(R.id.back_button);
        backBtn.setVisibility(View.VISIBLE);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        Bitmap bitmap = intent.getParcelableExtra("BitmapImage");
//        FirebaseApp.initializeApp(this);


        //Initializing ArrayList+ArrayAdapter+ListView
        searchRVModelArrayList = new ArrayList<>();

        searchRVAdapter = new SearchRVAdapter(this, searchRVModelArrayList);

        GridView objResultsListView = findViewById(R.id.results_gridView);
        objResultsListView.setAdapter(searchRVAdapter);

        activityName = findViewById(R.id.activity_name);
        loadingIV = findViewById(R.id.progress_bar_img);
        loadingTV = findViewById(R.id.textview_loading);

        activityName.setText("Results");
        loadingIV.setVisibility(View.VISIBLE);
        loadingTV.setVisibility(View.VISIBLE);

        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);

        FirebaseVisionImageLabeler labeler = FirebaseVision.getInstance().getOnDeviceImageLabeler();

        labeler.processImage(image).addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionImageLabel>>() {
            @Override
            public void onSuccess(@NonNull List<FirebaseVisionImageLabel> firebaseVisionImageLabels) {
                String searchQuery = firebaseVisionImageLabels.get(0).getText();
                searchData(searchQuery);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // displaying error message.
                Toast.makeText(ImageSearchResultActivity.this, "Fail to detect image..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchData(String searchQuery) {
        String apiKey = "a55ae9365e2bd1fbafef5c2b53bdaf34fe39e7a78271ae35ea204b569e80bbf7";
        String url = "https://serpapi.com/search.json?engine=google&q=" + searchQuery.trim() + "&location=Mumbai,India&hl=en&gl=us&google_domain=google.co.in&gl=in&hl=hi&safe=active&tbm=isch&device=mobile&api_key=" + apiKey;
        //https://serpapi.com/search.json?engine=google&q=Coffee&location=Mumbai%2C+Maharashtra%2C+India&google_domain=google.co.in&gl=in&hl=hi&safe=active&tbm=isch&device=mobile&api_key=a55ae9365e2bd1fbafef5c2b53bdaf34fe39e7a78271ae35ea204b569e80bbf7

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(ImageSearchResultActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    loadingIV.setVisibility(View.GONE);
                    loadingTV.setVisibility(View.GONE);
                    // on below line we are extracting data from our json.
                    JSONArray organicResultsArray = response.getJSONArray("images_results");
                    for (int i = 0; i < organicResultsArray.length(); i++) {
                        JSONObject organicObj = organicResultsArray.getJSONObject(i);

                        if (organicObj.has("thumbnail")) {
                            imageURL = organicObj.getString("thumbnail");
                        }
                        if (organicObj.has("title")) {
                            title = organicObj.getString("title");
                        }
                        if (organicObj.has("link")) {
                            link = organicObj.getString("link");
                        }
                        // on below line we are adding data to our array list.
                        searchRVModelArrayList.add(new SearchRVModel(imageURL, title, link));
                    }
                    // notifying our adapter class
                    // on data change in array list.
                    searchRVAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // displaying error message.
                int[] placeHolderImages = {R.drawable.error,
                        R.drawable.no_internet, R.drawable.no_internet1
                        , R.drawable.no_internet2,
                        R.drawable.no_in1,
                        R.drawable.not_found, R.drawable.not_found1};
                Runnable runnable = new Runnable() {
                    int i = 0;

                    @Override
                    public void run() {
                        loadingIV.setImageResource(placeHolderImages[i]);
                        i++;
                        if (i >= placeHolderImages.length) {
                            i = 0;
                        }
                        handler.postDelayed(this, 3000);
                    }
                };
                handler.postDelayed(runnable, 0);
                loadingTV.setText("No Results found or No Internet connection!");
//                Toast.makeText(ImageSearchResultActivity.this, "No Result found for the search query..", Toast.LENGTH_SHORT).show();
            }
        });
        // adding json object request to our queue.
        queue.add(jsonObjectRequest);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchRVModelArrayList.clear();
        searchRVAdapter.notifyDataSetChanged();
    }
}