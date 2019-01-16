package com.example.harshendrashah.cryptocurrencies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
        holder.name.setText(currency.getName());
        holder.currentRate.setText(currency.getCurrentRate());
        holder.openDay.setText(currency.getOpenDay());
        holder.lowDay.setText(currency.getLowDay());
        holder.highDay.setText(currency.getHighDay());
        Picasso.get().load(currency.getImageURL()).into(holder.currencyImage);
    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, currentRate, lowDay, highDay, openDay;
        public de.hdodenhof.circleimageview.CircleImageView currencyImage;

        public MyViewHolder(View view) {
            super(view);
            currencyImage = view.findViewById(R.id.currency_image);
            name = view.findViewById(R.id.currency_name);
            currentRate = view.findViewById(R.id.current_rate);
            lowDay = view.findViewById(R.id.low_day);
            highDay = view.findViewById(R.id.high_day);
            openDay = view.findViewById(R.id.open_day);

        }
    }

}
