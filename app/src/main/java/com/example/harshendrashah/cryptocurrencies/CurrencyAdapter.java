package com.example.harshendrashah.cryptocurrencies;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.MyViewHolder> {

    private Context mContext;
    private List<Currency> currencyList;

    public CurrencyAdapter(Context mContext, List<Currency> currencyList) {
        this.mContext = mContext;
        this.currencyList = currencyList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.currency_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Currency currency = currencyList.get(position);
        String name = currency.getFullName() + " (" + currency.getCode() + ")";
        holder.name.setText(name);
        holder.currentRate.setText(currency.getCurrentRate());
        holder.openDay.setText(currency.getOpenDay());
        holder.lowDay.setText(currency.getLowDay());
        holder.highDay.setText(currency.getHighDay());
        Picasso.get().load(currency.getImageURL()).into(holder.currencyImage);
        holder.currency = currency;
    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, currentRate, lowDay, highDay, openDay;
        public de.hdodenhof.circleimageview.CircleImageView currencyImage;

        public Currency currency;

        public MyViewHolder(View view) {
            super(view);
            currencyImage = view.findViewById(R.id.currency_image);
            name = view.findViewById(R.id.currency_name);
            currentRate = view.findViewById(R.id.current_rate);
            lowDay = view.findViewById(R.id.low_day);
            highDay = view.findViewById(R.id.high_day);
            openDay = view.findViewById(R.id.open_day);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    ArrayList<String> data = new ArrayList<>();
                    data.add(currency.getCode());
                    data.add(currency.getFullName());
                    data.add(currency.getAlgorithm());
                    bundle.putStringArrayList("Data", data);
                    // set CurrencyDetailsFragment Arguments
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    String s = activity.getClass().getSimpleName();
                    Fragment fragment = new CurrencyDetailsFragment();
                    fragment.setArguments(bundle);
                    if(s.equals("MainActivity")) {
                        activity.getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.activity_main, fragment)
                                .addToBackStack(null)
                                .commit();
                    } else if (s.equals("AllCryptosActivity")) {
                        activity.getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.activity_all_cryptos, fragment)
                                .addToBackStack(null)
                                .commit();
                    }
                }
            });

        }
    }

}
