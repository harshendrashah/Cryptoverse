package com.example.harshendrashah.cryptocurrencies;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CurrrencyAdapter extends RecyclerView.Adapter<CurrrencyAdapter.MyViewHolder> {

    private Context mContext;
    private List<Currency> currencyList;

    public CurrrencyAdapter(Context mContext, List<Currency> currencyList) {
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
        holder.changeInHour.setText(currency.getChangeInHour());
        holder.changeInDay.setText(currency.getChangeInDay());
        holder.changeInWeek.setText(currency.getChangeInWeek());
        holder.currencyImage.setImageURI(Uri.parse(currency.getImageURL()));

    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, currentRate, changeInHour, changeInDay, changeInWeek;
        public ImageView currencyImage, imageHour, imageDay, imageWeek;

        public MyViewHolder(View view) {
            super(view);
            currencyImage = view.findViewById(R.id.currency_image);
            name = view.findViewById(R.id.currency_name);
            currentRate = view.findViewById(R.id.current_rate);
            changeInHour = view.findViewById(R.id.change_in_hour);
            changeInDay = view.findViewById(R.id.change_in_day);
            changeInWeek = view.findViewById(R.id.change_in_week);
            imageDay = view.findViewById(R.id.image_day);
            imageHour = view.findViewById(R.id.image_hour);
            imageWeek = view.findViewById(R.id.image_week);

        }
    }

}
