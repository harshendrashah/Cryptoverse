package com.example.harshendrashah.cryptocurrencies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    String BASE_URL = "https://min-api.cryptocompare.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        TextView title = toolbar.findViewById(R.id.txt_title);
        title.setText("News");
        recyclerView = findViewById(R.id.recycler_view);

        setupMenu();

        newsList = new ArrayList<>();

        prepareNews();

        adapter = new NewsAdapter(this, newsList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    private void prepareNews() {

        String url = BASE_URL + "/data/v2/news/?lang=EN";

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray js = response.getJSONArray("Data");
                    for (int i = 0; i < js.length(); i++) {
                        JSONObject display = js.getJSONObject(i);
                        News n = new News(display.getString("id"),
                                display.getString("title"),
                                display.getJSONObject("source_info").getString("name"),
                                display.getString("imageurl"),
                                display.getString("categories"),
                                display.getJSONObject("source_info").getString("img"),
                                display.getString("body"),
                                display.getLong("published_on"),
                                display.getString("url"));
                        newsList.add(n);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("******", "Error");
            }
        });

        queue.add(jsObjRequest);

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
        resideMenu.closeMenu();
    }


    // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
    }
}
