package com.example.harshendrashah.cryptocurrencies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class ConverterActivity extends AppCompatActivity {

    ArrayList<String> spinnerFullNames = new ArrayList<>();
    ArrayList<String> spinnerCodes = new ArrayList<>();
    ArrayList<Integer> spinnerImages = new ArrayList<>();

    private boolean isUserInteracting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        String[] data = {"Arial", "Calibri", "Helvetica", "Roboto", "Veranda"};

        spinnerFullNames.add("Hello");
        spinnerCodes.add("Harshendra");
        spinnerImages.add(R.color.blue);
        spinnerFullNames.add("Hello");
        spinnerCodes.add("Harshendra");
        spinnerImages.add(R.color.blue);
        spinnerFullNames.add("Hello");
        spinnerCodes.add("Harshendra");
        spinnerImages.add(R.color.blue);
        spinnerFullNames.add("Hello");
        spinnerCodes.add("Harshendra");
        spinnerImages.add(R.color.blue);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_selected, data);
//        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        final CustomSpinner spinner = findViewById(R.id.spinner);
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, spinnerFullNames, spinnerImages, spinnerCodes);
//        final CustomSpinner spinner =  findViewById(R.id.spinner);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (isUserInteracting) {
                            Toast.makeText(ConverterActivity.this, spinnerFullNames.get(i), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );

        spinner.setSpinnerEventsListener(new CustomSpinner.OnSpinnerEventsListener() {
            public void onSpinnerOpened() {
                spinner.setSelected(true);
            }
            public void onSpinnerClosed() {
                spinner.setSelected(false);
            }
        });
    }
    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        isUserInteracting = true;
    }
}
