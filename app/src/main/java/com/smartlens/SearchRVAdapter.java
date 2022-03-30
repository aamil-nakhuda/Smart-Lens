package com.smartlens;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class SearchRVAdapter extends ArrayAdapter<SearchRVModel> {
    //Constructor
    public SearchRVAdapter(Activity context, ArrayList<SearchRVModel> searchRVModels) {
        super(context, 0, searchRVModels);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        //This is scrapView
        //Now we need to check if the scrapView is null then add a view from the custom list_item we made

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //get the current position from the  arrayList
        SearchRVModel currentSearch = getItem(position);

        //Bind all the textViews now by findViewById and set its text
        ShapeableImageView imageURL = listItemView.findViewById(R.id.image_url);
        Glide.with(getContext()).load(currentSearch.getImageUrl()).into(imageURL);

        TextView titleTextView = listItemView.findViewById(R.id.title_tv);
        titleTextView.setText(currentSearch.getTitle());

        TextView urlTextView = listItemView.findViewById(R.id.site_tv);
        urlTextView.setText(currentSearch.getLink());


        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(currentSearch.getLink()));
                getContext().startActivity(intent);
            }
        });

        return listItemView;
    }
}
