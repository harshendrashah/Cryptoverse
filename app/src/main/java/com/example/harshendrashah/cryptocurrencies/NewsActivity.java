package com.example.harshendrashah.cryptocurrencies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private List<News> newsList;

    private ResideMenu resideMenu;
    private ResideMenuItem itemTrending;
    private ResideMenuItem itemNews;
    private ResideMenuItem itemAll;
    private ResideMenuItem itemConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);

        setupMenu();

        newsList = new ArrayList<>();
        News n = new News("Hello there, This is a news Title","Times of India",
                "https://images.cryptocompare.com/news/cryptoglobe/f31UhdFM888.png");
        newsList.add(n);
        newsList.add(n);
        newsList.add(n);
        newsList.add(n);

        adapter = new NewsAdapter(this, newsList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

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

        //resideMenu.closeMenu();
    }


    @Override
    public void onClick(View view) {
        if (view == itemAll) {
            Intent i = new Intent(NewsActivity.this, AllCryptosActivity.class);
            startActivity(i);
            Toast.makeText(this, "All Cryptos", Toast.LENGTH_SHORT).show();
        } else if (view == itemTrending) {
            Intent i = new Intent(NewsActivity.this, MainActivity.class);
            startActivity(i);
            Toast.makeText(this, "Trending", Toast.LENGTH_SHORT).show();
        } else if (view == itemNews) {
            Toast.makeText(this, "News", Toast.LENGTH_SHORT).show();
        } else if (view == itemConverter) {
            Intent i = new Intent(NewsActivity.this, ConverterActivity.class);
            startActivity(i);
            Toast.makeText(this, "Converter", Toast.LENGTH_SHORT).show();
        }
    }


    // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
    }
}
