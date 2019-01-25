package com.example.harshendrashah.cryptocurrencies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
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


public class AllCryptosActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private AllCryptoAdapter adapter;
    private List<Currency> currencyList;

    private ResideMenu resideMenu;
    private ResideMenuItem itemTrending;
    private ResideMenuItem itemNews;
    private ResideMenuItem itemAll;
    private ResideMenuItem itemConverter;

    ArrayList<String> names = new ArrayList<>();
    String BASE_URL = "https://min-api.cryptocompare.com";
    String IMAGE_URL= "https://www.cryptocompare.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_crypto);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        TextView title = toolbar.findViewById(R.id.txt_title);

        title.setText("Major Cryptos");

        setupMenu();

        recyclerView = findViewById(R.id.recycler_view);

        currencyList = new ArrayList<>();
        adapter = new AllCryptoAdapter(this, currencyList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        String url = BASE_URL + "/data/top/mktcapfull?limit=50&tsym=USD";
        prepareCurrencies(url);

    }

    private void setupMenu() {

        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.gradient_background);
        resideMenu.attachToActivity(this);

        itemTrending = new ResideMenuItem(this, R.drawable.trending, "Trending");
        itemNews = new ResideMenuItem(this, R.drawable.news, "News");
        itemAll = new ResideMenuItem(this, R.drawable.dashboard, "Major Cryptos");
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

    private void prepareCurrencies(String url) {

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray js = response.getJSONArray("Data");
                    for (int i=0; i < js.length(); i++) {
                        JSONObject display = js.getJSONObject(i).getJSONObject("DISPLAY").getJSONObject("USD");
                        String image = IMAGE_URL + display.getString("IMAGEURL");
                        JSONObject coinInfo = js.getJSONObject(i).getJSONObject("CoinInfo");
                        names.add(coinInfo.getString("FullName"));

                        Currency c = new Currency(coinInfo.getString("FullName"), coinInfo.getString("Name"), display.getString("PRICE"),
                                display.getString("LOWDAY"), display.getString("HIGHDAY"),
                                display.getString("OPENDAY"), image, coinInfo.getString("Algorithm"));
                        currencyList.add(c);

                        Log.i("nnnn: ", ""+i );
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


    @Override
    public void onClick(View view) {
        if (view == itemAll){
            Toast.makeText(this, "Major Crptos", Toast.LENGTH_SHORT).show();
        }else if (view == itemTrending){
            Intent i = new Intent(AllCryptosActivity.this, MainActivity.class);
            startActivity(i);
            Toast.makeText(this, "Trending", Toast.LENGTH_SHORT).show();
        }else if (view == itemNews){
            Intent i = new Intent(AllCryptosActivity.this, NewsActivity.class);
            startActivity(i);
            Toast.makeText(this, "News", Toast.LENGTH_SHORT).show();
        }else if (view == itemConverter){
            Intent i = new Intent(AllCryptosActivity.this, ConverterActivity.class);
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
