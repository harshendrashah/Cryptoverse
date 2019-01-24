package com.example.harshendrashah.cryptocurrencies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.HashMap;


public class NewsDetailsFragment extends Fragment {

    HashMap<String, String> hashMap;

    public NewsDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            hashMap = (HashMap<String, String>) getArguments().getSerializable("HashMap");
            Toast.makeText(getContext(), hashMap.get("id"), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_details, container, false);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

}
