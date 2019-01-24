package com.example.harshendrashah.cryptocurrencies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;


public class NewsDetailsFragment extends Fragment {

    HashMap<String, String> hashMap;

    ImageView newsImage, backArrow;
    CircleImageView srcImage;
    TextView categories, srcName, published_on, title, body;

    public NewsDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            hashMap = (HashMap<String, String>) getArguments().getSerializable("HashMap");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_news_details, container, false);

        backArrow = rootView.findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        newsImage = rootView.findViewById(R.id.news_image);
        srcImage = rootView.findViewById(R.id.source_image);
        srcImage.bringToFront();
        categories= rootView.findViewById(R.id.categories);
        srcName = rootView.findViewById(R.id.source_name);
        published_on = rootView.findViewById(R.id.news_date);
        title = rootView.findViewById(R.id.news_ttl);
        body = rootView.findViewById(R.id.news_body);

        prepareNews();

        return rootView;
    }


    private void prepareNews() {
        Picasso.get().load(hashMap.get("imageUrl")).into(newsImage);
        Picasso.get().load(hashMap.get("sourceImgUrl")).into(srcImage);
        categories.setText(hashMap.get("categories"));
        srcName.setText(hashMap.get("source"));
        title.setText(hashMap.get("title"));
        body.setText(hashMap.get("body"));
        published_on.setText(getDate(Long.parseLong(hashMap.get("publishedOn"))));
    }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
