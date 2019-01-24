package com.example.harshendrashah.cryptocurrencies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class CurrencyDetailsFragment extends Fragment {

    String BASE_URL = "https://min-api.cryptocompare.com";
    String IMAGE_URL = "https://www.cryptocompare.com";

    ArrayList<String> data = new ArrayList<>();

    TextView price, changePCT, marketCap, algorithm, market, title;
    ImageView image, backArrow;

    TextView lowPrice, highPrice, openPrice, volume1h, volume24h;

    GraphView graph;

    public CurrencyDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = getArguments().getStringArrayList("Data");
            Toast.makeText(getContext(), data.get(1), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_currency_details, container, false);

        String appbarTitle = data.get(1) + " (" + data.get(0) + ")";

        title = rootView.findViewById(R.id.currency_details_title);
        backArrow = rootView.findViewById(R.id.back_arrow);
        price = rootView.findViewById(R.id.currency_details_price);
        changePCT = rootView.findViewById(R.id.currency_details_CNG_PTC);
        marketCap = rootView.findViewById(R.id.market_cap);
        algorithm = rootView.findViewById(R.id.currency_details_algo);
        market = rootView.findViewById(R.id.currency_details_market);
        image = rootView.findViewById(R.id.currency_details_image);

        lowPrice = rootView.findViewById(R.id.currency_details_low);
        openPrice = rootView.findViewById(R.id.currency_details_open);
        highPrice = rootView.findViewById(R.id.currency_details_high);
        volume1h = rootView.findViewById(R.id.currency_details_volume_hour);
        volume24h = rootView.findViewById(R.id.currency_details_volume_24hour);

        title.setText(appbarTitle);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        String url = BASE_URL + "/data/pricemultifull?fsyms=" + data.get(0) + "&tsyms=USD";
        //Log.i("*****", url);
        prepareCurrencies(url);

        graph = rootView.findViewById(R.id.graph);

        plotGraph();


        return rootView;
    }

    private void prepareCurrencies(String url) {

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject display = response.getJSONObject("DISPLAY").getJSONObject(data.get(0)).getJSONObject("USD");
                    String img = IMAGE_URL + display.getString("IMAGEURL");

                    price.setText(display.getString("PRICE"));
                    market.setText(display.getString("LASTMARKET"));
                    String change_pct = display.getString("CHANGEPCT24HOUR")+"%";
                    changePCT.setText(change_pct);
                    marketCap.setText(display.getString("MKTCAP"));

                    algorithm.setText(data.get(2));

                    lowPrice.setText(display.getString("LOWDAY"));
                    openPrice.setText(display.getString("OPENDAY"));
                    highPrice.setText(display.getString("HIGHDAY"));
                    volume1h.setText(display.getString("VOLUMEHOUR"));
                    volume24h.setText(display.getString("VOLUME24HOUR"));

                    Picasso.get().load(img).into(image);

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
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                getActivity().onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void plotGraph() {

        String graphURL = BASE_URL + "/data/histoday?fsym="+ data.get(0) + "&tsym=USD&limit=7";

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, graphURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray js = response.getJSONArray("Data");
                    ArrayList<String> date = new ArrayList<>();
                    ArrayList<Double> price = new ArrayList<>();
                    DataPoint[] values = new DataPoint[js.length()];
                    for (int i = 0; i < js.length(); i++) {
                        JSONObject data = js.getJSONObject(i);
                        date.add(getDate(data.getLong("time")));
                        price.add(data.getDouble("close"));
                        DataPoint v = new DataPoint(new Date(data.getLong("time")).getTime(), price.get(i));
                        values[i] = v;
                    }

                    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(values);

                    graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                        @Override
                        public String formatLabel(double value, boolean isValueX) {
                            if (isValueX) {
                                // show normal x values
                                return getDate(new Double(value).longValue());
                            } else {

                                // show currency for y values
                                return super.formatLabel(value, isValueX);
                            }
                        }
                    });

                    graph.addSeries(series);

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


    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("dd-MM", cal).toString();
        return date;
    }

}
