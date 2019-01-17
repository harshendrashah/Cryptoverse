package com.example.harshendrashah.cryptocurrencies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class CurrencyDetailsFragment extends Fragment {

    String currencyName;
    public CurrencyDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            currencyName = getArguments().getString("name");
            Toast.makeText(getContext(), "Hello " +currencyName, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_currency_details, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
