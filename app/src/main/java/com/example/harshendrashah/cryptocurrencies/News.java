package com.example.harshendrashah.cryptocurrencies;

public class News {

    String newsTitle;
    String newsSource;
    String newsImageURL;

    public News() {

    }

    public News(String newsTitle, String newsSource, String newsImageURL) {
        this.newsTitle = newsTitle;
        this.newsSource = newsSource;
        this.newsImageURL = newsImageURL;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }

    public String getNewsImageURL() {
        return newsImageURL;
    }

    public void setNewsImageURL(String newsImageURL) {
        this.newsImageURL = newsImageURL;
    }
}
