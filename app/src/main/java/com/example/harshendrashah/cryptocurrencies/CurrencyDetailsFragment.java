package com.example.harshendrashah.cryptocurrencies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class CurrencyDetailsFragment extends Fragment {

    String BASE_URL = "https://min-api.cryptocompare.com";
    String IMAGE_URL = "https://www.cryptocompare.com";

    ArrayList<String> data = new ArrayList<>();

    TextView price, changePTC, marketCap, algorithm, market, title;
    ImageView image;

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
        price = rootView.findViewById(R.id.currency_details_price);
        changePTC = rootView.findViewById(R.id.currency_details_CNG_PTC);
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
        String url = BASE_URL + "/data/pricemultifull?fsyms=" + data.get(0) + "&tsyms=USD";
        //Log.i("*****", url);
        prepareCurrencies(url);

        graph = rootView.findViewById(R.id.graph);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);
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
                    changePTC.setText(display.getString("CHANGEPCT24HOUR"));
                    Log.i("*****", response.toString());
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
}
