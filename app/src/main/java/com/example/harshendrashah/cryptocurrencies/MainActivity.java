package com.example.harshendrashah.cryptocurrencies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private CurrencyAdapter adapter;
    private List<Currency> currencyList;

    private ResideMenu resideMenu;
    private MainActivity mContext;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemTrending;
    private ResideMenuItem itemNews;
    private ResideMenuItem itemConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        setupMenu();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        currencyList = new ArrayList<>();
        adapter = new CurrencyAdapter(this, currencyList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareCurrencies();

    }

    private void setupMenu() {

        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.ic_launcher_background);
        resideMenu.attachToActivity(this);

        itemHome = new ResideMenuItem(this, R.mipmap.ic_launcher_round, "Home");
        itemNews = new ResideMenuItem(this, R.mipmap.ic_launcher_round, "News");
        itemTrending = new ResideMenuItem(this, R.mipmap.ic_launcher_round, "Trending");
        itemConverter = new ResideMenuItem(this, R.mipmap.ic_launcher_round, "Converter");

        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemNews, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemTrending, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemConverter, ResideMenu.DIRECTION_LEFT);

        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.btn_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
    }

    private void prepareCurrencies() {
        Currency a = new Currency("a","a", "a", "a", "a", "" );
        currencyList.add(a);
        a = new Currency("a","a", "a", "a", "a", "" );
        currencyList.add(a);
        a = new Currency("a","a", "a", "a", "a", "" );
        currencyList.add(a);
        a = new Currency("a","a", "a", "a", "a", "" );
        currencyList.add(a);
        a = new Currency("a","a", "a", "a", "a", "" );
        currencyList.add(a);
        a = new Currency("a","a", "a", "a", "a", "" );
        currencyList.add(a);
        a = new Currency("a","a", "a", "a", "a", "" );
        currencyList.add(a);
        a = new Currency("a","a", "a", "a", "a", "" );
        currencyList.add(a);
        a = new Currency("a","a", "a", "a", "a", "" );
        currencyList.add(a);
        a = new Currency("a","a", "a", "a", "a", "" );
        currencyList.add(a);

        adapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View view) {
        if (view == itemHome){
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
        }else if (view == itemTrending){
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
