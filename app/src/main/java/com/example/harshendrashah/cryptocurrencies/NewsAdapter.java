package com.example.harshendrashah.cryptocurrencies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context mContext;
    private List<News> newsList;

    public NewsAdapter(Context mContext, List<News> newsList) {
        this.mContext = mContext;
        this.newsList = newsList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_card, parent, false);

        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final NewsAdapter.NewsViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.newsTitle.setText(news.getNewsTitle());
        holder.newsSource.setText(news.getNewsSource());
        Picasso.get().load(news.getNewsImageURL()).into(holder.newsImage);
        holder.news = news;
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        public TextView newsTitle, newsSource;
        public ImageView newsImage;

        public News news;

        public NewsViewHolder(View view) {
            super(view);
            newsTitle = view.findViewById(R.id.news_title);
            newsSource = view.findViewById(R.id.news_source);
            newsImage = view.findViewById(R.id.news_image);

//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Bundle bundle = new Bundle();
//                    ArrayList<String> data = new ArrayList<>();
//                    data.add(currency.getCode());
//                    data.add(currency.getFullName());
//                    data.add(currency.getAlgorithm());
//                    bundle.putStringArrayList("Data", data);
//                    // set CurrencyDetailsFragment Arguments
//                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                    String s = activity.getClass().getSimpleName();
//                    Fragment fragment = new CurrencyDetailsFragment();
//                    fragment.setArguments(bundle);
//                    if(s.equals("MainActivity")) {
//                        activity.getSupportFragmentManager()
//                                .beginTransaction()
//                                .replace(R.id.activity_main, fragment)
//                                .addToBackStack(null)
//                                .commit();
//                    } else if (s.equals("AllCryptosActivity")) {
//                        activity.getSupportFragmentManager()
//                                .beginTransaction()
//                                .replace(R.id.activity_all_cryptos, fragment)
//                                .addToBackStack(null)
//                                .commit();
//                    }
//                }
//            });

        }
    }

}
