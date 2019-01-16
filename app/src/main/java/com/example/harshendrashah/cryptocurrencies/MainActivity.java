package com.example.harshendrashah.cryptocurrencies;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CurrencyAdapter mAdapter;

    private RecyclerView recyclerView;
    private CurrencyAdapter adapter;
    private List<Currency> currencyList;

    private ResideMenu resideMenu;
    private MainActivity mContext;
    private ResideMenuItem itemDashboard;
    private ResideMenuItem itemTrending;
    private ResideMenuItem itemNews;
    private ResideMenuItem itemConverter;


    ArrayList<String> names = new ArrayList<>();
    String BASE_URL = "https://min-api.cryptocompare.com";
    String IMAGE_URL= "https://www.cryptocompare.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        setupMenu();

        recyclerView = findViewById(R.id.recycler_view);

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
        resideMenu.setBackground(R.drawable.gradient_background);
        resideMenu.attachToActivity(this);

        itemDashboard = new ResideMenuItem(this, R.drawable.home, "Dashboard");
        itemNews = new ResideMenuItem(this, R.drawable.news, "News");
        itemTrending = new ResideMenuItem(this, R.drawable.trending, "Trending");
        itemConverter = new ResideMenuItem(this, R.drawable.converter, "Converter");

        itemDashboard.setOnClickListener(this);
        itemNews.setOnClickListener(this);
        itemTrending.setOnClickListener(this);
        itemConverter.setOnClickListener(this);

        resideMenu.addMenuItem(itemDashboard, ResideMenu.DIRECTION_LEFT);
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

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = BASE_URL + "/data/pricemultifull?fsyms=ETH,DASH,REP,BTC&tsyms=USD";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject js = response.getJSONObject("DISPLAY");
                    for (int i=0; i < js.names().length(); i++) {
                        names.add(js.names().getString(i));
                        JSONObject details = js.getJSONObject(js.names().getString(i)).getJSONObject("USD");
                        String image = IMAGE_URL + details.getString("IMAGEURL");

                        Currency c = new Currency(names.get(i), details.getString("PRICE"), details.getString("LOWDAY"), details.getString("HIGHDAY"), details.getString("OPENDAY"), image);
                        currencyList.add(c);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(jsObjRequest);

    }


    @Override
    public void onClick(View view) {
        if (view == itemDashboard){
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
