package com.example.harshendrashah.cryptocurrencies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class ConverterActivity extends AppCompatActivity implements View.OnClickListener{

    private ResideMenu resideMenu;
    private ResideMenuItem itemTrending;
    private ResideMenuItem itemNews;
    private ResideMenuItem itemAll;
    private ResideMenuItem itemConverter;

    ArrayList<String> spinnerFullNames = new ArrayList<>();
    ArrayList<String> spinnerCodes = new ArrayList<>();
    ArrayList<Integer> spinnerImages = new ArrayList<>();

    private boolean isUserInteracting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        TextView title = toolbar.findViewById(R.id.txt_title);

        title.setText("Converter");

        setupMenu();

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

        final CustomSpinner spinnerInput = findViewById(R.id.spinner_input);
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, spinnerFullNames, spinnerImages, spinnerCodes);
//        final CustomSpinner spinner =  findViewById(R.id.spinner);
        spinnerInput.setAdapter(spinnerAdapter);

        spinnerInput.setOnItemSelectedListener(
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

        spinnerInput.setSpinnerEventsListener(new CustomSpinner.OnSpinnerEventsListener() {
            public void onSpinnerOpened() {
                spinnerInput.setSelected(true);
            }
            public void onSpinnerClosed() {
                spinnerInput.setSelected(false);
            }
        });

        final CustomSpinner spinnerOutput = findViewById(R.id.spinner_output);
        SpinnerAdapter spinnerOutputAdapter = new SpinnerAdapter(this, spinnerFullNames, spinnerImages, spinnerCodes);
//        final CustomSpinner spinner =  findViewById(R.id.spinner);
        spinnerOutput.setAdapter(spinnerOutputAdapter);

        spinnerOutput.setOnItemSelectedListener(
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

        spinnerOutput.setSpinnerEventsListener(new CustomSpinner.OnSpinnerEventsListener() {
            public void onSpinnerOpened() {
                spinnerOutput.setSelected(true);
            }
            public void onSpinnerClosed() {
                spinnerOutput.setSelected(false);
            }
        });
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        isUserInteracting = true;
    }

    private void setupMenu() {

        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.gradient_background);
        resideMenu.attachToActivity(this);

        itemTrending = new ResideMenuItem(this, R.drawable.trending, "Trending");
        itemNews = new ResideMenuItem(this, R.drawable.news, "News");
        itemAll = new ResideMenuItem(this, R.drawable.home, "Major Cryptos");
        itemConverter = new ResideMenuItem(this, R.drawable.converter, "Converter");

        itemAll.setOnClickListener(this);
        itemNews.setOnClickListener(this);
        itemTrending.setOnClickListener(this);
        itemConverter.setOnClickListener(this);

        resideMenu.addMenuItem(itemTrending, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemNews, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemAll, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemConverter, ResideMenu.DIRECTION_LEFT);

        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.btn_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == itemAll){
            Intent i = new Intent(ConverterActivity.this, AllCryptosActivity.class);
            startActivity(i);
            Toast.makeText(this, "All Crptos", Toast.LENGTH_SHORT).show();
        }else if (view == itemTrending){
            Intent i = new Intent(ConverterActivity.this, MainActivity.class);
            startActivity(i);
            Toast.makeText(this, "Trending", Toast.LENGTH_SHORT).show();
        }else if (view == itemNews){
            Toast.makeText(this, "News", Toast.LENGTH_SHORT).show();
        }else if (view == itemConverter){
            Toast.makeText(this, "Converter", Toast.LENGTH_SHORT).show();
        }

        resideMenu.closeMenu();
    }

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
    }

}
