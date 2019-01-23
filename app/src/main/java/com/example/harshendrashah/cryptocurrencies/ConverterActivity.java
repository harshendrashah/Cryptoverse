package com.example.harshendrashah.cryptocurrencies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

import static java.security.AccessController.getContext;

public class ConverterActivity extends AppCompatActivity implements View.OnClickListener{

    String BASE_URL = "https://min-api.cryptocompare.com";
    String IMAGE_URL = "https://www.cryptocompare.com";

    String answer;
    String inp = "0";

    private SpinnerAdapter spinnerInputAdapter;
    private SpinnerAdapter spinnerOutputAdapter;

    private ResideMenu resideMenu;
    private ResideMenuItem itemTrending;
    private ResideMenuItem itemNews;
    private ResideMenuItem itemAll;
    private ResideMenuItem itemConverter;

    ArrayList<String> spinnerFullNames = new ArrayList<>();
    ArrayList<String> spinnerCodes = new ArrayList<>();
    ArrayList<String> spinnerImages = new ArrayList<>();

    EditText input;
    TextView output;

    String selectedInputFormat = "BTC";
    String selectedOutputFormat = "BTC";

    private boolean isUserInteracting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        TextView title = toolbar.findViewById(R.id.txt_title);

        title.setText("Converter");

        input = findViewById(R.id.input);
        output = findViewById(R.id.output);
        inp = input.getText().toString();
        output.setText(answer);

        conversion(inp, selectedInputFormat, selectedOutputFormat);

        setupMenu();

        spinnerData();

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_selected, data);
//        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        final CustomSpinner spinnerInput = findViewById(R.id.spinner_input);
        spinnerInputAdapter = new SpinnerAdapter(this, spinnerFullNames, spinnerImages, spinnerCodes);

        spinnerInput.setAdapter(spinnerInputAdapter);

        spinnerInput.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (isUserInteracting) {
                            selectedInputFormat = spinnerCodes.get(i);
                            inp = input.getText().toString();
                            conversion(inp, selectedInputFormat, selectedOutputFormat);
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
        spinnerOutputAdapter = new SpinnerAdapter(this, spinnerFullNames, spinnerImages, spinnerCodes);//        final CustomSpinner spinner =  findViewById(R.id.spinner);
        spinnerOutput.setAdapter(spinnerOutputAdapter);

        spinnerOutput.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (isUserInteracting) {
                            selectedOutputFormat = spinnerCodes.get(i);
                            inp = input.getText().toString();
                            conversion(inp, selectedInputFormat, selectedOutputFormat);
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

        conversion(inp, selectedInputFormat, selectedOutputFormat);

        output.setText(answer);
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        isUserInteracting = true;
    }

    private void conversion(final String inp, String inputFormat, final String outputFormat) {
        String url = BASE_URL + "/data/price?fsym=" + inputFormat + "&tsyms=" + outputFormat;

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    String js = response.getString(outputFormat);
                    if (!inp.equals("")) {
                        answer = String.valueOf(Double.parseDouble(js) * Double.parseDouble(inp));
                        output.setText(answer);
                    } else {
                        answer = "0";
                        output.setText(answer);
                    }
                    spinnerInputAdapter.notifyDataSetChanged();
                    spinnerOutputAdapter.notifyDataSetChanged();

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

    private void spinnerData() {
        String url = BASE_URL + "/data/top/mktcapfull?limit=10&tsym=USD";

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray js = response.getJSONArray("Data");
                    for (int i = 0; i < js.length(); i++) {
                        JSONObject display = js.getJSONObject(i).getJSONObject("DISPLAY").getJSONObject("USD");
                        String image = IMAGE_URL + display.getString("IMAGEURL");
                        JSONObject coinInfo = js.getJSONObject(i).getJSONObject("CoinInfo");

                        spinnerFullNames.add(coinInfo.getString("FullName"));
                        spinnerCodes.add(coinInfo.getString("Name"));
                        spinnerImages.add(image);

                    }
                    spinnerInputAdapter.notifyDataSetChanged();
                    spinnerOutputAdapter.notifyDataSetChanged();
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
