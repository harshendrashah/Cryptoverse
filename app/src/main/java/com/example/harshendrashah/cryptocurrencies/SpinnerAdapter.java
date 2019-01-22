package com.example.harshendrashah.cryptocurrencies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SpinnerAdapter extends ArrayAdapter<String> {


    private static class ViewHolder {
        ImageView spinnerImage;
        TextView spinnerFullName;
        TextView spinnerCode;
    }

    ArrayList<String> spinnerFullNames;
    ArrayList<Integer> spinnerImages;
    ArrayList<String> spinnerCodes;
    Context mContext;

    public SpinnerAdapter(@NonNull Context context, ArrayList<String> spinnerFullNames,
                          ArrayList<Integer> spinnerImages, ArrayList<String> spinnerCodes) {
        super(context, R.layout.spinner_item_row);
        this.spinnerFullNames = spinnerFullNames;
        this.spinnerImages = spinnerImages;
        this.spinnerCodes = spinnerCodes;
        this.mContext = context;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return spinnerFullNames.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.spinner_item_row, parent, false);
            mViewHolder.spinnerImage = (ImageView) convertView.findViewById(R.id.spinner_image);
            mViewHolder.spinnerFullName = (TextView) convertView.findViewById(R.id.spinner_full_name);
            mViewHolder.spinnerCode = (TextView) convertView.findViewById(R.id.spinner_code);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.spinnerImage.setImageResource(spinnerImages.get(position));
        mViewHolder.spinnerFullName.setText(spinnerFullNames.get(position));
        mViewHolder.spinnerCode.setText(spinnerCodes.get(position));

        return convertView;
    }

}
